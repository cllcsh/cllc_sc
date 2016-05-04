package me.cllc.sc.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.cllc.sc.R;
import me.cllc.sc.model.ShareBean;

/**
 * Created by Administrator on 2016/4/18.
 */
public class SharePopupWindow {
    private GridView gvShare;
    private LayoutInflater inflater = null;
    private PopupWindow popupWindow = null;
    private Activity ctx;

    public SharePopupWindow(Context context) {
        this.ctx = (Activity) context;
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        ViewGroup menuView = (ViewGroup) inflater.inflate(R.layout.activity_share_popupwindow, null, true);
        if (popupWindow == null) {
            popupWindow = new PopupWindow(menuView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true) {
                @Override
                public void dismiss() {
                    super.dismiss();
                }
            };
        }

        //设置分享的参数
        List<ShareBean> shareList = new ArrayList<>(0);
        shareList.add(new ShareBean("QQ", R.drawable.ssdk_oks_classic_qq));
        shareList.add(new ShareBean("QQ空间", R.drawable.ssdk_oks_classic_qzone));
        shareList.add(new ShareBean("微信", R.drawable.ssdk_oks_classic_wechat));
        shareList.add(new ShareBean("朋友圈", R.drawable.ssdk_oks_classic_wechatmoments));

        gvShare = (GridView) menuView.findViewById(R.id.gvShare);
        ShareAdapter adapter = new ShareAdapter(context, shareList);
        gvShare.setAdapter(adapter);

        popupWindow.setContentView(menuView);//设置包含视图
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
    }


    public boolean isShowing() {
        return popupWindow.isShowing();
    }

    public void dismiss() {
        popupWindow.dismiss();
    }

    public void showAtLocation(View parent, int gravity, int x, int y) {
        popupWindow.showAtLocation(parent, gravity, x, y);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        gvShare.setOnItemClickListener(listener);
    }

    class ShareAdapter extends BaseAdapter {
        private Context context;
        private List<ShareBean> shareList;

        public ShareAdapter(Context context, List<ShareBean> shareList) {
            this.context = context;
            this.shareList = shareList;
        }

        @Override
        public int getCount() {
            return shareList.size();
        }

        @Override
        public Object getItem(int position) {
            return shareList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(context, R.layout.umeng_socialize_shareboard_item, null);
                viewHolder.shareImg = (ImageView) convertView.findViewById(R.id.umeng_socialize_shareboard_image);
                viewHolder.shareName = (TextView) convertView.findViewById(R.id.umeng_socialize_shareboard_pltform_name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            ShareBean shareBean = shareList.get(position);
            viewHolder.shareImg.setImageResource(shareBean.getShareImg());
            viewHolder.shareName.setText(shareBean.getShareName());
            return convertView;
        }

        class ViewHolder {
            ImageView shareImg;
            TextView shareName;
        }
    }
}
