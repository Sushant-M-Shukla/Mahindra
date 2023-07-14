package com.mahindra.finance.repository;

import com.mahindra.finance.entity.Advisor;
import com.mahindra.finance.entity.AdvisorBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvisoryBookingRepository extends JpaRepository<AdvisorBooking, Long> {
    AdvisorBooking findByAdvisorId(Long advisor);
}
