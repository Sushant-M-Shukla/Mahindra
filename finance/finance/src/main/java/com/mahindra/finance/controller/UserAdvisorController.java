package com.mahindra.finance.controller;

import com.mahindra.finance.dto.AdvisorBookingResponse;
import com.mahindra.finance.dto.LoginRequest;
import com.mahindra.finance.dto.UserResponse;
import com.mahindra.finance.entity.Advisor;
import com.mahindra.finance.entity.AdvisorBooking;
import com.mahindra.finance.entity.User;
import com.mahindra.finance.service.AdvisorService;
import com.mahindra.finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserAdvisorController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdvisorService advisorService;


    //@PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/register" , method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public UserResponse registerUser(@RequestBody User userRequest, @RequestHeader String roleName) {
        if(roleName == null || !roleName.equalsIgnoreCase("user"))
            return null;
        User user = userService.registerUser(userRequest);
        UserResponse response = new UserResponse();
        String token = userService.generateAuthToken(user.getId());
        response.setToken(token);
        response.setUserId(user.getId());
        return response;
    }

    //@PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/login" , method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public UserResponse loginUser(@RequestBody LoginRequest userRequest, @RequestHeader String roleName) {
        if(roleName == null || !roleName.equalsIgnoreCase("user"))
            return null;
        User user = userService.loginUser(userRequest);
        UserResponse response = new UserResponse();
        String token = userService.generateAuthToken(user.getId());
        response.setToken(token);
        response.setUserId(user.getId());
        return response;
    }

    //@PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/{userId}/advisor" , method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public List<Advisor> getAdvisors(@PathVariable Long userId, @RequestHeader String roleName) {
        if(roleName == null || !roleName.equalsIgnoreCase("user"))
            return null;
        Boolean checkUserExists=false;
        if(userId !=null){
            checkUserExists = userService.checkUserExists(userId);
        }
        List<Advisor> advisors = null;
        if(checkUserExists){
            return advisors = advisorService.getAdvisors();
        }
        return null;
    }

    //@PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/{userId}/advisor/{advisorId}" , method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<Void> bookAdvisor(
            @PathVariable Long userId,
            @PathVariable Long advisorId,
            @RequestHeader String bookingTime,
            @RequestHeader String roleName) {
        if(roleName == null || !roleName.equalsIgnoreCase("user"))
            return null;
        advisorService.bookAdvisor(userId, advisorId, bookingTime);
        return ResponseEntity.ok().build();
    }

    //@PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/{userId}/advisor/booking" , method = RequestMethod.GET, produces = {"application/json"}, consumes = {"application/json"})
    public AdvisorBookingResponse getBookings(@PathVariable Long userId, @RequestHeader String roleName) {
        if (roleName == null || !roleName.equalsIgnoreCase("user"))
            return null;
        return advisorService.getBookings(userId);
    }

}
