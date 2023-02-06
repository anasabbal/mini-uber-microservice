package com.nas.driver.command;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingCommand {
    private Integer ratingScore;
    private String driverId;
    private String customerId;
}
