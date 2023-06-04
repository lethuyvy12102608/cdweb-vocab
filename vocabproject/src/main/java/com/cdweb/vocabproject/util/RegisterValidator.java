package com.cdweb.vocabproject.util;

import com.cdweb.vocabproject.model.dto.AccountDTO;
import com.cdweb.vocabproject.model.entity.Account;
import com.cdweb.vocabproject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class RegisterValidator implements Validator {
    @Autowired
    private AccountService accountService ;
    @Override
    public boolean supports(Class<?> clazz) {
        return AccountDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            AccountDTO accountDTO = (AccountDTO) target;
            Account account = null;

            // verify hoVaTen
            if (ValidatorUtil.isEmpty(accountDTO.getFullName())) {
                errors.rejectValue("fullName", "Vui lòng nhập Họ và Tên!",
                        "Vui lòng nhập Họ và Tên!");
            }

            // verify username
            String email = accountDTO.getEmail();
            if (ValidatorUtil.isEmpty(email)) {
                errors.rejectValue("username", "Vui lòng nhập Tên Đăng Nhập!",
                        "Vui lòng nhập Tên Đăng Nhập!");
            } else {
                account = accountService.findByEmail(email.trim());
                if (account != null && account.getEmail() != accountDTO.getEmail()) {
                    errors.rejectValue("email", "Tên Email đã được đăng ký!",
                            "Tên Email đã được đăng ký!");
                }
            }

            // verify password
            if (ValidatorUtil.isEmpty(accountDTO.getPassword())) {
                errors.rejectValue("password", "Vui lòng nhập Mật Khẩu!",
                        "Vui lòng nhập Mật Khẩu!");
            } else {
                if (accountDTO.getPassword().length() < 6) {
                    errors.rejectValue("password", "Vui lòng nhập Mật Khẩu lớn hơn 6 ký tự!",
                            "Vui lòng nhập Mật Khẩu lớn hơn 6 ký tự!");
                }
            }
        } catch (Exception e) {
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }


    }





}