package me.cllc.sc;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.AdapterView;
import android.widget.Toast;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import me.cllc.sc.util.SharePopupWindow;

/**
 * Created by zhangyang on 16/1/17.
 */
public class JSBridge {

    private View inflate;
    public Context ctx;
    private UMShareListener listener;
    private String title;
    private String body;
    private String img;
    private String url;

    public JSBridge(Context ctx) {
        inflate = LayoutInflater.from(ctx).inflate(R.layout.activity_main, null);
        this.ctx = ctx;
    }

    @JavascriptInterface
    public void share(String title, String body, String img, String url) {
        this.title = title;
        this.body = body;
        this.img = img;
        this.url = url;

        // 微信配置
        String appID = "wx37e4d31bf0789573";
        String appSecret = "63c9518ec886c523b92d821313789a66";

        PlatformConfig.setQQZone("1105121716", "9JwI0F3OVMUb6pPW");
        PlatformConfig.setWeixin(appID, appSecret);

        SharePopupWindow popupWindow = new SharePopupWindow(ctx);
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {

            popupWindow.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
        }

        listener = new UMShareListener() {
            @Override
            public void onResult(SHARE_MEDIA share_media) {
                Toast.makeText(ctx, " 分享成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                Toast.makeText(ctx, " 分享失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                Toast.makeText(ctx, " 分享取消", Toast.LENGTH_SHORT).show();
            }
        };

        popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        handler.sendEmptyMessage(0);
                        break;
                    case 1:
                        handler.sendEmptyMessage(1);
                        break;
                    case 2:
                        handler.sendEmptyMessage(2);
                        break;
                    case 3:
                        handler.sendEmptyMessage(3);
                        break;
                }
            }
        });


    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    new ShareAction((Activity) ctx)
                            .setPlatform(SHARE_MEDIA.QQ)
                            .withTitle(title)
                            .withTargetUrl(url)
                            .withText(body)
                            .setCallback(listener)
                            .withMedia(new UMImage(ctx, img))
                            .share();
                    break;
                case 1:
                    new ShareAction((Activity) ctx)
                            .setPlatform(SHARE_MEDIA.QZONE)
                            .withTitle(title)
                            .withTargetUrl(url)
                            .withText(body)
                            .setCallback(listener)
                            .withMedia(new UMImage(ctx, img))
                            .share();
                    break;
                case 2:
                    new ShareAction((Activity) ctx)
                            .setPlatform(SHARE_MEDIA.WEIXIN)
                            .withTitle(title)
                            .withTargetUrl(url)
                            .withText(body)
                            .setCallback(listener)
                            .withMedia(new UMImage(ctx, img))
                            .share();
                    break;
                case 3:
                    new ShareAction((Activity) ctx)
                            .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                            .withTitle(title)
                            .withTargetUrl(url)
                            .withText(body)
                            .setCallback(listener)
                            .withMedia(new UMImage(ctx, img))
                            .share();
                    break;
            }
        }
    };


}
