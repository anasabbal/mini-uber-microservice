package com.nas.rating.controller;


import com.nas.rating.command.RatingCommand;
import com.nas.rating.dto.RatingDto;
import com.nas.rating.models.Rating;
import com.nas.rating.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.nas.core.constants.ResourcePath.RATINGS;
import static com.nas.core.constants.ResourcePath.V1;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController(V1 + RATINGS)
@RequestMapping
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody final RatingCommand ratingCommand){
        final Rating rating = ratingService.createRating(ratingCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(rating.getId()).toUri();
        return ResponseEntity.created(uri).body(rating);
    }
}