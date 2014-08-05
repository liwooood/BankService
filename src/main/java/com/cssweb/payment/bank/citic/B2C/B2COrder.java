package com.cssweb.payment.bank.citic.B2C;


import com.cssweb.payment.bank.citic.B2B.FileUtil;
import com.lsy.baselib.crypto.exception.ECCryptoProcessorException;
import com.lsy.baselib.crypto.processor.ECCryptoProcessor;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenhf on 2014/8/3.
 */
@XStreamAlias("stream")
public class B2COrder {
    private String E3RDPAYNO;
    private String ORDERMODE;
    private String ORDERDATE;
    private String ORDERTIME;
    private String ORDERNO;
    private String CURRID;
    private String ORDERAMT;
    private String MEMO;
    private String NOTIFYMODE;
    private String NOTIFYURL;
    private String RISKLEVEL;
    private String SUPPTCARDTYPE;
    private String TTL;
    private String MEMBERID;
    private String NOTIFYSCOPE;
    private String ISSAFEINF;
    private String REFERER;

    public String getE3RDPAYNO() {
        return E3RDPAYNO;
    }

    public void setE3RDPAYNO(String e3RDPAYNO) {
        E3RDPAYNO = e3RDPAYNO;
    }

    public String getORDERMODE() {
        return ORDERMODE;
    }

    public void setORDERMODE(String ORDERMODE) {
        this.ORDERMODE = ORDERMODE;
    }

    public String getORDERDATE() {
        return ORDERDATE;
    }

    public void setORDERDATE(String ORDERDATE) {
        this.ORDERDATE = ORDERDATE;
    }

    public String getORDERTIME() {
        return ORDERTIME;
    }

    public void setORDERTIME(String ORDERTIME) {
        this.ORDERTIME = ORDERTIME;
    }

    public String getORDERNO() {
        return ORDERNO;
    }

    public void setORDERNO(String ORDERNO) {
        this.ORDERNO = ORDERNO;
    }

    public String getCURRID() {
        return CURRID;
    }

    public void setCURRID(String CURRID) {
        this.CURRID = CURRID;
    }

    public String getORDERAMT() {
        return ORDERAMT;
    }

    public void setORDERAMT(String ORDERAMT) {
        this.ORDERAMT = ORDERAMT;
    }

    public String getMEMO() {
        return MEMO;
    }

    public void setMEMO(String MEMO) {
        this.MEMO = MEMO;
    }

    public String getNOTIFYMODE() {
        return NOTIFYMODE;
    }

    public void setNOTIFYMODE(String NOTIFYMODE) {
        this.NOTIFYMODE = NOTIFYMODE;
    }

    public String getNOTIFYURL() {
        return NOTIFYURL;
    }

    public void setNOTIFYURL(String NOTIFYURL) {
        this.NOTIFYURL = NOTIFYURL;
    }

    public String getRISKLEVEL() {
        return RISKLEVEL;
    }

    public void setRISKLEVEL(String RISKLEVEL) {
        this.RISKLEVEL = RISKLEVEL;
    }

    public String getSUPPTCARDTYPE() {
        return SUPPTCARDTYPE;
    }

    public void setSUPPTCARDTYPE(String SUPPTCARDTYPE) {
        this.SUPPTCARDTYPE = SUPPTCARDTYPE;
    }

    public String getTTL() {
        return TTL;
    }

    public void setTTL(String TTL) {
        this.TTL = TTL;
    }

    public String getMEMBERID() {
        return MEMBERID;
    }

    public void setMEMBERID(String MEMBERID) {
        this.MEMBERID = MEMBERID;
    }

    public String getNOTIFYSCOPE() {
        return NOTIFYSCOPE;
    }

    public void setNOTIFYSCOPE(String NOTIFYSCOPE) {
        this.NOTIFYSCOPE = NOTIFYSCOPE;
    }

