package com.cssweb.payment.bank;

/**
 * Created by chenhf on 2014/9/2.
 */
public interface BankService {



    public String sign(BankOrder bankOrder);
    public boolean verify(String paymentResult, int bank);

}
