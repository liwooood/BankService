package com.cssweb.network;

import com.cssweb.payment.bank.BOC.BasicResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by chenhf on 2014/8/5.
 */
public class HttpClient {

    private String bankEnterpriseServer;
    private final String CHARSET = "UTF-8";

    public HttpClient(String bankEnterpriseServer)
    {
        this.bankEnterpriseServer = bankEnterpriseServer;
    }

    public String postRequest(String request)
    {
        String response = "";

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000).build();

        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();


        try {
            StringEntity reqEntity = null;
            reqEntity = new StringEntity(request, CHARSET);

            reqEntity.setContentType("text/xml;charset=" + CHARSET);
            reqEntity.setContentEncoding(CHARSET);

            HttpPost httpPost = new HttpPost(bankEnterpriseServer);
            httpPost.setEntity(reqEntity);
            httpPost.setHeader("Content-Type", "text/xml;charset=" + CHARSET);



            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            try {
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                System.out.println("status code = " + statusCode);

                if (statusCode != 200) {

                }

                HttpEntity resEntity = httpResponse.getEntity();
                response = EntityUtils.toString(resEntity, CHARSET);

                EntityUtils.consume(resEntity);
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

        return response;
    }

    public String postRequest2(String request)  {

        String response = "";

        try {
            URL url = new URL(bankEnterpriseServer);


            HttpURLConnection con = (HttpURLConnection) url.openConnection();
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
            out.flush();
            out.close();


            StringBuffer buffer = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(con
                    .getInputStream(), CHARSET));
            String temp;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
                buffer.append("\n");
            }

             response = buffer.toString();
            //System.out.println("response = " + response);


            br.close();
            con.disconnect();



        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

        return response;
    }
}
