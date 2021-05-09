package com.doan.admindonghohanquoc.Controller;


import com.doan.admindonghohanquoc.Model.Entity.ProductAtributeEntity;
import com.doan.admindonghohanquoc.Model.Input.ProductAtributeInput;
import com.doan.admindonghohanquoc.Model.Input.ReceiverInFor;
import com.doan.admindonghohanquoc.Repository.ProductAtributeRepository;
import com.doan.admindonghohanquoc.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("gio-hang")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductAtributeRepository productAtributeRepository;
    @GetMapping
    public String pageCart(Model model, HttpSession session) {
        model.addAttribute("thongtinnguoinhan", new ReceiverInFor());
        return cartService.pageCart(model, session);
    }
    ProductAtributeInput info= new ProductAtributeInput();
    @PostMapping("/addproductdetail-tocart")
    public String addproductdetail(@ModelAttribute("productAtributeInput") ProductAtributeInput productDetail, Model model)
    {
        /*productDetailId = productDetail.getId();*/
        info.setId(productDetail.getId());
        info.setQuantity(productDetail.getQuantity());
        ProductAtributeEntity productAtributeEntity= productAtributeRepository.findById(productDetail.getId()).get();
        if(productAtributeEntity.getQuantity()  < productDetail.getQuantity())
        {
            Integer productid=productAtributeEntity.getProductentity().getId();
            return "redirect:/product-details/"+productid;
        }
        return "redirect:/gio-hang/add";
    }
    @GetMapping("/add")
    public String addToCart(Model model, HttpSession session)
    {
        return cartService.addToCart(model, session, info);
    }
    @GetMapping("update")
    public String updateToCart(Model model, HttpSession session, @RequestParam("id") int id,
                               @RequestParam("flag") int flag) {
        return cartService.updateToCart(model, session, id, flag);
    }

    @GetMapping("delete")
    public String deleteToCart(Model model, HttpSession session, @RequestParam("id") int id) {
        return cartService.deleteToCart(model, session, id);
    }
    @GetMapping("deleteallcart")
    public String deleteallcart(Model model,HttpSession session)
    {
        return cartService.deleteAllCart(model,session);
    }

}
