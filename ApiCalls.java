package com.dealsheel.dealsheeluser.NetworkClasses;

import android.content.Context;
import android.graphics.Bitmap;
import com.dealsheel.dealsheeluser.interfaces.VolleyResponse;
import com.dealsheel.dealsheeluser.models.ImagePathModel;
import com.dealsheel.dealsheeluser.utilities.Constants;
import com.dealsheel.dealsheeluser.utilities.Functions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ApiCalls {

    static HashMap<String, String> emptyHashMap=new HashMap<>();
    static Map<String, VolleyMultipartRequest.DataPart> emptyDataPartParams=new HashMap<>();

    public static void loginCall(Context context, String phone, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("mobile",phone);

        volleyNetworkClass.makeRequest(Constants.LOGIN_URL,true, hashMap, emptyDataPartParams);
    }

    public static void verifyOTPCall(Context context, String device_token, String phone, String otp,final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("mobile",phone);
        hashMap.put("otp",otp);
        hashMap.put("device_type",Constants.device_type);
        hashMap.put("device_token",device_token);
        volleyNetworkClass.makeRequest(Constants.VERIFY_OTP_URL, true, hashMap, emptyDataPartParams);
    }

    public static void dashboardCall(Context context,String lat, String lng, final VolleyResponse volleyResponse){
        VolleyNetworkClass networkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("lat", lat);
        hashMap.put("lng", lng);
        networkClass.makeRequest(Constants.DASHBOARD_URL, false, hashMap, emptyDataPartParams);
    }

    public static void getCategoryChildListCall(Context context, String catID, String lat, String lng, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });
        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("cat_id",catID);
        hashMap.put("lat", lat);
        hashMap.put("lng", lng);
        volleyNetworkClass.makeRequest(Constants.CATEGORY_BASED_BUSINESS_URL, false, hashMap, emptyDataPartParams);
    }

    public static void businessDetailCall(Context context, String businessID, String offerID, final VolleyResponse volleyResponse){
        VolleyNetworkClass networkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                 volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });
        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("business_id",businessID);
        hashMap.put("offer_id",offerID);
        networkClass.makeRequest(Constants.BUSINESS_DETAIL_URL, true,hashMap, emptyDataPartParams);

    }

    public static void addOrRemoveFavourites(Context context, String addOrRemove, String businessID, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("business_id", businessID);
        hashMap.put("add_fav", addOrRemove);

        volleyNetworkClass.makeRequest(Constants.ADD_REMOVE_TO_FAVOURITE_URL, false, hashMap, emptyDataPartParams);
    }

    public static void getAllFavouriteList(Context context, boolean showProgress, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });

        volleyNetworkClass.makeRequest(Constants.ALL_FAVOURITE_LIST_URL,showProgress,emptyHashMap,emptyDataPartParams);
    }

    public static void getAllOrders(Context context, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });


        volleyNetworkClass.makeRequest(Constants.ALL_ORDER_URL, false, emptyHashMap, emptyDataPartParams);
    }

    public static void getInvoiceDetail(Context context, String invoice_id, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });
        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("invoice_id", invoice_id);
        volleyNetworkClass.makeRequest(Constants.INVOICE_DETAILS_URL, true, hashMap, emptyDataPartParams);
    }

    public static void viewProfile(Context context, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });
        volleyNetworkClass.makeRequest(Constants.VIEW_PROFILE_URL, true, emptyHashMap, emptyDataPartParams);
    }

    public static void registrationCall(Context context, String device_token, String name, String mobile, String email, String dob, String gender, String full_address, String lat, String lng, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });
        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("name",name);
        hashMap.put("mobile",mobile);
        hashMap.put("email",email);
        hashMap.put("dob",dob);
        hashMap.put("gender",gender);
        hashMap.put("full_address",full_address);
        hashMap.put("lat",lat);
        hashMap.put("lng",lng);
        hashMap.put("device_token",device_token);
        hashMap.put("device_type",Constants.device_type);
        volleyNetworkClass.makeRequest(Constants.REGISTRATION_URL, true, hashMap, emptyDataPartParams);
    }

    public static void updateProfile(Context context, Bitmap bitmap, String name, String dob, String gender, String address, String email, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("name",name);
        hashMap.put("dob",dob);
        hashMap.put("gender",gender);
        hashMap.put("address",address);
        hashMap.put("email",email);

        Map<String, VolleyMultipartRequest.DataPart> dataPartMap=new HashMap<>();
        long imagename = System.currentTimeMillis();
        dataPartMap.put("profile_pic", new VolleyMultipartRequest.DataPart(imagename + ".png", Functions.getFileDataFromDrawable(bitmap)));
        volleyNetworkClass.makeRequest(Constants.UPDATE_PROFILE_URL, true, hashMap, dataPartMap);
    }

    public static void getAllBusiness(Context context, String lat, String lng, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
              volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("lat", lat);
        hashMap.put("lng", lng);

        volleyNetworkClass.makeRequest(Constants.ALL_BUSINESS_LIST_URL, false, hashMap, emptyDataPartParams);
    }

    public static void getNotifications(Context context, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });
        volleyNetworkClass.makeRequest(Constants.NOTIFICATION_URL, false, emptyHashMap, emptyDataPartParams);
    }

    public static void deleteNotification(Context context, String notificationID, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("nId", notificationID);
        volleyNetworkClass.makeRequest(Constants.DELETE_NOTIFICATION_URl, true, hashMap, emptyDataPartParams);
    }

    public static void readNotification(Context context, String notificationID, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });
        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("nId", notificationID);
        volleyNetworkClass.makeRequest(Constants.READ_NOTIFICATION_URL, false, hashMap, emptyDataPartParams);

    }

    public static void redeemOffer(Context context, String offerID, String promoCode, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("offer_id",offerID);
        hashMap.put("promo_code",promoCode);
        volleyNetworkClass.makeRequest(Constants.REDEEM_OFFER_URL, true, hashMap, emptyDataPartParams);

    }

    public static void redeemOffer2(Context context, String offerID, String promoCode, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("invoice_id",offerID);
        volleyNetworkClass.makeRequest(Constants.REDEEM_OFFER_URL2, true, hashMap, emptyDataPartParams);

    }

    public static void howToRedeemContent(Context context, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });
        volleyNetworkClass.makeRequest(Constants.HOW_TO_REDEEM_URL, false, emptyHashMap, emptyDataPartParams);
    }

    public static void getDepartmentList(Context context, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });
        volleyNetworkClass.makeRequest(Constants.DEPARTMENT_LIST_URL, false, emptyHashMap, emptyDataPartParams);
    }

    public static void getAllTickets(Context context, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });
        volleyNetworkClass.makeRequest(Constants.ALL_TICKETS_URL, false, emptyHashMap, emptyDataPartParams);
    }

    public static void promoCodeCall(Context context, String promoCode, String offer_id, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });
        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("promo_code", promoCode);
        hashMap.put("offer_id", offer_id);
        volleyNetworkClass.makeRequest(Constants.PROMO_CODE_CHECK_URL, true, hashMap, emptyDataPartParams);
    }

    public static void buySubscription(Context context, String id, String method, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });
        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("subscription_id",id);
        hashMap.put("payment_method",method);
        volleyNetworkClass.makeRequest(Constants.BUY_SUBSCRIPTION_URL, true, hashMap, emptyDataPartParams);
    }

    public static void buyOffer(Context context, String offer_id, String payment_method, String promo_code, String discount_price, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });
        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("offer_id", offer_id);
        hashMap.put("payment_method", payment_method);
        hashMap.put("promo_code", promo_code);
        hashMap.put("discount_price", discount_price);

        volleyNetworkClass.makeRequest(Constants.BUY_DEAL_URL, true, hashMap, emptyDataPartParams);
    }

    public static void addCard(Context context, String cardNumber, String cardHolderName, String expiryDate, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });
        HashMap<String, String> hashMap=new HashMap<>();

        hashMap.put("card_number",cardNumber);
        hashMap.put("card_holder",cardHolderName);
        hashMap.put("card_expiry",expiryDate);
        hashMap.put("card_type","");
        volleyNetworkClass.makeRequest(Constants.ADD_CARD_URL, true, hashMap, emptyDataPartParams);
    }

    public static void getSavedCards(Context context, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });
        volleyNetworkClass.makeRequest(Constants.CARD_LIST_URL, true, emptyHashMap, emptyDataPartParams);
    }

    public static void getSubscriptionPlans(Context context, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });
        volleyNetworkClass.makeRequest(Constants.SUBSCRIPTION_PLANS_URL, true, emptyHashMap, emptyDataPartParams);
    }

    public static void deleteCard(Context context, String cardID, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });
        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("card_id",cardID);
        volleyNetworkClass.makeRequest(Constants.DELETE_CARD_URL, true, hashMap, emptyDataPartParams);
    }

    public static void generateTicket(Context context, String departmentID, String subject, String description, ArrayList<ImagePathModel> imagePathModelArrayList, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("department",departmentID);
        hashMap.put("subject",subject);
        hashMap.put("body",description);

        Map<String, VolleyMultipartRequest.DataPart> dataPartMap=new HashMap<>();
        for (int i=0; i<imagePathModelArrayList.size(); i++){
            long imagename2 = System.currentTimeMillis();
            String ext="";
            if (imagePathModelArrayList.get(i).getMimeType().equalsIgnoreCase("image")){
                dataPartMap.put("image_id["+i+"]", new VolleyMultipartRequest.DataPart(imagename2 + ".png", Functions.getFileDataFromDrawable(imagePathModelArrayList.get(i).getBitmap())));
            }else {
                if (imagePathModelArrayList.get(i).getMimeType().equalsIgnoreCase("application/doc")){
                    ext=".doc";
                }else if (imagePathModelArrayList.get(i).getMimeType().equalsIgnoreCase("text/plain")){
                    ext=".txt";
                }else if (imagePathModelArrayList.get(i).getMimeType().equalsIgnoreCase("application/ppt")){
                    ext=".ppt";
                }else if (imagePathModelArrayList.get(i).getMimeType().equalsIgnoreCase("application/pdf")){
                    ext=".pdf";
                }
               dataPartMap.put("image_id["+i+"]", new VolleyMultipartRequest.DataPart(imagename2 + ext, Functions.getFileDataFromFile(imagePathModelArrayList.get(i).getFile())));
            }

        }

        volleyNetworkClass.makeRequest(Constants.GENERATE_NEW_TICKET_URL, true, hashMap, dataPartMap);
    }

    public static void chatReply(Context context, final String body, final String ref_id, final ArrayList<Bitmap> bitmapArrayList, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("body", body);
        hashMap.put("ref_id", ref_id);

        Map<String, VolleyMultipartRequest.DataPart> dataPartMap=new HashMap<>();

        for (int i=0; i<bitmapArrayList.size(); i++){
            long imagename2 = System.currentTimeMillis();
            dataPartMap.put(" image_id["+i+"]", new VolleyMultipartRequest.DataPart(imagename2 + ".png", Functions.getFileDataFromDrawable(bitmapArrayList.get(i))));
        }

        volleyNetworkClass.makeRequest(Constants.TICKET_REPLY_URL, false, hashMap, dataPartMap);
    }

    public static void conversationList(Context context, String ticketID, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass= new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });
        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("ticket_id",ticketID);
        volleyNetworkClass.makeRequest(Constants.TICKET_CONVERSATION_URL, true, hashMap, emptyDataPartParams);
    }

    public static void getHtmlPage(Context context, String page,  final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getResult(error);
            }
        });

        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("page",page);

        volleyNetworkClass.makeRequest(Constants.PAGE_DETAILS_URL, false, hashMap, emptyDataPartParams);
    }

    public static void getAllStatesAndCities(Context context, final VolleyResponse volleyResponse){
        VolleyNetworkClass volleyNetworkClass=new VolleyNetworkClass(context, new VolleyResponse() {
            @Override
            public void getResult(String result) {
                volleyResponse.getResult(result);
            }

            @Override
            public void getError(String error) {
                volleyResponse.getError(error);
            }
        });

        volleyNetworkClass.makeRequest(Constants.All_STATES_CITIES_URL, true, emptyHashMap, emptyDataPartParams);

    }
}
