package network.scau.com.tomatoclock.vm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import network.scau.com.tomatoclock.R;
import network.scau.com.tomatoclock.model.TomatoTask;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Created by ZhengHy on 2016/7/5 0005.
 */

public class TomatoTasksListAdapter extends RecyclerView.Adapter {

    /**源数据 */
    private List<TomatoTask> sourceData;

    /**上下文 */
    private Context context;

    /**删除事件 */
    private Action1<Integer> deleteEvent;
    /**完成事件 */
    private Action1<Integer> completeEvent;

    public TomatoTasksListAdapter() {
        deleteEvent =new Action1<Integer>(){
            @Override
            public void call(Integer integer) {
                if (sourceData != null) {
                    //以下三个顺序不能缺少和替换，先删除源数据，然后展示动画，最后对于以后的项目进行范围更新
                    sourceData.remove(integer.intValue());
                    TomatoTasksListAdapter.this.notifyItemRemoved(integer);
                    TomatoTasksListAdapter.this.notifyItemRangeChanged(integer,sourceData.size());
                }
            }
        };

        completeEvent =new Action1<Integer>(){
            @Override
            public void call(Integer integer) {
                if (sourceData != null) {
                    sourceData.get(integer).setCompleted(true);
                    TomatoTasksListAdapter.this.notifyItemChanged(integer);
                    TomatoTasksListAdapter.this.notifyDataSetChanged();
                }
            }
        };
    }

    public TomatoTasksListAdapter(List<TomatoTask> sourceData) {
        this();
        this.sourceData = sourceData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_recycle_tasks, null);
        context = parent.getContext();
        return new TomatoTaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (sourceData == null) {
            return;
        }
        TomatoTask task = sourceData.get(position);
        TomatoTaskViewHolder viewholder = ((TomatoTaskViewHolder) holder);


        viewholder.title.setText(task.getStrTitle());
        viewholder.content.setText(task.getStrContent());
        viewholder.isCompleted.setBackgroundColor(task.isCompleted() ? context.getColor(R.color.colorCompleted) : context.getColor(R.color.colorAccent));

        setClick(viewholder.delete, deleteEvent, position);
        setClick(viewholder.isCompleted, completeEvent, position);

    }

    /**设置按钮点击事件 */
    private void setClick(View view, final Action1<Integer> innerEvent, final int position) {
        RxView.clicks(view).throttleFirst(1, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Observable.just(position).observeOn(AndroidSchedulers.mainThread()).subscribe(innerEvent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sourceData == null ? 0 : sourceData.size();
    }

    protected final static class TomatoTaskViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageView isCompleted;
        public TextView content;
        public ImageView delete;

        public TomatoTaskViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            isCompleted = (ImageView) itemView.findViewById(R.id.is_completed);
            delete = (ImageView) itemView.findViewById(R.id.delete);
        }
    }



}
