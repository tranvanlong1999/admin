package com.doan.admindonghohanquoc.Constants;

public enum ResCode {
    SUCCESS("200", "Success"),
    INPUT_INVALID("400", "Input data is invalid"),
    ACCOUNT_NUMBER_INVALID("201", "Account number is invalid"),
    PERMISSION_DENIED("403", "Permission denied"),
    DUPLICATE_RECORD("402", "Duplicate record"),
    RECORD_DO_NOT_EXIST("404", "không tồn tại trong hệ thống"),
    UNKNOWN_ERROR("500", "Unknown error"),
    RECORD_EXISTS("401", "Record exists"),
    ACCOUNT_LOGIN("102", "Tên đăng nhập hoặc mật khẩu không đúng"),
    EMAIL_IS_WRONG_FORMAT("204","Email không đúng định dạng");
    private String code;
    private String message;

    ResCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
