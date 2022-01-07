package com.project.etsapi.mapper;

import com.project.etsapi.entity.Account;
import com.project.etsapi.vo.AccountInfo;
import com.project.etsapi.vo.Privacy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Created by Intellij IDEA
 * @Description
 * @Author Li Lijun
 * @Date 2021/11/19
 * @Time 19:36
 **/

@Mapper
public interface AccountMapper {

    int addAccount(Account account);

    int deleteAccount(String account_ID);

    Account getAccountById(String account_ID);

    List<AccountInfo> getAllTeacherAccount();

    List<AccountInfo> getAllStudentAccount();

    int updateActive(String account_ID, String is_active);

    int updatePassword(String account_ID, String password);

    int updateEmail(String account_ID, String email);

    Privacy getStudentPrivacy(String student_ID);

    Privacy getTeacherPrivacy(String teacher_ID);

}
