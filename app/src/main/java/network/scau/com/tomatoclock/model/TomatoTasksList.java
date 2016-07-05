package network.scau.com.tomatoclock.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhengHy on 2016/7/5 0005.
 */

public class TomatoTasksList {

    /** 当前任务列表*/
    private List<TomatoTask> listLotalTasks;

    public TomatoTasksList() {

        checkListIsNull();
    }

    public TomatoTasksList(List<TomatoTask> listLotalTasks) {
        this.listLotalTasks = listLotalTasks;
    }

    /**读取本地的任务列表，使用Rx读取 */
    private List<TomatoTask> readLocalList() {
        return new ArrayList<TomatoTask>();
    }


    /**往当前任务列表 添加新任务 */
    public boolean addTask(TomatoTask newTask){

        checkListIsNull();

        return  listLotalTasks.add(newTask);
    }

    /**往当前任务列表 移除任务 */
    public boolean removeTask(TomatoTask newTask){
        checkListIsNull();

        return listLotalTasks.remove(newTask);
    }

    /**检查当前列表是否为空 */
    private void checkListIsNull(){
        if(listLotalTasks==null){
            listLotalTasks = readLocalList();
        }
    }

    /**返回列表中指定位置的 TomatoTask,如果position 非法则返回null */
    public TomatoTask getTask(int position){
        checkListIsNull();
        if(position>=0&&listLotalTasks.size()>position){
            return listLotalTasks.get(position);
        }else{
            return null;
        }
    }

}
