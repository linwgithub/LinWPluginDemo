package com.lvcd.pluginlib;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

public class PluginApk {

    private DexClassLoader classLoader;
    private Resources resources;
    private PackageInfo packageInfo;
    private AssetManager assetManager;

    public PluginApk(DexClassLoader classLoader, Resources resources, PackageInfo packageInfo) {
        this.classLoader = classLoader;
        this.resources = resources;
        this.packageInfo = packageInfo;
        this.assetManager = resources.getAssets();
    }

    public DexClassLoader getClassLoader() {
        return classLoader;
    }

    public Resources getResources() {
        return resources;
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
