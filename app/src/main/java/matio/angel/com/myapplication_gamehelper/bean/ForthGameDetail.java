package matio.angel.com.myapplication_gamehelper.bean;

/**
 * Created by Angel on 2016/1/16.
 */
public class ForthGameDetail {

    private String id;
    //存放图片的网址（有多个）
    private String snapshot;

    //小图片网址
    private String icon;

    //游戏名称
    private String name;

    //评分
    private String score;

    //版本
    private String version;

    //大小
    private String size;

    //下载人数
    private String count_dl;

    //积分
    private String dl_back_jifen;

    //下载
    private String android_dl;

    //游戏介绍
    private String game_desc;

    //
    private String game_task_state;

    public ForthGameDetail(String id, String snapshot, String icon, String name, String score,
                           String version, String size, String count_dl, String dl_back_jifen,
                           String android_dl, String game_desc, String game_task_state) {
        this.id = id;
        this.snapshot = snapshot;
        this.icon = icon;
        this.name = name;
        this.score = score;
        this.version = version;
        this.size = size;
        this.count_dl = count_dl;
        this.dl_back_jifen = dl_back_jifen;
        this.android_dl = android_dl;
        this.game_desc = game_desc;
        this.game_task_state = game_task_state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCount_dl() {
        return count_dl;
    }

    public void setCount_dl(String count_dl) {
        this.count_dl = count_dl;
    }

    public String getDl_back_jifen() {
        return dl_back_jifen;
    }

    public void setDl_back_jifen(String dl_back_jifen) {
        this.dl_back_jifen = dl_back_jifen;
    }

    public String getAndroid_dl() {
        return android_dl;
    }

    public void setAndroid_dl(String android_dl) {
        this.android_dl = android_dl;
    }

    public String getGame_desc() {
        return game_desc;
    }

    public void setGame_desc(String game_desc) {
        this.game_desc = game_desc;
    }

    public String getGame_task_state() {
        return game_task_state;
    }

    public void setGame_task_state(String game_task_state) {
        this.game_task_state = game_task_state;
    }
}
