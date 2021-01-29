package com.example.edibleflowers.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.edibleflowers.MainActivity;
import com.example.edibleflowers.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText eUserName;
    private EditText ePassword;
    private ImageView mClearAccountView;
    private ImageView mClearPasswordView,mBtnQq,mBtnWeChat;
    private CheckBox mEyeView;
    private CheckBox mDropDownView;
    private Button mLoginView,mSkipView;
    private TextView mForgetPsdView;
    private TextView mRegisterView;
    private LinearLayout mTermsLayout;
    private TextView mTermsView;
    private RelativeLayout mPasswordLayout;
    private List<View> mDropDownInvisibleViews;
    private TextView name,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initView();
        initDropDownGroup();

        ePassword.setLetterSpacing(0.2f);
        mClearAccountView.setOnClickListener(this);
        mClearPasswordView.setOnClickListener(this);

        mEyeView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    ePassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                else
                    ePassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        eUserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //当账号栏正在输入状态时，密码栏的清除按钮和眼睛按钮都隐藏
                if(hasFocus){
                    mClearPasswordView.setVisibility(View.INVISIBLE);
                    mEyeView.setVisibility(View.INVISIBLE);
                }else {
                    mClearPasswordView.setVisibility(View.VISIBLE);
                    mEyeView.setVisibility(View.VISIBLE);
                }
            }
        });

        ePassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //当密码栏为正在输入状态时，账号栏的清除按钮隐藏
                if(hasFocus)
                    mClearAccountView.setVisibility(View.INVISIBLE);
                else mClearAccountView.setVisibility(View.VISIBLE);
            }
        });

        mDropDownView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //下拉按钮点击的时候，密码栏、忘记密码、新用户注册、同意服务条款先全部隐藏
                    setDropDownVisible(View.INVISIBLE);
                    //下拉箭头变为上拉箭头
                    //弹出一个popupWindow
//                    showDropDownWindow();
                }else {
                    setDropDownVisible(View.VISIBLE);
                }
            }
        });

    }

    private void initView() {
        eUserName = findViewById(R.id.et_input_username);
        ePassword = findViewById(R.id.et_input_password);
        mClearAccountView = findViewById(R.id.iv_clear_account);
        mClearAccountView.setOnClickListener(this);
        mClearPasswordView = findViewById(R.id.iv_clear_password);
        mClearPasswordView.setOnClickListener(this);
        mEyeView = findViewById(R.id.iv_login_open_eye);
        mDropDownView = findViewById(R.id.cb_login_drop_down);
        mLoginView = findViewById(R.id.btn_login);
        mLoginView.setOnClickListener(this);
        mForgetPsdView = findViewById(R.id.tv_forget_password);
        mRegisterView = findViewById(R.id.tv_register_account);
        mRegisterView.setOnClickListener(this);
        mTermsLayout = findViewById(R.id.ll_terms_of_service_layout);
        mTermsView = findViewById(R.id.tv_terms_of_service);
        mPasswordLayout = findViewById(R.id.rl_password_layout);
        mSkipView = findViewById(R.id.btn_skip);
        mSkipView.setOnClickListener(this);
        mBtnQq = findViewById(R.id.login_qq);
        name = findViewById(R.id.main_name);
        title = findViewById(R.id.main_title);
        mBtnWeChat = findViewById(R.id.login_we_chat);
        mBtnQq.setOnClickListener(this);
        mBtnWeChat.setOnClickListener(this);
        Typeface typeface = Typeface.createFromAsset(this.getAssets(),"fly.ttf");
        name.setTypeface(typeface);
        title.setTypeface(typeface);
    }

    private void initDropDownGroup() {
        mDropDownInvisibleViews = new ArrayList<>();
        mDropDownInvisibleViews.add(ePassword);
        mDropDownInvisibleViews.add(mForgetPsdView);
        mDropDownInvisibleViews.add(mRegisterView);
        mDropDownInvisibleViews.add(mPasswordLayout);
        mDropDownInvisibleViews.add(mLoginView);
        mDropDownInvisibleViews.add(mTermsLayout);
    }

    private void setDropDownVisible(int visible) {
        for (View view:mDropDownInvisibleViews){
            view.setVisibility(visible);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register_account:
                //注册
                startActivity(new Intent(SignInActivity.this, RegisterActivity.class));
                break;
            case R.id.btn_login:
                //登录
                initSignIn();
                break;
            case R.id.btn_skip:
                //游客进入
                finish();
                startActivity(new Intent(SignInActivity.this, MainActivity.class));
                break;
            case R.id.iv_clear_account:
                //清除账号
                eUserName.setText("");
                break;
            case R.id.iv_clear_password:
                //清楚密码：
                ePassword.setText("");
                break;
            default:
                break;
        }
    }

    private void initSignIn() {

        if ("".equals(eUserName.getText().toString()) || "".equals(ePassword.getText().toString())) {
            Toast.makeText(this, "请正确输入用户名和密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String bt_name = eUserName.getText().toString();
        String bt_password = ePassword.getText().toString();

        Toast.makeText(SignInActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
        Intent mainActivity = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(mainActivity);
        finish();

        OkGo.<String>post("")
                .params("name", bt_name)
                .params("password", bt_password)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                            Toast.makeText(SignInActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                            Intent mainActivity = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(mainActivity);
                            finish();
                        }
//                        else {
//                            Toast.makeText(SignInActivity.this, "账号或密码错误，无法登录", Toast.LENGTH_SHORT).show();
//                        }
                });
    }
}