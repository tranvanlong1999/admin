package com.doan.admindonghohanquoc.Service;

import com.doan.admindonghohanquoc.Model.Entity.OrderEntity;
import com.doan.admindonghohanquoc.Model.Entity.OrderItemEntity;
import com.doan.admindonghohanquoc.Model.Input.ReceiverInFor;
import com.doan.admindonghohanquoc.Model.OutPut.Cart;
import com.doan.admindonghohanquoc.Repository.OrderItemRepository;
import com.doan.admindonghohanquoc.Repository.OrderRepository;
import com.doan.admindonghohanquoc.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PayService {
    private static final String AMOUNT = "amount";
    private static final String SESSION_CART = "carts";
    private static final String REDIRECT_GIO_HANG = "redirect:/gio-hang";
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderDetailRepository;
    @SuppressWarnings("unchecked")
    public String pagePay(HttpSession session, Model model) {
        String result = "checkout";
        List<Cart> carts = (List<Cart>) session.getAttribute(SESSION_CART);
        if(ObjectUtils.isEmpty(carts)) {
            result = REDIRECT_GIO_HANG;
        }
        model.addAttribute("thongtinnguoinhan", new ReceiverInFor());
        model.addAttribute("totalMoney", Utils.currencyMoney((int) Utils.amount(carts)));
        model.addAttribute(AMOUNT, Utils.currencyMoney((int) Utils.amount(carts) + 15000));
        return result;
    }
    @Transactional(rollbackOn = Exception.class)
    public String pay(HttpSession session, ReceiverInFor input) {
        try {
            System.out.println(input);
            List<Cart> carts = (List<Cart>) session.getAttribute(SESSION_CART);
            List<OrderItemEntity> orderDetails = new ArrayList<>();
            OrderEntity order = new OrderEntity();
            int amount = 0;

            for (Cart cart : carts) {
                amount += cart.getCount() * cart.getProductDetail().getProductentity().getPrice();
            }

            order.setRecipientaddress(input.getRecipientAddress());
            order.setCreatedate(new Date());
            order.setTotal(amount);
            order.setEmail(input.getRecipientEmail());
            order.setRecipientname(input.getRecipientName());
            order.setRecipientphone(input.getRecipientPhone());
            order.setStatus(1);

            order = orderRepository.save(order);

            for (Cart cart : carts) {
                OrderItemEntity orderDetail = new OrderItemEntity();

                orderDetail.setOrderEntity(order);
                orderDetail.setQuantity(cart.getCount());
                orderDetail.setProductAtributeEntity(cart.getProductDetail());
                orderDetails.add(orderDetail);
            }
            orderDetailRepository.saveAll(orderDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/home";
    }
}
