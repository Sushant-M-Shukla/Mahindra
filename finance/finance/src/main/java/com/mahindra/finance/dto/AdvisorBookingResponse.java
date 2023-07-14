package com.mahindra.finance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class AdvisorBookingResponse {
    private Long advisorBookingId;
    private Long advisorId;
    private String bookingTime;
    private String advisorName;
    private String advisorPhotoUrl;

}
