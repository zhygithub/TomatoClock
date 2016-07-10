package network.scau.com.tomatoclock.model;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import network.scau.com.tomatoclock.tools.FileUtils;

/**
 * Created by ZhengHy on 2016/7/5 0005.
 */

public class TomatoTasksList {

    /**
     * 当前任务列表
     */
    private List<TomatoTask> listLotalTasks = null;


    private TomatoTasksList() {
        checkListIsNull();
    }

    public static TomatoTasksList getInstance(List<TomatoTask> listLotalTasks) {
//        if (IntanceHolder.INSTANCE == null) {
        IntanceHolder.INSTANCE = new TomatoTasksList(listLotalTasks);
//        }
        return IntanceHolder.INSTANCE;
    }

    public static TomatoTasksList getInstance() {
        if (IntanceHolder.INSTANCE == null) {
            IntanceHolder.INSTANCE = new TomatoTasksList();
        }
        return IntanceHolder.INSTANCE;
    }

    private static class IntanceHolder {
        public static TomatoTasksList INSTANCE;
    }

    private TomatoTasksList(List<TomatoTask> listLotalTasks) {
        this.listLotalTasks = listLotalTasks;
    }

    public List<TomatoTask> getListLotalTasks() {
        return listLotalTasks;
    }

    public void setListLotalTasks(List<TomatoTask> listLotalTasks) {
        this.listLotalTasks = listLotalTasks;
    }

//    private String text1 = "abdjsdkfjksdkjkjflsd";
//
//    private String text2 = "abdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsd";
//
//    private String text3 = "abdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfj";


    /**
     * 往当前任务列表 添加新任务
     */
    public boolean addTask(TomatoTask newTask) {

        checkListIsNull();

        return listLotalTasks.add(newTask);
    }

    /**
     * 往当前任务列表 移除任务
     */
    public boolean removeTask(TomatoTask newTask) {
        checkListIsNull();

        return listLotalTasks.remove(newTask);
    }

    /**
     * 检查当前列表是否为空
     */
    private void checkListIsNull() {
        if (listLotalTasks == null) {
            Log.d("Tomato", "readLocalList");
            listLotalTasks = new ArrayList<>();
        }
    }

    /**
     * 返回列表中指定位置的 TomatoTask,如果position 非法则返回null
     */
    public TomatoTask getTask(int position) {
        checkListIsNull();
        if (position >= 0 && listLotalTasks.size() > position) {
            return listLotalTasks.get(position);
        } else {
            return null;
        }
    }

}
