package com.pixel.user.bloom.NetworkClasses;

import android.content.Context;
import android.graphics.Bitmap;
import com.pixel.user.bloom.Interfaces.VolleyResponse;
import com.pixel.user.bloom.utils.Constants;
import com.pixel.user.bloom.utils.Functions;
import java.util.HashMap;
import java.util.Map;

public class ApiCalls {
    public static void login(Context context, String mobile_number, String password, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("mobile_number", mobile_number);
        hashMap.put("password", password);
        hashMap.put("device_type", "1");
        hashMap.put("device_token","test");

        Functions.printHashMap(hashMap);
        //todo set device_token for notification
        volleyNetworkClass.makeRequest(Constants.LOGIN_URL, true, hashMap);
    }

    public static void registration(Context context, String full_name, String sponsor_id, String mobile_number, String email_address, String password, String confirm_password, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("full_name",full_name);
        hashMap.put("sponsor_id",sponsor_id);
        hashMap.put("mobile_number",mobile_number);
        hashMap.put("email_address",email_address);
        hashMap.put("password",password);
        hashMap.put("confirm_password",confirm_password);

        volleyNetworkClass.makeRequest(Constants.REGISTERATION_URL, true, hashMap);
    }

    public static void optVerification(Context context, String mobile_number, String otp, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("mobile_number", mobile_number);
        hashMap.put("otp", otp);
        volleyNetworkClass.makeRequest(Constants.OTP_VERIFICATION_URL, true, hashMap);
    }

    public static void sendOTP(Context context, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        volleyNetworkClass.makeRequest(Constants.SEND_OTP_URL, true, hashMap);
    }

    public static void userInfo(Context context, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        volleyNetworkClass.makeRequest(Constants.USER_INFO_URL, false);
    }

    public static void dashboard(Context context, boolean showProgress, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        volleyNetworkClass.makeRequest(Constants.DASHBOARD_URL, showProgress);
    }

    public static void changePassword(Context context, String password, String confirmPassword, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("password",password);
        hashMap.put("confirm_password",confirmPassword);
        volleyNetworkClass.makeRequest(Constants.CHANGE_PASSWORD_URL, true, hashMap);
    }

    public static void updateProfile(Context context, Bitmap bitmap, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();

        Map<String, VolleyMultipartRequest.DataPart> dataPartMap=new HashMap<>();
        long imageName = System.currentTimeMillis();
        dataPartMap.put("profile_pic", new VolleyMultipartRequest.DataPart(imageName + ".png", Functions.getFileDataFromDrawable(bitmap)));

        volleyNetworkClass.makeRequest(Constants.UPDATE_PROFILE_URL, true, hashMap, dataPartMap);
    }

    public static void editProfile(Context context, String full_name, String email_address, String dob, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("full_name", full_name);
        hashMap.put("email_address", email_address);
        hashMap.put("dob", dob);
        volleyNetworkClass.makeRequest(Constants.EDIT_PROFILE_URL, true, hashMap);
    }

    public static void logout(Context context, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        volleyNetworkClass.makeRequest(Constants.LOGOUT_URL, false);
    }

    public static void availableCarsList(Context context, String brandID, boolean showProgress, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("brand", brandID);
        volleyNetworkClass.makeRequest(Constants.CARS_LIST_URL, showProgress, hashMap);
    }

    public static void offeredCarsList(Context context, String offerID, boolean showProgress, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("offer", offerID);
        volleyNetworkClass.makeRequest(Constants.OFFERED_CAR_LIST_URL, showProgress, hashMap);
    }

    public static void brandsList(Context context, boolean showProgress, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        volleyNetworkClass.makeRequest(Constants.BRANDS_URL, showProgress);
    }

    public static void getCarDetails(Context context, String id, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("car", id);
        volleyNetworkClass.makeRequest(Constants.CAR_DETAIL_URL, true, hashMap);
    }

    public static void createBooking(Context context, String offerID, String distance_km, String car_id, String start_date, String start_location, String start_lat, String start_lng,
                                     String end_date, String end_location, String end_lat, String end_lng, String extra_km_required, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("car_id", car_id);
        hashMap.put("start_date", start_date);
        hashMap.put("start_location", start_location);
        hashMap.put("start_lat", start_lat);
        hashMap.put("start_lng", start_lng);
        hashMap.put("end_date", end_date);
        hashMap.put("end_location", end_location);
        hashMap.put("end_lat", end_lat);
        hashMap.put("end_lng", end_lng);
        hashMap.put("extra_km_required", extra_km_required);
        hashMap.put("offer_id", offerID);
        hashMap.put("distance_km", distance_km);
        volleyNetworkClass.makeRequest(Constants.CREATE_BOOKING_URL, true, hashMap);
    }

    public static void applyWalletAmount(Context context, String booking_id, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("booking_id", booking_id);
        volleyNetworkClass.makeRequest(Constants.APPLY_WALLET_AMOUNT_URL, true, hashMap);
    }

    public static void removeWalletAmount(Context context, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        //TODO ADD URL NAME
        volleyNetworkClass.makeRequest(Constants.REMOVE_WALLET_AMOUNT_URL, true, hashMap);
    }

    public static void applyPromoCode(Context context, String booking_id, String promo_code, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("booking_id", booking_id);
        hashMap.put("promo_code", promo_code);
        volleyNetworkClass.makeRequest(Constants.PROMOCODE_APPLY_URL, true, hashMap);
    }

    public static void getBookingDetail(Context context, String bookingID, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("booking_id", bookingID);
        volleyNetworkClass.makeRequest(Constants.BOOKING_DETAIL_URL, true, hashMap);
    }

    public static void bookingHistory(Context context, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        volleyNetworkClass.makeRequest(Constants.BOOKING_HISTORY_URL, true);
    }

    public static void allTransactions(Context context, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        //TODO set url
        volleyNetworkClass.makeRequest("", false);
    }

    public static void getNotifications(Context context, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        //TODO set url
        volleyNetworkClass.makeRequest("", false);
    }

    public static void verifyBooking(Context context, String promo_code, String booking_id, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });
        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("booking_id", booking_id);
        hashMap.put("promo_code", promo_code);
        volleyNetworkClass.makeRequest(Constants.BOOKING_VERIFY_URL, true, hashMap);
    }

    public static void verifyPayment(Context context, String payment_id, String booking_id, String ride_key, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });
        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("booking_id", booking_id);
        hashMap.put("payment_id", payment_id);
        hashMap.put("firebase_key", ride_key);
        volleyNetworkClass.makeRequest(Constants.BOOKING_VERIFY_PAYMENT_URL, true, hashMap);
    }

    public static void getWalletAmountCall(Context context, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void onResult(String result, String status, String message) {
                volleyResponse.onResult(result, status, message);
            }

            @Override
            public void onError(String error) {
                volleyResponse.onError(error);
            }
        });

        volleyNetworkClass.makeRequest(Constants.WALLET_AMOUNT_URL, true);
    }
}
