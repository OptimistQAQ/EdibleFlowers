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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.edibleflowers.activity.AboutUsActivity;
import com.example.edibleflowers.activity.PersonalActivity;
import com.example.edibleflowers.activity.SignInActivity;
import com.example.edibleflowers.utils.ActivityCollectorUtil;
import com.example.edibleflowers.utils.CurrentUserInfo;
import com.example.edibleflowers.R;
import com.example.edibleflowers.activity.RegisterActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class MineFragment extends Fragment implements View.OnClickListener {

    private View root;
    private ImageButton btnSettings;
    private ImageButton btnAccountSettings;
    private Button btnRegister;

    private LinearLayout menuUser, menuAbout, menuSetting, menuExit;
    private TextView userName, tv_time_long, tv_dist, tv_count;
    private CircleImageView profileImage;

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
        btnAccountSettings = root.findViewById(R.id.mine_account_settings);
        btnAccountSettings.setOnClickListener(this);
        menuUser = root.findViewById(R.id.menu_user);
        menuUser.setOnClickListener(this);
        menuAbout = root.findViewById(R.id.menu_about);
        menuAbout.setOnClickListener(this);
        menuSetting = root.findViewById(R.id.menu_setting);
        menuSetting.setOnClickListener(this);
        menuExit = root.findViewById(R.id.menu_exit);
        menuExit.setOnClickListener(this);

        //将用户名修改为当前登录用户
        userName = root.findViewById(R.id.user_name);
        userName.setText(CurrentUserInfo.nick_name);

        //加载用户头像
        profileImage = root.findViewById(R.id.profile_image);
        Glide.with(this).load(CurrentUserInfo.profilePhoto).into(profileImage);

        tv_time_long = root.findViewById(R.id.tv_time_long);
        tv_time_long.setText(String.valueOf(CurrentUserInfo.like + "个"));
        tv_dist = root.findViewById(R.id.tv_dist);
        tv_dist.setText(String.valueOf(CurrentUserInfo.publish + "篇"));
        tv_count = root.findViewById(R.id.tv_count);
        tv_count.setText(String.valueOf(CurrentUserInfo.praise + "篇"));

        if (!userName.getText().toString().equals("游客")) {
            btnRegister.setText("已登录");
        } else {
            btnRegister.setText("立即注册");
        }


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
                if (btnRegister.getText().toString().equals("立即注册")) {
                    Toast.makeText(getActivity(), "Register", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), RegisterActivity.class));
                }
                break;
            case R.id.menu_user:
                Intent userIntent = new Intent(root.getContext(), PersonalActivity.class);
                startActivity(userIntent);
                Toast.makeText(getActivity(), "个人中心", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_about:
                Intent aboutIntent = new Intent(root.getContext(), AboutUsActivity.class);
                startActivity(aboutIntent);
                Toast.makeText(getActivity(), "关于", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_setting:
                Toast.makeText(getActivity(), "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_exit:
                ActivityCollectorUtil.finishAllActivity();
                Intent loginIntent = new Intent(root.getContext(), SignInActivity.class);
                startActivity(loginIntent);
                Toast.makeText(getActivity(), "退出登录", Toast.LENGTH_SHORT).show();
                break;
            default:
                Log.d("MineFragment", "OnClick Default");
                break;
        }
    }
}
