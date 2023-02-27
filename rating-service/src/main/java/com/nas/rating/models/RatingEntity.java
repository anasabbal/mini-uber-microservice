package com.nas.rating.models;


import com.nas.rating.command.RatingCommand;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RatingEntity extends BaseEntity{

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_ID_FEEDBACK")
    private String userIdFeedback = "";
    @Column(name = "SCORE")
    private Integer ratingScore = null;

    public static RatingEntity create(final RatingCommand ratingCommand){
        final RatingEntity rating = new RatingEntity();

        return rating;
    }
    public static RatingEntity getOneAndCreate(final String userId){
        final RatingEntity rating = new RatingEntity();

        rating.userId = userId;
        return rating;
    }
}
