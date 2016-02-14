package com.example.paulconroy.onit;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Base64;

import com.example.paulconroy.onit.model.BusData;
import com.example.paulconroy.onit.model.Model;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by paulconroy on 16/12/2015.
 */
public class GetBusAsync extends AsyncTask<String, String, JSONArray> {

    Model m = Model.getInstance();
    private Context mContext;

    public GetBusAsync(Context context) {
        mContext = context;
    }

    @Override
    protected JSONArray doInBackground(String... params) {


        URL url;
        HttpURLConnection urlConnection = null;
        JSONObject response = new JSONObject();
        JSONArray busResults = new JSONArray();


        try {
            url = new URL(params[0]);
            String userpass = "paulconroy" + ":" + "RghUjk£$%";

            urlConnection = (HttpURLConnection) url.openConnection();
            String encoded = Base64.encodeToString(userpass.getBytes("UTF-8"), Base64.NO_WRAP);
            urlConnection.setRequestProperty("Authorization", "Basic " + encoded);

            int responseCode = urlConnection.getResponseCode();


            if (responseCode == HttpStatus.SC_OK) {
                String responseString = readStream(urlConnection.getInputStream());
                response = new JSONObject(responseString);
                JSONParse(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return busResults;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();


        try {
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line + "\n");
            }
            reader.close();
            System.out.println("" + response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return response.toString();
    }

    public void JSONParse(JSONObject response) throws JSONException {
        JSONArray busResults = response.getJSONArray("results");
        storeBusData(response, busResults);
    }

    public void storeBusData(JSONObject response, JSONArray busResults) throws JSONException {
        m.emptyBusList();
        for (int i = 0; i < busResults.length(); i++) {
            JSONObject jRealObject = busResults.getJSONObject(i);
            BusData busData = new BusData();


            busData.setDestination(jRealObject.getString("destination"));
            busData.setDuetime(jRealObject.getString("duetime"));
            busData.setRoute(jRealObject.getString("route"));

            busData.setId(-1);

            m.addLocation(jRealObject.getString("origin"));
            m.addBus(busData);
        }
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);
        String busNumber = m.getBusNumber();
        Intent intent = new Intent(mContext, Result.class);
        intent.putExtra("number", busNumber);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);

    }


}

