package com.cssweb.payment.bank.BOC.BankEnterprise;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by chenhf on 2014/6/26.
 */
@XStreamAlias("trans")
public class Trans {
    @XStreamAlias("trn-b2e0078-rs")
    private Trnb2e0078rs trnb2e0078rs;

    public Trnb2e0078rs getTrnb2e0078rs() {
        return trnb2e0078rs;
    }

    public void setTrnb2e0078rs(Trnb2e0078rs trnb2e0078rs) {
        this.trnb2e0078rs = trnb2e0078rs;
    }
}
