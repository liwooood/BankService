package com.cssweb.payment.bank.BOC.B2C;

import com.bocnet.common.security.PKCS7Tool;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;

/**
 * Created by chenhf on 2014/8/7.
 */
public class B2COrder {
    public String payment(String merchantNo, String orderNo, String curCode, BigDecimal orderAmount, String orderTime, String orderDesc) {

        String signData = "";



        String keyStorePath = "E:\\payment\\bank\\bankservice\\src\\main\\resources\\boc\\20120810 U3铁道部证书--密码6个1.pfx";
        String keyStorePassword = "111111";
        String keyPassword = "111111";



        try {
            PKCS7Tool pkcs7 = PKCS7Tool.getSigner(keyStorePath, keyStorePassword, keyPassword);

            BigDecimal newOrderAmount = orderAmount.setScale(2, BigDecimal.ROUND_HALF_UP);


            String b2c = orderNo + "|" + orderTime + "|" + curCode + "|" + newOrderAmount + "|" + merchantNo;

            signData = pkcs7.sign(b2c.getBytes());
            System.out.println("signData=" + signData);

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return signData;
    }
}
