package com.lmq.study.thread.ch2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.lmq.study.thread.annotation.ThreadSafe;

public @ThreadSafe class DelegatingVehicleTracker {
    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        this.locations = new ConcurrentHashMap<String, Point>(points);
        this.unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        return this.unmodifiableMap;
    }

    public Point getLocation(String id) {
        return this.locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (this.locations.replace(id, new Point(x, y)) == null) {
        	throw new IllegalArgumentException("invalid vehicle name: " + id);
        }
    }

    public Map<String, Point> getLocationsAsStatic() {
        return Collections.unmodifiableMap(new HashMap<String, Point>(this.locations));
    }
}