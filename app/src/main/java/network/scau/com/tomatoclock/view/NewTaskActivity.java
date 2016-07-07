package network.scau.com.tomatoclock.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import network.scau.com.tomatoclock.R;
import network.scau.com.tomatoclock.base.BaseActivity;
import network.scau.com.tomatoclock.databinding.ActivityNewTaskBinding;
import network.scau.com.tomatoclock.model.TomatoTask;
import network.scau.com.tomatoclock.vm.TasksListVm;

public class NewTaskActivity extends BaseActivity{

    public static TasksListVm tasksListVm;

    private  ActivityNewTaskBinding binding;

    private TomatoTask newTask;

    private DialogShow quitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_new_task);
        newTask = new TomatoTask();
        binding.setTask(newTask);

        tasksListVm = TaskListActivity.tasksListVm;
        tasksListVm.setNewTaskActivity(this);
        binding.setVm(tasksListVm);

        init();


    }

    private void init() {

        initQuitDialog();

    }

    /**初始化 退出弹窗 */
    private void initQuitDialog() {

        View view = View.inflate(this, R.layout.dialog_newtask_quit, null);

        quitDialog = new DialogShow(this,view);
    }

    /**展示 退出弹窗 */
    public void showQuitDialog(){
        if(quitDialog!=null){
            quitDialog.show();
        }

    }
}
