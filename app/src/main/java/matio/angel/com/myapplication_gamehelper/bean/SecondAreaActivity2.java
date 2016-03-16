package matio.angel.com.myapplication_gamehelper.bean;

/**
 * Created by Angel on 2016/1/11.
 */
public class SecondAreaActivity2 {
    private String id;
    //图片网址
    private String hotpic;
    //标题
    private String anme;
    //中
    private String shortname;
    //参加的人数
    private String total_join_user;
    //状态
    private String status;


    public SecondAreaActivity2(String id, String hotpic, String anme, String shortname, String total_join_user, String status) {
        this.hotpic = hotpic;
        this.anme = anme;
        this.shortname = shortname;
        this.total_join_user = total_join_user;
        this.status = status;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHotpic() {
        return hotpic;
    }

    public void setHotpic(String hotpic) {
        this.hotpic = hotpic;
    }

    public String getAnme() {
        return anme;
    }

    public void setAnme(String anme) {
        this.anme = anme;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getTotal_join_user() {
        return total_join_user;
    }

    public void setTotal_join_user(String total_join_user) {
        this.total_join_user = total_join_user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
