package com.cssweb.payment.bank.CMB.BankEnterprise;

import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.Properties;

/**
 * HTTP通讯范例: 直接支付
 *
 * @author 徐蓓
 */
public class HttpRequest {
    /**
     * 生成请求报文
     *
     * @return
     */

    private String toPersonal() {
        // 构造支付的请求报文
        XmlPacket xmlPkt = new XmlPacket("AgentRequest", "银企直连专用集团1");

        Map mpPodInfo = new Properties();
        mpPodInfo.put("BUSCOD", "N03020");
        mpPodInfo.put("BUSMOD", "00001");
        //mpPodInfo.put("C_TRSTYP", "");
        mpPodInfo.put("TRSTYP", "BYBK");
        mpPodInfo.put("DBTACC", "591902896710201");
        mpPodInfo.put("BBKNBR", "59");
        mpPodInfo.put("SUM", "100.00");
        mpPodInfo.put("TOTAL", "1");
        mpPodInfo.put("YURREF", "0000000001"); // 流水号或订单号
        mpPodInfo.put("MEMO", "提现");
        xmlPkt.putProperty("SDKATSRQX", mpPodInfo);

        Map mpPayInfo = new Properties();
        mpPayInfo.put("ACCNBR", "6225885910000066");
        mpPayInfo.put("CLTNAM", "曾亮");
        mpPayInfo.put("TRSAMT", "100.00");
        xmlPkt.putProperty("SDKATDRQX", mpPayInfo);

        return xmlPkt.toXmlString();
    }
    private void toPersonalResult(String result) {
        if (result != null && result.length() > 0) {
            XmlPacket pktRsp = XmlPacket.valueOf(result);
            if (pktRsp != null) {
                String sRetCod = pktRsp.getRETCOD();
                if (sRetCod.equals("0")) {
                    Map propPayResult = pktRsp.getProperty("NTREQNBRY", 0);
                    String sREQNBR = (String) propPayResult.get("REQNBR");

                   /*
                    if (sREQSTS.equals("FIN") && sRTNFLG.equals("F")) {
                        System.out.println("支付失败："
                                + propPayResult.get("ERRTXT"));
                    } else {
                        System.out.println("支付已被银行受理（支付状态：" + sREQSTS + "）");
                    }
                    */
                } else if (sRetCod.equals("-9")) {
                    System.out.println("支付未知异常，请查询支付结果确认支付状态，错误信息："
                            + pktRsp.getERRMSG());
                } else {
                    System.out.println("支付失败：" + pktRsp.getERRMSG());
                }
            } else {
                System.out.println("响应报文解析失败");
            }
        }
    }

    private String searchAccount() {
        // 构造支付的请求报文
        XmlPacket xmlPkt = new XmlPacket("GetAccInfo", "商户41");

        Map mpPodInfo = new Properties();
        mpPodInfo.put("BBKNBR", "59");
        mpPodInfo.put("ACCNBR", "591902897710601");
        xmlPkt.putProperty("SDKACINFX", mpPodInfo);



        return xmlPkt.toXmlString();
    }

    /**
     * 连接前置机，发送请求报文，获得返回报文
     *
     * @param data
     * @return
     * @throws MalformedURLException
     */
    private String sendRequest(String data) {
        String result = "";
        try {
            URL url;
            url = new URL("http://localhost:8080");

            HttpURLConnection conn;
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os;
            os = conn.getOutputStream();
            os.write(data.toString().getBytes("UTF-8"));
            os.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn
                    .getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }

            System.out.println(result);
            br.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 处理返回的结果
     *
     * @param result
     */
    private void searchAccountResult(String result) {
        if (result != null && result.length() > 0) {
            XmlPacket pktRsp = XmlPacket.valueOf(result);
            if (pktRsp != null) {
                String sRetCod = pktRsp.getRETCOD();
                if (sRetCod.equals("0")) {
                    Map propPayResult = pktRsp.getProperty("NTQACINFZ", 0);
                    String sCCYNBR = (String) propPayResult.get("CCYNBR");
                    String sACCITM = (String) propPayResult.get("ACCITM");
                    String sBBKNBR = (String) propPayResult.get("BBKNBR");
                    String sACCNBR = (String) propPayResult.get("ACCNBR");
                    String sACCNAM = (String) propPayResult.get("ACCNAM");
                    String sACCBLV = (String) propPayResult.get("ACCBLV");
                    String sONLBLV = (String) propPayResult.get("ONLBLV");
                    String sSTSCOD = (String) propPayResult.get("STSCOD");
                    String sOPNDAT = (String) propPayResult.get("OPNDAT");
                   /*
                    if (sREQSTS.equals("FIN") && sRTNFLG.equals("F")) {
                        System.out.println("支付失败："
                                + propPayResult.get("ERRTXT"));
                    } else {
                        System.out.println("支付已被银行受理（支付状态：" + sREQSTS + "）");
                    }
                    */
                } else if (sRetCod.equals("-9")) {
                    System.out.println("支付未知异常，请查询支付结果确认支付状态，错误信息："
                            + pktRsp.getERRMSG());
                } else {
                    System.out.println("支付失败：" + pktRsp.getERRMSG());
                }
            } else {
                System.out.println("响应报文解析失败");
            }
        }

        /*
        if (result != null && result.length() > 0) {
            XmlPacket pktRsp = XmlPacket.valueOf(result);
            if (pktRsp != null) {
                String sRetCod = pktRsp.getRETCOD();
                if (sRetCod.equals("0")) {
                    Map propPayResult = pktRsp.getProperty("NTQPAYRQZ", 0);
                    String sREQSTS = (String) propPayResult.get("REQSTS");
                    String sRTNFLG = (String) propPayResult.get("RTNFLG");
                    if (sREQSTS.equals("FIN") && sRTNFLG.equals("F")) {
                        System.out.println("支付失败："
                                + propPayResult.get("ERRTXT"));
                    } else {
                        System.out.println("支付已被银行受理（支付状态：" + sREQSTS + "）");
                    }
                } else if (sRetCod.equals("-9")) {
                    System.out.println("支付未知异常，请查询支付结果确认支付状态，错误信息："
                            + pktRsp.getERRMSG());
                } else {
                    System.out.println("支付失败：" + pktRsp.getERRMSG());
                }
            } else {
                System.out.println("响应报文解析失败");
            }
        }
        */
    }

    public static void main(String[] args) {
        try {
            HttpRequest request = new HttpRequest();

            // 生成请求报文
            String data = request.toPersonal();
            // 连接前置机，发送请求报文，获得返回报文
            String result = request.sendRequest(data);
            // 处理返回的结果
            request.toPersonalResult(result);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}