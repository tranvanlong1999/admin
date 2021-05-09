package com.doan.admindonghohanquoc.Converter;

import com.doan.admindonghohanquoc.Constants.Constants;
import com.doan.admindonghohanquoc.Model.Entity.ProductEntity;
import com.doan.admindonghohanquoc.Model.Input.ProductInput;
import com.doan.admindonghohanquoc.Model.OutPut.ProductOutput;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class ProductConverter {
    public ProductOutput toProductEntity(ProductEntity productEntity)
    {
        ProductOutput productOutput = new ProductOutput();
        DateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if(!ObjectUtils.isEmpty(productEntity))
        {
            productOutput.setId(productEntity.getId());
            productOutput.setProductname(productEntity.getProductname());
            productOutput.setDescription(productEntity.getDescription());
            productOutput.setBrandid(productEntity.getBrandentity().getId());
            productOutput.setCategoryid(productEntity.getCategory().getId());
            productOutput.setPriceStr(productEntity.getPriceStr());
            productOutput.setCreateAt(dateFormat.format(productEntity.getCreatedat()));
            productOutput.setStatus(productEntity.getStatus());
            productOutput.setImage(productEntity.getImage());
            productOutput.setBrandname(productEntity.getBrandentity().getName());
            productOutput.setCategoryname(productEntity.getCategory().getName());
            productOutput.setPrice(productEntity.getPrice());
        }
        return productOutput;

    }
    public ProductEntity toProductInput(ProductInput productInput)
    {
        ProductEntity productEntity = new ProductEntity();
        if(!ObjectUtils.isEmpty(productInput))
        {
            productEntity.setProductname(productInput.getProductname());
            productEntity.setImage(productInput.getImage());
            productEntity.setPrice(productInput.getPrice());
            productEntity.setDescription(productInput.getDescription());
            productEntity.setCreatedby(productInput.getCreatedby());
            productEntity.setStatus(Constants.STATUS_ACTIVE);
            productEntity.setCreatedat(new Date());
        }
        return productEntity;
    }

}
