package com.travix.medusa.busyflights.controller;



import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightFactory;
import com.travix.medusa.busyflights.service.BusyFlightService;
import com.travix.medusa.busyflights.service.IranAirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("busyFlights")
public class BusyFlightsController {

    private final BusyFlightService busyFlightService;

    @Autowired
    public BusyFlightsController(BusyFlightService busyFlightService) {
        this.busyFlightService = busyFlightService;
    }

    @PostConstruct
    public void init() {
        BusyFlightFactory.addSupplier("iranAir", IranAirService::new);
    }

    @PostMapping(value = "/search")
    public List<BusyFlightsResponse> search(@RequestBody BusyFlightsRequest request) {
        return busyFlightService.search(request);
    }
}
