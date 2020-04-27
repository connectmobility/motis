#pragma once

#include <fstream>
#include <sstream>
#include <string>
#include <vector>

#include "boost/filesystem.hpp"

#include "ppr/profiles/parse_search_profile.h"

#include "motis/core/common/logging.h"

namespace motis::ppr {

inline std::string read_file(std::string const& filename) {
  std::ifstream f(filename);
  std::stringstream ss;
  f.exceptions(std::ifstream::failbit | std::ifstream::badbit);
  ss.exceptions(std::ifstream::failbit | std::ifstream::badbit);
  ss << f.rdbuf();
  return ss.str();
}

template <typename Map>
inline void read_profile_file(boost::filesystem::path const& profile_file,
                              Map& profiles) {
  namespace fs = boost::filesystem;
  if (!fs::exists(profile_file)) {
    LOG(motis::logging::error)
        << "ppr profile file not found: " << profile_file;
    return;
  }
  auto const name = profile_file.stem().string();
  auto const content = read_file(profile_file.string());
  profiles[name] = ::ppr::profiles::parse_search_profile(content);
  LOG(motis::logging::info) << "ppr profile loaded: " << name;
}

template <typename Map>
inline void read_profile_files(std::vector<std::string> const& profile_files,
                               Map& profiles) {
  namespace fs = boost::filesystem;
  for (auto const& pf : profile_files) {
    fs::path path{pf};
    if (!fs::exists(path)) {
      LOG(motis::logging::error) << "ppr profile file not found: " << pf;
      continue;
    }
    if (fs::is_directory(path)) {
      for (auto const& f : fs::directory_iterator{path}) {
        if (!fs::is_directory(f)) {
          read_profile_file(f, profiles);
        }
      }
    } else {
      read_profile_file(path, profiles);
    }
  }
}

}  // namespace motis::ppr