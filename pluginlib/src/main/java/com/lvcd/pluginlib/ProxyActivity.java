package com.lvcd.pluginlib;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.IpPrefix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

public class ProxyActivity extends Activity {

    private String mClassName;
    private PluginApk mPluginApk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName = getIntent().getStringExtra("className");
        mPluginApk = PluginManager.getInstance().getPluginApk();

        launchPluginActivity();

    }

    private void launchPluginActivity() {
        if (mPluginApk == null) {
            Log.e("==>", "Loading apk file first");
        }

        try {
            Class<?> clazz = mPluginApk.getClassLoader().loadClass(mClassName);
            Object obj = clazz.newInstance();
            if (obj instanceof IPlugin) {
                IPlugin iPlugin = (IPlugin) obj;
                iPlugin.attach(this);
                Bundle bundle = new Bundle();
                bundle.putInt("FROM", IPlugin.FROM_EXTERNAL);
                iPlugin.onCreate(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        // 作为插件加载后上下文变化了，需要从插件工具种提供
        return mPluginApk != null ? mPluginApk.getResources() : super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return mPluginApk != null ? mPluginApk.getAssetManager() : super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        return mPluginApk != null ? mPluginApk.getClassLoader() : super.getClassLoader();
    }
}
