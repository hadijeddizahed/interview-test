package com.travix.medusa.busyflights.domain.toughjet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ToughJetRequest {
    private String from;
    private String to;
    private String outboundDate;
    private String inboundDate;
    private int numberOfAdults;
}
