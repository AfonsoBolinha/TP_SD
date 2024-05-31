package com.example.tp_sd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;

@SpringBootApplication
public class TpSdApplication {


    private final AdminService adminService;

    public TpSdApplication(AdminService adminService) {
        this.adminService = adminService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TpSdApplication.class, args);
    }

    @PostConstruct
    public void init() {
        // To create an admin when the application starts running
        adminService.createAdmin();
    }

}
