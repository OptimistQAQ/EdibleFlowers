package com.example.edibleflowers.fragment.zixun;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.edibleflowers.R;
import com.example.edibleflowers.binder.ZixunNewsBinder;
import com.example.edibleflowers.item.ZixunNewsItem;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ZixunNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @author 65667
 */
public class ZixunNewsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View root;

    private RecyclerView recyclerView;
    private MultiTypeAdapter adapter;

    private Items mItems;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ZixunNewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ZixunNewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ZixunNewsFragment newInstance(String param1, String param2) {
        ZixunNewsFragment fragment = new ZixunNewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_zixun_news, container, false);
        initView();
        return root;
    }

    private void initView() {
        List<String> img = new ArrayList<>();
        List<String> title = new ArrayList<>();
        List<String> author = new ArrayList<>();
        List<String> time = new ArrayList<>();

//        img.add("http://i0.sinaimg.cn/dy/c/2012-03-29/1332982771_V9gkot.jpg");
//        title.add("40种食用花卉 “绽放”市民餐桌");
//        author.add("新浪");
//        time.add("2012年03月29日06:35");
//
//        img.add("http://n.sinaimg.cn/sinakd10123/590/w714h676/20210116/8481-khstaxs7825983.jpg");
//        title.add("几款花卉，不仅观赏值高，还能食用，适合家养");
//        author.add("皮皮望电影");
//        time.add("2021年01月19日 23:14");
//
//        img.add("http://n.sinaimg.cn/sinacn00/219/w640h379/20180830/594c-hikcahf6528948.jpg");
//        title.add("可以吃的花朵，这些美丽的食用花卉，不仅可以观赏还能做美食！");
//        author.add("无处下西楼飞翔");
//        time.add("2018年08月30日 00:06");
//
//        img.add("http://n.sinaimg.cn/sinakd20210430ac/458/w1206h852/20210430/fcad-kphwums3884259.jpg");
//        title.add("春天种上这4种花卉植物，既能赏花还能食用！");
//        author.add("实鲜新食");
//        time.add("2021年04月30日 09:38");
//
//        img.add("http://n.sinaimg.cn/sinakd10107/24/w500h324/20201113/0a7c-kcunqze4558017.jpg");
//        title.add("此花既能观赏又能食用，在东北是家常菜，也是漂亮的盆栽花卉");
//        author.add("情感解忧课堂");
//        time.add("2020年11月13日 07:24");

        recyclerView = root.findViewById(R.id.ev_news);
        adapter = new MultiTypeAdapter();
        adapter.register(ZixunNewsItem.class, new ZixunNewsBinder());
        recyclerView.setAdapter(adapter);

//        mItems = new Items();
//        for (int i=0; i<img.size(); i++) {
//            ZixunNewsItem zixunNewsItem = new ZixunNewsItem();
//            zixunNewsItem.setImg_news(img.get(i));
//            zixunNewsItem.setTitle(title.get(i));
//            zixunNewsItem.setAuthor(author.get(i));
//            zixunNewsItem.setTime(time.get(i));
//            mItems.add(zixunNewsItem);
//        }

        //[{"nathor":"新浪","nimg":"http://i0.sinaimg.cn/dy/c/2012-03-29/1332982771_V9gkot.jpg","nno":1,"ntime":"2012年03月29日06:35","ntitle":"40种食用花卉 “绽放”市民餐桌"},{"nathor":"皮皮望电影","nimg":"http://n.sinaimg.cn/sinakd10123/590/w714h676/20210116/8481-khstaxs7825983.jpg","nno":2,"ntime":"2021年01月19日 23:14","ntitle":"几款花卉，不仅观赏值高，还能食用，适合家养"},{"nathor":"无处下西楼飞翔","nimg":"http://n.sinaimg.cn/sinacn00/219/w640h379/20180830/594c-hikcahf6528948.jpg","nno":3,"ntime":"2018年08月30日 00:06","ntitle":"可以吃的花朵，这些美丽的食用花卉，不仅可以观赏还能做美食！"},{"nathor":"实鲜新食","nimg":"http://n.sinaimg.cn/sinakd20210430ac/458/w1206h852/20210430/fcad-kphwums3884259.jpg","nno":4,"ntime":"2021年04月30日 09:38","ntitle":"春天种上这4种花卉植物，既能赏花还能食用！"},{"nathor":"情感解忧课堂","nimg":"http://n.sinaimg.cn/sinakd10107/24/w500h324/20201113/0a7c-kcunqze4558017.jpg","nno":5,"ntime":"2020年11月13日 07:24","ntitle":"此花既能观赏又能食用，在东北是家常菜，也是漂亮的盆栽花卉"},{"nathor":"盐城晚报","nimg":"http://n.sinaimg.cn/sinakd2021317s/533/w800h533/20210317/a2f8-kmkptxe1140411.jpg","nno":6,"ntime":"","ntitle":"赏花卉游世遗品美食"},{"nathor":"花卉网","nimg":"http://n.sinaimg.cn/sinakd20200623ac/211/w600h411/20200623/76e6-ivmqpch9644629.jpg","nno":7,"ntime":"123","ntitle":"在农村花卉植物变成“美食”"},{"nathor":"话匣子FM","nimg":"http://n.sinaimg.cn/spider20191217/84/w1080h604/20191217/7a9c-ikvenft5407836.jpg","nno":8,"ntime":"123","ntitle":"“把花做进美食里！” 崇明的这些花卉产业你知道吗？"},{"nathor":"佛山新闻","nimg":"http://n.sinaimg.cn/sinacn20190715s/250/w600h450/20190715/fc0d-hzuhxyq0611621.png","nno":9,"ntime":"123","ntitle":"舌尖上的“花花世界”！陈村花卉美食文化节火热进行中"},{"nathor":"羊城晚报金羊网","nimg":"http://n.sinaimg.cn/sinakd2020916s/162/w550h412/20200916/f8b1-izeysaz4718876.jpg","nno":10,"ntime":"1","ntitle":"第八届陈村花卉美食文化节直播启动 寻味物美价廉鲜花盛宴"},{"nathor":"大小新闻客户端","nimg":"http://n.sinaimg.cn/sinakd202162s/411/w734h477/20210602/564b-kquziik3943684.png","nno":11,"ntime":"1","ntitle":"走进乡村|养花赚钱它不香吗？烟台这个小乡村种植食用玫瑰和月季，带动农民增收"},{"nathor":"喵儿娱乐","nimg":"http://n.sinaimg.cn/sinakd20210527ac/320/w1200h720/20210527/760b-kqpyfha1326392.jpg","nno":12,"ntime":"1","ntitle":"这种植物,看似珍珠一般的果实,内含有很强的毒素,切不可食用"},{"nathor":"我的小宠花猫","nimg":"http://n.sinaimg.cn/sinakd20210518ac/224/w640h384/20210518/83ea-kqhwhri7097692.jpg","nno":13,"ntime":"1","ntitle":"墙角边这种植物，农民当“香菜”食用，古代人当“染色剂”染布！"},{"nathor":"白马抡天下","nimg":"http://n.sinaimg.cn/sinakd20210505ac/419/w723h496/20210505/12e7-kppteat0214708.jpg","nno":14,"ntime":"1","ntitle":"几种花卉，比玫瑰花还漂亮，四季常开，种植容易"},{"nathor":"小雄美i食","nimg":"http://n.sinaimg.cn/sinakd20210429ac/223/w640h383/20210429/4f9c-kphwums2551205.jpg","nno":15,"ntime":"1","ntitle":"假期出去游玩,遇到这6种植物花卉不要靠近,都是有毒植物"}]

        OkGo.<String>post("http://10.132.150.15:9596/showNews")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("response", response.body());
                        mItems = new Items();
                        response.toString();
                        JSONArray jsonArray = JSON.parseArray(response.body());
                        for (int i=0; i<5; i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            img.add(jsonObject.getString("nimg"));
                            author.add(jsonObject.getString("nathor"));
                            title.add(jsonObject.getString("ntitle"));
                            time.add(jsonObject.getString("ntime"));
                        }
                        for (int i=0; i<5; i++) {
                            ZixunNewsItem zixunNewsItem = new ZixunNewsItem();
                            zixunNewsItem.setImg_news(img.get(i));
                            zixunNewsItem.setTitle(title.get(i));
                            zixunNewsItem.setAuthor(author.get(i));
                            zixunNewsItem.setTime(time.get(i));
                            mItems.add(zixunNewsItem);
                        }
                        adapter.setItems(mItems);
                        adapter.notifyDataSetChanged();
                    }
                });

    }
}