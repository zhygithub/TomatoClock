package network.scau.com.tomatoclock.view;

import android.databinding.DataBindingUtil;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import network.scau.com.tomatoclock.R;
import network.scau.com.tomatoclock.base.BaseActivity;
import network.scau.com.tomatoclock.base.BaseApplication;
import network.scau.com.tomatoclock.databinding.ActivityTaskListBinding;
import network.scau.com.tomatoclock.databinding.ContentTaskListBinding;
import network.scau.com.tomatoclock.vm.TomatoTasksListAdapter;
import rx.functions.Action1;

public class TaskListActivity extends BaseActivity {

    /**
     * 绑定的xml
     */
    private ActivityTaskListBinding binding;

    /**
     * 其中recycleview 适配器的实例
     */
    private TomatoTasksListAdapter tasksListAdapter;

    private RecyclerView rcTasksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task_list);
        setSupportActionBar(binding.toolbar);

        init();
    }

    private void init() {
        tasksListAdapter = new TomatoTasksListAdapter(BaseApplication.tomatoTasksList.getListLotalTasks());
        tasksListAdapter.setCompleteCommand(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                tasksListAdapter.notifyItemChanged(integer);
                Toast.makeText(TaskListActivity.this,"complete",Toast.LENGTH_SHORT).show();
            }
        });
        tasksListAdapter.setDeleteCommand(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                tasksListAdapter.notifyItemRemoved(integer);
                Toast.makeText(TaskListActivity.this,"delete",Toast.LENGTH_SHORT).show();
            }
        });

        rcTasksList = (RecyclerView) findViewById(R.id.rv_tomato_tasks_list);
        rcTasksList.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rcTasksList.addItemDecoration(new RecycleViewDivider(RecycleViewDivider.Type.ALL,50));
        rcTasksList.setAdapter(tasksListAdapter);
        rcTasksList.setItemAnimator(new DefaultItemAnimator());

        RxView.clicks(binding.fab)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Snackbar.make(binding.fab, "Add a new task", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.action_quit){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
