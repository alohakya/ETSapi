package com.project.etsapi.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.project.etsapi.entity.Account;
import com.project.etsapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/*
 * @title: JwtUtil
 * @package com.project.etsapi.Util
 * @description: token生成与验证类
 * @author: R-YYY
 * @date: 2021-12-15 10:04
 * @version: V1.0
*/
@Component
public class JwtUtil {

    @Autowired
    AccountService accountService;

    public static JwtUtil jwtUtil;

    private static final String[] SECRET_KEY = {
            "sd237#@sd!$%sdk-=",
            "a+*ag1!#@%FSDc21f",
            "65+-*#xsa@dhx?3r5"
    };

    @PostConstruct
    void init(){
        jwtUtil = this;
        jwtUtil.accountService = this.accountService;
    }

    public String getSECRET_KEY(String account_ID){
        int index = Integer.parseInt(account_ID);
        return SECRET_KEY[index % 3];
    }

    /**
     * 生成token
     * @param account_ID
     * @param password
     * @return
     */
    public String createToken(String account_ID,String password){
        try {
            Algorithm algorithm = Algorithm.HMAC256(getSECRET_KEY(account_ID) + password);
            return JWT.create().withAudience(account_ID).sign(algorithm);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public Boolean verifyToken(String token){
        try{
            String account_ID = JWT.decode(token).getAudience().get(0);
            Account account = jwtUtil.accountService.getAccountById(account_ID);
            if(account == null){
                return false;
            }
            Algorithm algorithm = Algorithm.HMAC256(getSECRET_KEY(account_ID) + account.getPassword());
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        }
        catch (JWTVerificationException e){
            return false;
        }
    }
}
