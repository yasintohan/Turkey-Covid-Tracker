package com.tohandesign.turkeycovidtracker;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VaccineTracker extends AsyncTask<Void, Void, Void> {

    public static List<VaccineItem> itemList = new ArrayList<VaccineItem>();

    private Context context;
    private ProgressDialog pd;


    public VaccineTracker(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("Lütfen bekleyiniz...");
        pd.setIndeterminate(false);
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected Void doInBackground(Void... params) {

        Document doc;
        String url = "https://covid19asi.saglik.gov.tr/";

        try {

            doc = Jsoup.connect(url).ignoreContentType(true).get();

            String vaccineText;
            Elements elements=doc.select("script");
            for(Element element : elements) {

                if(Jsoup.parse(element.html()).text().contains("var asiyapilankisisayisi =")) {
                    vaccineText = Jsoup.parse(element.html()).text();
                    vaccineText = vaccineText.replace("var asiyapilankisisayisi = ", "");
                    vaccineText = vaccineText.replace(";", "");
                    Log.i("JsonLog", vaccineText);
                    VaccineActivity.vaccineCount = Integer.parseInt(vaccineText);

                }
            }


            Elements cityElements =doc.select("#color1");
            for(Element element : cityElements) {
                String valueStr = Jsoup.parse(element.html()).text();
                valueStr = valueStr.replace(".", "");
                String cityName = Jsoup.parse( element.attr("data-adi")).text();
                VaccineItem item = new VaccineItem();
                item.setCityName(cityName);
                item.setValue(Integer.parseInt(valueStr));
                VaccineActivity.itemList.add(item);
                itemList.add(item);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        pd.dismiss();
        if(context instanceof VaccineActivity) {
            VaccineActivity.itemList = itemList;
        }
    }

}
