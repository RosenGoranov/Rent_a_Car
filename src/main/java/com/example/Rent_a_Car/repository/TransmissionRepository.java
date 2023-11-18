package com.example.Rent_a_Car.repository;

import com.example.Rent_a_Car.model.entity.TransmissionEntity;
import com.example.Rent_a_Car.model.enums.TransmissionsEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransmissionRepository extends JpaRepository<TransmissionEntity, Long> {
    TransmissionEntity findByName(TransmissionsEnum transmission);
}
