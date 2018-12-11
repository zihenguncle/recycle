package jx.com.okhttp_1.recycle;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.HashMap;
import java.util.Map;

import jx.com.okhttp_1.R;
import jx.com.okhttp_1.adapter.StaggeredAdapter;
import jx.com.okhttp_1.bean.BossBean;
import jx.com.okhttp_1.persenter.IpersenterImpl;
import jx.com.okhttp_1.url.Apis;
import jx.com.okhttp_1.view.IVew;

public class RecycleStaggeredView extends AppCompatActivity implements IVew {

    private RecyclerView recyclerView;
    private int spanCount = 3;
    private IpersenterImpl ipersenter;
    private StaggeredAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_staggered_view);

        recyclerView = findViewById(R.id.staggered_recycle);

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(spanCount,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        ipersenter = new IpersenterImpl(this);
        Map<String,String> map = new HashMap<>();
        ipersenter.startRequest(Apis.BOSS_URL,map,BossBean.class);
        adapter = new StaggeredAdapter(this);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration decoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        DividerItemDecoration decoration1 = new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL);
        decoration1.setDrawable(ContextCompat.getDrawable(this,R.drawable.recycle_item_decoration_vertical));
        decoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.recycle_item_decoration));
        recyclerView.addItemDecoration(decoration);
        recyclerView.addItemDecoration(decoration1);
    }

    @Override
    public void showResponsData(Object data) {
        BossBean bean = (BossBean) data;
        if(bean.getCode().equals("0")){
            adapter.setMlist(bean.getData().getFenlei());
        }
    }
}
