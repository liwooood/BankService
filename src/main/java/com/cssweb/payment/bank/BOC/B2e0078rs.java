package com.cssweb.payment.bank.BOC;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by chenhf on 2014/6/26.
 */
@XStreamAlias("b2e0078-rs")
public class B2e0078rs {
    private Status status;
    private String insid;
    private String obssid;


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getInsid() {
        return insid;
    }

    public void setInsid(String insid) {
        this.insid = insid;
    }

    public String getObssid() {
        return obssid;
    }

    public void setObssid(String obssid) {
        this.obssid = obssid;
    }
}
