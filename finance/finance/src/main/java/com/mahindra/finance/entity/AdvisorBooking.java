package com.mahindra.finance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Table(name = "advisor_booking")
public class AdvisorBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advisor_booking_id")
    private String id;

    private Long advisorId;

    private String bookingTime;

}
