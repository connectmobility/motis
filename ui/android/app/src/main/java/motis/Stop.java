// automatically generated by the FlatBuffers compiler, do not modify

package motis;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class Stop extends Table {
  public static Stop getRootAsStop(ByteBuffer _bb) { return getRootAsStop(_bb, new Stop()); }
  public static Stop getRootAsStop(ByteBuffer _bb, Stop obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Stop __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public motis.Station station() { return station(new motis.Station()); }
  public motis.Station station(motis.Station obj) { int o = __offset(4); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  public EventInfo arrival() { return arrival(new EventInfo()); }
  public EventInfo arrival(EventInfo obj) { int o = __offset(6); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  public EventInfo departure() { return departure(new EventInfo()); }
  public EventInfo departure(EventInfo obj) { int o = __offset(8); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  public boolean exit() { int o = __offset(10); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  public boolean enter() { int o = __offset(12); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }

  public static int createStop(FlatBufferBuilder builder,
      int stationOffset,
      int arrivalOffset,
      int departureOffset,
      boolean exit,
      boolean enter) {
    builder.startObject(5);
    Stop.addDeparture(builder, departureOffset);
    Stop.addArrival(builder, arrivalOffset);
    Stop.addStation(builder, stationOffset);
    Stop.addEnter(builder, enter);
    Stop.addExit(builder, exit);
    return Stop.endStop(builder);
  }

  public static void startStop(FlatBufferBuilder builder) { builder.startObject(5); }
  public static void addStation(FlatBufferBuilder builder, int stationOffset) { builder.addOffset(0, stationOffset, 0); }
  public static void addArrival(FlatBufferBuilder builder, int arrivalOffset) { builder.addOffset(1, arrivalOffset, 0); }
  public static void addDeparture(FlatBufferBuilder builder, int departureOffset) { builder.addOffset(2, departureOffset, 0); }
  public static void addExit(FlatBufferBuilder builder, boolean exit) { builder.addBoolean(3, exit, false); }
  public static void addEnter(FlatBufferBuilder builder, boolean enter) { builder.addBoolean(4, enter, false); }
  public static int endStop(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};
