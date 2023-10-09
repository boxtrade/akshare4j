package com.boxtrade.eastmoney;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.boxtrade.akshare4j.dto.Candlestick;
import com.boxtrade.akshare4j.eastmoney.dao.EastMoneyApiDao;
import com.boxtrade.akshare4j.util.DateUtil;

/**
 * @author 1763113879@qq.com
 * @version V2.1
 * @since 2.1.0 2023/10/9 10:05
 */
public class EastMoneyApiDaoTest {

    @Test
    public void test() {
        String symbol = "0.300059";
        // 固定日线 101 period {"daily": "101", "weekly": "102", "monthly": "103"} 周期
        String period = "101";
        //开始日期 默认 19970826
        String startDate = "19970826";
        String endDate = DateUtil.getDateString(new Date(), DateUtil.FORMAT_YMD);
        //复权标记  adjust {"qfq": "1", "hfq": "2", "": "0"} {"qfq": "前复权", "hfq": "后复权", "": "不复权"} 循环 测试
        String adjust = "0";
        List<Candlestick> data = queryStockCandlesticks(period, adjust, symbol, startDate, endDate);

    }

    /**
     * 查询 股票的 蜡烛图
     *
     * @param period {"daily": "101", "weekly": "102", "monthly": "103"} 周期
     * @param adjust {"qfq": "1", "hfq": "2", "": "0"} {"qfq": "前复权", "hfq": "后复权", "": "不复权"}
     * @param symbol 股票代码
     * @param startDate 开始日期 格式&beg=20220301 YYYYMMDD
     * @param endDate 结束日期 格式&end=20230907 YYYYMMDD
     * @return
     */
    public static List<Candlestick> queryStockCandlesticks(String period, String adjust, String symbol,
        String startDate, String endDate) {
        //格式化后的  股票代码 上证 1.603828；深证 0.300059
        if (symbol.startsWith("6")) {
            symbol = "1." + symbol;
        } else {
            symbol = "0." + symbol;
        }

        EastMoneyApiDao eastMoneyApiDao = new EastMoneyApiDao();
        List<Candlestick> candlesticks = eastMoneyApiDao.getDailyKLineCandlesticks(period, adjust, symbol, startDate,
            endDate);
        return candlesticks;
    }

}
