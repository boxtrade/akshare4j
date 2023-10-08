package com.boxtrade.akshare4j.eastmoney.dto;

import java.util.List;

/**
 * @author 1763113879@qq.com
 * @version V2.1
 * @since 2.1.0 2023/8/3 13:30
 */
public class StockData {

    private String code;
    private int market;
    private String name;
    private int decimal;
    private int dktotal;
    private double preKPrice;
    private List<String> klines;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getMarket() {
        return market;
    }

    public void setMarket(int market) {
        this.market = market;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDecimal() {
        return decimal;
    }

    public void setDecimal(int decimal) {
        this.decimal = decimal;
    }

    public int getDktotal() {
        return dktotal;
    }

    public void setDktotal(int dktotal) {
        this.dktotal = dktotal;
    }

    public double getPreKPrice() {
        return preKPrice;
    }

    public void setPreKPrice(double preKPrice) {
        this.preKPrice = preKPrice;
    }

    public List<String> getKlines() {
        return klines;
    }

    public void setKlines(List<String> klines) {
        this.klines = klines;
    }
}
