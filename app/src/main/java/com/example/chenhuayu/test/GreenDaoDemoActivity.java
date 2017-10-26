package com.example.chenhuayu.test;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenhuayu.test.db.User;
import com.usher.greendao_demo.greendao.gen.DaoMaster;
import com.usher.greendao_demo.greendao.gen.DaoSession;
import com.usher.greendao_demo.greendao.gen.UserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;

public class GreenDaoDemoActivity extends AppCompatActivity {
    UserDao userDao;
    private EditText etId;
    private EditText etName;
    private Button btnAdd;
    private Button btnDelete;
    private Button btnQuery;
    private TextView tvQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao_demo);
        initView();
        initDbHelp();

        /*新增一条数据*/
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etId.getText().toString();
                String name = etName.getText().toString();
                if (isNotEmpty(id) && isNotEmpty(name)) {
                    QueryBuilder qb = userDao.queryBuilder();
                    ArrayList<User> list = (ArrayList<User>) qb.where(UserDao.Properties.Id.eq(id)).list();
                    if (list.size() > 0) {
                        Toast.makeText(GreenDaoDemoActivity.this, "主键重复", Toast.LENGTH_SHORT).show();
                    } else {
                        userDao.insert(new User(Long.valueOf(id), name));
                        Toast.makeText(GreenDaoDemoActivity.this, "插入数据成功", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (isEmpty(id) && isNotEmpty(name)) {
                        Toast.makeText(GreenDaoDemoActivity.this, "id为空", Toast.LENGTH_SHORT).show();
                    }
                    if (isEmpty(name) && isNotEmpty(id)) {
                        Toast.makeText(GreenDaoDemoActivity.this, "姓名为空", Toast.LENGTH_SHORT).show();
                    }
                    if (isEmpty(id) && isEmpty(name)) {
                        Toast.makeText(GreenDaoDemoActivity.this, "请填写信息", Toast.LENGTH_SHORT).show();
                    }

                }
                etId.setText("");
                etName.setText("");
            }
        });

        /*删除指定数据*/
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etId.getText().toString();
                if (isNotEmpty(id)) {
                    userDao.deleteByKey(Long.valueOf(id));
                    QueryBuilder qb = userDao.queryBuilder();
                    ArrayList<User> list = (ArrayList<User>) qb.where(UserDao.Properties.Id.eq(id)).list();
                    if (list.size() < 1) {
                        Toast.makeText(GreenDaoDemoActivity.this, "删除数据成功", Toast.LENGTH_SHORT).show();
                        etId.setText("");
                        etName.setText("");
                    }
                } else {
                    Toast.makeText(GreenDaoDemoActivity.this, "id为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*查询数据*/
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etId.getText().toString();
                if (isNotEmpty(id)) {
                    QueryBuilder qb = userDao.queryBuilder();
                    ArrayList<User> list = (ArrayList<User>) qb.where(UserDao.Properties.Id.eq(id)).list();
                    if (list.size() > 0) {
                        String text = "";
                        for (User user : list) {
                            text = text + "\r\n" + user.getName();
                        }
                        tvQuery.setText(text);
                    } else {
                        tvQuery.setText("");
                        Toast.makeText(GreenDaoDemoActivity.this, "不存在该数据", Toast.LENGTH_SHORT).show();
                    }
                    etId.setText("");
                    etName.setText("");
                } else {
                    Toast.makeText(GreenDaoDemoActivity.this, "id为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*初始化数据库相关*/
    private void initDbHelp() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "recluse-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
    }

    private void initView() {
        etId = (EditText) findViewById(R.id.etId);
        etName = (EditText) findViewById(R.id.etName);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnQuery = (Button) findViewById(R.id.btnQuery);
        tvQuery = (TextView) findViewById(R.id.tvQuery);
    }

    private boolean isNotEmpty(String s) {
        if (s != null && !s.equals("") || s.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isEmpty(String s) {
        if (isNotEmpty(s)) {
            return false;
        } else {
            return true;
        }

    }
}
