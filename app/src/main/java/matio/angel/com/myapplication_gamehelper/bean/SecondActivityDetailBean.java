package matio.angel.com.myapplication_gamehelper.bean;

/**
 * Created by Angel on 2016/1/15.
 */
public class SecondActivityDetailBean {
    private String aname;
    private String hotpic;
    private String writer;

    private String pubdate;
    private String content;


    public SecondActivityDetailBean(String aname, String hotpic, String writer, String pubdate, String content) {
        this.aname = aname;
        this.hotpic = hotpic;
        this.writer = writer;
        this.pubdate = pubdate;
        this.content = content;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getHotpic() {
        return hotpic;
    }

    public void setHotpic(String hotpic) {
        this.hotpic = hotpic;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
