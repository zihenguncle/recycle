package jx.com.myapplication.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


public class OKHttpUtils {

    public Handler handler = new Handler(Looper.getMainLooper());
    public static OKHttpUtils instance;
    public OkHttpClient mClient;
    public static OKHttpUtils getInstance(){
        if(instance == null){
            synchronized (OKHttpUtils.class){
                instance = new OKHttpUtils();
            }
        }
        return instance;
    }

    public OKHttpUtils() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        /**
         * 使用构造者模式
         * 设置链接超时
         * 读取超时
         * 写超时
         */
        mClient = new OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }

    public void postEnqueue(String path, Map<String,String> params, final Class clazz, final OKCallBack okCallBack){
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String,String> entry : params.entrySet()){
            builder.add(entry.getKey(),entry.getValue());
        }
        FormBody body = builder.build();
        final Request request = new Request.Builder()
                //.post(body)
                .url(path)
                .build();

        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okCallBack.failed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                final Object o = new Gson().fromJson(result, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okCallBack.success(o);
                    }
                });
            }
        });
    }
}
