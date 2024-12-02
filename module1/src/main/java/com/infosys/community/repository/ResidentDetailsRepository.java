package com.infosys.community.repository;

import com.infosys.community.model.ResidentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentDetailsRepository extends JpaRepository<ResidentDetails,Long> {
}