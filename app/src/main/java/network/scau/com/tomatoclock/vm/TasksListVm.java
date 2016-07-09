package network.scau.com.tomatoclock.vm;

import android.content.Intent;
import android.view.View;

import java.util.List;

import network.scau.com.tomatoclock.base.BaseApplication;
import network.scau.com.tomatoclock.model.TomatoTask;
import network.scau.com.tomatoclock.model.TomatoTasksList;
import network.scau.com.tomatoclock.view.NewTaskActivity;
import network.scau.com.tomatoclock.view.TaskListActivity;

/**
 * Created by ZhengHy on 2016/7/5 0005.
 */

public class TasksListVm {

    /**TaskListActivity 的实例 */
    private TaskListActivity taskListActivity;

    /**NewTaskActivity的实例 */
    private NewTaskActivity newTaskActivity;

    private TomatoTasksList tomatoTasksList = BaseApplication.tomatoTasksList;

    private TomatoTasksListAdapter tasksListAdapter;


    public TasksListVm(TaskListActivity taskListActivity) {
        this.taskListActivity = taskListActivity;
    }

    public void setTasksListAdapter(TomatoTasksListAdapter tasksListAdapter) {
        this.tasksListAdapter = tasksListAdapter;
    }

    public void setNewTaskActivity(NewTaskActivity newTaskActivity) {
        this.newTaskActivity = newTaskActivity;
    }


    // --------------------- 以下为业务逻辑 ----------------------

    /**进入创建新Taskactivity */
    public void intoNewTaskActivity(){
        taskListActivity.startActivity(new Intent(taskListActivity,NewTaskActivity.class));
    }

    /**获得当前所有Task列表 */
    public List<TomatoTask> getSourceData(){
        return tomatoTasksList.getListLotalTasks();
    }

    /**从新任务activity回到主activity */
    public void backToListActivity(View view){
        newTaskActivity.showQuitDialog();
    }

    /**新任务activity结束 */
    public void newTaskActivityFinish(View view){
        newTaskActivity.finish();
    }

    /**取消 从新任务activity回到主activity*/
    public void cancleBackToListActivity(View view){
        newTaskActivity.dismissQuitDialog();
    }

    /**选择新任务开始时间 */
    public void selectYourTime(View view){
        newTaskActivity.showSelectTimeDialog();
    }

    /**取消选择任务开始时间 */
    public void cancleSelectTime(View view){
        newTaskActivity.dismissSelectTimeDialog();
    }



}
