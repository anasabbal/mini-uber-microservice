package com.nas.carrier.repository;

import com.nas.carrier.model.ApplicationSubmit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApplicationSubmitRepository extends JpaRepository<ApplicationSubmit, String> {
}
