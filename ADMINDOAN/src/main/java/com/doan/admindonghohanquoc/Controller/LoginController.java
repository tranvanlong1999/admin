package com.doan.admindonghohanquoc.Controller;

import com.doan.admindonghohanquoc.Common.PageConstant;
import com.doan.admindonghohanquoc.Model.Input.LoginInput;
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
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/dang-nhap")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoriesService categoriesService;
    @GetMapping
    public String pageLogin(Model model, HttpSession session, HttpServletRequest request) {
        List<CategoriesOutput> categoriesOutputList = categoriesService.getListCategories();
        List<BrandOutput> brandOutputs = brandService.getlistbrand();
        model.addAttribute("brandlist", brandOutputs);
        List<CategoriesOutput> listnam = new LinkedList<>();
        List<CategoriesOutput> listnu = new LinkedList<>();
        model.addAttribute("user",new LoginInput());
        //
        session.removeAttribute("user");
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
        //
        return PageConstant.PAGE_LOGIN;
       /* return */
    }
    @PostMapping
    public String login(Model model, HttpSession session, HttpServletResponse response,
                        @ModelAttribute("user") LoginInput user) {
        return userService.login(model, session, response, user);

    }

}
