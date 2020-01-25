package com.dealsheel.dealsheeluser.NetworkClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.dealsheel.dealsheeluser.BuildConfig;
import com.dealsheel.dealsheeluser.activities.Login;
import com.dealsheel.dealsheeluser.interfaces.VolleyResponse;
import com.dealsheel.dealsheeluser.models.StatusModel;
import com.dealsheel.dealsheeluser.utilities.Constants;
import com.dealsheel.dealsheeluser.utilities.Functions;
import com.dealsheel.dealsheeluser.utilities.Preferences;
import com.dealsheel.dealsheeluser.utilities.SingleTone;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class VolleyNetworkClass {

   private VolleyResponse volleyResponse;
   Context context;
   private HashMap<String, String> stringHashMap;
   Preferences preferences;

    public VolleyNetworkClass(Context context, VolleyResponse volleyResponse) {
        this.volleyResponse = volleyResponse;
        this.context = context;
    }

    AlertDialog progressDialog;
    public void makeRequest(String url, final boolean showProgress, HashMap<String, String> hashMap, final Map<String, VolleyMultipartRequest.DataPart> params){

        stringHashMap=hashMap;
        String URL= Constants.BASE_URL+url;

        if (preferences==null){
            preferences=new Preferences(context);
        }

        if (showProgress){
           progressDialog=Functions.showProgressBar(context);
        }

        VolleyMultipartRequest volleyMultipartRequest=new VolleyMultipartRequest(Request.Method.POST, URL, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {



                if (showProgress){
                    progressDialog.dismiss();
                }
                String responseString=new String(response.data);
                Functions.printLog("allAPISRESPONSE"+responseString);

                //check user already login on another device
                try {
                    JSONObject jsonObject=new JSONObject(responseString);
                    StatusModel statusModel=new StatusModel(jsonObject);
                    if (statusModel.status.equals(Constants.ALREADY_LOGIN_STATUS_CODE)){
                        //logout
                        if (!preferences.getString(Preferences.ACCESS_TOKEN,"").equals("")){
                            Functions.showToast(statusModel.message);
                            preferences.clearPreferences();
                            context.startActivity(new Intent(context, Login.class));
                            ((Activity)context).finishAffinity();
                        }

                    }else {
                        if (volleyResponse!=null){
                            volleyResponse.getResult(responseString);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (showProgress){
                    progressDialog.dismiss();
                }
                String errorString=error.getMessage();
                if (volleyResponse!=null){
                    volleyResponse.getError(errorString);
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return checkParams(stringHashMap);
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("token", BuildConfig.TOKEN);
               // params.put("accessToken", "807566");
                params.put("accessToken", preferences.getString(Preferences.ACCESS_TOKEN,""));
                return params;
            }
        };

        volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(SingleTone.getInstance()).add(volleyMultipartRequest);
    }


    private Map<String, String> checkParams(Map<String, String> map) {
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
            if (pairs.getValue() == null) {
                map.put(pairs.getKey(), "");
            }
        }
        return map;
    }
}
