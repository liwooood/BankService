package com.cssweb.payment.bank.BOC.BankEnterprise;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhf on 2014/6/22.
 */
public class XMLGen {
    private static final String VERSION = "120";
    private static final String SECURITY = "true";
    private static final String LANG = "chs";

    private static final String CUST_ID = "25372371";
    private static final String CUST_OPERATOR = "25929693";
    private static final String OPER_PASSWORD = "m37LXptg";
    private static final String BANK_GATEWAY= "E172016001030";

    //private String bankGateway = "http://172.16.1.30:8080/B2EC/E2BServlet";
    private String bankGateway = "http://180.168.7.162:8080/B2EC/E2BServlet";
    //private String bankGateway = "http://127.0.0.1:8080/B2EC/E2BServlet";
    private static final int CONNECT_TIMEOUT = 10;
    private static final int READ_WRITE_TIMEOUT = 10;

    private static final String CHARSET = "UTF-8";



    private String accessToken = "";

    public boolean login() {
        boolean ret = false;

        String trncod = "b2e0001";

        try
        {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            doc.setXmlStandalone(true);

            Element root = doc.createElement("bocb2e");
            doc.appendChild(root);
            root.setAttribute("version", VERSION);
            root.setAttribute("security", SECURITY);
            root.setAttribute("lang", LANG);

            createHead(doc, root, BANK_GATEWAY, "", CUST_ID, CUST_OPERATOR, trncod, accessToken);

            // trans
            Element trans = createElement(doc, root, "trans", null);

            Element trnrq = createElement(doc, trans, "trn-b2e0001-rq", null);

            Element rq = createElement(doc, trnrq, "b2e0001-rq", null);

            createElement(doc, rq, "custdt", "20060704091000");


           createElement(doc, rq, "oprpwd", OPER_PASSWORD);

            String request = convertDocToString(doc);
            System.out.println("request = " + request);



            BasicResponse basicResponse = postRequest(request);
            if (basicResponse.getRetCode() == 0) {

                ret = false;
                return ret;
            }

           // System.out.println("response = " + basicResponse.getRetMsg());

            // ????????????????
            //xmlStr = new String(xmlResponse.getBytes(),"gb2312");
            StringReader sr = new StringReader(basicResponse.getRetMsg());
            InputSource is = new InputSource(sr);

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();


            Document docResponse = builder.parse(is);

            XPath xpath = XPathFactory.newInstance().newXPath();

            XPathExpression expRspCod = xpath.compile("//bocb2e/trans/trn-b2e0001-rs/status/rspcod/text()");
            Object objRspCod = expRspCod.evaluate(docResponse, XPathConstants.STRING);

            XPathExpression expRspMsg = xpath.compile("//bocb2e/trans/trn-b2e0001-rs/status/rspmsg/text()");
            Object objRspMsg = expRspMsg.evaluate(docResponse, XPathConstants.STRING);
            String rspmsg = (String) objRspMsg;

            if (!rspmsg.equalsIgnoreCase("ok")) {
                return false;

            }

            XPathExpression expToken = xpath.compile("//bocb2e/trans/trn-b2e0001-rs/token/text()");
            Object objToken = expToken.evaluate(docResponse, XPathConstants.STRING);
            accessToken = (String) objToken;
            System.out.println("token = " + accessToken);

            ret = true;

        } catch (SAXException e) {
                    e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


        return ret;
    }

    public void logout()
    {
        accessToken = "";
    }



    private BasicResponse postRequest(String request)  {
        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setRetCode(0);
        basicResponse.setRetMsg("");

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000).build();

        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
//        httpClient.getParams().setParameter("http.protocol.content-charset", HTTP.UTF_8);
//        httpClient.getParams().setParameter(HTTP.CONTENT_ENCODING, HTTP.UTF_8);
//        httpClient.getParams().setParameter(HTTP.CHARSET_PARAM, HTTP.UTF_8);
//        httpClient.getParams().setParameter(HTTP.DEFAULT_PROTOCOL_CHARSET, HTTP.UTF_8);

        try {

            HttpPost httpPost = new HttpPost(bankGateway);
            //httpPost.getParams().setParameter("http.protocol.content-charset", HTTP.UTF_8);
            //httpPost.getParams().setParameter(HTTP.CONTENT_ENCODING, HTTP.UTF_8);
           // httpPost.getParams().setParameter(HTTP.CHARSET_PARAM, HTTP.UTF_8);
           // httpPost.getParams().setParameter(HTTP.DEFAULT_PROTOCOL_CHARSET, HTTP.UTF_8);

            StringEntity reqEntity = null;

            //String utf8Request = getUTF8XMLString(request);
            reqEntity = new StringEntity(request, CHARSET);



           // reqEntity.setContentType("application/xmlstream");
            reqEntity.setContentType("text/xml;charset=" + CHARSET);
            reqEntity.setContentEncoding(CHARSET);





            httpPost.setEntity(reqEntity);

            httpPost.setHeader("Content-Type", "text/xml;charset=" + CHARSET);

            //String temp = EntityUtils.toString(reqEntity);
            //System.out.println("temp=" + temp);
           // System.out.println("Executing request: " + httpPost.getRequestLine());

            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

            try {
               // System.out.println(httpResponse.getStatusLine());

                int statusCode = httpResponse.getStatusLine().getStatusCode();

                if (statusCode != 200) {
                    basicResponse.setRetCode(0);
                    basicResponse.setRetMsg(String.valueOf(statusCode));
                }

                HttpEntity resEntity = httpResponse.getEntity();
                String response = EntityUtils.toString(resEntity);

                basicResponse.setRetMsg(response);

                EntityUtils.consume(resEntity);
                //System.out.println("response = " + response);
                basicResponse.setRetCode(1);

            }
            finally
            {
                httpResponse.close();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return basicResponse;
    }

    private BasicResponse postRequest2(String request)  {
        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setRetCode(0);
        basicResponse.setRetMsg("");


        try {
            URL url = new URL(bankGateway);


            HttpURLConnection  con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "text/xml;charset=" + CHARSET);
/*
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");

            osw.write(request.toString());
            osw.flush();
            osw.close();
            */


            OutputStream out = con.getOutputStream();
            out.write(request.getBytes(CHARSET));


            StringBuffer buffer = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(con
                    .getInputStream(), CHARSET));
            String temp;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
                buffer.append("\n");
            }

            String response = buffer.toString();
            //System.out.println("response = " + response);

                basicResponse.setRetMsg(response);


                basicResponse.setRetCode(1);



        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

        return basicResponse;
    }

