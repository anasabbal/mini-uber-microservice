package com.nas.rating.service;

import com.nas.rating.command.RatingCommand;
import com.nas.rating.models.Rating;

public interface RatingService {
    Rating createRating(final RatingCommand ratingCommand);
}
