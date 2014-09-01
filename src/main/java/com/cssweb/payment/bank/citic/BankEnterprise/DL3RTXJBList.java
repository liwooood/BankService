package com.cssweb.payment.bank.citic.BankEnterprise;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by chenhf on 2014/8/13.
 */
public class DL3RTXJBList {
    @XStreamAlias("name")
    @XStreamAsAttribute
    private String name;

    @XStreamImplicit(itemFieldName="row")
    private List<DL3RTXJBRow> list;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DL3RTXJBRow> getList() {
        return list;
    }

    public void setList(List<DL3RTXJBRow> list) {
        this.list = list;
    }
}
