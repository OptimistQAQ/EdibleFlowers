package com.example.edibleflowers.fragment.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edibleflowers.R;
import com.example.edibleflowers.binder.HomeDailyRecommendBinder;
import com.example.edibleflowers.item.HomeDailyRecommendItem;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeDailyRecommendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeDailyRecommendFragment extends Fragment {

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

    public HomeDailyRecommendFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeDailyRecommendFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeDailyRecommendFragment newInstance(String param1, String param2) {
        HomeDailyRecommendFragment fragment = new HomeDailyRecommendFragment();
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
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_home_daily_recommend, container, false);
        initView();
        return root;
    }

    private void initView() {
        List<String> img = new ArrayList<>();
        List<String> title = new ArrayList<>();
        List<String> author = new ArrayList<>();

        img.add("http://n.sinaimg.cn/sinakd2021317s/533/w800h533/20210317/a2f8-kmkptxe1140411.jpg");
        title.add("赏花卉游世遗品美食");
        author.add("盐城晚报");

        img.add("http://n.sinaimg.cn/sinakd20200623ac/211/w600h411/20200623/76e6-ivmqpch9644629.jpg");
        title.add("在农村花卉植物变成“美食”");
        author.add("花卉网");

        img.add("http://n.sinaimg.cn/spider20191217/84/w1080h604/20191217/7a9c-ikvenft5407836.jpg");
        title.add("“把花做进美食里！” 崇明的这些花卉产业你知道吗？");
        author.add("话匣子FM");

        img.add("http://n.sinaimg.cn/sinacn20190715s/250/w600h450/20190715/fc0d-hzuhxyq0611621.png");
        title.add("舌尖上的“花花世界”！陈村花卉美食文化节火热进行中");
        author.add("佛山新闻");

        img.add("http://n.sinaimg.cn/sinakd2020916s/162/w550h412/20200916/f8b1-izeysaz4718876.jpg");
        title.add("第八届陈村花卉美食文化节直播启动 寻味物美价廉鲜花盛宴");
        author.add("羊城晚报金羊网");

        img.add("http://n.sinaimg.cn/sinakd202162s/411/w734h477/20210602/564b-kquziik3943684.png");
        title.add("走进乡村|养花赚钱它不香吗？烟台这个小乡村种植食用玫瑰和月季，带动农民增收");
        author.add("大小新闻客户端");

        img.add("http://n.sinaimg.cn/sinakd20210527ac/320/w1200h720/20210527/760b-kqpyfha1326392.jpg");
        title.add("这种植物,看似珍珠一般的果实,内含有很强的毒素,切不可食用");
        author.add("喵儿娱乐");

        img.add("http://n.sinaimg.cn/sinakd20210518ac/224/w640h384/20210518/83ea-kqhwhri7097692.jpg");
        title.add("墙角边这种植物，农民当“香菜”食用，古代人当“染色剂”染布！");
        author.add("我的小宠花猫");

        img.add("http://n.sinaimg.cn/sinakd20210505ac/419/w723h496/20210505/12e7-kppteat0214708.jpg");
        title.add("几种花卉，比玫瑰花还漂亮，四季常开，种植容易");
        author.add("白马抡天下");

        img.add("http://n.sinaimg.cn/sinakd20210429ac/223/w640h383/20210429/4f9c-kphwums2551205.jpg");
        title.add("假期出去游玩,遇到这6种植物花卉不要靠近,都是有毒植物");
        author.add("小雄美i食");

        recyclerView = root.findViewById(R.id.ev_recommend);
        adapter = new MultiTypeAdapter();
        adapter.register(HomeDailyRecommendItem.class, new HomeDailyRecommendBinder());
        recyclerView.setAdapter(adapter);

        mItems = new Items();
        for (int i=0; i<img.size(); i++) {
            HomeDailyRecommendItem homeDailyRecommendItem = new HomeDailyRecommendItem();
            homeDailyRecommendItem.setImg(img.get(i));
            homeDailyRecommendItem.setTitle(title.get(i));
            homeDailyRecommendItem.setAuthor(author.get(i));
            mItems.add(homeDailyRecommendItem);
        }
        adapter.setItems(mItems);
        adapter.notifyDataSetChanged();
    }
}