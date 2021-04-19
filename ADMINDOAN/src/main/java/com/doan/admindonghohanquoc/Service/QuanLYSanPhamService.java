package com.doan.admindonghohanquoc.Service;

import com.doan.admindonghohanquoc.Converter.ProductConverter;
import com.doan.admindonghohanquoc.Model.Entity.ProductEntity;
import com.doan.admindonghohanquoc.Model.OutPut.ProductOutput;
import com.doan.admindonghohanquoc.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class QuanLYSanPhamService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductConverter productConverter;
    public List<ProductOutput> getListProduct() {
        List<ProductOutput> productOutputList = new LinkedList<>();

        Page<ProductEntity> page = productRepository.findAll(PageRequest.of(0,5));

        for (ProductEntity productEntity : page) {
            productOutputList.add(productConverter.toProductEntity(productEntity));
        }
        return productOutputList;
    }
}
