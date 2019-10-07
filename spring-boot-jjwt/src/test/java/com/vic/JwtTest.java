package com.vic;

import com.vic.bean.UserInfo;
import com.vic.utils.JwtUtils;
import com.vic.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JwtTest {
    private static final String pubKeyPath = "F:\\tmp\\rsa\\rsa.pub";

    private static final String priKeyPath = "F:\\tmp\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU2OTk0MjI2MX0.f5whi3Zaxsb5EVs6rjRpxL0_1pXOOjdpkTplrP7rfc3_MMOg4-IP4wdEiHChzTYobokD8u5Is5hXWtjHGkCc4-VUUh7df9CIjcsS7ogxRbOQVmK33L9yGWhTTibanYLd-oqk6YcpVdnh2j97t4Pmv9JbHq4jNmtkg53s5APNtBQ";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}
