package com.chenhailong.shareprefrence;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    private EditText userName;
    private EditText userPass;
    private CheckBox chk;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.userName);
        userPass = (EditText) findViewById(R.id.userPass);
        chk = (CheckBox) findViewById(R.id.checkBox);
        pref = getSharedPreferences("userInfo", MODE_PRIVATE);
        editor = pref.edit();

        String name = pref.getString("userName", "");
        if (name == null) {
            chk.setChecked(false);
        } else {
            chk.setChecked(true);
            userName.setText(name);
        }

    }

    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String name = userName.getText().toString().trim();
                String pass = userPass.getText().toString().trim();
                if ("admin".equals(name) && "123".equals(pass)) {
                    if (chk.isChecked()) {
                        editor.putString("userName", name);
                        editor.commit();
                    } else {
                        editor.remove("userName");
                        editor.commit();
                    }
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_cancel:

                break;
        }

    }
}

