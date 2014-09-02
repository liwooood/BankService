package com.cssweb.payment.bank;

/**
 * Created by chenhf on 2014/9/2.
 */
public  class BankOrder {


    private int orderType;
    private int paymentBank;
    private String orderDate;
    private String orderTime;


    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public int getPaymentBank() {
        return paymentBank;
    }

    public void setPaymentBank(int paymentBank) {
        this.paymentBank = paymentBank;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }


}
