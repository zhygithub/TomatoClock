package network.scau.com.tomatoclock.view;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import network.scau.com.tomatoclock.base.BaseConstant;

public class DialogShow {

	private View view;

	private Dialog showdialog;

	private Context context;

	public DialogShow(Context context) {
		init(context);
	}

	private void init(Context context) {
		this.context = context;
		showdialog = new Dialog(context);
		showdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 这里设置dialog的背景透明
		showdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
	}

	public DialogShow(Context context, View view) {
		super();
		this.view = view;
		init(context);
		showdialog.setContentView(view);
	}

	public void setContentView(View view) {
		showdialog.setContentView(view);
	}

	/**
	 * 设置弹窗的宽高
	 * @param w 屏幕的宽的百分比
	 * @param h 屏幕的高的百分比
	 */
	public void setPercentHeightAndWidth(float w, float h) {
		WindowManager.LayoutParams p = showdialog.getWindow().getAttributes(); // 获取对话框当前的参数值
		p.height = (int) (BaseConstant.windowheight * h);
		p.width = (int) (BaseConstant.windowWidth * w);
		showdialog.getWindow().setAttributes(p);
	}

	public void setOnBottom(int dx, int dy) {
		if (showdialog != null) {
			Window mWindow = showdialog.getWindow();
			WindowManager.LayoutParams lp = mWindow.getAttributes();

			// 透明度的范围为：0.0f-1.0f;0.0f表示完全透明,1.0f表示完全不透明(系统默认的就是这个)。
			lp.alpha = 1f;

			// 设置对话框在屏幕的底部显示，当然还有上下左右，任意位置
			// mWindow.setGravity(Gravity.LEFT);
			mWindow.setGravity(Gravity.BOTTOM);
			lp.x = dx;
			lp.y = dy;
		}
	}

	public void setBgAlpha(float alpha) {
		if (showdialog != null) {
			Window mWindow = showdialog.getWindow();
			WindowManager.LayoutParams lp = mWindow.getAttributes();

			// 背景的透明度
			lp.dimAmount = alpha;
		}
	}

	public void setOnCenter() {
		if (showdialog != null) {
			Window mWindow = showdialog.getWindow();
			WindowManager.LayoutParams lp = mWindow.getAttributes();
			// 透明度的范围为：0.0f-1.0f;0.0f表示完全透明,1.0f表示完全不透明(系统默认的就是这个)。
			lp.alpha = 1f;
			// 设置对话框在屏幕的底部显示，当然还有上下左右，任意位置
			// mWindow.setGravity(Gravity.LEFT);
			mWindow.setGravity(Gravity.CENTER);
		}
	}

	public void show() {
		if (showdialog != null) {
			showdialog.show();
		}
	}
	
	public boolean isShowing(){
		if (showdialog != null) {
			return showdialog.isShowing();
		}
		return false;
	}

	public void dismiss() {
		if (showdialog != null) {
			showdialog.dismiss();
		}
	}

	public void setOnCancelListener(OnCancelListener listener) {
		showdialog.setOnCancelListener(listener);
	}

	public void setOnDismissListener(OnDismissListener listener) {
		showdialog.setOnDismissListener(listener);
	}

	public void setOnShowListener(OnShowListener listener) {
		showdialog.setOnShowListener(listener);
	}
}
