package me.cllc.sc.model;

/**
 * Created by Administrator on 2016/4/18.
 */
public class ShareBean {
    private String shareName;
    private int shareImg;

    public ShareBean(String shareName, int shareImg) {
        this.shareName = shareName;
        this.shareImg = shareImg;
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }

    public int getShareImg() {
        return shareImg;
    }

    public void setShareImg(int shareImg) {
        this.shareImg = shareImg;
    }
}
