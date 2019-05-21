package com.travix.medusa.busyflights.service;


import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.enums.CabinClassType;
import com.travix.medusa.busyflights.utils.BusyFlightUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IranAirService implements BusyFlightSupplier<BusyFlightsRequest, BusyFlightsResponse> {

    public IranAirService() {
    }


    /**
     * This method will provide CrazyAir supplier results. first we should
     * convert busyFlightRequest object to CrazyAirRequest object to call
     *
     * @param busyFlightRequest
     * @return
     */
    @Override
    public List<BusyFlightsResponse> search(BusyFlightsRequest busyFlightRequest) {
        CrazyAirRequest crazyAirRequest = BusyFlightUtil.mapToCrazyAir(busyFlightRequest);
        List<BusyFlightsResponse> result = Arrays.asList(CrazyAirResponse.builder()
                        .airline("IranAir")
                        .price(390d)
                        .cabinClass(CabinClassType.BUSINESS.getValue())
                        .departureAirportCode("LHR")
                        .departureDate("2019-16-5")
                        .arrivalDate("2019-16-9")
                        .destinationAirportCode("LHR")
                        .destinationAirportCode("LHR")
                        .build(),
                CrazyAirResponse.builder()
                        .airline("IranAir")
                        .price(1470d)
                        .cabinClass(CabinClassType.ECONOMY.getValue())
                        .departureAirportCode("LHR")
                        .destinationAirportCode("LHR")
                        .departureDate("2019-16-5")
                        .arrivalDate("2019-16-9")
                        .destinationAirportCode("LHR")
                        .build()
        ).stream().map(BusyFlightUtil::map).collect(Collectors.toList());
        return result;
    }


}
