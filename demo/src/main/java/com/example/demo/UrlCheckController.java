package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UrlCheckController {
    private final String siteRunning = "Site is Running";
    private final String siteNotRunning = "Site is not running";
    private final String wrongUrl = "URL not found";

    @GetMapping("/check")
    public String getUrlStatus(@RequestParam String url) {
        String message = "";

        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode() / 100;
            if ((responseCode != 2 || responseCode != 3)) {
                message = siteNotRunning;
            } else {
                message = siteRunning;
            }

        } catch (MalformedURLException e) {
            message = wrongUrl;
        } catch (IOException e) {

            return siteNotRunning;
        }

        return message;
    }

}
