package com.pixel.user.bloom.NetworkClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pixel.user.bloom.Activities.Login;
import com.pixel.user.bloom.BuildConfig;
import com.pixel.user.bloom.Interfaces.VolleyResponse;
import com.pixel.user.bloom.ModelClasses.StatusModel;
import com.pixel.user.bloom.R;
import com.pixel.user.bloom.utils.Constants;
import com.pixel.user.bloom.utils.Functions;
import com.pixel.user.bloom.utils.PreferenceDelegate;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class VolleyNetworkClass {

    private VolleyResponse volleyResponse;
    Context context;
    private HashMap<String, String> stringHashMap;
    PreferenceDelegate preferences;
    AlertDialog progressDialog;

    public VolleyNetworkClass(Context context, VolleyResponse volleyResponse) {
        this.volleyResponse = volleyResponse;
        this.context = context;
    }

    public void makeRequest(String url, final boolean showProgress, HashMap<String, String> hashMap, final Map<String, VolleyMultipartRequest.DataPart> params){

        stringHashMap=hashMap;
        String URL= Constants.BASE_URL+url;

        if (preferences==null){
            preferences=new PreferenceDelegate(context);
        }

        if (showProgress){
           progressDialog= Functions.showProgressBar(context);
        }

        VolleyMultipartRequest volleyMultipartRequest=new VolleyMultipartRequest(Request.Method.POST, URL, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {

                if (showProgress){
                    progressDialog.dismiss();
                }
                String responseString=new String(response.data);


                //check user already login on another device
                try {
                    JSONObject jsonObject=new JSONObject(responseString);
                    StatusModel statusModel=new StatusModel(jsonObject);
                    if (statusModel.status.equals(Constants.ALREADY_LOGIN_STATUS_CODE)){
                        //auto logout
                        if (!preferences.getString(PreferenceDelegate.ACCESS_TOKEN,"").equals("")){

                            Toast.makeText(context, statusModel.message, Toast.LENGTH_SHORT).show();

                            preferences.setString(PreferenceDelegate.ACCESS_TOKEN,"");
                            context.startActivity(new Intent(context, Login.class));
                            ((Activity)context).finishAffinity();
                        }

                    }else {
                        if (volleyResponse!=null){
                            String status="";
                            String message="";
                            if (jsonObject.has("status")){
                                status=jsonObject.getString("status");
                            }

                            if (jsonObject.has("message")){
                                message=jsonObject.getString("message");
                            }

                            volleyResponse.onResult(responseString, status, message);
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
                    volleyResponse.onError(errorString);
                }

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(context,context.getString(R.string.timeOut) , Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(context,context.getString(R.string.requestUnAuthorized) , Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(context,context.getString(R.string.serverError) , Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(context,context.getString(R.string.networkIssue) , Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(context,context.getString(R.string.parserError) , Toast.LENGTH_SHORT).show();
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
                params.put("Token", BuildConfig.TOKEN);
                params.put("AccessToken", preferences.getString(PreferenceDelegate.ACCESS_TOKEN,""));
                return params;
            }
        };

        volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(context).add(volleyMultipartRequest);
    }

    public void makeRequest(String url, final boolean showProgress, HashMap<String, String> hashMap){

        stringHashMap=hashMap;
        String URL= Constants.BASE_URL+url;

        if (preferences==null){
            preferences=new PreferenceDelegate(context);
        }

        if (showProgress){
            progressDialog= Functions.showProgressBar(context);
        }

        VolleyMultipartRequest volleyMultipartRequest=new VolleyMultipartRequest(Request.Method.POST, URL, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {

                if (showProgress){
                    progressDialog.dismiss();
                }
                String responseString=new String(response.data);

                //check user already login on another device
                try {
                    JSONObject jsonObject=new JSONObject(responseString);
                    StatusModel statusModel=new StatusModel(jsonObject);
                    if (statusModel.status.equals(Constants.ALREADY_LOGIN_STATUS_CODE)){
                        //auto logout
                        if (!preferences.getString(PreferenceDelegate.ACCESS_TOKEN,"").equals("")){
                            Toast.makeText(context, statusModel.message, Toast.LENGTH_SHORT).show();
                            preferences.setString(PreferenceDelegate.ACCESS_TOKEN,"");
                            context.startActivity(new Intent(context, Login.class));
                            ((Activity)context).finishAffinity();
                        }

                    }else {
                        if (volleyResponse!=null){
                            String status="";
                            String message="";
                            if (jsonObject.has("status")){
                                status=jsonObject.getString("status");
                            }

                            if (jsonObject.has("message")){
                                message=jsonObject.getString("message");
                            }

                            volleyResponse.onResult(responseString, status, message);
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
                    volleyResponse.onError(errorString);
                }

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(context,context.getString(R.string.timeOut) , Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(context,context.getString(R.string.requestUnAuthorized) , Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(context,context.getString(R.string.serverError) , Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(context,context.getString(R.string.networkIssue) , Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(context,context.getString(R.string.parserError) , Toast.LENGTH_SHORT).show();
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return checkParams(stringHashMap);
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                return null;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Token",BuildConfig.TOKEN);
                params.put("AccessToken", preferences.getString(PreferenceDelegate.ACCESS_TOKEN,""));
                return params;
            }
        };

        volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(context).add(volleyMultipartRequest);
    }

    public void makeRequest(String url, final boolean showProgress){

        String URL= Constants.BASE_URL+url;

        if (preferences==null){
            preferences=new PreferenceDelegate(context);
        }

        if (showProgress){
            progressDialog= Functions.showProgressBar(context);
        }

        VolleyMultipartRequest volleyMultipartRequest=new VolleyMultipartRequest(Request.Method.POST, URL, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {

                if (showProgress){
                    progressDialog.dismiss();
                }
                String responseString=new String(response.data);

                //check user already login on another device
                try {
                    JSONObject jsonObject=new JSONObject(responseString);
                    StatusModel statusModel=new StatusModel(jsonObject);
                    if (statusModel.status.equals(Constants.ALREADY_LOGIN_STATUS_CODE)){
                        //auto logout
                        if (!preferences.getString(PreferenceDelegate.ACCESS_TOKEN,"").equals("")){
                            Toast.makeText(context, statusModel.message, Toast.LENGTH_SHORT).show();
                            preferences.setString(PreferenceDelegate.ACCESS_TOKEN,"");
                            context.startActivity(new Intent(context, Login.class));
                            ((Activity)context).finishAffinity();
                        }

                    }else {
                        if (volleyResponse!=null){
                            String status="";
                            String message="";
                            if (jsonObject.has("status")){
                                status=jsonObject.getString("status");
                            }

                            if (jsonObject.has("message")){
                                message=jsonObject.getString("message");
                            }

                            volleyResponse.onResult(responseString, status, message);
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
                    volleyResponse.onError(errorString);
                }

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(context,context.getString(R.string.timeOut) , Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(context,context.getString(R.string.requestUnAuthorized) , Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(context,context.getString(R.string.serverError) , Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(context,context.getString(R.string.networkIssue) , Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(context,context.getString(R.string.parserError) , Toast.LENGTH_SHORT).show();
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return null;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                return null;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Token",BuildConfig.TOKEN);
                params.put("AccessToken", preferences.getString(PreferenceDelegate.ACCESS_TOKEN,""));
                return params;
            }
        };

        volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(context).add(volleyMultipartRequest);
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

    public void makeGetRequest(String url, String param, final boolean showProgress){
        if (preferences==null){
            preferences=new PreferenceDelegate(context);
        }

        if (showProgress){
            progressDialog= Functions.showProgressBar(context);
        }

        String URL= Constants.BASE_URL+url+"?"+param;
        String SURL=URL.replace("%20","");


        RequestQueue requestQueue= Volley.newRequestQueue(context);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, SURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (showProgress){
                    progressDialog.dismiss();
                }

                //check user already login on another device
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    StatusModel statusModel=new StatusModel(jsonObject);
                    if (statusModel.status.equals(Constants.ALREADY_LOGIN_STATUS_CODE)){
                        //auto logout
                        if (!preferences.getString(PreferenceDelegate.ACCESS_TOKEN,"").equals("")){
                            Toast.makeText(context, statusModel.message, Toast.LENGTH_SHORT).show();
                            preferences.setString(PreferenceDelegate.ACCESS_TOKEN,"");
                            context.startActivity(new Intent(context, Login.class));
                            ((Activity)context).finishAffinity();
                        }

                    }else {
                        if (volleyResponse!=null){
                            String status="";
                            String message="";
                            if (jsonObject.has("status")){
                                status=jsonObject.getString("status");
                            }

                            if (jsonObject.has("message")){
                                message=jsonObject.getString("message");
                            }

                            volleyResponse.onResult(response, status, message);
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
                    volleyResponse.onError(errorString);
                }

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(context,context.getString(R.string.timeOut) , Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(context,context.getString(R.string.requestUnAuthorized) , Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(context,context.getString(R.string.serverError) , Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(context,context.getString(R.string.networkIssue) , Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(context,context.getString(R.string.parserError) , Toast.LENGTH_SHORT).show();
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headerMap=new HashMap<>();
                headerMap.put("Token",BuildConfig.TOKEN);
                headerMap.put("AccessToken", preferences.getString(PreferenceDelegate.ACCESS_TOKEN,""));
                return headerMap;
            }
        };

        requestQueue.add(stringRequest);
    }
}