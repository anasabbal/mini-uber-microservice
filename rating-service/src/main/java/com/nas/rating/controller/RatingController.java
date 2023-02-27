package com.nas.rating.controller;


import com.nas.rating.command.RatingCommand;
import com.nas.rating.models.RatingEntity;
import com.nas.rating.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.nas.core.constants.ResourcePath.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(V1 + RATINGS)
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<RatingEntity> create(@RequestBody final RatingCommand ratingCommand){
        final RatingEntity rating = ratingService.createRating(ratingCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(rating.getId()).toUri();
        return ResponseEntity.created(uri).body(rating);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RatingEntity> getOneAndCreate(@PathVariable("id") final String id){
        final RatingEntity rating = ratingService.getOneAndCreate(id);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(rating.getId()).toUri();
        return ResponseEntity.created(uri).body(rating);
    }
    /*
    @GetMapping
    public ResponseEntity<Page<Rating>> getRatings(Pageable pageable){
        return ResponseEntity.ok(ratingService.getRatings(pageable));
    }
    @GetMapping(DRIVERS + "/{driverId}")
    public ResponseEntity<Rating> getByDriverId(@PathVariable("driverId") final String driverId){
        return ResponseEntity.ok(ratingService.findRatingByDriverId(driverId));
    }*/
}