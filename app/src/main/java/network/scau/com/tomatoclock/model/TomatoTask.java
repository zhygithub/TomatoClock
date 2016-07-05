package network.scau.com.tomatoclock.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.Date;

import network.scau.com.tomatoclock.BR;

/**
 * Created by ZhengHy on 2016/7/5 0005.
 */

public class TomatoTask extends BaseObservable{

    /**创建时间 */
    private Date dateCreateDate;

    /**完成时间 */
    private Date dateCompletedDate;

    /**任务标题 */
    private String strTitle;

    /**任务内容 */
    private String strContent;

    /**任务状态，当前只有 完成（true） 或者 未完成（false） */
    private boolean isCompleted = false;

    @Bindable
    public String getStrContent() {
        return strContent;
    }

    public void setStrContent(String strContent) {
        this.strContent = strContent;
        notifyPropertyChanged(BR.strTitle);
    }

    @Bindable
    public Date getDateCompletedDate() {
        return dateCompletedDate;
    }

    public void setDateCompletedDate(Date dateCompletedDate) {
        this.dateCompletedDate = dateCompletedDate;
        notifyPropertyChanged(BR.dateCompletedDate);
    }

    @Bindable
    public Date getDateCreateDate() {
        return dateCreateDate;
    }

    public void setDateCreateDate(Date dateCreateDate) {
        this.dateCreateDate = dateCreateDate;
        notifyPropertyChanged(BR.dateCreateDate);
    }

    @Bindable
    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
        notifyPropertyChanged(BR.strTitle);
    }

    @Bindable
    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
        notifyPropertyChanged(BR.completed);
    }

    @Override
    public String toString() {
        return "TomatoTask{" +
                "dateCompletedDate=" + dateCompletedDate +
                ", dateCreateDate=" + dateCreateDate +
                ", strTitle='" + strTitle + '\'' +
                ", strContent='" + strContent + '\'' +
                ", isCompleted=" + isCompleted +
                "} " + super.toString();
    }
}
