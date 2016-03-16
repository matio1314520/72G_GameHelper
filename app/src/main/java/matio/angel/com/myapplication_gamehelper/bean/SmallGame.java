package matio.angel.com.myapplication_gamehelper.bean;

/**
 * Created by Angel on 2016/1/22.
 */
public class SmallGame {
    private String id;
    private String icon;
    private String name;
    private String click;
    private String game_desc;

    public SmallGame(String id, String icon, String name, String click, String game_desc) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.click = click;
        this.game_desc = game_desc;
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

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public String getGame_desc() {
        return game_desc;
    }

    public void setGame_desc(String game_desc) {
        this.game_desc = game_desc;
    }
}
