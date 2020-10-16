package com.vaden.budsjett.sources;

import com.vaden.budsjett.Services;

import org.json.JSONArray;

import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterDataSource {
    public boolean register(String username, String password,
                            String firstname, String lastname, String email,
                            String address, String city) {
        JSONArray items = null;
        HttpURLConnection c = null;
        try {
            URL url = new URL(Services.REGISTER_URL
                    + "?username=" +username+ "&password=" +password+
                    "&firstname=" +firstname+
                    "&lastname=" +lastname+ "&email=" +email+
                    "&address=" +address+
                    "&city=" +city);
            c = (HttpURLConnection) url.openConnection();
            c.setUseCaches(true);
            c.setRequestMethod("GET");

            if (c.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("TRUE: " + true);
                return true;
            } else {
                System.out.println("FALSE: " + false);
                return false;
            }


        } catch (Exception e) {
            System.out.println("FEILMELDING: " + e);
            return false;
        } finally {
            if (c != null) c.disconnect();
        }
    }
}
