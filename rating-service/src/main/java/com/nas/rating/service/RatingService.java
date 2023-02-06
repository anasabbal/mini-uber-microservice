package com.nas.rating.service;

import com.nas.rating.command.RatingCommand;
import com.nas.rating.models.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RatingService {
    Rating createRating(final RatingCommand ratingCommand);
    Page<Rating> getRatings(Pageable pageable);
}
