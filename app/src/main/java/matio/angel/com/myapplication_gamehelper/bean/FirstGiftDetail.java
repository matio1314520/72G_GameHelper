package matio.angel.com.myapplication_gamehelper.bean;

/**
 * Created by Angel on 2016/1/13.
 */
public class FirstGiftDetail {
    private String id;
    //图标网址
    private String icon;
    //名称
    private String name;
    //总量
    private String total;
    //剩余量
    private String remain;
    //开始时间
    private String stime;
    //截止时间
    private String etime;
    //游戏类型
    private String game_type;
    //大小
    private String size;
    //礼包内容
    private String content;
    //领取方式
    private String howget;

    private String consume;

    public FirstGiftDetail(String id, String icon, String name, String total, String remain,
                           String stime, String etime, String game_type, String size,
                           String content, String howget, String consume) {
        this.icon = icon;
        this.id = id;
        this.name = name;
        this.total = total;
        this.remain = remain;
        this.stime = stime;
        this.etime = etime;
        this.game_type = game_type;
        this.size = size;
        this.content = content;
        this.howget = howget;
        this.consume = consume;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRemain() {
        return remain;
    }

    public void setRemain(String remain) {
        this.remain = remain;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getGame_type() {
        return game_type;
    }

    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHowget() {
        return howget;
    }

    public void setHowget(String howget) {
        this.howget = howget;
    }
}
