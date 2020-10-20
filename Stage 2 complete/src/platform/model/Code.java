package platform.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Code {

    String code;

    String date;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String title;


    public Code() {
    }

    public Code(String title, String code, String date) {
        this.title = title;
        this.code = code;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
