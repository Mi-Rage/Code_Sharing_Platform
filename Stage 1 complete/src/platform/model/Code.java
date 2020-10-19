package platform.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Code {

    String code;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String title;


    public Code() {
    }

    public Code(String title, String code) {
        this.title = title;
        this.code = code;
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
}
