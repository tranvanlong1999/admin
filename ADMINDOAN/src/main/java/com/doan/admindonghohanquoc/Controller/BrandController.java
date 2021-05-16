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

import java.util.HashMap;
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
    HashMap<Integer,String> PriceList = new HashMap<Integer, String>();

    List<ColorEntity>  colorEntities;
    Integer  brandidInfo;
    Integer categoryidInfo;
    Integer priceValueInfo;
    @GetMapping("/brand")
    public String getformbrand(Model model, Pageable pageable,
                               @RequestParam(value = "size", defaultValue = "5") Integer size,
                               @RequestParam(value = "page",defaultValue = "0") Integer page,
                               @RequestParam(value = "sortby", required = false) Integer sortby) {
        brandidInfo =null;
        categoryidInfo =null;
        priceValueInfo =null;
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
        ArrayPrice();
        List<BrandOutput> brandOutputs = brandService.getlistbrand();
        List<CategoriesOutput> categoriesOutputList = categoriesService.getListCategories();
        colorEntities= colorRepository.findAll();
        model.addAttribute("brandlist", brandOutputs);
        model.addAttribute("productInfo", list);
        model.addAttribute("categorylist",categoriesOutputList);
        model.addAttribute("colorEntities",colorEntities);
        model.addAttribute("PriceList",PriceList);

        return "shop";
    }

    @GetMapping("/getproductbybrand/{brandid}/detail/{categoryid}/{priceValue}")
    public String getProductbybrand(@PathVariable(value = "brandid" ,required = false) Integer brandid,
                                    @PathVariable(value = "categoryid" , required = false) Integer categoryid ,
                                    Model model,
                                    Pageable pageable,
                                    @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
                                    @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                    @PathVariable(value = "priceValue", required = false) Integer priceValue
                                    ) {
        productOutputList.clear();
        System.out.println(categoryid);
        List<ProductEntity>list;
        //
        int priceLeft=0, priceRight=1000000000;
        if(priceValue == 1)
        {
            priceLeft =100000;
            priceRight=500000;
        }else if(priceValue ==2)
        {
            priceLeft=500000;
            priceRight=1000000;
        }
        else if(priceValue ==3)
        {
            priceLeft=1500000;
            priceRight=2000000;
        }
        else if(priceValue==4)
        {
            priceLeft=2000000;
        }
        else
        {
            priceRight=1000000000;
        }

        //
        if(categoryid ==-1 && brandid   !=-1){
            list= productRepository.findBybrandid(brandid,priceLeft,priceRight);
        }
        else if(brandid ==-1 && categoryid!=-1 && priceValue==-1)
        {
            list =productRepository.findByandcategoryid(categoryid);
        }
        else if(brandid!= -1 && categoryid!=-1){
            list =productRepository.findBybrandidandcategoryid(brandid,categoryid);
        }
        else if(brandid==-1 && categoryid==-1)
        {
            list =productRepository.findByPrice(priceLeft,priceRight);
        }
        else
        {
            list =productRepository.findByPriceandAndCategory(priceLeft,priceRight,categoryid);
        }
        for (ProductEntity info : list) {
            productOutputList.add(productConverter.toProductEntity(info));
        }
        ArrayPrice();
        brandidInfo =brandid;
        categoryidInfo=categoryid;
        priceValueInfo =priceValue;
        List<CategoriesOutput> categoriesOutputList = categoriesService.getListCategories();
        List<BrandOutput> brandOutputs = brandService.getlistbrand();
        model.addAttribute("brandlist", brandOutputs);
        model.addAttribute("categorylist",categoriesOutputList);
        model.addAttribute("productInfo", productOutputList);
        model.addAttribute("brandidInfo", brandidInfo);
        model.addAttribute("colorEntities",colorEntities);
        model.addAttribute("PriceList",PriceList);
        model.addAttribute("price_value",priceValueInfo);
        model.addAttribute("categoryidInfo",categoryidInfo);
        return "shop";
    }

    public void ArrayPrice ()
    {
        PriceList.put(1,"100.000 đ ->500.000 đ");
        PriceList.put(2,"500.000 đ ->1000.000 đ");
        PriceList.put(3,"1500.000 đ ->2000.000 đ");
        PriceList.put(4,"2000.000 đ trở lên");
    }
}
