package com.cssweb.payment.bank.BOC;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by chenhf on 2014/6/26.
 */
@XStreamAlias("bocb2e")
public class DaifaResponse {
    private Head head;
    private Trans trans;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Trans getTrans() {
        return trans;
    }

    public void setTrans(Trans trans) {
        this.trans = trans;
    }
}
