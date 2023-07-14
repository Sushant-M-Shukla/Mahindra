package com.mahindra.finance.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Table(name = "advisor")
public class Advisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long advisorId;

    @NonNull
    private String name;
    private String photoUrl;

}