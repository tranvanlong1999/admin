package com.doan.admindonghohanquoc.Service;

import com.doan.admindonghohanquoc.Converter.BrandConverter;
import com.doan.admindonghohanquoc.Model.Entity.BrandEntity;
import com.doan.admindonghohanquoc.Model.OutPut.BrandOutput;
import com.doan.admindonghohanquoc.Repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    BrandConverter brandConverter;
    public List<BrandOutput> getlistbrand() {
        List<BrandOutput> brandOutputList= new LinkedList<>();
        List<BrandEntity> brandEntityList = brandRepository.findAll();
        for (BrandEntity item: brandEntityList) {
            brandOutputList.add(brandConverter.toBrandEntity(item));
        }
        return brandOutputList;
    }
}
