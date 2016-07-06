package network.scau.com.tomatoclock.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import network.scau.com.tomatoclock.R;
import network.scau.com.tomatoclock.base.BaseActivity;
import network.scau.com.tomatoclock.databinding.ActivityNewTaskBinding;
import network.scau.com.tomatoclock.model.TomatoTask;
import network.scau.com.tomatoclock.vm.TasksListVm;

public class NewTaskActivity extends BaseActivity{

    public static TasksListVm tasksListVm;

    private  ActivityNewTaskBinding binding;

    private TomatoTask newTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_new_task);
        newTask = new TomatoTask();
        binding.setTask(newTask);

        tasksListVm = TaskListActivity.tasksListVm;
        tasksListVm.setNewTaskActivity(this);


    }
}
