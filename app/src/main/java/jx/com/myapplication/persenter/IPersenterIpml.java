package jx.com.myapplication.persenter;

import java.util.Map;

import jx.com.myapplication.callback.ICallBack;
import jx.com.myapplication.model.IModel;
import jx.com.myapplication.model.IModelImpl;
import jx.com.myapplication.view.IView;

public class IPersenterIpml implements IPersenter {

    IView iView;
    IModelImpl model;
    public IPersenterIpml(IView iView) {
        this.iView = iView;
        model = new IModelImpl();
    }

    @Override
    public void startRequest(String path, Map<String, String> params, Class clazz) {
        model.getData(path, params, clazz, new ICallBack() {
            @Override
            public void getData(Object data) {
                iView.success(data);
            }
        });
    }
}
