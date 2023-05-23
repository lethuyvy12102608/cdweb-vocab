package com.cdweb.vocabproject.validator;

import com.cdweb.vocabproject.model.dto.AccountDTO;
import com.cdweb.vocabproject.model.entity.Account;
import com.cdweb.vocabproject.service.AccountService;
import com.cdweb.vocabproject.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator {

    @Autowired
    private AccountService accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            AccountDTO accountDTO = (AccountDTO) target;
            Account account = null;

            // verify fullName
            if (ValidatorUtil.isEmpty(accountDTO.getFullName())) {
                errors.rejectValue("fullName", "Vui lòng nhập Họ và Tên!",
                        "Vui lòng nhập Họ và Tên!");
            }

            // verify username
            if (ValidatorUtil.isEmpty(accountDTO.getUsername())) {
                errors.rejectValue("username", "Vui lòng nhập Tên Đăng Nhập!",
                        "Vui lòng nhập Tên Đăng Nhập!");
            } else {
                account = accountService.findByUsername(accountDTO.getUsername().trim());
                if (account != null && !account.getUsername().equals(accountDTO.getUsername())) {
                    errors.rejectValue("username", "Tên Đăng Nhập đã được đăng ký!",
                            "Tên Đăng Nhập đã được đăng ký!");
                }
            }
        } catch (Exception e) {
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }

    }
}
