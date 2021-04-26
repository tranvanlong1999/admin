package com.doan.admindonghohanquoc.Service;

import com.doan.admindonghohanquoc.Model.Entity.ColorEntity;
import com.doan.admindonghohanquoc.Repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;
    public List<ColorEntity> getListColor()
    {
        return colorRepository.findAll();
    }
}
