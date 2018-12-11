package jx.com.okhttp_1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import jx.com.okhttp_1.R;
import jx.com.okhttp_1.bean.BossBean;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private List<BossBean.DataBean.MiaoshaBean.ListBean> mlist;
    private Context context;

    public GridAdapter(Context context) {
        this.context = context;
        mlist = new ArrayList<>();
    }

    public void setMlist(List<BossBean.DataBean.MiaoshaBean.ListBean> list) {
        mlist.clear();
        if(list!=null){
            mlist.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_grid_recycle,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        BossBean.DataBean.MiaoshaBean.ListBean listBean = mlist.get(i);
        viewHolder.title.setText(listBean.getTitle());
        viewHolder.price.setText(listBean.getBargainPrice()+"");
        final String[] split = listBean.replace().split("\\!");
        Glide.with(context).load(split[0]).into(viewHolder.image);
        viewHolder.grid_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(setClick!=null){
                    setClick.onClick(i);
                }
            }
        });
    }

    public setClick setClick;
    public void setOnClickListener(setClick setClick){
        this.setClick = setClick;
    }
    public interface setClick{
        void onClick(int positon);
    }
    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,price;
        ImageView image;
        RelativeLayout grid_relative;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.grid_title);
            price = itemView.findViewById(R.id.grid_bargainPrice);
            image = itemView.findViewById(R.id.grid_image);
            grid_relative = itemView.findViewById(R.id.grid_relative);
        }
    }
    public void removeItem(int position){
        mlist.remove(position);
        notifyItemRemoved(position);
    }
}
