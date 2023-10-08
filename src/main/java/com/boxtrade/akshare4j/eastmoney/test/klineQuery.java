package com.boxtrade.akshare4j.eastmoney.test;

/**
 * 东方财富 股票 kline 测试
 *
 * @author 1763113879@qq.com
 * @version V2.1
 * @since 2.1.0 2023/8/3 11:03
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class klineQuery {

    public static void main(String[] args) throws IOException {
        URL url = new URL(
            "http://push2his.eastmoney.com/api/qt/stock/kline/get?fields1=f1,f2,f3,f4,f5,f6&fields2=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61,f116&ut=7eea3edcaed734bea9cbfc24409ed989&klt=101&fqt=0&secid=1.603828&beg=20220301&end=20230907&_=1623766962675");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2 ? httpConn.getInputStream() :
            httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        System.out.println(response);
    }
}