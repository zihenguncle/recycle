package jx.com.myapplication.model;

import java.util.Map;

import jx.com.myapplication.callback.ICallBack;

public interface IModel {
    void getData(String path, Map<String,String> params, Class clazz, ICallBack iCallBack);
}
