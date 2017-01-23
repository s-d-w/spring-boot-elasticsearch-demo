package com.esdemo.repository;

import com.esdemo.domain.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {

}
