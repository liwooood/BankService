package com.cssweb.payment.bank.BankComm.B2B;

import com.cssweb.payment.bank.BankOrder;
import com.cssweb.payment.bank.BankOrderAction;

/**
 * Created by chenhf on 2014/9/2.
 */
public class BankCommB2COrder implements BankOrderAction{

    // 消息版本号
    private String interfaceVersion;
    // 商户客户号（商户编号）
    private String merID;
    // 订单号
    private String orderid;
    // 渠道编号
    private String netType;
    // 商家签名
    private String merSignMsg;


    @Override
    public String sign() {
        return null;
    }

    @Override
    public boolean verify() {
        return false;
    }
}
