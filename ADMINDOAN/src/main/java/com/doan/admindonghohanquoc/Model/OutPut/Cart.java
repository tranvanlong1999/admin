package com.doan.admindonghohanquoc.Model.OutPut;


import com.doan.admindonghohanquoc.Model.Entity.ProductAtributeEntity;
import com.doan.admindonghohanquoc.Utils.Utils;
import lombok.Data;

@Data
public class Cart {
    private int id;
    private int count;
    private int productId;
    private int sizeId;
    private ProductAtributeEntity productDetail;
    public String getAmount() {
        int money = (int) (this.productDetail.getProductentity().getPrice() * this.count);
        return Utils.currencyMoney(money);
    }
}
