package com.doan.admindonghohanquoc.Service;

import com.doan.admindonghohanquoc.Model.Entity.SizeEntity;
import com.doan.admindonghohanquoc.Repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {
    @Autowired
    SizeRepository sizeRepository;
    public List<SizeEntity> getListSize() {
        return sizeRepository.findAll();
    }
}
