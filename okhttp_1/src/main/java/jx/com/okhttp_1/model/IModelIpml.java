package jx.com.okhttp_1.model;

import java.util.Map;

import jx.com.okhttp_1.callback.ICallBack;
import jx.com.okhttp_1.okhttp.OkCallBack;
import jx.com.okhttp_1.okhttp.OkHttpUtils;

public class IModelIpml implements IModel {
    @Override
    public void requestData(String path, Map<String, String> params, Class clazz, final ICallBack iCallBack) {
        OkHttpUtils.getInstance().getEnqueue(path, clazz, new OkCallBack() {
            @Override
            public void success(Object obj) {
                iCallBack.setData(obj);
            }

            @Override
            public void failed(Exception e) {
                iCallBack.setData(e.getMessage());
            }
        });
    }
}
