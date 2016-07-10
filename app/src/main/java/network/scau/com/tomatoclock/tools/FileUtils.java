package network.scau.com.tomatoclock.tools;

import android.content.Context;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by ZhengHy on 2016/7/10 0010.
 */

public class FileUtils {
    /**
     * 将字符串保存到本地
     */
    public static boolean saveStringJson(Context context, String filename, String content) {
        String str;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {

            fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(content.getBytes());

            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 读取保存在本地的字符串
     */
    public static String readStringJson(Context context, String filename) {
        FileInputStream fis;
        try {
            fis = context.openFileInput(filename);

            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line = reader.readLine();

            fis.close();

            return line;
        } catch (FileNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
