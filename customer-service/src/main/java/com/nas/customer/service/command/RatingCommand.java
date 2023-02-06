package com.nas.customer.service.command;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingCommand {
    private Integer ratingScore;
    private String driverId;
    private String customerId;
}
