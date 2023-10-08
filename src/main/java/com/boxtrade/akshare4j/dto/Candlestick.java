package com.boxtrade.akshare4j.dto;

import java.time.Duration;
import java.util.HashMap;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/**
 * Kline/Candlestick bars for a symbol. Klines are uniquely identified by their open time.
 */
@JsonPropertyOrder()
@Data
public class Candlestick {

    private Long openTime;

    /** 原始日期 */
    private String dateTime;
    /** 开盘价 */
    private String open;
    /** 最高价 */
    private String high;
    /** 最低价 */
    private String low;
    /** 收盘价 */
    private String close;
    /** 结算价 */
    private String settlementPrice;
    /** 交易量 */
    private String volume;
    /** 持仓量 */
    private String PositionVolume;

    private Long closeTime;

    // 报价 资产量
    private String quoteAssetVolume;

    // 交易数量
    private Long numberOfTrades;

    // 接受者购买基础资产数量
    private String takerBuyBaseAssetVolume;

    // 接受者买入报价资产量
    private String takerBuyQuoteAssetVolume;

    private Duration candleDuration;

    private HashMap<String, String> indicator;

    public Long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Long openTime) {
        this.openTime = openTime;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    public String getQuoteAssetVolume() {
        return quoteAssetVolume;
    }

    public void setQuoteAssetVolume(String quoteAssetVolume) {
        this.quoteAssetVolume = quoteAssetVolume;
    }

    public Long getNumberOfTrades() {
        return numberOfTrades;
    }

    public void setNumberOfTrades(Long numberOfTrades) {
        this.numberOfTrades = numberOfTrades;
    }

    public String getTakerBuyBaseAssetVolume() {
        return takerBuyBaseAssetVolume;
    }

    public void setTakerBuyBaseAssetVolume(String takerBuyBaseAssetVolume) {
        this.takerBuyBaseAssetVolume = takerBuyBaseAssetVolume;
    }

    public String getTakerBuyQuoteAssetVolume() {
        return takerBuyQuoteAssetVolume;
    }

    public void setTakerBuyQuoteAssetVolume(String takerBuyQuoteAssetVolume) {
        this.takerBuyQuoteAssetVolume = takerBuyQuoteAssetVolume;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("openTime", openTime)
            .append("open", open)
            .append("high", high)
            .append("low", low)
            .append("close", close)
            .append("volume", volume)
            .append("closeTime", closeTime)
            .append("quoteAssetVolume", quoteAssetVolume)
            .append("numberOfTrades", numberOfTrades)
            .append("takerBuyBaseAssetVolume", takerBuyBaseAssetVolume)
            .append("takerBuyQuoteAssetVolume", takerBuyQuoteAssetVolume)
            .toString();
    }
}
