package com.doan.admindonghohanquoc.Controller;


import com.doan.admindonghohanquoc.Model.Entity.ImageEntity;
import com.doan.admindonghohanquoc.Model.Entity.ProductAtributeEntity;
import com.doan.admindonghohanquoc.Model.Entity.ProductEntity;
import com.doan.admindonghohanquoc.Model.Input.ProductAtributeInput;
import com.doan.admindonghohanquoc.Model.OutPut.BrandOutput;
import com.doan.admindonghohanquoc.Model.OutPut.CategoriesOutput;
import com.doan.admindonghohanquoc.Model.OutPut.ProductOutput;
import com.doan.admindonghohanquoc.Repository.*;
import com.doan.admindonghohanquoc.Service.BrandService;
import com.doan.admindonghohanquoc.Service.CategoriesService;
import com.doan.admindonghohanquoc.Service.ProductDetailService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    CategoriesService categoriesService;
    @Autowired
    BrandService brandService;
    @Autowired
    SizeRepository sizeRepository;
    @Autowired
    ColorRepository colorRepository;
    @Autowired
    ProductAtributeRepository productAtributeRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ImageRepository imageRepository;
    @SneakyThrows
    @GetMapping("product-details/{productid}")
    public String getDetailProduct(@PathVariable("productid") Integer productid, Model model)
    {
        ProductAtributeInput productAtributeInput= new ProductAtributeInput();
        ProductEntity productEntity= productRepository.findById(productid).get();
        model.addAttribute("productAtributeInput",productAtributeInput);
        List<CategoriesOutput> categoriesOutputList = categoriesService.getListCategories();
        ProductOutput productOutput= productDetailService.getDetailProduct(productid);
        HashMap<Integer, String> listcolorsize= new HashMap<Integer,String>();
        List<ProductAtributeEntity> listproductdetail= productAtributeRepository.findByProductId(productid);
        for (ProductAtributeEntity info: listproductdetail)  {
            if(info.getProductentity().getId() == productid)
            {
                Integer idDetail=info.getId();
                String colorSizeName="Màu: "+info.getColorentity().getName()+"/cỡ : "+info.getSizeentity().getName();
                listcolorsize.put(idDetail,colorSizeName);
            }
        }
        model.addAttribute("listcolorsize",listcolorsize);
        model.addAttribute("productdetai",productOutput);
        List<BrandOutput> brandOutputs = brandService.getlistbrand();
        model.addAttribute("brandlist", brandOutputs);
        List<ProductEntity> listProductOfBrand= new LinkedList<>();
        List<ProductEntity> listEntity= productRepository.findAll();
        for (ProductEntity infor : listEntity) {
            if(infor.getBrandentity().getId()==productOutput.getBrandid()
                    && infor.getId()!=productOutput.getId()
                    && infor.getStatus()==1)
                listProductOfBrand.add(infor);
        }
        List<ImageEntity> listImage = imageRepository.findByProduct(productEntity);
        model.addAttribute("listImage",listImage);
        model.addAttribute("listProductOfBrand",listProductOfBrand);
        return "product-details";
    }
}
