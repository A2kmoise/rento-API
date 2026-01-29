package org.rent.rentify.controller;

import org.rent.rentify.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    private AdminController(AdminService adminService) {this.adminService = adminService;}

    /*
    ADMIN LOGIN
     */

}
