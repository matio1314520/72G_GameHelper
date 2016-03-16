package matio.angel.com.myapplication_gamehelper.bean;

/**
 * Created by Angel on 2016/1/11.
 */
public class SecondAreaActivity1 {

    private String target_id;

    private String bimg;

    private String bname;

    public SecondAreaActivity1(String target_id, String bimg, String bname) {
        this.bimg = bimg;
        this.target_id = target_id;
        this.bname = bname;
    }

    public String getTarget_id() {
        return target_id;
    }

    public void setTarget_id(String target_id) {
        this.target_id = target_id;
    }

    public String getBimg() {
        return bimg;
    }

    public void setBimg(String bimg) {
        this.bimg = bimg;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }
}
