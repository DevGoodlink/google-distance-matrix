package com.integrateur.json;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.util.Map;


/**
 * Created by DevGoodlink on 09/08/2016.
 */
public class Main {
    private static OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {
        /*for (String str : getTree()) {
            System.out.println(str);
        }*/

        Dm dm= getTree("Fes","Rabat|Meknes|Casablanca|settat|Taza");
        System.out.println("Origine : "+dm.getOriginAddresses().get(0));
        System.out.println("---------------Destinations ----------");
        for(int i=0;i<dm.getDestinationAddresses().size();i++ )
        {
            System.out.println((i+1)+" - "+dm.getDestinationAddresses().get(i)+" distance = "+dm.getRows().get(0).getElements().get(i).getDistance().getText());
        }
       // System.out.println(getTree().toString());
    }

    public static String getJSON(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static Dm getTree(String origine, String destinations ) {
        String json = null;

        try {
            String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+origine+"&destinations="+destinations+"&key=Your_Key";
            json = getJSON(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Dm dm = gson.fromJson(json, Dm.class);
        return dm;

    }

}
