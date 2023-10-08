package com.boxtrade.akshare4j.util;

/**
 * @author 1763113879@qq.com
 * @version V2.1
 * @since 2.1.0 2022/11/3 22:56
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 文件名：RetrofitUtil
 * 创建者：HY
 * 创建时间：2019/6/5 10:54
 * 描述：  Retrofit工具包
 * 单例模式
 * <p>
 * {@link RetrofitUtil#getService(String, Class)}
 */
@Slf4j
public class RetrofitUtil {

    private static ConcurrentHashMap<String, Retrofit> retrofitMap = new ConcurrentHashMap<>();

    /**
     * 默认超时时长
     * 单位：秒
     */
    private static final int DEFAULT_TIMEOUT = 10;

    /**
     * 默认日期转换格式
     */
    private static final String DEFAULT_DATA_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * url 类名 存储已加载过的Service
     */
    private static ConcurrentHashMap<Retrofit, HashMap<String, Object>> urlServiceMap = new ConcurrentHashMap<>();

    /**
     * 返回局部对象
     *
     * 解析为对象
     */
    public static Retrofit getRetrofit(String baseUrl) {
        Retrofit retrofit = retrofitMap.get(baseUrl);
        if (retrofit == null) {
            retrofit = generateRetrofit(baseUrl);
            retrofitMap.put(baseUrl, retrofit);
            // 类名 存储已加载过的Service
            HashMap<String, Object> mServiceMap = new HashMap<>();
            urlServiceMap.put(retrofit, mServiceMap);

        }
        return retrofit;
    }

    /*=========================================================*/

    private static Retrofit generateRetrofit(String baseUrl) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        //配置OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(logging);
        //连接超时时间
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //写超时时间
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //读超时时间
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //日期转换
        Gson gson = new GsonBuilder().setDateFormat(DEFAULT_DATA_FORMAT)
            .create();
        //缓存

        //拦截器

        Retrofit mRetrofit = new Retrofit.Builder().baseUrl(baseUrl)
            .client(builder.build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))//设置使用Gson解析
            .build();

        return mRetrofit;
    }

    /**
     * 获取 Service
     */
    public static <T> T getService(String url, final Class<T> serviceClass) {
        Retrofit retrofit = getRetrofit(url);
        HashMap<String, Object> mServiceMap = urlServiceMap.get(retrofit);
        String serviceName = serviceClass.getName();
        if (mServiceMap.containsKey(serviceName)) {
            return (T) mServiceMap.get(serviceName);
        } else {
            T service = retrofit.create(serviceClass);
            mServiceMap.put(serviceName, service);
            return service;
        }
    }

    public static <T> T getData(Call<T> call) {
        if (call == null) {
            return null;
        }
        try {
            Response<T> response = call.execute();
            log.debug("返回的数据：{}", GsonUtil.toJson(response.body()));
            return response.body();
        } catch (IOException e) {
            log.error("接口调用异常", e);
            e.printStackTrace();
        }
        return null;
    }

}