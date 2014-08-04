package com.cssweb.payment.bank.BOC;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhf on 2014/6/26.
 */
@XStreamAlias("trn-b2e0078-rs")
public class Trnb2e0078rs {
    private Status status;

    //@XStreamAlias("b2e0078-rs")
    @XStreamImplicit(itemFieldName="b2e0078-rs")
    private List<B2e0078rs> B2e0078rs = new ArrayList<B2e0078rs>();

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<B2e0078rs> getB2e0078rs() {
        return B2e0078rs;
    }

    public void setB2e0078rs(List<B2e0078rs> B2e0078rs) {
        this.B2e0078rs = B2e0078rs;
    }
}
