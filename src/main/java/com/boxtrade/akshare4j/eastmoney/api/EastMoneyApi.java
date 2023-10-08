package com.boxtrade.akshare4j.eastmoney.api;

import com.boxtrade.akshare4j.eastmoney.dto.EastMoneyApiResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author 1763113879@qq.com
 * @version V2.1
 * @since 2.1.0 2023/8/3 13:35
 */
public interface EastMoneyApi {

    /**
     * 东财 股票 k线数据获取接口
     * 原始接口
     * http://push2his.eastmoney.com/api/qt/stock/kline/get?fields1=f1,f2,f3,f4,f5,f6&fields2=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61,f116&ut=7eea3edcaed734bea9cbfc24409ed989&klt=101&fqt=0&secid=1.603828&beg=20220301&end=20230907&_=1623766962675;
     *
     * @param period {"daily": "101", "weekly": "102", "monthly": "103"} 周期
     * @param adjust {"qfq": "1", "hfq": "2", "": "0"} {"qfq": "前复权", "hfq": "后复权", "": "不复权"}
     * @param symbol 格式化后的 股票代码 上证 1.603828；深证 0.300059
     * @param startDate 开始日期 格式&beg=20220301 YYYYMMDD
     * @param endDate 结束日期 格式&end=20230907 YYYYMMDD
     * @return
     */
    @GET(
        "/api/qt/stock/kline/get?fields1=f1,f2,f3,f4,f5,f6&fields2=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61,f116&ut=7eea3edcaed734bea9cbfc24409ed989&_=1623766962675")
    Call<EastMoneyApiResult> getKline(@Query("klt") String period, @Query("fqt") String adjust,
        @Query("secid") String symbol, @Query("beg") String startDate, @Query("end") String endDate);

}
