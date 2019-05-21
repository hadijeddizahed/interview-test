package com.travix.medusa.busyflights.utils;


import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

import java.util.function.BiFunction;

public class BusyFlightUtil {


    private static BiFunction<Double, Double, Double> COMPUTE_DISCOUNT = (price, discount) -> price - (price * discount/100);
    private static BiFunction<Double, Double, Double> COMPUTE_TAX = (price, tax) -> price - (price * tax/100);

    public static BusyFlightsResponse map(CrazyAirResponse crazyAirResponse) {
        return BusyFlightsResponse.builder()
                .fare(crazyAirResponse.getPrice())
                .airline(crazyAirResponse.getAirline())
                .supplier("CrazyAir")
                .arrivalDate(crazyAirResponse.getArrivalDate())
                .departureAirportCode(crazyAirResponse.getDepartureAirportCode())
                .departureDate(crazyAirResponse.getDepartureDate())
                .build();
    }

    public static BusyFlightsResponse map(ToughJetResponse toughJetResponse) {
        return BusyFlightsResponse.builder()
                .fare((COMPUTE_DISCOUNT.apply(toughJetResponse.getBasePrice(), toughJetResponse.getDiscount())
                        + COMPUTE_TAX.apply(toughJetResponse.getBasePrice(), toughJetResponse.getTax())) / 2)
                .airline(toughJetResponse.getCarrier())
                .supplier("ToughJet")
                .arrivalDate(toughJetResponse.getInboundDateTime())
                .departureDate(toughJetResponse.getOutboundDateTime())
                .departureAirportCode(toughJetResponse.getDepartureAirportName())
                .destinationAirportCode(toughJetResponse.getArrivalAirportName())
                .build();
    }

    public static CrazyAirRequest mapToCrazyAir(BusyFlightsRequest busyFlightRequest) {
        return CrazyAirRequest.builder()
                .origin(busyFlightRequest.getOrigin())
                .passengerCount(busyFlightRequest.getNumberOfPassengers())
                .departureDate(busyFlightRequest.getDepartureDate())
                .destination(busyFlightRequest.getDestination())
                .returnDate(busyFlightRequest.getReturnDate())
                .build();
    }

    public static ToughJetRequest mapToToughJet(BusyFlightsRequest busyFlightRequest) {
        return ToughJetRequest.builder()
                .from(busyFlightRequest.getOrigin())
                .to(busyFlightRequest.getDestination())
                .inboundDate(busyFlightRequest.getReturnDate())
                .outboundDate(busyFlightRequest.getDepartureDate())
                .numberOfAdults(busyFlightRequest.getNumberOfPassengers())
                .build();
    }

}
