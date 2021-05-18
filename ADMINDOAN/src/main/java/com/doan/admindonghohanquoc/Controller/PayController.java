package com.doan.admindonghohanquoc.Controller;



import com.doan.admindonghohanquoc.Model.Input.ReceiverInFor;
import com.doan.admindonghohanquoc.Model.OutPut.BrandOutput;
import com.doan.admindonghohanquoc.Model.OutPut.CategoriesOutput;
import com.doan.admindonghohanquoc.Service.BrandService;
import com.doan.admindonghohanquoc.Service.CategoriesService;
import com.doan.admindonghohanquoc.Service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("pay")
public class PayController {
    @Autowired
    private PayService payService;
    @Autowired
    private BrandService brandService;
    @Autowired
    CategoriesService categoriesService;
    @GetMapping
    public String pagePay(HttpSession session, Model model) {
        List<CategoriesOutput> categoriesOutputList = categoriesService.getListCategories();
        List<BrandOutput> brandOutputs = brandService.getlistbrand();
        model.addAttribute("brandlist", brandOutputs);
        return payService.pagePay(session, model);
    }
    @PostMapping
    public String pay(HttpSession session, @ModelAttribute("thongtinnguoinhan") ReceiverInFor input) {
        return payService.pay(session, input);
    }
}
