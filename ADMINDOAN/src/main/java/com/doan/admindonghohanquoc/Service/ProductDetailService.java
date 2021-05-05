package com.doan.admindonghohanquoc.Service;

import com.doan.admindonghohanquoc.Common.MessageConstant;
import com.doan.admindonghohanquoc.Converter.ProductConverter;
import com.doan.admindonghohanquoc.Model.Entity.ProductAtributeEntity;
import com.doan.admindonghohanquoc.Model.Entity.ProductEntity;
import com.doan.admindonghohanquoc.Model.OutPut.ProductOutput;
import com.doan.admindonghohanquoc.Repository.ProductAtributeRepository;
import com.doan.admindonghohanquoc.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProductDetailService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private ProductAtributeRepository productAtributeRepository;

    public ProductOutput getDetailProduct(Integer productId) throws Exception {
        ProductOutput productOutput;
        // san pham
        ProductEntity productEntity = productRepository.findById(productId).get();
        productOutput = productConverter.toProductEntity(productEntity);
        return productOutput;
    }

    public List<ProductAtributeEntity> getinforProductdetail(Integer productId) throws Exception {
        List<ProductAtributeEntity> listinfo= new LinkedList<>();
        if(productId==null)
        {
            listinfo= productAtributeRepository.findAll();
        }
        else
        {
            listinfo= productAtributeRepository.findByProductId(productId);
        }
        return listinfo;
    }
    public String deleteproductatribute(Integer id, Model model) {
        String error = "alo";
        try {
            ProductAtributeEntity productAtributeEntity= productAtributeRepository.findById(id).get();
            productAtributeRepository.delete(productAtributeEntity);
        } catch (Exception e) {
            error = MessageConstant.DELETE_ERROR;
        }
        model.addAttribute("error", error);
        return "redirect:/chitiet";
    }
}
