package com.example.edibleflowers.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.edibleflowers.R;
import com.example.edibleflowers.activity.RegisterActivity;

public class MineFragment extends Fragment implements View.OnClickListener {

    private View root;
    private ImageButton btnSettings;
    private ImageButton btnAccountSettings;
    private Button btnRegister;
    private Button btnMyFavourite;
    private Button btnAbout;
    private Button btnLogout;

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

    /**
     * 初始化控件
     */
    private void initView()
    {
        btnSettings = root.findViewById(R.id.mine_settings);
        btnSettings.setOnClickListener(this);
        btnRegister = root.findViewById(R.id.mine_register);
        btnRegister.setOnClickListener(this);
        btnMyFavourite = root.findViewById(R.id.mine_my_favourite);
        btnMyFavourite.setOnClickListener(this);
        btnAbout = root.findViewById(R.id.mine_about);
        btnAbout.setOnClickListener(this);
        btnAccountSettings = root.findViewById(R.id.mine_account_settings);
        btnAccountSettings.setOnClickListener(this);
        btnLogout = root.findViewById(R.id.mine_logout);
        btnLogout.setOnClickListener(this);
    }

    /**
     * 处理点击事件
     * @param v 控件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_settings:
                Toast.makeText(getActivity(), "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_register:
//                Toast.makeText(getActivity(), "Register", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), RegisterActivity.class));
                break;
            case R.id.mine_my_favourite:
                Toast.makeText(getActivity(), "MyFavourite", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_about:
                Toast.makeText(getActivity(), "About", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_account_settings:
                Toast.makeText(getActivity(), "AccountSettings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_logout:
                Toast.makeText(getActivity(), "Logout", Toast.LENGTH_SHORT).show();
                break;
            default:
                Log.d("MineFragment", "OnClick Default");
                break;
        }
    }
}
