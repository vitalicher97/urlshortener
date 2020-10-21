package urlshortener.model;

import javax.persistence.*;

@Entity
@Table(name = "URLs")
public class URLsModel {

    @Id
    private int requestID;
    private String longURL;
    private String shortURL;

    protected URLsModel() {
    }

    public URLsModel(int request_id, String long_url, String short_url){
        this.requestID = request_id;
        this.longURL = long_url;
        this.shortURL = short_url;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int request_id) {
        this.requestID = request_id;
    }

    public String getLongURL() {
        return longURL;
    }

    public void setLongURL(String long_url) {
        this.longURL = long_url;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String short_url) {
        this.shortURL = short_url;
    }
}
