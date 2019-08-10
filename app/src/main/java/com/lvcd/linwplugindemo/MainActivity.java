package com.lvcd.linwplugindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lvcd.pluginlib.PluginManager;
import com.lvcd.pluginlib.ProxyActivity;
import com.lvcd.pluginlib.utils.ApkPluginUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PluginManager.getInstance().init(this);

        findViewById(R.id.tv_load_apk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadApk();
            }
        });

        findViewById(R.id.tv_goto_apk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                routerToApk();
            }
        });
    }

    private void routerToApk() {
        Intent intent = new Intent();
        intent.setClass(this, ProxyActivity.class);
        intent.putExtra("className", "com.lvcd.linwpluginapk.LinwPlugintivity");
        startActivity(intent);
    }

    private void loadApk() {
        String apkPath = ApkPluginUtils.copyAssetAndWrite(this, "linwpluginapk.apk");
        PluginManager.getInstance().loadApk(apkPath);
    }
}
