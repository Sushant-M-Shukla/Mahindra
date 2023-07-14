package com.mahindra.finance.repository;

import com.mahindra.finance.entity.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, Long> {
}

