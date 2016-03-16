package matio.angel.com.myapplication_gamehelper.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import matio.angel.com.myapplication_gamehelper.R;

/**
 * Created by Angel on 2016/1/15.
 * 登录
 */
public class LoginActivity extends AppCompatActivity {

    private EditText telEdt;

    private EditText passwordEdt;

    private Button loginBtn;

    private ImageView lastImag;

    private TextView registerTxt;

    private SQLiteDatabase db;

    private String whichone;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_user);

        //初始化控件
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        telEdt = (EditText) findViewById(R.id.tel_edt);
        passwordEdt = (EditText) findViewById(R.id.password_edt);
        loginBtn = (Button) findViewById(R.id.login_user);
        lastImag = (ImageView) findViewById(R.id.lastpage_loginuser);
        registerTxt = (TextView) findViewById(R.id.nextpage_register);
    }

    /**
     * 点击事件
     *
     * @param view
     */
    public void doUserLogin(View view) {
        switch (view.getId()) {
            case R.id.lastpage_loginuser:
                //返回上一页
                finish();
                break;
            case R.id.nextpage_register:
                //跳到注册界面
                Intent intent2 = new Intent(this, RegisterActivity.class);
                startActivity(intent2);
                break;
            case R.id.forgetpassword:
                //忘记密码
                break;
            case R.id.login_user:
                //点击登录
                // login();
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
        String tel = telEdt.getText().toString();
        String password = passwordEdt.getText().toString();
        if (!TextUtils.isEmpty(tel) && !TextUtils.isEmpty(password)) {
            //如果手机号和密码都不为空
            //用手机号和密码去数据库查询
            Cursor cursor = db.rawQuery("select tel from userinfo where tel like ? and password like ?",
                    new String[]{tel, password});
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String queryTel = cursor.getString(cursor.getColumnIndex("tel"));
                    if (tel.equals(queryTel)) {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        }
    }
}