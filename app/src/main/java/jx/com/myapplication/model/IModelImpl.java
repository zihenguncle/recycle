package jx.com.myapplication.model;

import java.util.Map;

import jx.com.myapplication.callback.ICallBack;
import jx.com.myapplication.okhttp.OKCallBack;
import jx.com.myapplication.okhttp.OKHttpUtils;

public class IModelImpl implements IModel {
    @Override
    public void getData(String path, Map<String, String> params, Class clazz, final ICallBack iCallBack) {
        OKHttpUtils.getInstance().postEnqueue(path, params, clazz, new OKCallBack() {
            @Override
            public void success(Object obj) {
                iCallBack.getData(obj);
            }

            @Override
            public void failed(Exception e) {
                iCallBack.getData(e.getMessage());
            }
        });
    }
}
