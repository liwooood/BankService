package com.cssweb.payment.bank.citic.B2B;

import com.lsy.baselib.crypto.exception.ECCryptoProcessorException;
import com.lsy.baselib.crypto.processor.ECCryptoProcessor;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenhf on 2014/8/3.
 */
@XStreamAlias("stream")
public class B2BOrder {
    private String VERSION;
    private String BIZCODE;

    private String MCTNO;
    private String NTFTYPE;
    private String RSPTYPE;
    private String PCBURL;
    private String SCBURL;
    private String TRANPERIOD;

    @XStreamAlias("list")
    private TranList tranList;

    public TranList getTranList() {
        return tranList;
    }

    public void setTranList(TranList tranList) {
        this.tranList = tranList;
    }

    public String getVERSION() {
        return VERSION;
    }

    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }

    public String getBIZCODE() {
        return BIZCODE;
    }

    public void setBIZCODE(String BIZCODE) {
        this.BIZCODE = BIZCODE;
    }

    public String getMCTNO() {
        return MCTNO;
    }

    public void setMCTNO(String MCTNO) {
        this.MCTNO = MCTNO;
    }

    public String getNTFTYPE() {
        return NTFTYPE;
    }

    public void setNTFTYPE(String NTFTYPE) {
        this.NTFTYPE = NTFTYPE;
    }

    public String getRSPTYPE() {
        return RSPTYPE;
    }

    public void setRSPTYPE(String RSPTYPE) {
        this.RSPTYPE = RSPTYPE;
    }

    public String getPCBURL() {
        return PCBURL;
    }

    public void setPCBURL(String PCBURL) {
        this.PCBURL = PCBURL;
    }

    public String getSCBURL() {
        return SCBURL;
    }

    public void setSCBURL(String SCBURL) {
        this.SCBURL = SCBURL;
    }

    public String getTRANPERIOD() {
        return TRANPERIOD;
    }

    public void setTRANPERIOD(String TRANPERIOD) {
        this.TRANPERIOD = TRANPERIOD;
    }




    public static void main(String[] args)
    {
        /*
        XStream xstream = new XStream(new StaxDriver());
        //XStream xstream = new XStream(new DomDriver());
       // xstream.
        xstream.autodetectAnnotations(true);

        Tran tran = new Tran();
        tran.setMCTJNLNO("000001"); // 商户交易流水号， 20位
        tran.setBSNNO("00"); //业务编号
        tran.setPAYEEACCNO("7111010182600198774"); // 收款账号
        tran.setCRYCODE("CNY"); // 币种代码
        tran.setPURPOSE("b2b trade"); // 备注
        tran.setORDERNO("000001"); // 订单编号，长度20

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderTime = sdf.format(now);
        System.out.println("订单时间："+ orderTime);


        tran.setORDERTIME(orderTime);
        tran.setMCDNAME("intel cpu"); // 商品名称
        tran.setMCDTYPE("00"); // 商品类别
        tran.setORDERDESC("buy ic"); // 订单描述
        tran.setORDERAMT("100.01"); // 订单金额
        tran.setBERNAME("test customer"); // 订单人
        tran.setORDERDESC("test customer"); // 订单来源



        List<Tran> rows = new ArrayList<Tran>();
        rows.add(tran);

        TranList tranlist = new TranList();
        tranlist.setName("TRANLIST");
        tranlist.setList(rows);

        B2BOrder order = new B2BOrder();
        order.setVERSION("3.0");
        order.setBIZCODE("ECGTPODR"); // 交易号
        order.setMCTNO("10001469"); // 商户编号
        order.setNTFTYPE("1"); // 通知类型
        order.setRSPTYPE("0"); // 应答类型
        order.setPCBURL("http://cashier.cecpay.com/notify"); //页面通知地址URL
        order.setSCBURL("http://cashier.cecpay.com/notify"); // 后台通知地址URL
        order.setTRANPERIOD("120");// 交易确认有效期，单位为分钟

        order.setTranList(tranlist);



        String xml = xstream.toXML(order);
        System.out.println(xml);

*/
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<stream>");
        sb.append("<VERSION>");
        sb.append("3.0");
        sb.append("</VERSION>");

        sb.append("<BIZCODE>");
        sb.append("ECGTPODR");
        sb.append("</BIZCODE>");

        sb.append("<MCTNO>");
        sb.append("10001469");
        sb.append("</MCTNO>");

        sb.append("<NTFTYPE>");
        sb.append("1");
        sb.append("</NTFTYPE>");

        sb.append("<RSPTYPE>");
        sb.append("0");
        sb.append("</RSPTYPE>");

        sb.append("<PCBURL>");
        sb.append("http://cashier.cecpay.com/notify");
        sb.append("</PCBURL>");

        sb.append("<SCBURL>");
        sb.append("http://cashier.cecpay.com/notify");
        sb.append("</SCBURL>");

        sb.append("<TRANPERIOD>");
        sb.append("120");
        sb.append("</TRANPERIOD>");

        sb.append("<list name=\"TRANLIST\">");
        sb.append("<row>");

        // 每次要加1
        sb.append("<MCTJNLNO>");
        sb.append("000001002");
        sb.append("</MCTJNLNO>");

        sb.append("<BSNNO>");
        sb.append("00");
        sb.append("</BSNNO>");

        sb.append("<PAYEEACCNO>");
        sb.append("7111010182600198774");
        sb.append("</PAYEEACCNO>");

        sb.append("<CRYCODE>");
        sb.append("CNY");
        sb.append("</CRYCODE>");

        sb.append("<PURPOSE>");
        sb.append("b2b trade");
        sb.append("</PURPOSE>");

        sb.append("<ORDERNO>");
        sb.append("000001");
        sb.append("</ORDERNO>");

        String orderTime = "";
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        orderTime = sdf.format(now);

        sb.append("<ORDERTIME>");
        sb.append(orderTime);
        sb.append("</ORDERTIME>");

        sb.append("<MCDNAME>");
        sb.append("intel cpu");
        sb.append("</MCDNAME>");

        sb.append("<MCDTYPE>");
        sb.append("00");
        sb.append("</MCDTYPE>");

        sb.append("<ORDERDESC>");
        sb.append("test customer");
        sb.append("</ORDERDESC>");

        sb.append("<ORDERAMT>");
        sb.append("100.01");
        sb.append("</ORDERAMT>");

        sb.append("<BERNAME>");
        sb.append("test customer");
        sb.append("</BERNAME>");

        sb.append("<ORDERSRC>");
        sb.append("test customer");
        sb.append("</ORDERSRC>");

        sb.append("</row>");
        sb.append("</list>");
        sb.append("</stream>");
        String xml = sb.toString();
        System.out.println(xml);

        ECCryptoProcessor processor	= new ECCryptoProcessor();

        String signCert = FileUtil.readFile("e:/payment/bankservice/src/main/resources/citic/b2b/ecclient.cer");

        System.out.println("证书=" + signCert);

        String signPrivateKey = FileUtil.readFile("e:/payment/bankservice/src/main/resources/citic/b2b/ecclient.key");
        System.out.println("私钥=" + signPrivateKey);

        String privateKeyPassword = "vHtOaaY";

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
