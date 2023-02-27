package com.nas.rating.service;

import com.nas.rating.command.RatingCommand;
import com.nas.rating.models.RatingEntity;

public interface RatingService {
    RatingEntity createRating(final RatingCommand ratingCommand);
    RatingEntity getOneAndCreate(final String accountId);
}
