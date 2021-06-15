package com.example.edibleflowers.fragment.home;

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
import com.example.edibleflowers.binder.HomeDailyRecommendBinder;
import com.example.edibleflowers.item.HomeDailyRecommendItem;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeDailyRecommendFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @author 65667
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
        List<String> main = new ArrayList<>();

        recyclerView = root.findViewById(R.id.ev_recommend);
        adapter = new MultiTypeAdapter();
        adapter.register(HomeDailyRecommendItem.class, new HomeDailyRecommendBinder());
        recyclerView.setAdapter(adapter);

        OkGo.<String>post("http://10.132.41.48:9596/showNews")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("response", response.body());
                        mItems = new Items();
                        response.toString();
                        JSONArray jsonArray = JSON.parseArray(response.body());
                        for (int i=5; i<jsonArray.size(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            img.add(jsonObject.getString("nimg"));
                            author.add(jsonObject.getString("nathor"));
                            title.add(jsonObject.getString("ntitle"));
                            main.add(jsonObject.getString("ndetail"));
                        }
                        for (int i=0; i<img.size(); i++) {
                            HomeDailyRecommendItem homeDailyRecommendItem = new HomeDailyRecommendItem();
                            homeDailyRecommendItem.setImg(img.get(i));
                            homeDailyRecommendItem.setTitle(title.get(i));
                            homeDailyRecommendItem.setAuthor(author.get(i));
                            homeDailyRecommendItem.setMain(main.get(i));
                            mItems.add(homeDailyRecommendItem);
                        }
                        adapter.setItems(mItems);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}