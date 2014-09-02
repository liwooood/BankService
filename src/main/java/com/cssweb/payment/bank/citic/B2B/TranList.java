package com.cssweb.payment.bank.CITIC.B2B;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by chenhf on 2014/8/3.
 */

public class TranList {
    @XStreamAlias("name")
    @XStreamAsAttribute
    private String name;

    @XStreamImplicit(itemFieldName="row")
    private List<Tran> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tran> getList() {
        return list;
    }

    public void setList(List<Tran> list) {
        this.list = list;
    }
}
