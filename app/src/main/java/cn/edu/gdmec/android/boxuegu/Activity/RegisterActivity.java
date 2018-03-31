package cn.edu.gdmec.android.boxuegu.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.Utils.MD5Utils;
import cn.edu.gdmec.android.boxuegu.R;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout title_bar;
    private EditText et_user_name;
    private EditText et_psw;
    private EditText et_psw_again;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_psw = (EditText) findViewById(R.id.et_psw);
        et_psw_again = (EditText) findViewById(R.id.et_psw_again);
        btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(this);
        tv_main_title.setText("注册");
        title_bar.setBackgroundColor(Color.TRANSPARENT);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });

    }

    private void submit() {
        // validate
        String name = et_user_name.getText().toString().trim();
        String psw = et_psw.getText().toString().trim();
        String again = et_psw_again.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(psw)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(again)) {
            Toast.makeText(this, "请再次输入密码", Toast.LENGTH_SHORT).show();
            return;
        }else if (!psw.equals(again)){
            Toast.makeText(this, "输入两次的结果不一样", Toast.LENGTH_SHORT).show();
            return;
        }else if (isExistUserName(name)){
            Toast.makeText(this, "此账户名已存在", Toast.LENGTH_SHORT).show();
            return;
        }else {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            saveRegisterInfo(name,psw);
            Intent data=new Intent();
            data.putExtra("userName",name);
            setResult(RESULT_OK,data);
            RegisterActivity.this.finish();
        }



    }

    private void saveRegisterInfo(String name, String psw) {
        String md5Psw= MD5Utils.md5(psw);
        SharedPreferences sharedPreferences=getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(name,md5Psw);
        editor.commit();

    }

    private boolean isExistUserName(String name) {
        boolean has_userName=false;
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw=sp.getString(name,"");
        if(!TextUtils.isEmpty(spPsw)){
            has_userName=true;
        }
        return has_userName;


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                //TODO implement
                submit();
                break;
        }
    }
}
