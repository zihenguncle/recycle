package jx.com.okhttp_1.recycle;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;

import jx.com.okhttp_1.R;
import jx.com.okhttp_1.adapter.LinearAdapter;
import jx.com.okhttp_1.bean.BossBean;
import jx.com.okhttp_1.persenter.IpersenterImpl;
import jx.com.okhttp_1.url.Apis;
import jx.com.okhttp_1.view.IVew;

public class RecycleLinearView extends AppCompatActivity implements IVew {

    private RecyclerView recyclerView;
    private IpersenterImpl ipersenter;
    private LinearAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_linear_view);
        ipersenter = new IpersenterImpl(this);
        recyclerView = findViewById(R.id.linear_recycle);
        //写一个布局管理器，线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置方向---垂直
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        Map<String,String> params = new HashMap<>();
        adapter = new LinearAdapter(this);
        recyclerView.setAdapter(adapter);
        ipersenter.startRequest(Apis.BOSS_URL,params,BossBean.class);
        //设置一条分割线(系统自定)
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        //添加自定义分割线
        decoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.recycle_item_decoration));
        recyclerView.addItemDecoration(decoration);

    }

    @Override
    public void showResponsData(Object data) {
        BossBean bean = (BossBean) data;
        if(bean.getCode().equals("0")){
            adapter.setmTuijian(bean.getData().getTuijian().getList());
        }
    }
}
