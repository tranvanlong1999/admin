package com.doan.admindonghohanquoc.Controller;

import com.doan.admindonghohanquoc.Model.Entity.ColorEntity;
import com.doan.admindonghohanquoc.Model.Entity.SizeEntity;
import com.doan.admindonghohanquoc.Model.Input.ProductInput;
import com.doan.admindonghohanquoc.Model.OutPut.BrandOutput;
import com.doan.admindonghohanquoc.Model.OutPut.CategoriesOutput;
import com.doan.admindonghohanquoc.Model.OutPut.ProductOutput;
import com.doan.admindonghohanquoc.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class QuanLySanPhamController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductDetailService productDetailService;
    @Autowired
    BrandService brandService;
    @Autowired
    CategoriesService categoriesService;
    @Autowired
    SizeService sizeService;
    @Autowired
    ColorService colorService;
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
    @GetMapping("/pagethemsanpham")
    public String pageThemsanpham(Model model) {
        List<BrandOutput> brandOutputList= brandService.getlistbrand();
        List<CategoriesOutput>categoriesOutputs= categoriesService.getListCategories();
        /*List<SizeEntity> sizeEntityList= sizeService.getListSize();
        List<ColorEntity> colorEntityList= colorService.getListColor();*/
        model.addAttribute("product",new ProductInput());
        model.addAttribute("brandlist",brandOutputList);
        model.addAttribute("categorylist",categoriesOutputs);
        /*model.addAttribute("sizeEntityList",sizeEntityList);
        model.addAttribute("colorEntityList",colorEntityList);*/
        model.addAttribute("error", null);
        return "Themsanpham";
    }
    @PostMapping("/themsanpham")
    public String createProductByAdmin(Model model,@ModelAttribute("productInput") ProductInput productInput,@RequestParam("files") MultipartFile[] files) {
         return productService.createProductByAdmin(model,productInput,files);
    }
}
