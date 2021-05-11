package com.doan.admindonghohanquoc.Service;

import com.doan.admindonghohanquoc.Common.PageConstant;
import com.doan.admindonghohanquoc.Model.Input.ProductAtributeInput;
import com.doan.admindonghohanquoc.Model.OutPut.Cart;
import com.doan.admindonghohanquoc.Repository.ColorRepository;
import com.doan.admindonghohanquoc.Repository.ProductAtributeRepository;
import com.doan.admindonghohanquoc.Repository.ProductRepository;
import com.doan.admindonghohanquoc.Repository.SizeRepository;
import com.doan.admindonghohanquoc.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private static final String REDIRECT_GIO_HANG = "redirect:/gio-hang";
    private static final String AMOUNT = "amount";
    private static final String TOTAL = "total";
    private static final String SESSION_CART = "carts";
    @Autowired
    ProductAtributeRepository productAtributeRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private ColorRepository colorRepository;
    @SuppressWarnings("unchecked")
    public String pageCart(Model model, HttpSession session) {
        List<Cart> carts = (List<Cart>) session.getAttribute(SESSION_CART);
        if (ObjectUtils.isEmpty(carts)) {
            carts = new ArrayList<>();
        }
        int total = carts == null ? 0 : carts.size();
        session.setAttribute(SESSION_CART, carts);
        model.addAttribute(TOTAL, total);
        model.addAttribute(AMOUNT, Utils.currencyMoney((int) Utils.amount(carts)));
        return PageConstant.PAGE_CART;
    }
    @SuppressWarnings("unchecked")
    public String deleteToCart(Model model, HttpSession session, int id) {
        try {
            List<Cart> carts = (List<Cart>) session.getAttribute(SESSION_CART);
            int total;

            if (!ObjectUtils.isEmpty(carts)) {
                /*carts.forEach(cart -> {
                    if (cart.getId() == id) {
                        carts.remove(cart);
                    }
                });*/
                for (int i=0;i<carts.size();i++)
                {
                    Cart cart= carts.get(i);
                    if(cart.getId()==id)
                    {
                        carts.remove(cart);
                    }

                }

            }
            total = carts == null ? 0 : carts.size();

            session.setAttribute(SESSION_CART, carts);
            model.addAttribute(TOTAL, carts.size());
            model.addAttribute(AMOUNT, Utils.currencyMoney((int) Utils.amount(carts)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return REDIRECT_GIO_HANG;
    }
    @SuppressWarnings("unchecked")
    public String deleteAllCart(Model model, HttpSession session) {
        try {
            List<Cart> carts = (List<Cart>) session.getAttribute(SESSION_CART);
            int total;

            if (!ObjectUtils.isEmpty(carts)) {
                for (int i=0;i<carts.size();i++)
                {
                    Cart cart= carts.get(i);
                    carts.remove(cart);
                }
            }
            total = carts == null ? 0 : carts.size();

            session.setAttribute(SESSION_CART, carts);
            model.addAttribute(TOTAL, carts.size());
            model.addAttribute(AMOUNT, Utils.currencyMoney((int) Utils.amount(carts)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return REDIRECT_GIO_HANG;
    }
    public String updateToCart(Model model, HttpSession session, int id, int flag) {
        try {
            List<Cart> carts = (List<Cart>) session.getAttribute(SESSION_CART);

            int index = 0;
            int count;
            if (!ObjectUtils.isEmpty(carts)) {
                for (Cart cart : carts) {
                    if (cart.getId() == id) {
                        if (flag == 0) {
                            count = cart.getCount() - 1 == 0 ? 1 : cart.getCount() - 1;
                            cart.setCount(count);
                        } else {
                            count = cart.getCount() + 1 == 5 ? 5 : cart.getCount() + 1;
                            cart.setCount(count);
                        }
                        carts.set(index, cart);
                        break;
                    }
                    index++;
                }
            }

            int total = carts == null ? 0 : carts.size();
            session.setAttribute(SESSION_CART, carts);
            model.addAttribute(TOTAL, carts.size());
            model.addAttribute(AMOUNT, Utils.currencyMoney((int) Utils.amount(carts)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return REDIRECT_GIO_HANG;
    }
    @SuppressWarnings("unchecked")
    public String addToCart(Model model, HttpSession session, ProductAtributeInput input) {
        try {
            System.out.println(input);

            Integer productID= productAtributeRepository.findById(input.getId()).get().getId();
            Cart cart = new Cart();
            List<Cart> carts = (List<Cart>) session.getAttribute(SESSION_CART);
            boolean flag = false;
            int cartId = 1;
            int index = 0;
            int total;

            if (ObjectUtils.isEmpty(carts)) {
                carts = new ArrayList<>();
            } else {
                for (Cart item : carts) {
                    if (item.getProductDetail().getProductentity().getId() == productID
                            && item.getProductDetail().getId()== input.getId()
                    ) {
                        cart = item;
                        flag = true;
                        index++;
                        break;
                    }
                }
                cartId = carts.get(carts.size() - 1).getId() + 1;
            }
            if (flag) {
                cart.setCount(cart.getCount() + 1);
                carts.set(index - 1, cart);
            } else {
                cart.setId(cartId);
                cart.setCount(1);
                cart.setProductDetail(productAtributeRepository.findById(input.getId()).get());
                carts.add(cart);
            }
            total = carts == null ? 0 : carts.size();
            session.setAttribute(SESSION_CART, carts);
            model.addAttribute(TOTAL, total);
            model.addAttribute(AMOUNT, Utils.currencyMoney((int) Utils.amount(carts)));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return REDIRECT_GIO_HANG;
    }
}
