package com.cssweb.payment.bank.BOC;

/**
 * Created by chenhf on 2014/6/25.
 */
public class LoginResponse extends BasicResponse{
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    private String response;
}
