package com.nas.rating.repository;

import com.nas.rating.models.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, String> {
}
