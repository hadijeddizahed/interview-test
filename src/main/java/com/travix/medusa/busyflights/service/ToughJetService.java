package com.travix.medusa.busyflights.service;


import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.utils.BusyFlightUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToughJetService implements BusyFlightSupplier<BusyFlightsRequest, BusyFlightsResponse>  {

    public ToughJetService() {
    }

    @Override
    public List<BusyFlightsResponse> search(BusyFlightsRequest busyFlightRequest) {

        //first convert busyFlightRequest to toughJetRequest
        ToughJetRequest toughJetRequest = BusyFlightUtil.mapToToughJet(busyFlightRequest);

        // now call ToughJet provider API to find flights base on request parameters.
        return Arrays.asList(ToughJetResponse.builder()
                        .carrier("ToughJet")
                        .basePrice(890d)
                        .tax(7)
                        .discount(5d)
                        .departureAirportName("LHR")
                        .arrivalAirportName("LHR")
                        .outboundDateTime("2019-16-5T09:20:27.14Z")
                        .inboundDateTime("2019-16-9T10:00:00.142Z")
                        .build(),
                ToughJetResponse.builder()
                        .carrier("ToughJet")
                        .basePrice(1580d)
                        .tax(8)
                        .discount(10d)
                        .departureAirportName("LHR")
                        .arrivalAirportName("LHR")
                        .outboundDateTime("2019-16-5T09:20:27.14Z")
                        .inboundDateTime("2019-16-9T10:00:00.142Z")
                        .build()
        ).stream().map(BusyFlightUtil::map).collect(Collectors.toList());
    }
}
