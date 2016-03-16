package matio.angel.com.myapplication_gamehelper.bean;

/**
 * Created by Angel on 2016/1/11.
 */
public class ForthMoney {
    private String id;
    //图标网址
    private String icon;
    //名称
    private String name;
    //评分
    private String source;
    //下载人数
    private String count_dl;
    //大小
    private String size;
    //奖励的U币
    private String all_prize;

    public ForthMoney(String id, String icon, String name, String source, String count_dl, String size, String all_prize) {
        this.icon = icon;
        this.id = id;
        this.name = name;
        this.source = source;
        this.count_dl = count_dl;
        this.size = size;
        this.all_prize = all_prize;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCount_dl() {
        return count_dl;
    }

    public void setCount_dl(String count_dl) {
        this.count_dl = count_dl;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAll_prize() {
        return all_prize;
    }

    public void setAll_prize(String all_prize) {
        this.all_prize = all_prize;
    }
}
