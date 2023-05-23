package com.cdweb.vocabproject.model.mapper;

import com.cdweb.vocabproject.model.dto.AccountDTO;
import com.cdweb.vocabproject.model.entity.Account;

import java.util.List;

public interface AccountMapper {

    AccountDTO toDTO(Account account);

    List<AccountDTO> toListDTO(List<Account> accountList);

    Account toEntity(Account account, AccountDTO accountDTO);

}
