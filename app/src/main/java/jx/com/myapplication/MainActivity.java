package jx.com.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jx.com.myapplication.bean.BannerBean;
import jx.com.myapplication.persenter.IPersenterIpml;
import jx.com.myapplication.view.IView;

public class MainActivity extends AppCompatActivity implements IView {


    private Banner banner;

    private IPersenterIpml iPersenterIpml;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iPersenterIpml = new IPersenterIpml(this);
        setContentView(R.layout.activity_main);
        banner = findViewById(R.id.main_banner);

        Map<String,String> map = new HashMap<>();
        map.put("pp","");
        iPersenterIpml.startRequest(Apis.BANNER_URI,map,BannerBean.class);
    }



    @Override
    public void success(Object data) {
        final BannerBean bannerBean = (BannerBean) data;
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new ImageLoaderInterface<ImageView>() {



            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                BannerBean bean =  bannerBean;

                //for (int j = 0; j < bean.getData().size(); j++) {

                    ImageLoader.getInstance().displayImage(bean.getData().get(0).getreplace(),imageView);
                //}

            }

            @Override
            public ImageView createImageView(Context context) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return imageView;
            }
        });
        setData(bannerBean);
    }

    private void setData(BannerBean mydata) {
        banner.setImages(mydata.getData());
        banner.setBannerTitles(gettitle(mydata.getData()));
        banner.setDelayTime(2000);
        banner.start();
    }


    private List<String> gettitle(List<BannerBean.DataBean> data) {
        List<String> list = new ArrayList<>();
        for (int j = 0; j <data.size(); j++) {
            list.add(data.get(j).getTitle());
        }
        return list;
    }
}
