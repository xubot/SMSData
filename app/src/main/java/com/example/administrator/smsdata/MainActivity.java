package com.example.administrator.smsdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.ContactsPage;
import cn.smssdk.gui.RegisterPage;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //启动SDK
        SMSSDK.initSDK(MainActivity.this, "1c17c306a8e6d", "279c8341438fe71f4e3b5ef5a8c1aba2");
    }
    //控件及赋值
    private void initView() {
        TextView code= (TextView) findViewById(R.id.code);
        TextView man= (TextView) findViewById(R.id.man);
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开注册页面
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
                        // 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            @SuppressWarnings("unchecked")
                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");
                        }
                    }
                });
                registerPage.show(MainActivity.this);
            }
        });
        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开通信录好友列表页面
                ContactsPage contactsPage = new ContactsPage();
                contactsPage.show(MainActivity.this);
            }
        });
    }
}
