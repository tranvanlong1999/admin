package com.doan.admindonghohanquoc.Controller;

import com.doan.admindonghohanquoc.Model.OutPut.BrandOutput;
import com.doan.admindonghohanquoc.Model.OutPut.ProductOutput;
import com.doan.admindonghohanquoc.Service.BrandService;
import com.doan.admindonghohanquoc.Service.ProductDetailService;
import com.doan.admindonghohanquoc.Service.QuanLYSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;


@Controller
public class QuanLySanPhamController {
    @Autowired
    QuanLYSanPhamService productService;
    @Autowired
    ProductDetailService productDetailService;
    @Autowired
    BrandService brandService;
    @GetMapping("/sanpham")
    public String sanpham(Model model) {
        List<ProductOutput> productOutputList = productService.getListProduct();
        model.addAttribute("productInfo", productOutputList);
        return "Quanlysanpham";
    }
    @GetMapping("chinhsuasanpham/{productid}")
    public String getDetailProduct(@PathVariable("productid") Integer productid, Model model)
    {
        ProductOutput productOutput= null;
        try {
            productOutput = productDetailService.getDetailProduct(productid);
            List<BrandOutput> brandOutputList= brandService.getlistbrand();
            model.addAttribute("productdetai",productOutput);
            model.addAttribute("brandlist",brandOutputList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Chinhsuasanpham";
    }
}
