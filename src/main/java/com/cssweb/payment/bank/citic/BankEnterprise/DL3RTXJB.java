package com.cssweb.payment.bank.CITIC.BankEnterprise;

import com.cssweb.network.HttpClient;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenhf on 2014/8/13.
 */
@XStreamAlias("stream")
public class DL3RTXJB {
    private static final String CHARSET = "GB2312";

    private String action;
    private String userName;
    private String accountNo;
    private String clientID;
    @XStreamAlias("list")
    private DL3RTXJBList list;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public DL3RTXJBList getList() {
        return list;
    }

    public void setList(DL3RTXJBList list) {
        this.list = list;
    }

    public static void main(String[] args)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\""  + CHARSET + "\"?>\r\n");

        XStream xstream = new XStream(new DomDriver(CHARSET));
        xstream.autodetectAnnotations(true);


        List<DL3RTXJBRow> rows = new ArrayList<DL3RTXJBRow>();

        DL3RTXJBRow row = new DL3RTXJBRow();
        Date row1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String cashFlwNo = sdf.format(row1);
        row.setCashFlwNo(cashFlwNo);
        row.setRcvAccNo("6226900719520153");
        row.setRcvAccNm("电商测试");
        row.setTranAmount("100.01");
        row.setAbs("提现");
        row.setMemo("提现");
        rows.add(row);

        DL3RTXJBList list = new DL3RTXJBList();
        list.setName("userDataList");
        list.setList(rows);

        DL3RTXJB searchAccountBalance = new DL3RTXJB();
        searchAccountBalance.setAction("DL3RTXJB");
        searchAccountBalance.setUserName("zrzl");
        searchAccountBalance.setAccountNo("7111010182600198774");
        Date now = new Date();
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String clientID = sdf.format(now);
        searchAccountBalance.setClientID(clientID);
        searchAccountBalance.setList(list);

        String body = xstream.toXML(searchAccountBalance);
        sb.append(body);

        String xml = sb.toString();

        //Pattern p = Pattern.compile("\\s*|\r|\n");
        //Matcher m = p.matcher(xml);
        //xml = m.replaceAll("");
        //xml = xml.replaceAll("\\s*|\r|\n", "");

        System.out.println(xml);

        String bankEnterpriseServer = "http://172.16.2.155:6789";
        //String bankEnterpriseServer = "http://127.0.0.1:8084/B2EC/E2BServlet";
        //String bankEnterpriseServer = "http://127.0.0.1:6789";

        HttpClient http = new HttpClient(bankEnterpriseServer);
        String response = http.postRequest2(xml);
        System.out.println("response = " + response);

    }

}
