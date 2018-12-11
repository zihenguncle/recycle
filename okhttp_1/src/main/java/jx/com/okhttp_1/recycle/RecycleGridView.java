package jx.com.okhttp_1.recycle;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;

import jx.com.okhttp_1.R;
import jx.com.okhttp_1.adapter.GridAdapter;
import jx.com.okhttp_1.bean.BossBean;
import jx.com.okhttp_1.persenter.IpersenterImpl;
import jx.com.okhttp_1.url.Apis;
import jx.com.okhttp_1.view.IVew;

public class RecycleGridView extends AppCompatActivity implements IVew {

    private RecyclerView recyclerView;
    private int spanCount = 3;
    private GridAdapter adapter;
    private IpersenterImpl ipersenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_grid_view);
        recyclerView = findViewById(R.id.recycle_gridview);

        //创建一个布局管理器
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        //设置方向
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        ipersenter = new IpersenterImpl(this);
        Map<String,String> map = new HashMap<>();
        map.put("pp","");
        ipersenter.startRequest(Apis.BOSS_URL,map,BossBean.class);
        adapter = new GridAdapter(this);
        recyclerView.setAdapter(adapter);
        //设置分割线
        DividerItemDecoration decoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        DividerItemDecoration decoration1 = new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL);
        //自定义分割线样式
        decoration1.setDrawable(ContextCompat.getDrawable(this,R.drawable.recycle_item_decoration_vertical));
        decoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.recycle_item_decoration));
        recyclerView.addItemDecoration(decoration);
        recyclerView.addItemDecoration(decoration1);
        adapter.setOnClickListener(new GridAdapter.setClick() {
            @Override
            public void onClick(int positon) {
                adapter.removeItem(positon);
            }
        });
    }

    @Override
    public void showResponsData(Object data) {
        BossBean bean = (BossBean) data;
        if(bean.getCode().equals("0")){
            adapter.setMlist(((BossBean) data).getData().getMiaosha().getList());
        }
    }
}
