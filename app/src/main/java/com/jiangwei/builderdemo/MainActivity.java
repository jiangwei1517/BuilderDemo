package com.jiangwei.builderdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jiangwei.builderdemo.bean.Person;
import com.jiangwei.builderdemo.dialog.AlertDialogHelper;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Person.Builder builder = new Person.Builder();
        Person person = builder.age(6).name("jiangwei").nation("China").sex("男").build();
        tv = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.btn);
        tv.setText(person.toString());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        AlertDialogHelper.AlertDialogBuilder builder = new AlertDialogHelper.AlertDialogBuilder(this);
        builder.setCancelable(true).setLeftButton(R.string.left, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "好的,我现在帮您预订", Toast.LENGTH_SHORT).show();
                showMiddleBtnDialog();
            }
        }).setRightButton(R.string.right, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setMessage("泰国曼谷是个美丽的城市").setTitle("泰国").build().show();
    }

    private void showMiddleBtnDialog() {
        AlertDialogHelper.AlertDialogBuilder builder = new AlertDialogHelper.AlertDialogBuilder(this);
        builder.setMessage("我已经帮您预订了后天的机票飞往泰国曼谷,祝您旅途愉快!").setTitle("成功预订").setMiddleButton(R.string.middle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).build().show();
    }
}
