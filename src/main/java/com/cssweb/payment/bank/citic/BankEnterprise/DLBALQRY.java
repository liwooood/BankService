package com.cssweb.payment.bank.CITIC.BankEnterprise;

import com.cssweb.network.HttpClient;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhf on 2014/8/5.
 */
@XStreamAlias("stream")
public class DLBALQRY {
    private String action;
    private String userName;
    private static final String CHARSET = "GB2312";

    @XStreamAlias("list")
    private DLBALQRYList list;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public DLBALQRYList getList() {
        return list;
    }

    public void setList(DLBALQRYList list) {
        this.list = list;
    }

    public String getUserName() {
        return userName;

    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static void main(String[] args)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\""  + CHARSET + "\"?>\r\n");

        XStream xstream = new XStream(new DomDriver(CHARSET));
        xstream.autodetectAnnotations(true);

        DLBALQRY searchAccountBalance = new DLBALQRY();
        searchAccountBalance.setUserName("zrzl");
        searchAccountBalance.setAction("DLBALQRY");


        List<DLBALQRYRow> rows = new ArrayList<DLBALQRYRow>();
        DLBALQRYRow row = new DLBALQRYRow();
        row.setAccountNo("7111010182600198774");
        rows.add(row);

        DLBALQRYList list = new DLBALQRYList();
        list.setName("userDataList");
        list.setList(rows);

        searchAccountBalance.setList(list);

        String body = xstream.toXML(searchAccountBalance);
        sb.append(body);

        String xml = sb.toString();

        //Pattern p = Pattern.compile("\\s*|\r|\n");
        //Matcher m = p.matcher(xml);
        //xml = m.replaceAll("");
        //xml = xml.replaceAll("\\s*|\r|\n", "");

        System.out.println(xml);

        String bankEnterpriseServer = "http://172.16.3.53:6789";
        //String bankEnterpriseServer = "http://127.0.0.1:8084/B2EC/E2BServlet";
        //String bankEnterpriseServer = "http://127.0.0.1:6789";

        HttpClient http = new HttpClient(bankEnterpriseServer);
        String response = http.postRequest2(xml);
        System.out.println("response = " + response);



    }

}
