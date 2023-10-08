package com.boxtrade.akshare4j.eastmoney.dto;

import lombok.Data;

/**
 * @author 1763113879@qq.com
 * @version V2.1
 * @since 2.1.0 2023/8/3 13:32
 */
@Data
public class EastMoneyApiResult {

    private int rc;
    private int rt;
    private long svr;
    private int lt;
    private int full;
    private String dlmkts;
    private StockData data;
}
