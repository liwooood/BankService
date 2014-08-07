package com.cssweb.payment.bank.ABC.B2C;

/**
 * Created by chenhf on 2014/8/7.
 */
public class B2COrder {
    public String abcB2CPayment() {
        Order tOrder = new Order();
        tOrder.setOrderNo(""); //设定订单编号 （必要信息）
        tOrder.setExpiredDate(2); //设定订单有效期 （必要信息）
        tOrder.setOrderDesc(""); //设定订单说明
        tOrder.setOrderDate(""); //设定订单日期 （必要信息 - YYYY/MM/DD）
        tOrder.setOrderTime(""); //设定订单时间 （必要信息 - HH:MM:SS）
        tOrder.setOrderAmount(100.00); //设定订单金额 （必要信息）
        tOrder.setOrderURL(""); //设定订单网址
        tOrder.setBuyIP("");       //设定订单IP
//3、生成定单订单对象，并将订单明细加入定单中（可选信息）
        tOrder.addOrderItem(new OrderItem("IP000001", "中国移动IP卡", 100.00f, 1));
        tOrder.addOrderItem(new OrderItem("IP000002", "网通IP卡"    ,  90.00f, 2));

        PaymentRequest tPaymentRequest = new PaymentRequest();
        tPaymentRequest.setOrder      (tOrder      ); //设定支付请求的订单 （必要信息）
        tPaymentRequest.setProductType(""); //设定商品种类 （必要信息）
        //PaymentRequest.PRD_TYPE_ONE：非实体商品，如服务、IP卡、下载MP3、...
        //PaymentRequest.PRD_TYPE_TWO：实体商品
        tPaymentRequest.setPaymentType(""); //设定支付类型
        //PaymentRequest.PAY_TYPE_ABC：农行卡支付
        //PaymentRequest.PAY_TYPE_INT：国际卡支付
        tPaymentRequest.setNotifyType("");   //设定商户通知方式
        //0：URL页面通知
        //1：服务器通知
        tPaymentRequest.setResultNotifyURL(""); //设定支付结果回传网址 （必要信息）
        tPaymentRequest.setMerchantRemarks(""); //设定商户备注信息
        tPaymentRequest.setPaymentLinkType("");//设定支付接入方式
        try {
            String signData = tPaymentRequest.genSignature(1);
        } catch (TrxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
