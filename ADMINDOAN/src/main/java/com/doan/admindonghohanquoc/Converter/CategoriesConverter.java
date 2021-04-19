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
            categoriesEntity.setCreatedat(new Date());
            categoriesEntity.setCreatedby(categoryInput.getCreatedby());
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
            categoriesOutput.setCreatedat(categoriesEntity.getCreatedat());
            categoriesOutput.setCreatedby(categoriesEntity.getCreatedby());
            categoriesOutput.setUpdatedat(categoriesEntity.getUpdatedat());
            categoriesOutput.setUpdatedby(categoriesEntity.getUpdatedby());
            categoriesOutput.setIsformen(categoriesEntity.getIsformen());
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
            categoriesEntity.setUpdatedat(new Date());
            categoriesEntity.setUpdatedby(categoryUpdateInput.getUpdatedby());
        }
        return categoriesEntity;
    }
}
