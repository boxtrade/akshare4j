package com.boxtrade.akshare4j.eastmoney.dto;

/**
 * @author 1763113879@qq.com
 * @version V2.1
 * @since 2.1.0 2023/8/3 13:32
 */
public class EastMoneyApiResult {

    private int rc;
    private int rt;
    private long svr;
    private int lt;
    private int full;
    private String dlmkts;
    private StockData data;

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public int getRt() {
        return rt;
    }

    public void setRt(int rt) {
        this.rt = rt;
    }

    public long getSvr() {
        return svr;
    }

    public void setSvr(long svr) {
        this.svr = svr;
    }

    public int getLt() {
        return lt;
    }

    public void setLt(int lt) {
        this.lt = lt;
    }

    public int getFull() {
        return full;
    }

    public void setFull(int full) {
        this.full = full;
    }

    public String getDlmkts() {
        return dlmkts;
    }

    public void setDlmkts(String dlmkts) {
        this.dlmkts = dlmkts;
    }

    public StockData getData() {
        return data;
    }

    public void setData(StockData data) {
        this.data = data;
    }
}
