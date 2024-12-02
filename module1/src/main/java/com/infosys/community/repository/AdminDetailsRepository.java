package com.infosys.community.repository;

import com.infosys.community.model.AdminDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDetailsRepository extends JpaRepository<AdminDetails,Long> {

}