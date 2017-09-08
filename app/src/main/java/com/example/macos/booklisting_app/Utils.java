package com.example.macos.booklisting_app;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by macos on 08.09.2017.
 */

public class Utils {
    private Utils(){
    }
    private static ArrayList<Book> parseJson(String json){
        ArrayList<Book> list = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(json);
            JSONArray items = root.getJSONArray("items");
            for(int i = 0; i<items.length();i++){
                JSONObject jsonBook = items.getJSONObject(i);
                Book book = new Book(jsonBook.getString("thumbnail"),jsonBook.getString("title"),jsonBook.getString("authors"));
                list.add(book);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Utils","Problem while parsing data");
        }
        return list;
    }
    private static String makeHttpConnection(URL url)throws IOException{
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        if(url == null){
            return jsonResponse;
        }
        try{
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(10000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200){
                stream = urlConnection.getInputStream();
                jsonResponse = readFromStream(stream);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally {
            if (urlConnection!=null) {
                urlConnection.disconnect();
            }
            if(stream!= null){
                stream.close();
            }

        }
        return jsonResponse;


    }

    private static String readFromStream(InputStream stream)throws IOException{
        StringBuilder builder = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        try {
            String line = bufferedReader.readLine();
            while(line!=null){
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            reader.close();
            bufferedReader.close();
        }
        return builder.toString();


    }
    public static List<Book> fetchData(String url){
        URL urly = createUrl(url);
        String jsonResponse = null;
        try {
        jsonResponse = makeHttpConnection(urly);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Book> list = parseJson(jsonResponse);
        return list;
    }
    public static URL createUrl(String stringUrl){
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

}
