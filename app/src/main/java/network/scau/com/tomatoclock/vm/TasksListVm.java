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

    public void addTask(){
        taskListActivity.startActivity(new Intent(taskListActivity,NewTaskActivity.class));
    }

    public List<TomatoTask> getSourceData(){
        return tomatoTasksList.getListLotalTasks();
    }

    public void backToListActivity(View view){
        newTaskActivity.showQuitDialog();
//        newTaskActivity.finish();
    }

}
