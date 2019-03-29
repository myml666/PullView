package com.itfitness.pullview;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @ProjectName: PullView
 * @Package: com.itfitness.pullview
 * @ClassName: HomeActivity
 * @Description: java类作用描述 ：
 * @Author: 作者名：lml
 * @CreateDate: 2019/3/29 9:31
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/3/29 9:31
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

public class HomeActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        startActivity(new Intent(this,MainActivity.class));
        tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "我是主界面", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
