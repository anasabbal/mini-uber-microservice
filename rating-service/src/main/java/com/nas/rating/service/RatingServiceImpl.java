package com.nas.rating.service;


import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.core.util.JSONUtil;
import com.nas.rating.command.RatingCommand;
import com.nas.rating.models.Rating;
import com.nas.rating.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService{

    private final RatingRepository ratingRepository;


    @Override
    public Rating createRating(RatingCommand ratingCommand) {
        log.info("[+] Begin creating Rating with payload {}", JSONUtil.toJSON(ratingCommand));
        final Rating rating = Rating.create(ratingCommand);
        log.info("[+] Rating with id {} created successfully", rating.getId());
        return ratingRepository.save(rating);
    }

    @Override
    public Page<Rating> getRatings(Pageable pageable) {
        return ratingRepository.findAll(pageable);
    }
    @Override
    public Rating findById(String ratingId){
        log.info("[+] Begin fetching rating with driver id {}", ratingId);
        final Rating rating = ratingRepository.findById(ratingId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.RATING_NOT_FOUND.get())
        );
        log.info("[+] Rating with id {} fetched successfully", rating.getId());
        return rating;
    }

    @Override
    public Rating findRatingByDriverId(String driverId) {
        log.info("[+] Begin fetching rating with driver id {}", driverId);
        final Rating rating = ratingRepository.findRatingByUserId(driverId);
        log.info("[+] Rating with id {} fetched successfully", rating.getId());
        return rating;
    }
}
