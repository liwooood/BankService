package com.cssweb.payment.bank.citic.BankEnterprise;

import com.cssweb.payment.bank.citic.B2B.Tran;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by chenhf on 2014/8/5.
 */
public class DLBALQRYList {
    @XStreamAlias("name")
    @XStreamAsAttribute
    private String name;

    @XStreamImplicit(itemFieldName="row")
    private List<DLBALQRYRow> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DLBALQRYRow> getList() {
        return list;
    }

    public void setList(List<DLBALQRYRow> list) {
        this.list = list;
    }
}
