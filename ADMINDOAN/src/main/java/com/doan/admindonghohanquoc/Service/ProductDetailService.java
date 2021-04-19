package com.doan.admindonghohanquoc.Service;

import com.doan.admindonghohanquoc.Converter.ProductConverter;
import com.doan.admindonghohanquoc.Model.Entity.ProductEntity;
import com.doan.admindonghohanquoc.Model.OutPut.ProductOutput;
import com.doan.admindonghohanquoc.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class ProductDetailService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductConverter productConverter;

    public ProductOutput getDetailProduct(Integer productId) throws Exception{
        ProductOutput productOutput;
        // san pham
        ProductEntity productEntity = productRepository.findById(productId).get();
        if(ObjectUtils.isEmpty(productEntity))
            throw new Exception("san pham không có trong cơ sở dữ liệu ");
        productOutput= productConverter.toProductEntity(productEntity);
        return productOutput;
    }
}
