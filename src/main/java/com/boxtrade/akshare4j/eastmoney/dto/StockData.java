package com.boxtrade.akshare4j.eastmoney.dto;

import java.util.List;

import lombok.Data;

/**
 * @author 1763113879@qq.com
 * @version V2.1
 * @since 2.1.0 2023/8/3 13:30
 */
@Data
public class StockData {

    private String code;
    private int market;
    private String name;
    private int decimal;
    private int dktotal;
    private double preKPrice;
    private List<String> klines;

}
