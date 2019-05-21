package com.travix.medusa.busyflights.service;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class BusyFlightFactory {

    final static Map<String, Supplier<? extends BusyFlightSupplier>> SUPPLIERS = new HashMap<>();

    static {
        SUPPLIERS.put("CRAZY_AIR", CrazyAirService::new);
        SUPPLIERS.put("TOUGH_JET", ToughJetService::new);
    }


    public BusyFlightSupplier get(String type) {
        Optional<Supplier< ? extends BusyFlightSupplier>> busyFlightSupplier = Optional.ofNullable(SUPPLIERS.get(type));
        return busyFlightSupplier
                .map(Supplier::get)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Service!"));
    }

    public static void addSupplier(String type, Supplier<? extends BusyFlightSupplier> supplier){
        SUPPLIERS.put(type,supplier);
    }

    public static Map<String, Supplier<? extends BusyFlightSupplier>> allSupplier() {
        return SUPPLIERS;
    }
}
