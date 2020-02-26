package com.pixel.user.bloom.ModelClasses;

import org.json.JSONException;
import org.json.JSONObject;

public class StatusModel {
    public String status;
    public String message;

    public StatusModel(JSONObject jsonObject) {
        try {
            this.status = jsonObject.getString("status");
            this.message = jsonObject.getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
