package com.example.edibleflowers.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.edibleflowers.R;

public class MineFragment extends Fragment implements View.OnClickListener {

    private View root;
    private ImageButton mine_settings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_mine, container, false);
        initView();
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView()
    {
        mine_settings = root.findViewById(R.id.mine_settings);
        mine_settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_settings:
                Toast.makeText(getActivity(), "Settigs", Toast.LENGTH_SHORT).show();
                break;
            default:
                Log.d("MineFrament", "OnClick Default");
                break;
        }
    }
}
