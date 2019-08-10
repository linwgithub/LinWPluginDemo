package com.lvcd.pluginlib;

import android.app.Activity;
import android.os.Bundle;

// 约束 其他实现类应该实现的方法
public interface IPlugin {

    int FROM_INTERNAL = 0;
    int FROM_EXTERNAL = 1;

    // 传入用于管理生命周期的上下文参数
    void attach(Activity proxyActivity);

    void onCreate(Bundle saveInstanceState);

    void onResume();



}
