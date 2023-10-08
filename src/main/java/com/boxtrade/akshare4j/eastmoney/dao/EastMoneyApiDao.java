package com.boxtrade.akshare4j.eastmoney.dao;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.boxtrade.akshare4j.dto.Candlestick;
import com.boxtrade.akshare4j.eastmoney.api.EastMoneyApi;
import com.boxtrade.akshare4j.eastmoney.dto.EastMoneyApiResult;
import com.boxtrade.akshare4j.util.DateUtil;
import com.boxtrade.akshare4j.util.RetrofitUtil;

/**
 * @author 1763113879@qq.com
 * @version V2.1
 * @since 2.1.0 2023/8/3 14:00
 */
public class EastMoneyApiDao {

    public static String BASE_URL = "http://push2his.eastmoney.com";

    EastMoneyApi eastMoneyApiService = RetrofitUtil.getService(BASE_URL, EastMoneyApi.class);

    /**
     * 接口查询 东方财富的 最新价格信息 日线
     *
     * @param period {"daily": "101", "weekly": "102", "monthly": "103"} 周期
     * @param adjust {"qfq": "1", "hfq": "2", "": "0"} {"qfq": "前复权", "hfq": "后复权", "": "不复权"}
     * @param symbol 格式化后的 股票代码 上证 1.603828；深证 0.300059
     * @param startDate 开始日期 格式&beg=20220301 YYYYMMDD
     * @param endDate 结束日期 格式&end=20230907 YYYYMMDD
     * @return
     */
    public List<Candlestick> getDailyKLineCandlesticks(String period, String adjust, String symbol, String startDate,
        String endDate) {
        EastMoneyApiResult apiResult = RetrofitUtil.getData(
            eastMoneyApiService.getKline(period, adjust, symbol, startDate, endDate));

        //      * @param period {"daily": "101", "weekly": "102", "monthly": "103"} 周期
        Duration candleDuration = Duration.ofDays(Long.valueOf(1));
        if ("102".equals(period)) {
            candleDuration = Duration.ofDays(Long.valueOf(7));
        } else if ("103".equals(period)) {
            //暂定30天；
            candleDuration = Duration.ofDays(Long.valueOf(30));
        }
        List<Candlestick> candlesticks = new ArrayList<>();
        for (String kstr : apiResult.getData()
            .getKlines()) {
            Candlestick candlestick = convertKlineStr2Candlestick(candleDuration, kstr);
            candlesticks.add(candlestick);
        }
        return candlesticks;
    }

    /**
     * 返回值 字符串 转为 蜡烛图数据
     *
     * @param candleDuration
     * @param kstr
     * @return
     */
    private Candlestick convertKlineStr2Candlestick(Duration candleDuration, String kstr) {
        String candlestickStr[] = kstr.split(",");
        Candlestick candlestick = new Candlestick();
        // "日期", 格式 2023-07-27
        candlestick.setDateTime(candlestickStr[0]);
        //     "开盘",
        candlestick.setOpen(candlestickStr[1]);
        //     "收盘",
        candlestick.setClose(candlestickStr[2]);
        //     "最高",
        candlestick.setHigh(candlestickStr[3]);
        //     "最低",
        candlestick.setLow(candlestickStr[4]);
        //     "成交量",
        candlestick.setVolume(candlestickStr[5]);
        //     "成交额",
        candlestick.setQuoteAssetVolume(candlestickStr[6]);
        //     "振幅",
        //     "涨跌幅",
        //     "涨跌额",
        //     "换手率",

        String format = DateUtil.FORMAT_YMDHM_S;
        if (candlestick.getDateTime()
            .length() == 10) {
            format = DateUtil.FORMAT_YMD_LINE;
        }
        Date date = DateUtil.getDate(candlestick.getDateTime(), format);
        candlestick.setCloseTime(date.getTime());

        candlestick.setCandleDuration(candleDuration);
        Date OpenDate = new Date(date.getTime() - candleDuration.toMillis());
        candlestick.setOpenTime(OpenDate.getTime());
        return candlestick;
    }

    public static void main(String[] args) {
        EastMoneyApi eastMoneyApi = RetrofitUtil.getService(BASE_URL, EastMoneyApi.class);
        //
        //       * @param period {"daily": "101", "weekly": "102", "monthly": "103"} 周期
        //        * @param adjust {"qfq": "1", "hfq": "2", "": "0"} {"qfq": "前复权", "hfq": "后复权", "": "不复权"}
        // * @param symbol 格式化后的 股票代码 上证 1.603828；深证 0.300059
        //        * @param startDate 开始日期 格式&beg=20220301 YYYYMMDD
        //        * @param endDate 结束日期 格式&end=20230907 YYYYMMDD
        String period = "101";
        String adjust = "0";
        String symbol = "0.300059";
        String startDate = "20220301";
        String endDate = "20230907";
        EastMoneyApiResult apiResult = RetrofitUtil.getData(
            eastMoneyApi.getKline(period, adjust, symbol, startDate, endDate));
        apiResult.getData()
            .getKlines()
            .stream()
            .forEach(System.out::println);

        EastMoneyApiDao eastMoneyApiDao = new EastMoneyApiDao();
        System.out.println(eastMoneyApiDao.getDailyKLineCandlesticks(period, adjust, symbol, startDate, endDate));
    }
}
