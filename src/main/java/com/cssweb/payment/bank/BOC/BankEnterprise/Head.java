package com.cssweb.payment.bank.BOC.BankEnterprise;

/**
 * Created by chenhf on 2014/6/26.
 */
public class Head {
    private String termid;
    private String custid;
    private String cusopr;
    private String trncod;
    private String token;

    public String getTermid() {
        return termid;
    }

    public void setTermid(String termid) {
        this.termid = termid;
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public String getCusopr() {
        return cusopr;
    }

    public void setCusopr(String cusopr) {
        this.cusopr = cusopr;
    }

    public String getTrncod() {
        return trncod;
    }

    public void setTrncod(String trncod) {
        this.trncod = trncod;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
