package com.cssweb.payment.bank;

/**
 * Created by chenhf on 2014/9/2.
 */
public interface BankOrderAction {

    public String sign();
    public boolean verify();
}
