package com.example.edibleflowers.fragment.zixun;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.edibleflowers.R;
import com.example.edibleflowers.binder.ZixunClassificationBinder;
import com.example.edibleflowers.item.ZixunClassificationItem;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ZixunClassificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @author 65667
 */
public class ZixunClassificationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View root;

    private RecyclerView recyclerView;
    private MultiTypeAdapter adapter;
    private GridLayoutManager layoutManager;

    private Items mItems;


    public ZixunClassificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ZixunClassificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ZixunClassificationFragment newInstance(String param1, String param2) {
        ZixunClassificationFragment fragment = new ZixunClassificationFragment();
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
        root = inflater.inflate(R.layout.fragment_zixun_classification, container, false);
        initView();
        return root;
    }

    private void initView() {
        List<String> img = new ArrayList<>();
        List<String> name = new ArrayList<>();
        List<String> huayu = new ArrayList<>();

        recyclerView = root.findViewById(R.id.ev_classification);
        layoutManager = new GridLayoutManager(root.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MultiTypeAdapter();
        adapter.register(ZixunClassificationItem.class, new ZixunClassificationBinder());
        recyclerView.setAdapter(adapter);

        //[{"imessage":"青春时期的回忆、惹人怜爱、轻愁。","iname":"丁香花","ino":1,"iphoto":"https://bkimg.cdn.bcebos.com/pic/5366d0160924ab18972b8fae5eb0f1cd7b899e519d5a?x-bce-process=image/resize,m_fill,w_360,h_280,align_50,limit_0/format,f_auto"},{"imessage":"白日梦，思慕，想念我","iname":"三色堇","ino":2,"iphoto":"https://bkimg.cdn.bcebos.com/pic/b8014a90f603738d820ee227b31bb051f819ec6b?x-bce-process=image/resize,m_lfit,w_440,limit_1/format,f_auto"},{"imessage":"谦逊质朴，无私奉献。","iname":"诸葛菜","ino":3,"iphoto":"http://img-arch.pconline.com.cn/images/upload/upc/tx/photoblog/1103/20/c9/7059208_7059208_1300628027484.jpg"}]
        OkGo.<String>post("http://10.132.41.48:9596/showClassification")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("classification_response", response.body());
                        mItems = new Items();
                        response.toString();
                        JSONArray jsonArray = JSON.parseArray(response.body());
                        for (int i=0; i<jsonArray.size(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            img.add(jsonObject.getString("iphoto"));
                            name.add(jsonObject.getString("iname"));
                            huayu.add(jsonObject.getString("imessage"));
                        }
                        for (int i=0; i<img.size(); i++) {
                            ZixunClassificationItem zixunClassificationItem = new ZixunClassificationItem();
                            zixunClassificationItem.setImg(img.get(i));
                            zixunClassificationItem.setName(name.get(i));
                            zixunClassificationItem.setHuayu(huayu.get(i));
                            mItems.add(zixunClassificationItem);
                        }
                        adapter.setItems(mItems);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}