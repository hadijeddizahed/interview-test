package com.travix.medusa.busyflights.service;



import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class BusyFlightService implements BusyFlightSupplier<BusyFlightsRequest, BusyFlightsResponse> {


    @Override
    public List<BusyFlightsResponse> search(BusyFlightsRequest request) {
        List<CompletableFuture<List<BusyFlightsResponse>>> completableFutures;
        List<BusyFlightsResponse> result = new ArrayList<>();

        completableFutures = BusyFlightFactory.allSupplier().values()
                .parallelStream()
                .map(supplier -> CompletableFuture.supplyAsync(() -> (List<BusyFlightsResponse>) supplier.get().search(request)))
                .collect(toList());


        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture<?>[0])).thenAccept(nothing ->
        {
            final List<BusyFlightsResponse> busyFlightsResponseList = completableFutures.stream()
                    .flatMap(completableFuture -> completableFuture.join().stream())
                    .collect(toList());
            result.addAll(busyFlightsResponseList);
        });

        System.out.println(result.size());
        return result
                .stream()
                .sorted(Comparator.comparing(BusyFlightsResponse::getFare))
                .collect(Collectors.toList());
    }
}
