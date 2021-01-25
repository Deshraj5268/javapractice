package javapractice.miscellaneousconcept;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class APiCall {

    public static void main(String[] args) throws IOException {
        restAPICall();
    }

    public static void restAPICall() throws IOException {
        try{
        String urlString = "http://localhost:8998/api/countries";
        URL obj = new URL(urlString);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + urlString);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String
        //System.out.println(response.toString());
        //Read JSON response and print .replace(/^\s+/,"")
            List<String> stringList = Collections.singletonList(response.toString());
        JSONObject myResponse = new JSONObject(stringList);
            Iterator<String> keys = myResponse.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                if (myResponse.get(key) instanceof JSONObject) {
                    System.out.printf(key);
                }
            }
        }catch(Exception e){
            System.out.printf(e.getMessage());
        }


    }
}
