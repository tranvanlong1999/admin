package com.doan.admindonghohanquoc.Common;

import com.doan.admindonghohanquoc.Model.Input.CategoryInput;
import com.doan.admindonghohanquoc.Model.Input.UserInput;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_FULL_NAME_REGEX = Pattern.compile("^[a-zA-Z]{3,30}$", Pattern.CASE_INSENSITIVE);

	public static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^[a-zA-Z0-9]{6,}$", Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_PHONE_REGEX = Pattern.compile("^[0-9]{10}$", Pattern.CASE_INSENSITIVE);

	public static boolean checkthemdanhmuc(CategoryInput danhmuc) {

		boolean flag = true;
		String tendanhmuc = danhmuc.getName();
		String mota = danhmuc.getDecription();
		Matcher matcher;
		// Case check tendanhmuc
		if (StringUtils.isEmpty(tendanhmuc)) {
			return false;
		}
		// case check mota
		if (StringUtils.isEmpty(mota)) {
			return false;
		}

		return flag;
	}
	@SuppressWarnings("deprecation")
	public static boolean checkRegister(UserInput user) {

		boolean flag = true;
		String fullname = user.getFullname();
		String email = user.getEmail();
		String password = user.getPassword();
		String address = user.getAddress();
		String phone = user.getPhone();
		Matcher matcher;


		// Case check fullname
		// phai viet lien
		matcher = VALID_FULL_NAME_REGEX.matcher(fullname);
		if (!matcher.find()) {
			return false;
		}
		System.out.println(flag);
		// Case check email
		matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		if (!matcher.find()) {
			return false;
		}

		// Case check password
		matcher = VALID_PASSWORD_REGEX.matcher(password);
		if (!matcher.find()) {
			return false;
		}
		System.out.println(flag);

		// Case check address
		if (StringUtils.isEmpty(address)) {
			return false;
		}
		// Case check phone
		matcher = VALID_PHONE_REGEX.matcher(phone);
		if (!matcher.find()) {
			return false;
		}
		System.out.println(flag);
		return flag;


	}
}