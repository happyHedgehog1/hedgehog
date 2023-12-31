package com.hedgehog.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDeniedController {
    @GetMapping("access-denied")
    public String showAccessDeniedPage() {
        return "/errors/403";
    }
}
