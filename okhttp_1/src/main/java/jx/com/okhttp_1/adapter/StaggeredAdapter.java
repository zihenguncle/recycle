package jx.com.okhttp_1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import jx.com.okhttp_1.R;
import jx.com.okhttp_1.bean.BossBean;

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.ViewHolder> {

    private List<BossBean.DataBean.FenleiBean> mlist;
    private Context context;

    public StaggeredAdapter(Context context) {
        this.context = context;
        mlist = new ArrayList<>();
    }

    public void setMlist(List<BossBean.DataBean.FenleiBean> list) {
        mlist.clear();
        if(list!=null){
            mlist.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_staggered_recycle,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BossBean.DataBean.FenleiBean fenleiBean = mlist.get(i);
        viewHolder.name.setText(fenleiBean.getName());
        Glide.with(context).load(fenleiBean.getIcon()).into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.stagered_title);
            image = itemView.findViewById(R.id.stagered_image);
        }
    }
}
