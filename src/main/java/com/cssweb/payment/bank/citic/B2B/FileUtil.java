package com.cssweb.payment.bank.citic.B2B;

import java.io.*;

/**
 * Created by chenhf on 2014/8/4.
 */
public class FileUtil {
    public static String readFile(String filePath) {
        String content = "";

        File file = new File(filePath);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;

            while ( (line = reader.readLine()) != null )
            {
                content += line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return content;
    }
}
