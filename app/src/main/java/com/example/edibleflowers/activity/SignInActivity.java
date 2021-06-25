package com.example.edibleflowers.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.edibleflowers.utils.ActivityCollectorUtil;
import com.example.edibleflowers.utils.CurrentUserInfo;
import com.example.edibleflowers.MainActivity;
import com.example.edibleflowers.R;
import com.example.edibleflowers.bean.User;
import com.example.edibleflowers.utils.Url;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 65667
 */
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

    private static Tencent mTencent;
    private static String APP_ID = "101801728";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ActivityCollectorUtil.addActivity(this);

        initView();
        initDropDownGroup();

        mTencent = Tencent.createInstance(APP_ID, SignInActivity.this);
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
        mTermsView.setOnClickListener(this);
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
                CurrentUserInfo.nick_name = "游客";
                CurrentUserInfo.profilePhoto = "http://ishero.net/share/06.png";
                startActivity(new Intent(SignInActivity.this, MainActivity.class));
                break;
            case R.id.iv_clear_account:
                //清除账号
                eUserName.setText("");
                break;
            case R.id.iv_clear_password:
                //清除密码：
                ePassword.setText("");
                break;
            case R.id.login_qq:
                login_qq();
                break;
            case R.id.login_we_chat:
                Toast.makeText(SignInActivity.this, "正在开发中…………", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_terms_of_service:
                startActivity(new Intent(SignInActivity.this, ServiceActivity.class));
                break;
            default:
                break;
        }
    }

    private void login_qq(){
        if(!mTencent.isSessionValid()){
            mTencent.login(SignInActivity.this,"all",loginListener);
        }
    }

    //新建BaseListener实例重写doComplete方法
    IUiListener loginListener = new BaseUiListener(){
        @Override
        protected void doComplete(JSONObject values) {
            initOpenidAndToken(values);
            Log.e("结果updateUserInfo","OnComplete成功");
            JSONObject jsonObject = values;
            Toast.makeText(SignInActivity.this,"授权成功",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignInActivity.this,MainActivity.class));
        }
    };

    private class BaseUiListener implements IUiListener {

        protected void doComplete(JSONObject values) {
            Log.e("result",values.toString());
        }

        @Override
        public void onComplete(Object response) {
            if (response == null){
                Log.e("QQ ---> BaseListener","返回为空，登录失败");
                return;
            }
            JSONObject jsonObject = (JSONObject) response;
            if (jsonObject.length() == 0) {
                Log.e("QQ ---> BaseListener","返回为空，登录失败2");
                return;
            }
            Log.i("登陆成功",response.toString());
            doComplete(jsonObject);
        }

        @Override
        public void onError(UiError e) {
            Log.e("onError:", "code:" + e.errorCode + ", msg:"
                    + e.errorMessage + ", detail:" + e.errorDetail);
        }
        @Override
        public void onCancel() {
            Log.e("onCancel", "");
        }
    }

    //QQ获取用户OPENID和TOKEN
    public static void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
                Log.i("OPENID",openId);
            }
        } catch(Exception e) {
        }
    }

    private void initSignIn() {

        if ("".equals(eUserName.getText().toString()) || "".equals(ePassword.getText().toString())) {
            Toast.makeText(this, "请正确输入用户名和密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String bt_name = eUserName.getText().toString();
        String bt_password = ePassword.getText().toString();

        OkGo.<String>post(Url.aliyunUrl + "/login")
                .params("name", bt_name)
                .params("password", bt_password)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                            User user =JSON.parseObject(response.body(), User.class);
                            Log.e("user_body", response.body());
                            if (user.getUname() != null) {
                                CurrentUserInfo.uno = user.getUno();
                                CurrentUserInfo.name = user.getUname();
                                CurrentUserInfo.nick_name = user.getUnickName();
                                CurrentUserInfo.password = user.getUpassword();
                                CurrentUserInfo.profilePhoto = user.getUprofilePhoto();
                                CurrentUserInfo.like = user.getUtotalTime();
                                CurrentUserInfo.publish = user.getUtotalDistance();
                                CurrentUserInfo.praise = user.getUtotalLine();
                                Toast.makeText(SignInActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                Intent mainActivity = new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(mainActivity);
                                finish();
                            }
                            else {
                                Toast.makeText(SignInActivity.this, "账号或密码错误，无法登录", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
