#pragma once

#include <string>
#include <vector>

#include "boost/asio/io_service.hpp"
#include "boost/asio/strand.hpp"

#include "conf/configuration.h"

#include "motis/core/schedule/synced_schedule.h"

#include "motis/module/message.h"
#include "motis/module/registry.h"

namespace motis::module {

struct module : public conf::configuration {
  explicit module(std::string name = "", std::string prefix = "")
      : configuration(std::move(name), std::move(prefix)) {}

  module(module const&) = delete;
  module& operator=(module const&) = delete;

  module(module&&) = delete;
  module& operator=(module&&) = delete;

  ~module() override = default;

  virtual std::string name() const { return prefix(); }

  void set_context(motis::schedule& schedule) { schedule_ = &schedule; }

  virtual void init(registry&) {}

protected:
  template <schedule_access A>
  synced_schedule<A> synced_sched() {
    return synced_schedule<A>(*schedule_);
  }

private:
  motis::schedule* schedule_ = nullptr;
};

}  // namespace motis::module