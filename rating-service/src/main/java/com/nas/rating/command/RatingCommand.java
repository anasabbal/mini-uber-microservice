package com.nas.rating.command;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingCommand {
    private Integer ratingScore;
    private String customerId;
    private String driverId;
}
