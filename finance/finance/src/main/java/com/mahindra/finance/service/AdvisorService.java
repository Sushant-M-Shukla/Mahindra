package com.mahindra.finance.service;

import com.mahindra.finance.dto.AdvisorBookingResponse;
import com.mahindra.finance.entity.Advisor;
import com.mahindra.finance.entity.AdvisorBooking;
import com.mahindra.finance.repository.AdvisorRepository;
import com.mahindra.finance.repository.AdvisoryBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvisorService {

    @Autowired
    private AdvisorRepository advisorRepository;

    @Autowired
    private AdvisoryBookingRepository advisoryBookingRepository;

    public void saveAdvisor(Advisor advisor) {
        advisorRepository.save(advisor);
    }

    public List<Advisor> getAdvisors() {
        return advisorRepository.findAll();
    }

    public void bookAdvisor(Long userId, Long advisorId, String bookingTime) {
        AdvisorBooking booking = new AdvisorBooking();
        booking.setBookingTime(bookingTime);
        Advisor advisor = advisorRepository.findById(advisorId).orElse(null);
        booking.setAdvisorId(advisor.getAdvisorId());
        advisoryBookingRepository.save(booking);
    }

    public AdvisorBookingResponse getBookings(Long advisorId) {
        AdvisorBookingResponse response =new AdvisorBookingResponse();
        Optional<Advisor> advisor = advisorRepository.findById(advisorId);
        if (!advisor.isPresent()) {
            return response;
        }else {
            AdvisorBooking booking = advisoryBookingRepository.findByAdvisorId(advisor.get().getAdvisorId());
            if (booking!=null) {
                response.setAdvisorId(booking.getAdvisorId());
                response.setBookingTime(booking.getBookingTime());
                response.setAdvisorBookingId(Long.valueOf(booking.getId()));
                response.setAdvisorName(advisor.get().getName());
                response.setAdvisorPhotoUrl(advisor.get().getPhotoUrl());
            }
        }
        return response;
    }
}
