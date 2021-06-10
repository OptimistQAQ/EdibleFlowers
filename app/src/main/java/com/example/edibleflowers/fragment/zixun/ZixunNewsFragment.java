package com.example.edibleflowers.fragment.zixun;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edibleflowers.R;
import com.example.edibleflowers.binder.ZixunNewsBinder;
import com.example.edibleflowers.item.ZixunNewsItem;

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

        img.add("http://i0.sinaimg.cn/dy/c/2012-03-29/1332982771_V9gkot.jpg");
        title.add("40种食用花卉 “绽放”市民餐桌");
        author.add("新浪");
        time.add("2012年03月29日06:35");

        img.add("http://n.sinaimg.cn/sinakd10123/590/w714h676/20210116/8481-khstaxs7825983.jpg");
        title.add("几款花卉，不仅观赏值高，还能食用，适合家养");
        author.add("皮皮望电影");
        time.add("2021年01月19日 23:14");

        img.add("http://n.sinaimg.cn/sinacn00/219/w640h379/20180830/594c-hikcahf6528948.jpg");
        title.add("可以吃的花朵，这些美丽的食用花卉，不仅可以观赏还能做美食！");
        author.add("无处下西楼飞翔");
        time.add("2018年08月30日 00:06");

        img.add("http://n.sinaimg.cn/sinakd20210430ac/458/w1206h852/20210430/fcad-kphwums3884259.jpg");
        title.add("春天种上这4种花卉植物，既能赏花还能食用！");
        author.add("实鲜新食");
        time.add("2021年04月30日 09:38");

        img.add("http://n.sinaimg.cn/sinakd10107/24/w500h324/20201113/0a7c-kcunqze4558017.jpg");
        title.add("此花既能观赏又能食用，在东北是家常菜，也是漂亮的盆栽花卉");
        author.add("情感解忧课堂");
        time.add("2020年11月13日 07:24");

        recyclerView = root.findViewById(R.id.ev_news);
        adapter = new MultiTypeAdapter();
        adapter.register(ZixunNewsItem.class, new ZixunNewsBinder());
        recyclerView.setAdapter(adapter);

        mItems = new Items();
        for (int i=0; i<img.size(); i++) {
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
}