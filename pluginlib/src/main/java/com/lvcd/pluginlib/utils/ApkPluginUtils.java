package com.lvcd.pluginlib.utils;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class ApkPluginUtils {

    /**
     * 将Assets目录下的fileName文件拷贝至app缓存目录下
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String copyAssetAndWrite(Context context, String fileName) {
        try {
            File cacheDir = context.getCacheDir();
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }

            File outFIle = new File(cacheDir, fileName);
            if (!outFIle.exists()) {

                boolean res = outFIle.createNewFile();
                if (res) {
                    InputStream is = context.getAssets().open(fileName);
                    FileOutputStream fos = new FileOutputStream(outFIle);
                    byte[] buffer = new byte[is.available()];
                    int byteCount;
                    while ((byteCount = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, byteCount);
                    }

                    fos.flush();
                    is.close();
                    fos.close();
                    Toast.makeText(context, "下载成功", Toast.LENGTH_LONG).show();
                    return outFIle.getAbsolutePath();
                } else {
                    Toast.makeText(context, "文件已存在", Toast.LENGTH_LONG).show();
                    return outFIle.getAbsolutePath();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
