package com.doan.admindonghohanquoc.Controller;

import com.doan.admindonghohanquoc.Model.Input.CategoryInput;
import com.doan.admindonghohanquoc.Model.OutPut.CategoriesOutput;
import com.doan.admindonghohanquoc.Service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/danhmuc")
public class QuanLyDanhMucController {
    @Autowired
    CategoriesService categoriesService;
    @GetMapping()
    public String getListCategoriesByAdmin(Model model)
    {
        model.addAttribute("listdanhmuc",categoriesService.getListCategories());
        return "Quanlydanhmuc";
    }
    @PostMapping
    public String createCategoriesByAdmin(Model model,@ModelAttribute("categoryInput") CategoryInput categoryInput)
    {
        return categoriesService.createCategorybyAdmin(model,categoryInput);
    }
}
