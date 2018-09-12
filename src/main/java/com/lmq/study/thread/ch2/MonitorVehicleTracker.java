package com.lmq.study.thread.ch2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.lmq.study.thread.annotation.GuardedBy;
import com.lmq.study.thread.annotation.ThreadSafe;

@ThreadSafe
public class MonitorVehicleTracker {
	private @GuardedBy("this") final Map<String, MutablePoint> locations;

	public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
		this.locations = deepCopy(locations);
	}

	public synchronized Map<String, MutablePoint> getLocations() {
		return deepCopy(this.locations);
	}

	public synchronized MutablePoint getLocation(String id) {
		MutablePoint loc = this.locations.get(id);
		return loc == null ? null : new MutablePoint(loc);
	}

	public synchronized void setLocation(String id, int x, int y) {
		MutablePoint loc = this.locations.get(id);
		if (Objects.isNull(loc)) {
			throw new IllegalArgumentException("No such ID: " + id);
		}
		loc.x = x;
		loc.y = y;
	}

	private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
		Map<String, MutablePoint> result = new HashMap<String, MutablePoint>();
		for (String id : m.keySet()) {
			result.put(id, new MutablePoint(m.get(id)));
		}
		return Collections.unmodifiableMap(result);
	}
}
