package matio.angel.com.myapplication_gamehelper.bean;

/**
 * Created by Angel on 2016/1/20.
 */
public class SmallGameAd {
    private String name;
    private String imag;

    public SmallGameAd(String name, String imag) {
        this.name = name;
        this.imag = imag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImag() {
        return imag;
    }

    public void setImag(String imag) {
        this.imag = imag;
    }
}
