package com.doan.admindonghohanquoc.Controller;

import com.doan.admindonghohanquoc.Model.Entity.ColorEntity;
import com.doan.admindonghohanquoc.Model.Entity.ProductAtributeEntity;
import com.doan.admindonghohanquoc.Model.Entity.SizeEntity;
import com.doan.admindonghohanquoc.Model.Input.ProductAtributeInput;
import com.doan.admindonghohanquoc.Model.Input.ProductInput;
import com.doan.admindonghohanquoc.Model.OutPut.BrandOutput;
import com.doan.admindonghohanquoc.Model.OutPut.CategoriesOutput;
import com.doan.admindonghohanquoc.Model.OutPut.ProductOutput;
import com.doan.admindonghohanquoc.Repository.ProductAtributeRepository;
import com.doan.admindonghohanquoc.Repository.ProductRepository;
import com.doan.admindonghohanquoc.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductAtributeRepository productAtributeRepository;

    ProductOutput productOutput;
    @GetMapping("/sanpham")
    public String sanpham(Model model) {
        List<ProductOutput> productOutputList = productService.getListProduct();
        model.addAttribute("productInfo", productOutputList);
        return "Quanlysanpham";
    }

    @GetMapping("/chitiet")
    public String productdetail(@RequestParam(value = "id", required = false) Integer id, Model model) throws Exception {
        List<ProductAtributeEntity> listproductdetail = productDetailService.getinforProductdetail(id);
        model.addAttribute("listproductdetail", listproductdetail);
        return "Quanlychitietsanpham";
    }

    @GetMapping("/xoachitiet")
    public String deleteproductatribute(@RequestParam("productdetailid") Integer productdetailid) {
        productAtributeRepository.deleteById(productdetailid);
        return "redirect:/chitiet";
    }

    @GetMapping("/pagesuasanpham/{productid}")
    public String getDetailProduct(@PathVariable(value = "productid", required = false) Integer productid, Model model) throws Exception {
        List<BrandOutput> brandOutputList = brandService.getlistbrand();
        List<CategoriesOutput> categoriesOutputs = categoriesService.getListCategories();
        model.addAttribute("brandlist", brandOutputList);
        model.addAttribute("categorylist", categoriesOutputs);
        productOutput = productDetailService.getDetailProduct(productid);
        model.addAttribute("productdetai", productOutput);
        return "Chinhsuaproduct";

    }

    @PostMapping("/chinhsuaproduct")
    public String updateUserByAdmin(@ModelAttribute("productdetai") ProductOutput productInfo, @RequestParam("files") MultipartFile[] files) {
        productInfo.setId(productOutput.getId());
        return productService.updateproduct(productInfo, files);
    }

    @GetMapping("/pagethemsanpham")
    public String pageThemsanpham(Model model) {
        List<BrandOutput> brandOutputList = brandService.getlistbrand();
        List<CategoriesOutput> categoriesOutputs = categoriesService.getListCategories();
        List<SizeEntity> sizeEntityList = sizeService.getListSize();
        List<ColorEntity> colorEntityList = colorService.getListColor();
        ProductInput productInput = new ProductInput();
        model.addAttribute("brandlist", brandOutputList);
        model.addAttribute("categorylist", categoriesOutputs);
        model.addAttribute("sizeEntityList", sizeEntityList);
        model.addAttribute("colorEntityList", colorEntityList);
        model.addAttribute("error", null);
        List<ProductAtributeInput> productAtributeInputs = new ArrayList<>(10);
        ProductAtributeInput productAtributeInput = new ProductAtributeInput();
        productAtributeInput.setSizeid(1);
        productAtributeInput.setColorid(1);
        productAtributeInput.setQuantity(1);
        productAtributeInputs.add(productAtributeInput);
        productInput.setProductAtributeInputs(productAtributeInputs);
        model.addAttribute("product", productInput);
        return "Themsanpham";
    }

    @PostMapping("/themsanpham")
    public String createProductByAdmin(Model model, @ModelAttribute("productInput") ProductInput productInput, @RequestParam("files") MultipartFile[] files) {
        System.out.println(productInput.getProductAtributeInputs());
        return productService.createProductByAdmin(model, productInput, files);
    }

    @GetMapping("/xoasanpham")
    public String deleteProductByAdmin(@RequestParam("productid") Integer id, Model model) {
        return productService.deleteProductByAdmin(id, model);
    }


}
