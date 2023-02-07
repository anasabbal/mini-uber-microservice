package com.nas.rating.models;


import com.nas.rating.command.RatingCommand;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Rating extends BaseEntity{

    @Column(name = "RATING_SCORE")
    private Integer ratingScore = 0;

    @Column(name = "USER_ID")
    private String userId;


    public static Rating create(final RatingCommand ratingCommand){
        final Rating rating = new Rating();

        rating.ratingScore = ratingCommand.getRatingScore();
        rating.userId = ratingCommand.getUserId();

        return rating;
    }
}
