package com.hedgehog.admin.adminMember.controller;

import com.hedgehog.admin.adminMember.model.service.adminUnregisterServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
@Slf4j
public class adminUnregisterController {

    private final adminUnregisterServiceImpl adminUnregisterServiceimpl;

    public adminUnregisterController(adminUnregisterServiceImpl adminUnregisterServiceimpl) {
        this.adminUnregisterServiceimpl = adminUnregisterServiceimpl;
    }
}
