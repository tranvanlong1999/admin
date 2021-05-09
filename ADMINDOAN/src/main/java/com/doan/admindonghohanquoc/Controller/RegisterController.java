package com.doan.admindonghohanquoc.Controller;


import com.doan.admindonghohanquoc.Common.PageConstant;
import com.doan.admindonghohanquoc.Model.Input.UserInput;
import com.doan.admindonghohanquoc.Model.OutPut.BrandOutput;
import com.doan.admindonghohanquoc.Model.OutPut.CategoriesOutput;
import com.doan.admindonghohanquoc.Service.BrandService;
import com.doan.admindonghohanquoc.Service.CategoriesService;
import com.doan.admindonghohanquoc.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoriesService categoriesService;
    @GetMapping("/pagedangky")
    public String pageRegister(Model model, HttpSession session, HttpServletRequest request) {
        //
        List<CategoriesOutput> categoriesOutputList = categoriesService.getListCategories();
        List<BrandOutput> brandOutputs = brandService.getlistbrand();
        model.addAttribute("brandlist", brandOutputs);
        model.addAttribute("userInput",new UserInput());
        session.removeAttribute("userInput");
        model.addAttribute("error", null);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("email")) {
                    model.addAttribute("email", cookie.getValue());
                }
                if (cookie.getName().equals("password")) {
                    model.addAttribute("password", cookie.getValue());
                }
                if (cookie.getName().equals("remember")) {
                    model.addAttribute("remember", cookie.getValue());
                }
            }
        }
        return PageConstant.PAGE_REGISTER;
    }

    @PostMapping("/dangky")
    public String register(Model model, @ModelAttribute("userInput") UserInput user) {
        return userService.register(model, user);
    }
}
