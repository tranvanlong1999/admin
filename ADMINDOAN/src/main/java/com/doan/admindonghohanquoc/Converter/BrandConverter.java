package com.doan.admindonghohanquoc.Converter;


import com.doan.admindonghohanquoc.Model.Entity.BrandEntity;
import com.doan.admindonghohanquoc.Model.OutPut.BrandOutput;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class BrandConverter {
    public BrandOutput toBrandEntity(BrandEntity brandEntity)
    {
        BrandOutput brandOutput = new BrandOutput();
        if(!ObjectUtils.isEmpty(brandEntity))
        {
            brandOutput.setId(brandEntity.getId());
            brandOutput.setName(brandEntity.getName());
            brandOutput.setDescription(brandEntity.getDescription());
        }
        return brandOutput;
    }
}
