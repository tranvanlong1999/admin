package com.doan.admindonghohanquoc.Converter;


import com.doan.admindonghohanquoc.Model.Entity.CategoriesEntity;
import com.doan.admindonghohanquoc.Model.Input.CategoryInput;
import com.doan.admindonghohanquoc.Model.Input.CategoryUpdateInput;
import com.doan.admindonghohanquoc.Model.OutPut.CategoriesOutput;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Component
public class CategoriesConverter {
    public CategoriesEntity toCategoriesInput(CategoryInput categoryInput)
    {
        CategoriesEntity categoriesEntity= new CategoriesEntity();
        if(!ObjectUtils.isEmpty(categoryInput))
        {
            categoriesEntity.setName(categoryInput.getName());
            categoriesEntity.setDescription(categoryInput.getDecription());
            categoriesEntity.setStatus(categoryInput.getStatus());
        }
        return categoriesEntity;
    }
    public CategoriesOutput toCategoriesEntity(CategoriesEntity categoriesEntity)
    {
        CategoriesOutput categoriesOutput = new CategoriesOutput();
        if(!ObjectUtils.isEmpty(categoriesEntity))
        {
            categoriesOutput.setId(categoriesEntity.getId());
            categoriesOutput.setName(categoriesEntity.getName());
            categoriesOutput.setDescription(categoriesEntity.getDescription());
            categoriesOutput.setStatus(categoriesEntity.getStatus());
        }
        return  categoriesOutput;
    }
    public CategoriesEntity toCategoriUpdateInput(CategoryUpdateInput categoryUpdateInput)
    {
        CategoriesEntity categoriesEntity= new CategoriesEntity();
        if(!ObjectUtils.isEmpty(categoryUpdateInput))
        {
            categoriesEntity.setId(categoryUpdateInput.getId());
            categoriesEntity.setName(categoryUpdateInput.getName());
            categoriesEntity.setDescription(categoryUpdateInput.getDescription());
            categoriesEntity.setStatus(categoryUpdateInput.getStatus());
        }
        return categoriesEntity;
    }
}
