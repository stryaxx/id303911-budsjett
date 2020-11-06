package com.vaden.budsjett.sources;

import com.vaden.budsjett.Services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class LoginDataSource {

    public String login(String username, String password) {
        HttpURLConnection c = null;
        try {
            URL url = new URL(Services.LOGIN_URL + "username=" + username + "&password=" + password);
            c = (HttpURLConnection) url.openConnection();
            c.setUseCaches(true);
            c.setRequestMethod("GET");

            if(c.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream(), StandardCharsets.UTF_8));
                String token = br.readLine();
                Services.SESSION_ID = token;
                System.out.println(token + "= token");
                                c.getInputStream().close(); // Why?
                return token;
            } else {
                System.out.println("ERROR: response code " + c.getResponseCode() + " " + c.getResponseMessage());
                System.out.println(url);
            }

            return "";
        } catch (Exception e) {
            return "";
        } finally {
            if(c != null) c.disconnect();
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

}
