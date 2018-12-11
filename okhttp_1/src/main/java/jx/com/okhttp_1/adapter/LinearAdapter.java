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

public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.ViewHolder> {

    private List<BossBean.DataBean.TuijianBean.ListBeanX> mList;
    private Context mContext;

    public LinearAdapter(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmTuijian(List<BossBean.DataBean.TuijianBean.ListBeanX> list) {
        mList.clear();
        if(list != null){
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_linear_recycle,viewGroup,false);
        return new ViewHolder(view);
    }

    /**
     * 静态内部类ViewHolder
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView title,subhead;
        public final ImageView images;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.linear_title);
            subhead = itemView.findViewById(R.id.linear_subhead);
            images = itemView.findViewById(R.id.linear_images);
        }
    }

    /**
     * 绑定ViewHolder
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BossBean.DataBean.TuijianBean.ListBeanX listBeanX = mList.get(i);
        viewHolder.title.setText(listBeanX.getTitle());
        viewHolder.subhead.setText(listBeanX.getSubhead());
        String[] split = listBeanX.getImages().split("\\!");
        Glide.with(mContext).load(split[0]).into(viewHolder.images);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
