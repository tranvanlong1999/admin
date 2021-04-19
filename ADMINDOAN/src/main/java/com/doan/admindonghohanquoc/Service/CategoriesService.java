package com.doan.admindonghohanquoc.Service;

import com.doan.admindonghohanquoc.Common.MessageConstant;
import com.doan.admindonghohanquoc.Common.Validate;
import com.doan.admindonghohanquoc.Converter.CategoriesConverter;
import com.doan.admindonghohanquoc.Model.Entity.CategoriesEntity;
import com.doan.admindonghohanquoc.Model.Input.CategoryInput;
import com.doan.admindonghohanquoc.Model.Input.CategoryUpdateInput;
import com.doan.admindonghohanquoc.Model.OutPut.CategoriesOutput;
import com.doan.admindonghohanquoc.Repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;

import java.util.LinkedList;
import java.util.List;


@Service
public class CategoriesService {
    @Autowired
    CategoriesRepository categoriesRepository;
    @Autowired
    CategoriesConverter categoriesConverter;

    public String createCategorybyAdmin(Model model, CategoryInput categoryInput) {

       String result = "Themdanhmuc";
       String error = null;
       CategoriesEntity category = null;
        try
        {
            // step 1: validate
            if(Validate.checkthemdanhmuc(categoryInput))
            {
                // chưa check được nếu danh mục trùng thì sao???
                category= categoriesConverter.toCategoriesInput(categoryInput);
                categoriesRepository.save(category);
                result ="redirect:/danhmuc";
            }
            else
            {
                error= MessageConstant.CREATE_ERROR;
            }
        } catch (Exception e) {
            error= MessageConstant.CREATE_ERROR;
        }
        model.addAttribute("error", error);
        return result;
    }

    public List<CategoriesOutput> getListCategories() {
        List<CategoriesOutput> categoriesOutputList = new LinkedList<>();
        // lay list
        try {
            List<CategoriesEntity> categoriesEntityList=categoriesRepository.findAll();
            for (CategoriesEntity categoriesEntity: categoriesEntityList) {
                categoriesOutputList.add(new CategoriesConverter().toCategoriesEntity(categoriesEntity));
            }
        } catch (Exception e) {

        }
        return categoriesOutputList;
    }

    public Boolean deleteCategoryByAdmin(Integer categoryID) {
        Boolean flag=false;
        try {
            // tim kiem category ID  trong danh sach category
            CategoriesEntity categoriesEntity= categoriesRepository.findById(categoryID).get();
            if(ObjectUtils.isEmpty(categoriesEntity))
            {
                throw new Exception("danh muc khong ton tai ");
            }
            if(!ObjectUtils.isEmpty(categoriesEntity))
            {
                categoriesRepository.delete(categoriesEntity);
                flag=true;
            }
        } catch (Exception e) {
        }
        return flag;
    }

    public Boolean updateCategoryByAdmin(CategoryUpdateInput categoryUpdateInput) {
        Boolean flag=false;
        try {
            if(!ObjectUtils.isEmpty(categoryUpdateInput))
            {
                CategoriesEntity categoriesEntity = categoriesRepository.findById(categoryUpdateInput.getId()).get();
                if(ObjectUtils.isEmpty(categoriesEntity))
                    throw new Exception("khong tim thay danh muc can sua");
                categoriesRepository.save(new CategoriesConverter().toCategoriUpdateInput(categoryUpdateInput));
                flag=true;
            }
        } catch (Exception e) {
        }
        return flag;
    }
}
