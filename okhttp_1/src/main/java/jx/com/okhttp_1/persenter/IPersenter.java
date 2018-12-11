package jx.com.okhttp_1.persenter;

import java.util.Map;

public interface IPersenter {
    void startRequest(String path, Map<String,String> params,Class clazz);
}
