package network.scau.com.tomatoclock.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import network.scau.com.tomatoclock.QuitDialogBinding;
import network.scau.com.tomatoclock.R;
import network.scau.com.tomatoclock.SelectTimeBinding;
import network.scau.com.tomatoclock.base.BaseActivity;
import network.scau.com.tomatoclock.databinding.ActivityNewTaskBinding;
import network.scau.com.tomatoclock.model.TomatoTask;
import network.scau.com.tomatoclock.vm.TasksListVm;

public class NewTaskActivity extends BaseActivity{

    public static TasksListVm tasksListVm;

    private  ActivityNewTaskBinding binding;

    private TomatoTask newTask;


    private DialogShow quitDialog;

    private DialogShow timeDialog;

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

        initSelectTimeDialog();

    }

    /**初始化 退出弹窗 */
    private void initQuitDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
//        View view = View.inflate(this, R.layout.dialog_newtask_quit, null);

        QuitDialogBinding quitDialogBinding = DataBindingUtil.inflate(inflater,R.layout.dialog_newtask_quit,null,false);
        quitDialogBinding.setVm(tasksListVm);
        quitDialogBinding.dialogBtnLeft.setText("SURE");
        quitDialogBinding.dialogBtnRight.setText("NO");
        quitDialogBinding.dialogContent.setText("DO YOU WANT TO GIVE UP AND BACK?");
        quitDialogBinding.dialogTitle.setText("WARNING");

        quitDialog = new DialogShow(this, quitDialogBinding.getRoot());
    }

    /**展示 退出弹窗 */
    public void showQuitDialog(){
        if(quitDialog!=null){
            quitDialog.show();
        }

    }

    /**隐藏 退出弹窗 */
    public void dismissQuitDialog(){
        if(quitDialog!=null){
            quitDialog.dismiss();
        }

    }

    /**初始化 选择时间弹窗 */
    public void initSelectTimeDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
//        View view = View.inflate(this, R.layout.dialog_newtask_quit, null);

        SelectTimeBinding selectTimeBinding = DataBindingUtil.inflate(inflater,R.layout.dialog_select_time,null,false);
        selectTimeBinding.setVm(tasksListVm);
        selectTimeBinding.dialogBtnLeft.setText("OK");
        selectTimeBinding.dialogBtnRight.setText("NO");
        selectTimeBinding.dialogTitle.setText("SELECT TIME");

        timeDialog = new DialogShow(this, selectTimeBinding.getRoot());
    }

    /**展示 退出弹窗 */
    public void showSelectTimeDialog(){
        if(timeDialog!=null){
            timeDialog.show();
        }

    }

    /**隐藏 退出弹窗 */
    public void dismissSelectTimeDialog(){
        if(timeDialog!=null){
            timeDialog.dismiss();
        }

    }
}
