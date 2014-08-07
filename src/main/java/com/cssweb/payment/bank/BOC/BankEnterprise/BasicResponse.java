package com.cssweb.payment.bank.BOC.BankEnterprise;

/**
 * Created by chenhf on 2014/6/25.
 */
public class BasicResponse {
    private int retCode;
    private String retMsg;

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}
