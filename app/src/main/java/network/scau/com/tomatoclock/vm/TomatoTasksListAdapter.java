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

    private List<TomatoTask> sourceData;

    private Context context;

    private Action1<Integer> deleteCommand;
    private Action1<Integer> completeCommand;

    private Func1<Integer,Void> deleteEvent;
    private Func1<Integer,Void> completeEvent;

    public TomatoTasksListAdapter() {
        deleteEvent = new Func1<Integer, Void>() {
            @Override
            public Void call(Integer integer) {
                if(sourceData!=null){
                    sourceData.remove(integer.intValue());
                }
                return null;
            }
        };

        completeEvent = new Func1<Integer, Void>() {
            @Override
            public Void call(Integer integer) {
                if(sourceData!=null){
                    sourceData.get(integer).setCompleted(true);
                }
                return null;
            }
        };

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
        Log.d("Tomato", "task = " + sourceData.get(position).getStrTitle());
        TomatoTask task = sourceData.get(position);

        TomatoTaskViewHolder viewholder = ((TomatoTaskViewHolder) holder);

        viewholder.title.setText(task.getStrTitle());
        viewholder.content.setText(task.getStrContent());
        viewholder.isCompleted.setBackgroundColor(task.isCompleted() ? context.getColor(R.color.colorCompleted) : context.getColor(R.color.colorAccent));

        setClick(viewholder.delete,deleteCommand,position);
        setClick(viewholder.isCompleted,completeCommand,position);

    }

    private void setClick(View view,final Action1 action,final int position){
        RxView.clicks(view).throttleFirst(1, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Observable.just(position).observeOn(AndroidSchedulers.mainThread()).subscribe(action);
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

    public Action1<Integer> getCompleteCommand() {
        return completeCommand;
    }

    public void setCompleteCommand(Action1<Integer> completeCommand) {
        this.completeCommand = completeCommand;
    }

    public Action1<Integer> getDeleteCommand() {
        return deleteCommand;
    }

    public void setDeleteCommand(Action1<Integer> deleteCommand) {
        this.deleteCommand = deleteCommand;
    }

    public TomatoTasksListAdapter(List<TomatoTask> sourceData) {
        this.sourceData = sourceData;

    }
}
