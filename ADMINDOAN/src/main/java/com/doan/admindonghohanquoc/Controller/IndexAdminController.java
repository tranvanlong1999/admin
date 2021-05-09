package com.doan.admindonghohanquoc.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexAdminController {
    @GetMapping("/indexAdmin")
    private String indexAdmin(){
        return "indexAdmin";
    }
}
