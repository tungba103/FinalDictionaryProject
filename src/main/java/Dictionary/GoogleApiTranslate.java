package Dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GoogleApiTranslate {

    public GoogleApiTranslate(){
    }

    public String translate(String langFrom, String langTo, String text) throws IOException {
        URL url = new URL(
                "https://script.google.com/macros/s/AKfycbyw6-MNCE-_-Pum2ZL3yxZzevbPzLWhsueBLmawKKeRQnMLSyY/exec"
                        + "?q=" + URLEncoder.encode(text, StandardCharsets.UTF_8)
                        + "&target=" + langTo + "&source=" + langFrom);
        StringBuilder response = new StringBuilder();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

}
