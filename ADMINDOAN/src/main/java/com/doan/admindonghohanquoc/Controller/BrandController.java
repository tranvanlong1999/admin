package com.doan.admindonghohanquoc.Controller;


import com.doan.admindonghohanquoc.Converter.ProductConverter;
import com.doan.admindonghohanquoc.Model.Entity.ColorEntity;
import com.doan.admindonghohanquoc.Model.Entity.ProductEntity;
import com.doan.admindonghohanquoc.Model.OutPut.BrandOutput;
import com.doan.admindonghohanquoc.Model.OutPut.CategoriesOutput;
import com.doan.admindonghohanquoc.Model.OutPut.ProductOutput;
import com.doan.admindonghohanquoc.Repository.ColorRepository;
import com.doan.admindonghohanquoc.Repository.ProductRepository;
import com.doan.admindonghohanquoc.Service.BrandService;
import com.doan.admindonghohanquoc.Service.CategoriesService;
import com.doan.admindonghohanquoc.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
public class BrandController {
    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @Autowired
    CategoriesService categoriesService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ColorRepository colorRepository;
    @Autowired
    ProductConverter productConverter;

    List<ProductOutput> productOutputList = new LinkedList<>();

    @GetMapping("/brand")
    public String getformbrand(Model model, Pageable pageable,
                               @RequestParam(value = "size", defaultValue = "5") Integer size,
                               @RequestParam(value = "page",defaultValue = "0") Integer page,
                               @RequestParam(value = "sortby", required = false) Integer sortby) {
        /*if(sortby==1)
        {
            pageable= PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"price"));
        }else if (sortby==2)
        {
            pageable= PageRequest.of(page,size, Sort.by(Sort.Direction.ASC,"price"));
        }
        else if(sortby==3)
        {
            pageable= PageRequest.of(page,size, Sort.by(Sort.Direction.ASC,"createdat"));
        }
        else {
            pageable= PageRequest.of(page,size);
        }*/
        pageable= PageRequest.of(page,size);
        Page<ProductEntity> list= productService.getListProduct(1,pageable,model);

        List<BrandOutput> brandOutputs = brandService.getlistbrand();
        List<CategoriesOutput> categoriesOutputList = categoriesService.getListCategories();
        List<ColorEntity>  colorEntities= colorRepository.findAll();
        model.addAttribute("brandlist", brandOutputs);
        model.addAttribute("productInfo", list);
        model.addAttribute("categorylist",categoriesOutputList);
        model.addAttribute("colorEntities",colorEntities);
        return "shop";
    }

    @GetMapping("/getproductbybrand/{brandid}")
    public String getProductbybrand(@PathVariable("brandid") Integer brandid, Model model, Pageable pageable,
                                    @RequestParam(value = "size", required = false) Integer size,
                                    @RequestParam(value = "page", required = false) Integer page) {

        productOutputList.clear();
        List<ProductEntity>list = productRepository.findBybrandid(brandid);
        for (ProductEntity info : list) {
            productOutputList.add(productConverter.toProductEntity(info));
        }
        List<CategoriesOutput> categoriesOutputList = categoriesService.getListCategories();
        List<BrandOutput> brandOutputs = brandService.getlistbrand();
        model.addAttribute("brandlist", brandOutputs);
        model.addAttribute("categorylist",categoriesOutputList);
        model.addAttribute("productInfo", productOutputList);
        model.addAttribute("brandid", brandid);
        System.out.println(productOutputList);
        return "shop";
    }


}
