package com.doan.admindonghohanquoc.Controller;

import com.doan.admindonghohanquoc.Model.Entity.OrderEntity;
import com.doan.admindonghohanquoc.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class QuanLyHoaDonController {
    @Autowired
    OrderRepository orderRepository;
    @GetMapping("/hoadon")
    public String hoadon(Model model) {
        /*Page<OrderEntity> orderList = orderRepository.findAll(PageRequest.of(0,));*/
        return "Quanlyhoadon";
    }
}
