package com.rafiq.poldailygiftawardlinks;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class getdatetime {

    Activity a;
    RequestQueue r;
    String url = "https://www.timeapi.io/api/TimeZone/zone?timeZone=Asia/Karachi";

    public getdatetime(Activity activity){
        this.a = activity;

        r = Volley.newRequestQueue(a);


    }


    public void getdatetime (VolleyCallBack v){

        JSONObject j = new JSONObject();
        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url, j, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    v.onGetdatetime(response.getString("currentLocalTime"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        r.add(jr);
    }

    public interface VolleyCallBack{
        void onGetdatetime(String Time);
    }
}
