package com.cssweb.payment.bank;

import com.cssweb.payment.bank.BankComm.B2B.BankCommB2COrder;
import com.cssweb.payment.bank.BankComm.B2C.BankCommB2BOrder;
import com.cssweb.util.DataDict;

/**
 * Created by chenhf on 2014/9/2.
 */
public class BankServiceImpl implements BankService{

    @Override
    public String sign(BankOrder bankOrder) {

        String signData = "";

        if (bankOrder.getPaymentBank() == DataDict.BANK_BANKCOMM)
        {
            if (bankOrder.getOrderType() == DataDict.ORDER_TYPE_B2C)
            {
                BankCommB2COrder b2c = new BankCommB2COrder();
                signData = b2c.sign();
            }
            else if (bankOrder.getOrderType() == DataDict.ORDER_TYPE_B2B)
            {
                BankCommB2BOrder b2b = new BankCommB2BOrder();
               signData = b2b.sign();
            }
            else
            {

            }
        }

        return signData;
    }

    @Override
    public boolean verify(String paymentResult, int bank) {
        return false;
    }
}
