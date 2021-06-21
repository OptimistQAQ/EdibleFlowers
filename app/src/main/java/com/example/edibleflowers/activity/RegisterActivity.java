package com.example.edibleflowers.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.edibleflowers.R;
import com.example.edibleflowers.utils.ActivityCollectorUtil;
import com.example.edibleflowers.utils.Url;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 65667
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText mUserName;
    private EditText mPassword, mPasswordRe;
    private Button btnRegister;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActivityCollectorUtil.addActivity(this);

        init();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initView();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void init() {
        mUserName = (EditText) findViewById(R.id.input_name);
        mPassword = (EditText) findViewById(R.id.input_password);
        mPasswordRe = (EditText) findViewById(R.id.input_password_again);
        btnRegister = (Button) findViewById(R.id.btn_sign_up);
        btnBack = (ImageView) findViewById(R.id.back_sign_up);
    }

    private void initView(){
        if("".equals(mUserName.getText().toString())){
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(mPassword.getText().toString())){
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(mPasswordRe.getText().toString())){
            Toast.makeText(this, "请再次输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        String password = mPassword.getText().toString();
        if(!password.equals(mPasswordRe.getText().toString()))
        {
            Toast.makeText(this, "两次输入密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.equals(mPasswordRe.getText().toString()))
        {
            Map<String, String> param = new HashMap<>();
            param.put("name", mUserName.getText().toString());
            param.put("password", mPassword.getText().toString());
            OkGo.<String>post(Url.localUrl + "/register")
                    .params("name", mUserName.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Log.e("pass", mPasswordRe.getText().toString());
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            Intent signInActivity = new Intent(RegisterActivity.this, SignInActivity.class);
                            startActivity(signInActivity);
                            finish();
                        }
                    });
        }
    }
}