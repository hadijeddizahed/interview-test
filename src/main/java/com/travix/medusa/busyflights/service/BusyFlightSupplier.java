package com.travix.medusa.busyflights.service;

import java.util.List;

public interface BusyFlightSupplier<T,R> {

    List<R> search(T t);
}
