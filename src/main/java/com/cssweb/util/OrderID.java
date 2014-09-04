package com.cssweb.util;

import java.util.Random;

/**
 * Created by chenhf on 2014/9/4.
 */
public class OrderID {
    public static  String getRandOrderId()
    {
        // 可以用java.util.Random
        // 可以用Math.Random

        String result = "";

        Random random = new Random();
        for (int i=0; i<6; i++) {
            result += random.nextInt(10);
        }

        return result;
    }
}
