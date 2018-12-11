package jx.com.okhttp_1.model;

import java.util.Map;

import jx.com.okhttp_1.callback.ICallBack;

public interface IModel {
    void requestData(String path, Map<String,String> params, Class clazz, ICallBack iCallBack);
}
