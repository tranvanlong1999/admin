package com.doan.admindonghohanquoc.Controller;

import com.doan.admindonghohanquoc.Model.OutPut.ProductOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hoadon")
public class QuanLyHoaDonController {
    @GetMapping
    public String hoadon(Model model) {
       // List<ProductOutput> productOutputList = productService.getListProduct();
       // model.addAttribute("productInfo", );
        return "Quanlyhoadon";
    }
}
