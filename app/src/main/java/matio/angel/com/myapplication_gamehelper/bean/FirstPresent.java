package matio.angel.com.myapplication_gamehelper.bean;

/**
 * Created by Angel on 2016/1/11.
 */
public class FirstPresent {

    private String id;

    private String floor;

    //图标网址
    private String icon;
    //名称
    private String name;
    //剩余量
    private String remain;
    //内容
    private String content;

    public FirstPresent(String id,String icon, String name, String remain, String content) {
        this.icon = icon;
        this.name = name;
        this.remain = remain;
        this.content = content;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRemain() {
        return remain;
    }

    public void setRemain(String remain) {
        this.remain = remain;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
