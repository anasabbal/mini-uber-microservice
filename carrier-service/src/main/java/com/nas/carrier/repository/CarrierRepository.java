package com.nas.carrier.repository;

import com.nas.carrier.model.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarrierRepository extends JpaRepository<Carrier, String> {
}
