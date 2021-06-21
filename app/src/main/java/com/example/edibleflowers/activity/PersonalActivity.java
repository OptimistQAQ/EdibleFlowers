package com.example.edibleflowers.activity;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.edibleflowers.R;
import com.example.edibleflowers.utils.CurrentUserInfo;
import com.example.edibleflowers.utils.Url;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * @author 65667
 */
public class PersonalActivity extends AppCompatActivity {

    private TextView mMenu;//Toolbar上的菜单
    private View mMenuLayout, mWarnLayout;

    /**
     * 账号显示（不允许修改）
     */
    private TextView mAccount;

    /**
     * 密码和昵称
     */
    private EditText mUserNameText, mPasswordText;


    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        mWarnLayout = findViewById(R.id.user_info_show_layout);
        mMenuLayout = findViewById(R.id.user_Info_edit_layout);
        mAccount = findViewById(R.id.account);
        mUserNameText = findViewById(R.id.username);
        mPasswordText = findViewById(R.id.password);

        mAccount.setText(CurrentUserInfo.name);
        mUserNameText.setText(CurrentUserInfo.nick_name);
        mPasswordText.setText(CurrentUserInfo.password);


        mMenu = findViewById(R.id.tv_edit);
        /*Toolbar上的"编辑"按钮*/
        mMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEdit = !isEdit;
                if (isEdit) {
                    //从完成变成编辑
                    mMenu.setText("完成");//修改显示文字
                } else {
                    mMenu.setText("编辑");
                    //从编辑变成完成
                    final String name = mUserNameText.getText().toString();
                    final String password = mPasswordText.getText().toString();
                    OkGo.<String>post(Url.localUrl + "/updateInfo")
                            .params("uno", CurrentUserInfo.uno)
                            .params("nick_name", name)
                            .params("password", password)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    Log.e("response", response.body());
                                    CurrentUserInfo.nick_name = name;
                                    CurrentUserInfo.password = password;
                                    Toast.makeText(PersonalActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                }

                mWarnLayout.setVisibility(isEdit ? View.GONE : View.VISIBLE);
                mMenuLayout.setVisibility(isEdit ? View.VISIBLE : View.GONE);
            }
        });

    }
}