package network.scau.com.tomatoclock.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;

import java.util.Calendar;
import java.util.Date;

import network.scau.com.tomatoclock.QuitDialogBinding;
import network.scau.com.tomatoclock.R;
import network.scau.com.tomatoclock.SelectDateBinding;
import network.scau.com.tomatoclock.SelectTimeBinding;
import network.scau.com.tomatoclock.base.BaseActivity;
import network.scau.com.tomatoclock.databinding.ActivityNewTaskBinding;
import network.scau.com.tomatoclock.model.TomatoTask;
import network.scau.com.tomatoclock.tools.ToastUtils;
import network.scau.com.tomatoclock.vm.TasksListVm;
import rx.Observable;

public class NewTaskActivity extends BaseActivity{

    public static TasksListVm tasksListVm;

    private  ActivityNewTaskBinding binding;

    private TomatoTask newTask;


    private DialogShow quitDialog;

    private DialogShow timeDialog;

    private DialogShow dateDialog;

    private QuitDialogBinding quitDialogBinding;

    private SelectTimeBinding selectTimeBinding;

    private SelectDateBinding selectDateBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_new_task);

        tasksListVm = TaskListActivity.tasksListVm;
        tasksListVm.setNewTaskActivity(this);
        binding.setVm(tasksListVm);
        newTask = tasksListVm.getCurrentNewTask();
        binding.setTask(newTask);
        init();


    }

    private void init() {

        initQuitDialog();
        initSelectDateDialog();
        initSelectTimeDialog();

    }

    /**初始化 退出弹窗 */
    private void initQuitDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
//        View view = View.inflate(this, R.layout.dialog_newtask_quit, null);

        quitDialogBinding = DataBindingUtil.inflate(inflater,R.layout.dialog_newtask_quit,null,false);
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
        selectTimeBinding = DataBindingUtil.inflate(inflater,R.layout.dialog_select_time,null,false);
        selectTimeBinding.setVm(tasksListVm);
        selectTimeBinding.dialogBtnLeft.setText("OK");
        selectTimeBinding.dialogBtnRight.setText("NO");
        selectTimeBinding.dialogTitle.setText("SELECT TIME");

        timeDialog = new DialogShow(this, selectTimeBinding.getRoot());
    }

    /**展示 时间弹窗 */
    public void showSelectTimeDialog(){
        if(timeDialog!=null){
            timeDialog.show();
        }

    }

    /**隐藏 时间弹窗 */
    public void dismissSelectTimeDialog(){
        if(timeDialog!=null){
            timeDialog.dismiss();
        }

    }

    /**初始化 选择日期弹窗 */
    public void initSelectDateDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        selectDateBinding = DataBindingUtil.inflate(inflater,R.layout.dialog_select_date,null,false);
        selectDateBinding.setVm(tasksListVm);
        selectDateBinding.dialogBtnLeft.setText("OK");
        selectDateBinding.dialogBtnRight.setText("NO");
        selectDateBinding.dialogTitle.setText("SELECT TIME");

        dateDialog = new DialogShow(this, selectDateBinding.getRoot());
    }

    /**展示 日期弹窗 */
    public void showSelectDateDialog(){
        if(dateDialog!=null){
            dateDialog.show();
        }

    }

    /**隐藏 日期弹窗 */
    public void dismissSelectDateDialog(){
        if(dateDialog!=null){
            dateDialog.dismiss();
        }
    }

    /**获得选择的日期 */
    public Date getSelectTime(){
        int hour = Calendar.HOUR_OF_DAY;
        int minute = Calendar.MINUTE;
        if(selectTimeBinding!=null){
            hour = selectTimeBinding.dialogTimepicker.getHour();
            minute = selectTimeBinding.dialogTimepicker.getMinute();
        }
        int year = Calendar.YEAR;
        int month = Calendar.MONTH;
        int day = Calendar.DAY_OF_MONTH;
        if(selectDateBinding!=null){
            year = selectDateBinding.dialogDatepicker.getYear();
            month = selectDateBinding.dialogDatepicker.getMonth();
            day = selectDateBinding.dialogDatepicker.getDayOfMonth();
        }

        Date date = new Date();
        date.setYear(year);
        date.setMonth(month);
        date.setDate(day);
        date.setHours(hour);
        date.setMinutes(minute);
        return date;
    }

    /**获得标题 */
    public String getEdtTitle(){
        Editable text = binding.edtTitle.getText();
        String title = text == null?"":text.toString();
        return title;
    }

    /**获得描述 */
    public String getEdtDescription(){
        Editable text = binding.edtContent.getText();
        String descri = text == null?"":text.toString();
        return descri;
    }

    /**展示Toast */
    public void showToast(String text){
        ToastUtils.showTextToast(this,text);

    }
}
