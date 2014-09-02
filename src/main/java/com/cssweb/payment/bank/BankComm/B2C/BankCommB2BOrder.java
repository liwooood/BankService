package com.cssweb.payment.bank.BankComm.B2C;

import com.cssweb.payment.bank.BankOrder;
import com.cssweb.payment.bank.BankOrderAction;

/**
 * Created by chenhf on 2014/9/2.
 */
public class BankCommB2BOrder implements BankOrderAction {
    @Override
    public String sign() {
        return null;
    }

    @Override
    public boolean verify() {
        return false;
    }
}