    /**
     * @Title
     * @Description
     * @param ibknum ???§Ü?
     * @param actacn ???
     * @return BalanceResponse
     * @throws
     */
    public BalanceResponse getBalance(String ibknum, String actacn)
    {
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setRetCode(0);
        balanceResponse.setRetMsg("");

        String trncod = "b2e0005";

        try
        {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            doc.setXmlStandalone(true);

            Element root = doc.createElement("bocb2e");
            doc.appendChild(root);
            root.setAttribute("version", VERSION);
            root.setAttribute("security", SECURITY);
            root.setAttribute("lang", LANG);


            createHead(doc, root, BANK_GATEWAY, "", CUST_ID, CUST_OPERATOR, trncod, accessToken);

            // trans
            Element trans = createElement(doc, root, "trans", null);

            Element trnrq = createElement(doc, trans, "trn-b2e0005-rq", null);

            Element rq = createElement(doc, trnrq, "b2e0005-rq", null);

            Element account = createElement(doc, rq, "account", null);


            createElement(doc, account, "ibknum", ibknum);


            createElement(doc, account, "actacn", actacn);



            String request = convertDocToString(doc);
            //System.out.println("request = " + request);



            BasicResponse basicResponse = postRequest(request);
            if (basicResponse.getRetCode() == 0) {


                return balanceResponse;
            }

            System.out.println("response = " + basicResponse.getRetMsg());

            // ????????????????
            //xmlStr = new String(xmlResponse.getBytes(),"gb2312");
            StringReader sr = new StringReader(basicResponse.getRetMsg());
            InputSource is = new InputSource(sr);

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();


            Document docResponse = builder.parse(is);

            XPath xpath = XPathFactory.newInstance().newXPath();

            XPathExpression expRspCod = xpath.compile("//bocb2e/trans/trn-b2e0001-rs/status/rspcod/text()");
            Object objRspCod = expRspCod.evaluate(docResponse, XPathConstants.STRING);

            XPathExpression expRspMsg = xpath.compile("//bocb2e/trans/trn-b2e0001-rs/status/rspmsg/text()");
            Object objRspMsg = expRspMsg.evaluate(docResponse, XPathConstants.STRING);
            String rspmsg = (String) objRspMsg;

            if (!rspmsg.equalsIgnoreCase("ok")) {

                return balanceResponse;

            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return balanceResponse;
    }

    /**
     * @param insid ???ID
     */
    public TransferResponse transfer(String insid, String fribkn, String actacn, String actnam, String toibkn, String toactacn, String toname, String tobknm, BigDecimal trnamt, String priolv, String cuspriolv, String furinfo, String trfdate, String comacn, String bocflag)
    {
        TransferResponse transferResponse = new TransferResponse();
        transferResponse.setRetCode(0);
        transferResponse.setRetMsg("");

        String trncod = "b2e0061";

        try
        {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            doc.setXmlStandalone(true);

            Element root = doc.createElement("bocb2e");
            doc.appendChild(root);
            root.setAttribute("version", VERSION);
            root.setAttribute("security", SECURITY);
            root.setAttribute("locale", "zh_CN");


           createHead(doc, root, BANK_GATEWAY, "", CUST_ID, CUST_OPERATOR, trncod, accessToken);


            // trans
            Element trans = createElement(doc, root, "trans", null);

            Element trnrq = createElement(doc, trans, "trn-b2e0061-rq", null);

            //Element ceitinfo = doc.createElement("ceitinfo");
            // ???????????????????????

            createElement(doc, trnrq, "transtype", null);


            Element rq = createElement(doc, trnrq, "b2e0061-rq", null);

            //???ID
            createElement(doc, rq, "insid", insid);


            //???????????
            createElement(doc, rq, "obssid", null);




            //?????????
            Element fractn = createElement(doc, rq, "fractn", null);

            // ???§Ü?
            createElement(doc, fractn, "fribkn", fribkn);


            // ???????
            createElement(doc, fractn, "actacn", actacn);


            // ?????????
            //String actnam_utf8 = new String(actnam.getBytes(), CHARSET);

            createElement(doc, fractn, "actnam", actnam);





            //???????????
            Element toactn = createElement(doc, rq, "toactn", null);


            // ????????§Ü?
            createElement(doc, toactn, "toibkn", toibkn);


            // ??????????
            // 119??????
            createElement(doc, toactn, "acttyp", "119");


            // ??????
            createElement(doc, toactn, "actacn", actacn);


            // ????????
            //String toname_utf8 = new String(toname.getBytes(), CHARSET);
            createElement(doc, toactn, "toname", toname);


            // ?????????????
            //String tobknm_utf8 = new String(tobknm.getBytes(), CHARSET);
            createElement(doc, toactn, "tobknm", tobknm);


            // ???????
            createElement(doc, toactn, "toaddr", null);




            // ?????
            createElement(doc, rq, "trnamt", trnamt.toString());


            // ??????
            createElement(doc, rq, "trncur", "CNY");


            // ???§Õ????????
            createElement(doc, rq, "priolv", priolv);


            // ????????????
            createElement(doc, rq, "cuspriolv", cuspriolv);


            // ???
            //createElement(doc, rq, "furinfo", furinfo);

            //String furinfo_utf8 = new String(furinfo.getBytes(), CHARSET);
            createElement(doc, rq, "furinfo", furinfo);

            // ???????????
            createElement(doc, rq, "trfdate", trfdate);


            // ??????????
            createElement(doc, rq, "comacn", comacn);


            // ???????????????
            createElement(doc, rq, "bocflag", bocflag);




            String request = convertDocToString(doc);
            System.out.println(request);

            BasicResponse basicResponse = postRequest2(request);
            if (basicResponse.getRetCode() == 0) {


                return transferResponse;
            }

            System.out.println("response = " + basicResponse.getRetMsg());

            // ????????????????
            //xmlStr = new String(xmlResponse.getBytes(),"gb2312");
            StringReader sr = new StringReader(basicResponse.getRetMsg());
            InputSource is = new InputSource(sr);

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();


            Document docResponse = builder.parse(is);

            XPath xpath = XPathFactory.newInstance().newXPath();

            XPathExpression expRspCod = xpath.compile("//bocb2e/trans/trn-b2e0001-rs/status/rspcod/text()");
            Object objRspCod = expRspCod.evaluate(docResponse, XPathConstants.STRING);

            XPathExpression expRspMsg = xpath.compile("//bocb2e/trans/trn-b2e0001-rs/status/rspmsg/text()");
            Object objRspMsg = expRspMsg.evaluate(docResponse, XPathConstants.STRING);
            String rspmsg = (String) objRspMsg;

            if (!rspmsg.equalsIgnoreCase("ok")) {

                return transferResponse;

            }



        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transferResponse;
    }

    public TransferResponse daifa(String insid, String fribkn, String actacn, BigDecimal pybamt, int pybnum, String trfdate, String toibkn, String toactn, BigDecimal pydamt, String toname)
    {
        TransferResponse transferResponse = new TransferResponse();
        transferResponse.setRetCode(0);
        transferResponse.setRetMsg("");

        String trncod = "b2e0078";

        try
        {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            doc.setXmlStandalone(true);

            Element root = doc.createElement("bocb2e");
            doc.appendChild(root);
            root.setAttribute("version", VERSION);
            root.setAttribute("security", SECURITY);
            root.setAttribute("locale", "zh_CN");


            createHead(doc, root, BANK_GATEWAY, "", CUST_ID, CUST_OPERATOR, trncod, accessToken);


            // trans
            Element trans = createElement(doc, root, "trans", null);

            Element trnrq = createElement(doc, trans, "trn-b2e0078-rq", null);

            //Element ceitinfo = doc.createElement("ceitinfo");
            // ???????????????????????

            createElement(doc, trnrq, "transtype", null);


            Element rq = createElement(doc, trnrq, "b2e0078-rq", null);

            //???ID
            createElement(doc, rq, "insid", insid);


            //?????????
            Element fractn = createElement(doc, rq, "fractn", null);

            // ???§Ü?
            createElement(doc, fractn, "fribkn", fribkn);


            // ???????
            createElement(doc, fractn, "actacn", actacn);


            // ?????????
            createElement(doc, fractn, "actnam", null);

            // ????
            createElement(doc, rq, "pybcur", "CNY");

            // ??????
            createElement(doc, rq, "pybamt", pybamt.toString());

            // ???????
            createElement(doc, rq, "pybnum", String.valueOf(pybnum));


            // ??????

            createElement(doc, rq, "crdtyp", "7");

            // ??
            createElement(doc, rq, "furinfo", "E1");

            // ???
            createElement(doc, rq, "useinf", null);


            // ???????????
            createElement(doc, rq, "trfdate", trfdate);

            // ?????????
            Element detail = createElement(doc, rq, "detail", null);

// ??????????§Ü?/?????§Ò??
            createElement(doc, detail, "toibkn", toibkn);

            // ???????
            createElement(doc, detail, "tobank", null);


            // ??????
            createElement(doc, detail, "toactn", toactn);


            // ????
            createElement(doc, detail, "pydcur", "CNY");

            // ????
            createElement(doc, detail, "pydamt", pydamt.toString());


            // ????????
            createElement(doc, detail, "toname", toname);


            // ????????????
            createElement(doc, detail, "toidtp", null);


            // ??????????
            createElement(doc, detail, "toidet", null);

            // ???
            createElement(doc, detail, "furinfo", null);


            createElement(doc, detail, "reserve1", null);
            createElement(doc, detail, "reserve2", null);
            createElement(doc, detail, "reserve3", null);
            createElement(doc, detail, "reserve4", null);




            String request = convertDocToString(doc);
            System.out.println(request);

            BasicResponse basicResponse = postRequest2(request);
            if (basicResponse.getRetCode() == 0) {


                return transferResponse;
            }

            System.out.println("response = " + basicResponse.getRetMsg());

            // ????????????????
            //xmlStr = new String(xmlResponse.getBytes(),"gb2312");
            StringReader sr = new StringReader(basicResponse.getRetMsg());
            InputSource is = new InputSource(sr);

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();


            Document docResponse = builder.parse(is);

            XPath xpath = XPathFactory.newInstance().newXPath();

            XPathExpression expRspCod = xpath.compile("//bocb2e/trans/trn-b2e0001-rs/status/rspcod/text()");
            Object objRspCod = expRspCod.evaluate(docResponse, XPathConstants.STRING);

            XPathExpression expRspMsg = xpath.compile("//bocb2e/trans/trn-b2e0001-rs/status/rspmsg/text()");
            Object objRspMsg = expRspMsg.evaluate(docResponse, XPathConstants.STRING);
            String rspmsg = (String) objRspMsg;

            if (!rspmsg.equalsIgnoreCase("ok")) {

                return transferResponse;

            }



        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transferResponse;
    }

    public String convertDocToString(Document doc) throws TransformerException {
        String result = "";

        DOMSource domSource = new DOMSource(doc);

        StringWriter sw = new StringWriter();
        StreamResult streamResult = new StreamResult(sw);


        Transformer transFormer = TransformerFactory.newInstance().newTransformer();

        transFormer.setOutputProperty(OutputKeys.ENCODING, CHARSET);
        transFormer.setOutputProperty(OutputKeys.INDENT,"yes");



        transFormer.transform(domSource, streamResult);

        result = sw.toString();
        return result;
    }

    public void createHead(Document doc, Element parent, String termid, String trnid, String custid, String cusopr, String trncod, String token)
    {
        Element head = createElement(doc, null, "head", null);


        // ????????????
        createElement(doc, head, "termid", termid);


        // ??????????????
        createElement(doc, head, "trnid", trnid);


        // ???????????????????????
        createElement(doc, head, "custid", custid);


        // ????????????
        createElement(doc, head, "cusopr", cusopr);


        // ???????
        createElement(doc, head, "trncod", trncod);


        //???????????????????????????
        createElement(doc, head, "token", token);

        parent.appendChild(head);

    }

    public Element createElement(Document doc, Element parent, String name, String value)
    {
        Element childElement = doc.createElement(name);

        if (value != null)
            childElement.setTextContent(value);

        if (parent != null)
            parent.appendChild(childElement);

        return childElement;
    }

    public void genXML()
    {

        XStream xstream = new XStream(new StaxDriver());

        xstream.autodetectAnnotations(true);



        Status status1 = new Status();
        status1.setRspcod("1");
        status1.setRspmsg("msg");

        Status status2 = new Status();
        status2.setRspcod("1");
        status2.setRspmsg("msg");

        B2e0078rs b2e0078rs1 = new B2e0078rs();
        b2e0078rs1.setStatus(status1);
        b2e0078rs1.setInsid("000001");
        b2e0078rs1.setObssid("000002");

        List<B2e0078rs> list = new ArrayList<B2e0078rs>();
        list.add(b2e0078rs1);

        B2e0078rs b2e0078rs2 = new B2e0078rs();
        b2e0078rs2.setStatus(status2);
        b2e0078rs2.setInsid("000001");
        b2e0078rs2.setObssid("000002");
        list.add(b2e0078rs2);

        Trnb2e0078rs trnb2e0078rs = new Trnb2e0078rs();
        Status status3 = new Status();

        status3.setRspcod("1");
        status3.setRspmsg("msg");
        trnb2e0078rs.setStatus(status3);
        trnb2e0078rs.setB2e0078rs(list);

        Trans trans = new Trans();
        trans.setTrnb2e0078rs(trnb2e0078rs);

        Head head = new Head();
        head.setTermid("termid");

        DaifaResponse  res = new DaifaResponse();

        res.setHead(head);
        res.setTrans(trans);

        String xml = xstream.toXML(res);
        System.out.println(xml);

        DaifaResponse res2 =  (DaifaResponse) xstream.fromXML(xml);
        System.out.println("finished");

    }


    public static void main(String args[])
    {


        XMLGen test = new XMLGen();
        test.genXML();




       test.login();

/*
        test.getBalance("40142", "319456082671");


        BigDecimal tranAmount = new BigDecimal("1.00");

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String tranDate = sdf.format(now);

        //test.transfer("000000000001", "40142", "319456082671", "??????", null, "6213320100000000103", "??????", "????????", tranAmount, "0", "0", "????",tranDate, null, "1");
        test.daifa("000000000002", "40142", "319456082671", tranAmount, 1, tranDate, "11", "6213320100000000103", tranAmount, "??????");

        test.getBalance("40142", "319456082671");
*/
    }
}
