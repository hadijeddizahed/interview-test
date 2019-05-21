package com.travix.medusa.busyflights.domain.crazyair;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Builder
public class CrazyAirResponse {

    private String airline;
    private double price;
    private String cabinClass;
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;
    private String arrivalDate;
}
