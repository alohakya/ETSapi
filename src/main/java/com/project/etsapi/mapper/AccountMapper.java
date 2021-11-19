package com.project.etsapi.mapper;

import com.project.etsapi.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Created by Intellij IDEA
 * @Description
 * @Author Li Lijun
 * @Date 2021/11/19
 * @Time 19:36
 **/

@Mapper
public interface AccountMapper {
    Account getAccountById(String account_ID);
}
