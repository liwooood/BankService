package com.cssweb.payment.bank.citic.B2C;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.StaxDriver;

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
        XStream xstream = new XStream(new StaxDriver());
        xstream.autodetectAnnotations(true);

        B2COrder order = new B2COrder();
        order.setE3RDPAYNO("111");

        String xml = xstream.toXML(order);
        System.out.println(xml);

        System.out.println("你好");

    }

}
