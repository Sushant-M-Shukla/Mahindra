package com.mahindra.finance.controller;

import com.mahindra.finance.entity.Advisor;
import com.mahindra.finance.service.AdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminAdvisorController {

    @Autowired
    private AdvisorService advisorService;

    @GetMapping("/get")
   // @PreAuthorize("hasRole('admin')")
    public String getWorld() {
        return "hi";
    }

    @PostMapping("/advisor")
    public ResponseEntity<Void> addAdvisor(@RequestBody Advisor advisor, @RequestHeader String roleName) {
        if (advisor.getPhotoUrl() == null || roleName == null || !roleName.equalsIgnoreCase("admin")) {
            return ResponseEntity.badRequest().build();
        }

        advisorService.saveAdvisor(advisor);
        return ResponseEntity.ok().build();
    }


}
