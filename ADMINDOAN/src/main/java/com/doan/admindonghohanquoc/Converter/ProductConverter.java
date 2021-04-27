package com.doan.admindonghohanquoc.Converter;

import com.doan.admindonghohanquoc.Constants.Constants;
import com.doan.admindonghohanquoc.Model.Entity.ProductEntity;
import com.doan.admindonghohanquoc.Model.Input.ProductInput;
import com.doan.admindonghohanquoc.Model.OutPut.ProductOutput;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;


@Component
public class ProductConverter {
    public ProductOutput toProductEntity(ProductEntity productEntity)
    {
        ProductOutput productOutput = new ProductOutput();
        if(!ObjectUtils.isEmpty(productEntity))
        {
            productOutput.setId(productEntity.getId());
            productOutput.setProductname(productEntity.getProductname());
            productOutput.setImage(productEntity.getImage());
            productOutput.setPrice(productEntity.getPrice());
            productOutput.setDescription(productEntity.getDescription());
            if(productEntity.getStatus()==1)
            productOutput.setStatus("Hoạt Động");
            productOutput.setBrandentity(productEntity.getBrandentity());
            productOutput.setQuantitysold(productEntity.getQuantitysold());
            productOutput.setQuantityremaining(productEntity.getQuantityremaining());
            System.out.println(productOutput.getQuantityremaining());
            productOutput.setPath(productEntity.getPath());
            productOutput.setPriceStr(productEntity.getPriceStr());
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
    /*public ProductEntity toProductUpdateInput(ProductUpdateInput productUpdateInput)
    {
        ProductEntity productEntity = new ProductEntity();
        if(!ObjectUtils.isEmpty(productUpdateInput))
        {
            productEntity.setId(productUpdateInput.getId());
            productEntity.setProductname(productUpdateInput.getProductname());
            productEntity.setImage(productUpdateInput.getImage());
            productEntity.setPrice(productUpdateInput.getPrice());
            productEntity.setDescription(productUpdateInput.getDescription());
            productEntity.setStatus(productUpdateInput.getStatus());
            productEntity.setBrandentity(productUpdateInput.getBrandEntity());
            productEntity.setQuantitysold(productUpdateInput.getQuantitysold());
            productEntity.setQuantityremaining(productUpdateInput.getQuantityremaining());
            productEntity.setUpdatedat(new Date());
            productEntity.setUpdatedby(productUpdateInput.getUpdatedby());
        }
        return productEntity;
    }*/
}
