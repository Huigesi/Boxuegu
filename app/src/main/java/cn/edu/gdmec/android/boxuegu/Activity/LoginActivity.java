package cn.edu.gdmec.android.boxuegu.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.MD5Utils;
import cn.edu.gdmec.android.boxuegu.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_back;
    private TextView tv_main_title;
    private ImageView iv_head;
    private EditText et_user_name;
    private EditText et_psw;
    private Button btn_login;
    private TextView tv_register;
    private TextView tv_find_psw;

    private String spPsw,name,psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();


    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        iv_head = (ImageView) findViewById(R.id.iv_head);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_psw = (EditText) findViewById(R.id.et_psw);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_find_psw = (TextView) findViewById(R.id.tv_find_psw);

        btn_login.setOnClickListener(this);
        tv_main_title.setText("登录");
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,1);

            }
        });
        tv_find_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent=new Intent(LoginActivity.this,FindPswActivity.class);
                startActivity(intent);*/
            }
        });
    }

    private void submit() {
        // validate
         name = et_user_name.getText().toString().trim();
         psw = et_psw.getText().toString().trim();
        String md5Psw= MD5Utils.md5(psw);
        spPsw=readPsw(name);

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(psw)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }else if (md5Psw.equals(spPsw)){
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            saveLoginStatus(true,name);
            Intent data=new Intent();
            data.putExtra("isLogin",true);
            setResult(RESULT_OK,data);
            LoginActivity.this.finish();
            return;
        }
        else if (!TextUtils.isEmpty(spPsw)&&!md5Psw.equals(spPsw)){
            Toast.makeText(this, "用户名和密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }else {
            Toast.makeText(this, "此用户名不存在", Toast.LENGTH_SHORT).show();
        }

        // TODO validate success, do something


    }

    private void saveLoginStatus(boolean status, String name) {
        SharedPreferences sharedPreferences=getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("isLogin",status);
        editor.putString("loginUserName",name);
        editor.commit();

    }

    private String readPsw(String name) {
        SharedPreferences sharedPreferences=getSharedPreferences("loginInfo",MODE_PRIVATE);
        return sharedPreferences.getString(name,"");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                //TODO implement
                submit();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            String userName=data.getStringExtra("userName");
            if (!TextUtils.isEmpty(userName)){
                et_user_name.setText(userName);
                et_user_name.setSelection(userName.length());

            }
        }
    }
}