    public String getISSAFEINF() {
        return ISSAFEINF;
    }

    public void setISSAFEINF(String ISSAFEINF) {
        this.ISSAFEINF = ISSAFEINF;
    }

    public String getREFERER() {
        return REFERER;
    }

    public void setREFERER(String REFERER) {
        this.REFERER = REFERER;
    }

    public static void main(String[] args)
    {
        /*
        XStream xstream = new XStream(new StaxDriver());
        xstream.autodetectAnnotations(true);

        B2COrder order = new B2COrder();
        order.setE3RDPAYNO("111");

        String xml = xstream.toXML(order);
        System.out.println(xml);

        */
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<stream>");

        sb.append("<E3RDPAYNO>");
        sb.append("100599");
        sb.append("</E3RDPAYNO>");

        sb.append("<ORDERMODE>");
        sb.append("01");
        sb.append("</ORDERMODE>");

        String orderDate = "";
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        orderDate = sdf.format(now);
        sb.append("<ORDERDATE>");
        sb.append(orderDate);
        sb.append("</ORDERDATE>");

        String orderTime = "";

        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        orderTime = sdf2.format(now);
        sb.append("<ORDERTIME>");
        sb.append(orderTime);
        sb.append("</ORDERTIME>");

        sb.append("<ORDERNO>");
        sb.append("0001001");
        sb.append("</ORDERNO>");

        sb.append("<CURRID>");
        sb.append("01");
        sb.append("</CURRID>");

        sb.append("<ORDERAMT>");
        sb.append("100.01");
        sb.append("</ORDERAMT>");

        sb.append("<MEMO>");
        sb.append("");
        sb.append("</MEMO>");

        sb.append("<NOTIFYMODE>");
        sb.append("01");
        sb.append("</NOTIFYMODE>");

        sb.append("<NOTIFYURL>");
        sb.append("http://cashier.cecpay.com/notify");
        sb.append("</NOTIFYURL>");

        sb.append("<RISKLEVEL>");
        sb.append("00");
        sb.append("</RISKLEVEL>");

        sb.append("<SUPPTCARDTYPE>");
        sb.append("00");
        sb.append("</SUPPTCARDTYPE>");

        sb.append("<TTL>");
        sb.append("120");
        sb.append("</TTL>");

        sb.append("<MEMBERID>");
        sb.append("001");
        sb.append("</MEMBERID>");

        sb.append("<NOTIFYSCOPE>");
        sb.append("02");
        sb.append("</NOTIFYSCOPE>");

        sb.append("<ISSAFEINF>");
        sb.append("N");
        sb.append("</ISSAFEINF>");

        sb.append("<REFERER>");
        sb.append("");
        sb.append("</REFERER>");

        sb.append("</stream>");
        String xml = sb.toString();
        System.out.println("order=" + xml);

        ECCryptoProcessor processor	= new ECCryptoProcessor();

        String signCert = FileUtil.readFile("e:/payment/bankservice/src/main/resources/citic/b2c/ecclient.cer");

        System.out.println("证书=" + signCert);

        String signPrivateKey = FileUtil.readFile("e:/payment/bankservice/src/main/resources/citic/b2c/ecclient.key");
        System.out.println("私钥=" + signPrivateKey);

        String privateKeyPassword = "dBacqjp";

        try {
            processor.setSignerCertificate(signCert.getBytes());
            processor.setSignerPrivatekey(signPrivateKey.getBytes(), privateKeyPassword);
            //签名对象的声明和私钥的设置需要耗费一定的时间，建议对上述操作进行全局设置。
            //对明文消息进行签名，得到BASE64编码的密文消息，包含了明文、签名和证书数据

            byte[] signedMessage	= processor.sign(xml.getBytes());
            String signData = new String(signedMessage);
            System.out.println("签名结果=" + signData);

        } catch (ECCryptoProcessorException e) {
            e.printStackTrace();
        }


    }

}
