package com.example.chenhuayu.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chenhuayu.test.bean.TestBean;

public class ToolbarActivity extends Activity implements View.OnClickListener {

    private Button btn;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(R.layout.activity_toolbar);
        btn = (Button) findViewById(R.id.btn_toolbar);
        editText = (EditText) findViewById(R.id.et_toolbar);
        btn.setOnClickListener(this);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("name")) {

            Object object = intent.getSerializableExtra("name");
            if (object instanceof TestBean) {
                TestBean testBean = (TestBean) object;
                editText.setText(testBean.name);
            }

        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_toolbar) {
            Toast.makeText(this, "haha...", Toast.LENGTH_SHORT).show();
        }
    }

    private void setTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
