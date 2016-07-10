package network.scau.com.tomatoclock.vm;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import network.scau.com.tomatoclock.base.BaseApplication;
import network.scau.com.tomatoclock.model.TomatoTask;
import network.scau.com.tomatoclock.model.TomatoTasksList;
import network.scau.com.tomatoclock.tools.FileUtils;
import network.scau.com.tomatoclock.view.NewTaskActivity;
import network.scau.com.tomatoclock.view.TaskListActivity;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZhengHy on 2016/7/5 0005.
 */

public class TasksListVm {

    /**TaskListActivity 的实例 */
    private TaskListActivity taskListActivity;

    /**NewTaskActivity的实例 */
    private NewTaskActivity newTaskActivity;

    private TomatoTasksList tomatoTasksList ;

    private TomatoTasksListAdapter tasksListAdapter;


    public TasksListVm(TaskListActivity taskListActivity) {
        this.taskListActivity = taskListActivity;
        readData();
    }

    public void setTasksListAdapter(TomatoTasksListAdapter tasksListAdapter) {
        this.tasksListAdapter = tasksListAdapter;
    }

    public void setNewTaskActivity(NewTaskActivity newTaskActivity) {
        this.newTaskActivity = newTaskActivity;
    }


    // --------------------- 以下为业务逻辑 ----------------------

    public static final String SAVE_TEXT_NAME = "DATA";

    /**进入创建新Taskactivity */
    public void intoNewTaskActivity(){
        currentNewTask = new TomatoTask();
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
        currentNewTask = null;
    }

    public void readData(){
        TomatoTasksList tomatoTasksList = readLocalList();
        if(tomatoTasksList==null){
            this.tomatoTasksList = TomatoTasksList.getInstance();
        }else{
            this.tomatoTasksList = TomatoTasksList.getInstance(tomatoTasksList.getListLotalTasks());
        }
    }

    /**保存数据 */
    public void saveData(){
       saveInLocal();
    }

    /**
     * 读取本地的任务列表
     */
    private TomatoTasksList readLocalList() {
        Gson gson = new Gson();
        String stringJson = FileUtils.readStringJson(taskListActivity, SAVE_TEXT_NAME);
        TomatoTasksList tomatoTasksList = gson.fromJson(stringJson, TomatoTasksList.class);
        return tomatoTasksList;
    }

    /**将列表保存到本地 */
    public void saveInLocal(){
        Gson gson = new Gson();
        String listJson = gson.toJson(tomatoTasksList);

        FileUtils.saveStringJson(taskListActivity,SAVE_TEXT_NAME,listJson);
    }


    /**取消 从新任务activity回到主activity*/
    public void cancleBackToListActivity(View view){
        newTaskActivity.dismissQuitDialog();
    }

    // ------  任务列表逻辑 ----------------

    /**根据任务开始时间排序 */
    private void sortByStartTime(){
        Collections.sort(tomatoTasksList.getListLotalTasks(), new Comparator<TomatoTask>() {
            @Override
            public int compare(TomatoTask tomatoTask, TomatoTask another) {

                if(tomatoTask.getDateCreateDate()==null){
                    return 1;
                }

                if(another.getDateCreateDate()==null){
                    return -1;
                }

                long createDate = tomatoTask.getDateCreateDate().getTime();
                long createDateAno = another.getDateCreateDate().getTime();


                return (int) (createDate-createDateAno);
            }
        });
    }


    //  ------  创建新任务逻辑 --------------

    private TomatoTask currentNewTask = null;

    private String currentNewTaskTitle = null;

    private String currentNewTaskDescription = null;

    private Date currentNewTaskStartTime = null;

    public static final int SIGN_NOTITLE = -10;
    public static final int SIGN_NOSTARTTIME = -11;
    public static final int SIGN_CREATENEWTASK_SUCCESS = 10;

    /**选择新任务开始时间 */
    public void selectYourTime(View view){
        newTaskActivity.showSelectTimeDialog();
    }

    /**取消选择任务开始时间 */
    public void cancleSelectTime(View view){
        newTaskActivity.dismissSelectTimeDialog();
    }

    /**选择新任务开始日期 */
    public void selectYourDate(View view){
        newTaskActivity.showSelectDateDialog();
    }

    /**取消选择任务开始日期 */
    public void cancleSelectDate(View view){
        newTaskActivity.dismissSelectDateDialog();
    }

    public TomatoTask getCurrentNewTask(){
        return currentNewTask;
    }

    public void setCurrentNewTaskTitle(String title){
        this.currentNewTaskTitle = title;
    }

    public void setCurrentNewTaskDescription(String description){
        this.currentNewTaskDescription = description;
    }

    public void setCurrentNewTaskStartTime(Date startTime){
        this.currentNewTaskStartTime = startTime;
    }

    public void getTitle(){
        setCurrentNewTaskTitle(newTaskActivity.getEdtTitle());
    }

    public void getDescription(){
        setCurrentNewTaskDescription(newTaskActivity.getEdtDescription());
    }

    public int createNewTask(){
        getTitle();
        if(currentNewTaskTitle.equals("")){
            return SIGN_NOTITLE;
        }
        getDescription();
        setCurrentNewTaskStartTime(newTaskActivity.getSelectTime());
        if(currentNewTaskStartTime==null){
            return SIGN_NOSTARTTIME;
        }
        currentNewTask.setCompleted(false);
        currentNewTask.setStrTitle(currentNewTaskTitle);
        currentNewTask.setStrContent(currentNewTaskDescription);
        currentNewTask.setDateCreateDate(currentNewTaskStartTime);

        return SIGN_CREATENEWTASK_SUCCESS;
    }

    public void btnSetTime(View view){
        setCurrentNewTaskStartTime(newTaskActivity.getSelectTime());

    }

    public void btnCreateNewTask(View view){
        int sign = createNewTask();
        if(sign==SIGN_NOTITLE){
            newTaskActivity.showToast("Need Title");
        }else if(sign == SIGN_NOSTARTTIME){
            newTaskActivity.showToast("Need Start Time");
        }else if(sign == SIGN_CREATENEWTASK_SUCCESS){
            newTaskActivity.showToast("Create Success");
            Log.d("tomato",currentNewTask.toString());
            tomatoTasksList.addTask(currentNewTask);
            sortByStartTime();
            tasksListAdapter.notifyDataSetChanged();
            newTaskActivityFinish(null);
        }
    }

}
