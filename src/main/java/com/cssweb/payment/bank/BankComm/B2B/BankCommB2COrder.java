package com.cssweb.payment.bank.BankComm.B2B;

import com.bocom.netpay.b2cAPI.BOCOMB2CClient;
import com.bocom.netpay.infosec.NetSignServer;
import com.cssweb.payment.bank.BankOrder;
import com.cssweb.payment.bank.BankOrderAction;
import com.cssweb.util.OrderID;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenhf on 2014/9/2.
 */
public class BankCommB2COrder implements BankOrderAction{
    private static final  String SEP = "|";

    // 消息版本号
    private String interfaceVersion;
    // 商户客户号（商户编号）
    private String merID;
    // 订单号
    private String orderid;
    // 商户订单日期
    private String orderDate;
    // 商户订单时间
    private String orderTime;
    // 交易类别
    private String tranType;
    // 订单金额
    private String amount;
    // 订单币种
    private String curType;
    // 订单内容
    private String orderContent;
    // 商家备注
    private String orderMono;
    // 物流配送标志
    private String phdFlag;
    // 通知方式
    private String notifyType;
    // 主动通知URL
    private String merURL;
    // 取货URL
    private String goodsURL;
    // 自动跳转时间
    private String jumpSeconds;
    // 商户批次号
    private String payBatchNo;
    // 二级商户名称
    private String proxyMerName;
    // 二级商户类型
    private String proxyMerType;

    public String getInterfaceVersion() {
        return interfaceVersion;
    }

    public void setInterfaceVersion(String interfaceVersion) {
        this.interfaceVersion = interfaceVersion;
    }

    public String getMerID() {
        return merID;
    }

    public void setMerID(String merID) {
        this.merID = merID;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
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

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurType() {
        return curType;
    }

    public void setCurType(String curType) {
        this.curType = curType;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public String getOrderMono() {
        return orderMono;
    }

    public void setOrderMono(String orderMono) {
        this.orderMono = orderMono;
    }

    public String getPhdFlag() {
        return phdFlag;
    }

    public void setPhdFlag(String phdFlag) {
        this.phdFlag = phdFlag;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getMerURL() {
        return merURL;
    }

    public void setMerURL(String merURL) {
        this.merURL = merURL;
    }

    public String getGoodsURL() {
        return goodsURL;
    }

    public void setGoodsURL(String goodsURL) {
        this.goodsURL = goodsURL;
    }

    public String getJumpSeconds() {
        return jumpSeconds;
    }

    public void setJumpSeconds(String jumpSeconds) {
        this.jumpSeconds = jumpSeconds;
    }

    public String getPayBatchNo() {
        return payBatchNo;
    }

    public void setPayBatchNo(String payBatchNo) {
        this.payBatchNo = payBatchNo;
    }

    public String getProxyMerName() {
        return proxyMerName;
    }

    public void setProxyMerName(String proxyMerName) {
        this.proxyMerName = proxyMerName;
    }

    public String getProxyMerType() {
        return proxyMerType;
    }

    public void setProxyMerType(String proxyMerType) {
        this.proxyMerType = proxyMerType;
    }

    public String getProxyMerCredentials() {
        return proxyMerCredentials;
    }

    public void setProxyMerCredentials(String proxyMerCredentials) {
        this.proxyMerCredentials = proxyMerCredentials;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getMerSignMsg() {
        return merSignMsg;
    }

    public void setMerSignMsg(String merSignMsg) {
        this.merSignMsg = merSignMsg;
    }

    // 二级商户号码
    private String proxyMerCredentials;
    // 渠道编号
    private String netType;
    // 商家签名
    private String merSignMsg;


    public BankCommB2COrder()
    {

    }

    @Override
    public String sign() {
        /*
        生成签名数据，以及表单数据
         */
        StringBuffer sb = new StringBuffer();
        sb.append(interfaceVersion);
        sb.append(SEP);
        sb.append(merID);
        sb.append(SEP);
        sb.append(orderid);
        sb.append(SEP);
        sb.append(orderDate);
        sb.append(SEP);
        sb.append(orderTime);
        sb.append(SEP);
        sb.append(tranType);
        sb.append(SEP);
        sb.append(amount);
        sb.append(SEP);
        sb.append(curType);
        sb.append(SEP);
        sb.append(orderContent);
        sb.append(SEP);
        sb.append(orderMono);
        sb.append(SEP);
        sb.append(phdFlag);
        sb.append(SEP);
        sb.append(notifyType);
        sb.append(SEP);
        sb.append(merURL);
        sb.append(SEP);
        sb.append(goodsURL);
        sb.append(SEP);
        sb.append(jumpSeconds);
        sb.append(SEP);
        sb.append(payBatchNo);
        sb.append(SEP);
        sb.append(proxyMerName);
        sb.append(SEP);
        sb.append(proxyMerType);
        sb.append(SEP);
        sb.append(proxyMerCredentials);
        sb.append(SEP);
        sb.append(netType);
        String msg = sb.toString();



        NetSignServer sign = new NetSignServer();
        String merchantNo = "";
        try {
            sign.NSSetPlainText(msg.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] signData = sign.NSDetachedSign(merchantNo);


        return null;
    }

    @Override
    public boolean verify() {
        return false;
    }

    public static void main(String[] args)
    {
        BOCOMB2CClient client = new BOCOMB2CClient();
        int ret = client.initialize("E:/payment/BankService/src/main/resources/BankComm/B2C/B2CMerchant.xml");

        if (ret != 0)
        {
            System.out.println("初始化失败");
            System.out.println(client.getLastErr());
            return ;
        }


        String merchantNo = "";

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderDate = sdf.format(now);
        String date = orderDate.substring(0, 8);
        String time = orderDate.substring(8);

        BankCommB2COrder order = new BankCommB2COrder();
        order.setInterfaceVersion("1.00");
        order.setMerID("");
        order.setOrderid(orderDate + OrderID.getRandOrderId());
        order.setOrderDate(date);
        order.setOrderTime(time);
        order.setTranType("0");
        order.setAmount("100.00");
        order.setCurType("CNY");
        order.setNotifyType("0");
        order.setNetType("0");

        order.sign();
    }

}
