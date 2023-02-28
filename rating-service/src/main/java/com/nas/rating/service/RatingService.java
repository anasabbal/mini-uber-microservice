package com.nas.rating.service;

import com.nas.rating.command.RatingCommand;
import com.nas.rating.models.RatingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RatingService {
    RatingEntity createRating(final RatingCommand ratingCommand);
    RatingEntity getOneAndCreate(final String accountId);
    Page<RatingEntity> getRatings(Pageable pageable);
}
