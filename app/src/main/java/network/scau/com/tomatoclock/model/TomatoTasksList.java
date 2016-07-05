package network.scau.com.tomatoclock.model;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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

    public static TomatoTasksList getInstance() {
        return IntanceHolder.INSTANCE;
    }

    private static class IntanceHolder {
        public static TomatoTasksList INSTANCE = new TomatoTasksList();
    }

    public TomatoTasksList(List<TomatoTask> listLotalTasks) {
        this.listLotalTasks = listLotalTasks;
    }

    public List<TomatoTask> getListLotalTasks() {
        return listLotalTasks;
    }

    public void setListLotalTasks(List<TomatoTask> listLotalTasks) {
        this.listLotalTasks = listLotalTasks;
    }

    private String text1 = "abdjsdkfjksdkjkjflsd";

    private String text2 = "abdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsd";

    private String text3 = "abdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfjksdkjkjflsdabdjsdkfj";
    /**
     * 读取本地的任务列表，使用Rx读取
     */
    private List<TomatoTask> readLocalList() {

        ArrayList<TomatoTask> tomatoTasks = new ArrayList<>();
        TomatoTask tomatoTask;
        for (int i = 0; i < 7; i++) {
            tomatoTask = new TomatoTask();
            tomatoTask.setStrTitle("test " + i);
            tomatoTask.setCompleted(i % 3 == 0 ? true : false);
            switch (i%3){
                case 0:
                    tomatoTask.setStrContent(text1);
                    break;
                case 1:
                    tomatoTask.setStrContent(text2);
                    break;
                case 2:
                    tomatoTask.setStrContent(text3);
                    break;
            }

            tomatoTasks.add(tomatoTask);
        }

        return tomatoTasks;
    }


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
            listLotalTasks = readLocalList();
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
