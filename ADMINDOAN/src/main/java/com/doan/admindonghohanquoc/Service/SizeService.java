package com.doan.admindonghohanquoc.Service;

import com.doan.admindonghohanquoc.Model.Entity.SizeEntity;
import com.doan.admindonghohanquoc.Repository.ColorRepository;
import com.doan.admindonghohanquoc.Repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService {
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private ColorRepository colorRepository;
    public List<SizeEntity> getListSize()
    {
        return sizeRepository.findAll();
    }
}
