package jx.com.myapplication.persenter;

import java.util.Map;

public interface IPersenter {
    void startRequest(String path, Map<String,String> params,Class clazz);
}
