package com.doan.admindonghohanquoc.Utils;

import com.doan.admindonghohanquoc.Model.OutPut.Cart;
import org.springframework.util.ObjectUtils;

import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Utils {
    public static String formatStringtoUrl(String  value)
    {
        value= value.replace("","");
        return ConvertVietnameseToEnglish(value.toLowerCase());
    }
    // class Class này cung cấp các phương thức để tiêu chuẩn hóa văn bản Unicode
    // thành văn bản tương đương.
    // Nhằm mục đích thuận tiện cho việc sắp xếp và tìm kiếm chuỗi.
    public static String ConvertVietnameseToEnglish(String src)
    {
        //Tiêu chuẩn hóa chuỗi s được truyền vào theo định dạng NFD.
        // Kết quả trả về là chuỗi đã được tiêu chuẩn hóa.
        // link đọc https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/replaceAll
        return Normalizer.normalize(src, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
    public static String currencyMoney(int money) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(money).replace("₫", "đ");
    }

    public static long amount(List<Cart> carts) {
        long result = 0;
        if (!ObjectUtils.isEmpty(carts)) {
            for (Cart cart : carts) {
                result += cart.getProductDetail().getProductentity().getPrice() * cart.getCount();
            }
        }
        return result;
    }

}
