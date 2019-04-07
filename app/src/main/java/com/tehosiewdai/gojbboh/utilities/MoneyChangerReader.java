package com.tehosiewdai.gojbboh.utilities;

import android.text.Html;
import android.util.Log;

import com.tehosiewdai.gojbboh.entity.MoneyChanger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MoneyChangerReader {

    private static final String TAG = MoneyChangerReader.class.getSimpleName();

    public static ArrayList<MoneyChanger> getMoneyChangerLocations(String results){
        JSONArray features = null;
        ArrayList<MoneyChanger> moneyChangers = null;

        try {
            JSONObject moneyChangerObject = new JSONObject(results);
            features = moneyChangerObject.getJSONArray("features");
        } catch (JSONException e) {
            Log.e(TAG, String.valueOf(e));
        }

        if (features != null) {
            moneyChangers = new ArrayList<>();
            for (int i = 0; i < features.length(); i++) {
                try {
                    JSONObject feature = features.getJSONObject(i);
                    double lat = feature.getJSONObject("geometry").getJSONArray("coordinates").getDouble(1);
                    double lng = feature.getJSONObject("geometry").getJSONArray("coordinates").getDouble(0);
                    String description = Html.fromHtml(feature.getJSONObject("properties").getString("Description")).toString();
                    String name = description.substring(description.lastIndexOf("NAME")+1, description.indexOf("BUSINESS_POSTALCODE"));
                    String postalCode = "Postal Code: ".concat(description.substring(description.lastIndexOf("BUSINESS_POSTALCODE")+19, description.indexOf("BUSINESS_ADDRESS2")));
                    String add1 = description.substring(description.lastIndexOf("BUSINESS_ADDRESS1")+17, description.indexOf("BUSINESS_TYPE"));
                    String add2 = description.substring(description.lastIndexOf("BUSINESS_ADDRESS2")+17, description.indexOf("BUSINESS_ADDRESS1"));
                    String address = "";
                    if(add2.compareTo("") != 0){
                        address = "\nAddress: ".concat(add2).concat("\n                  ").concat(add1);
                    } else {
                        address =  "\nAddress: ".concat(add1);
                    }


                    moneyChangers.add(new MoneyChanger(lat, lng, name, address, postalCode));

                } catch (JSONException e) {
                    Log.e(TAG,String.valueOf(e));
                }

            }
        }

        return moneyChangers;
    }

}
