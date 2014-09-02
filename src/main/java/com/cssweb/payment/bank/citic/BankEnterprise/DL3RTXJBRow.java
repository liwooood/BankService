package com.cssweb.payment.bank.CITIC.BankEnterprise;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by chenhf on 2014/8/13.
 */
public class DL3RTXJBRow {
    private String cashFlwNo;

    private String rcvAccNo;

    private String rcvAccNm;

    private String tranAmount;


    @XStreamAlias("abstract")
    private String abs;

    private String memo;

    public String getCashFlwNo() {
        return cashFlwNo;
    }

    public void setCashFlwNo(String cashFlwNo) {
        this.cashFlwNo = cashFlwNo;
    }

    public String getRcvAccNo() {
        return rcvAccNo;
    }

    public void setRcvAccNo(String rcvAccNo) {
        this.rcvAccNo = rcvAccNo;
    }

    public String getRcvAccNm() {
        return rcvAccNm;
    }

    public void setRcvAccNm(String rcvAccNm) {
        this.rcvAccNm = rcvAccNm;
    }

    public String getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(String tranAmount) {
        this.tranAmount = tranAmount;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
