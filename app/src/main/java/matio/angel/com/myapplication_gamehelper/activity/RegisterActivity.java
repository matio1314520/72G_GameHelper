package matio.angel.com.myapplication_gamehelper.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import matio.angel.com.myapplication_gamehelper.R;

/**
 * Created by Angel on 2016/1/15.
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText telLEdt;

    private EditText othernameEdt;

    private EditText passwordEdt;

    private EditText inviteEdt;

    private SQLiteDatabase db;

    private String tel;

    private String password;

    private String othername;

    private CheckBox checkBox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        //初始化控件
        initView();

        //创建数据库,数据表
        createMyDb();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        telLEdt = (EditText) findViewById(R.id.tel_register_edt);
        othernameEdt = (EditText) findViewById(R.id.other_register_edt);
        passwordEdt = (EditText) findViewById(R.id.password_register_edt);
        inviteEdt = (EditText) findViewById(R.id.invite_register_edt);
        checkBox = (CheckBox) findViewById(R.id.checkbox_register);
    }

    /**
     * 下一步
     *
     * @param view
     */
    public void next(View view) {
        tel = telLEdt.getText().toString();
        othername = othernameEdt.getText().toString();
        password = passwordEdt.getText().toString();
        if (!TextUtils.isEmpty(tel) && !TextUtils.isEmpty(othername) && !TextUtils.isEmpty(password)) {
            //查询数据
            Cursor cursor = db.rawQuery("select tel from gamerinfo where tel like ?", new String[]{tel});
            if (cursor != null) {
                //不为空
                while (cursor.moveToNext()) {
                    String queryTel = cursor.getString(cursor.getColumnIndex("tel"));
                }
            } else {
                //被勾选
                if (checkBox.isChecked()) {
                    //插入数据
                    insertData();
                    //跳转页面
                    toAnotherActivity();
                }

            }
        }
    }

    /**
     * 跳往手机验证界面
     */
    private void toAnotherActivity() {
        Intent intent = new Intent(this, TelActivity.class);
        intent.putExtra("tel", tel);
        startActivity(intent);

    }

    /**
     * 插入数据
     */
    private void insertData() {
        String sql = "insert into gamerinfo(tel,password,othername) values(" + tel +
                "," + password + "," + othername + ")";

        db.execSQL(sql);
    }

    /**
     * 创建数据库
     */
    private void createMyDb() {
        db = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + "/gamer.db", null);

        //创建数据表
        String sql = "create table if not exists gamerinfo(" +
                "_id integer primary key," +
                "tel text," +
                "password text," +
                "othername text)";
        db.execSQL(sql);
    }
}