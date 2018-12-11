package jx.com.okhttp_1.persenter;

import java.util.Map;

import jx.com.okhttp_1.callback.ICallBack;
import jx.com.okhttp_1.model.IModelIpml;
import jx.com.okhttp_1.view.IVew;

public class IpersenterImpl implements IPersenter {

    private IVew iVew;
    private IModelIpml iModelIpml;

    public IpersenterImpl(IVew iVew) {
        this.iVew = iVew;
        iModelIpml = new IModelIpml();
    }

    @Override
    public void startRequest(String path, Map<String, String> params, Class clazz) {
        iModelIpml.requestData(path, params, clazz, new ICallBack() {
            @Override
            public void setData(Object data) {
                iVew.showResponsData(data);
            }
        });
    }
}
