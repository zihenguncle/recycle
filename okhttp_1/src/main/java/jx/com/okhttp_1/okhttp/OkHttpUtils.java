package jx.com.okhttp_1.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtils {

    public OkHttpClient mClient;
    public Handler mHandler = new Handler(Looper.getMainLooper());

    public static OkHttpUtils instance;
    public static OkHttpUtils getInstance(){
        if(instance == null){
            synchronized (OkHttpUtils.class){
                instance = new OkHttpUtils();
            }
        }
        return instance;
    }

    public OkHttpUtils() {

        //日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        /**
         *
         */
        mClient = new OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }

    /**
     * 异步请求get方法
     * 1.创建一个请求
     * 2.创建一个call
     * 3.调用异步请求
     * @param path
     * @param clazz
     * @param okCallBack
     */
    public void getEnqueue(String path, final Class clazz, final OkCallBack okCallBack){
        Request request = new Request.Builder()
                .get()
                .url(path)
                .build();
        Call call = mClient.newCall(request);
        //调用异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okCallBack.failed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                final Object o = new Gson().fromJson(result, clazz);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        okCallBack.success(o);
                    }
                });
            }
        });
    }
}
