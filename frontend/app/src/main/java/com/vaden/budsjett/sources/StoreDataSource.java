package com.vaden.budsjett.sources;

import com.vaden.budsjett.Services;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class StoreDataSource {
    public boolean add(String name, String price, String storename, String sessionId) {
        JSONArray items = null;
        HttpURLConnection c = null;
        try {
            URL url = new URL(Services.STORE_ADD_URL
                    + "?name=" +name+ "&price=" +price+
                    "&storename=" +storename+
                    "&sessionId=" +sessionId);
            c = (HttpURLConnection) url.openConnection();
            c.setUseCaches(true);
            c.setRequestMethod("GET");
            System.out.println(Services.STORE_ADD_URL + "?name=" +name+ "&price=" +price+
                    "&storename=" +storename+
                    "&sessionId=" +sessionId);
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

    public JSONArray retrieve(String session) {
        JSONArray items = null;
        HttpURLConnection c = null;
        try {
            URL url = new URL(Services.STORE_GET_URL + "?sessionId=" + session);
            c = (HttpURLConnection) url.openConnection();
            c.setUseCaches(true);
            c.setRequestMethod("GET");

            if (c.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream(), StandardCharsets.UTF_8));
                String token = br.readLine();
                System.out.println(token + "BLABLABLBALBLA");
                items = new JSONArray(token);
                c.getInputStream().close(); // Why?
                return items;
            } else {
                System.out.println("ERROR: response code " + c.getResponseCode() + " " + c.getResponseMessage());
                System.out.println(url);
            }

            return items;
        } catch (Exception e) {
            return items;
        } finally {
            if (c != null) c.disconnect();
        }
    }
}
