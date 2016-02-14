package com.example.paulconroy.onit;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

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
 * Created by paulconroy on 17/12/2015.
 */
public class CheckBusUpdateAsync extends AsyncTask<String, String, JSONArray> {

    MainActivity mA = new MainActivity();
    Model m = Model.getInstance();
    private Context mContext;
    private Controller mController;

    public CheckBusUpdateAsync(Context context) {
        mContext = context;
    }

    @Override
    protected JSONArray doInBackground(String... params) {


        URL url;
        HttpURLConnection urlConnection = null;
        JSONObject response = new JSONObject();
        JSONArray busResults = new JSONArray();

        //mController = new Controller(mContext);

        m.settest();
        if (m.getTest() > 5)
            m.setBusAlert(true);

        //mController.stopAlarm();

        try {
            url = new URL(params[0]);
            String userpass = "USERNAME HERE" + ":" + "PASSWORD HERE";

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
        checkBusDueTime(busResults);
    }

    public void checkBusDueTime(JSONArray busResults) throws JSONException {
        JSONObject jRealObject = busResults.getJSONObject(0);
        System.out.println(jRealObject);
        //JSONObject jRealObject = response.get("duetime");


        String dueTime = jRealObject.getString("duetime");
        Log.d("testing", "due is" + dueTime);
        if (!dueTime.equalsIgnoreCase("due")) {
            int time = Integer.parseInt(dueTime);
            Log.d("testing2", "bus due time = " + time);
            if (time <= 3 && time >= 1) {
                Log.d("testing", "BUS IS COMING!!!");

                m.setBusAlert(true);


            }
        }
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);


    }

}
