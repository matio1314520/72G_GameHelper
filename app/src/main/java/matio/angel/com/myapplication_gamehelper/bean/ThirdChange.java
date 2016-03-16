package matio.angel.com.myapplication_gamehelper.bean;

/**
 * Created by Angel on 2016/1/11.
 */
public class ThirdChange {

    private String id;
    private String icon;
    private String name;
    private String consume;
    private String remain;

    public ThirdChange(String id, String icon, String name, String consume, String remain) {
        this.icon = icon;
        this.name = name;
        this.consume = consume;
        this.id = id;
        this.remain = remain;
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

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public String getRemain() {
        return remain;
    }

    public void setRemain(String remain) {
        this.remain = remain;
    }
}
