package com.v1.urlshortener;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.net.InetAddress;

@Data
public class UrlShortenerForm {

    @NotBlank(groups = Post.class)
    private String url;

    public String getCurrentUrl(){
        String hostName = null;
        InetAddress addr = InetAddress.getLoopbackAddress();
        hostName = addr.getHostName();
        return hostName;
    }

    public UrlShortenerModel buildUrlShortenerModel() {

        String randomChar = getRandomChars();
        String currentHost = getCurrentUrl();

        UrlShortenerModel urlShortenerModel = new UrlShortenerModel();
        urlShortenerModel.setOriginalLink(this.url);
        urlShortenerModel.setShortLink("http://" + currentHost + "/"  + randomChar);
        urlShortenerModel.setRandomString(randomChar);

        return urlShortenerModel;
    }

    private String getRandomChars() {
        String randomStr = "";
        String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 5; i++)
            randomStr += possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length()));
        return randomStr;
    }

    public interface Post {

    }

    public interface Put {

    }
}
