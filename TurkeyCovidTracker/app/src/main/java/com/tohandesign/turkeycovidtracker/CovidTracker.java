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


public class CovidTracker extends AsyncTask<Void, Void, Void> {

    private Context context;

    private String totalDeath;

    private List<CovidInfoItem> itemList = new ArrayList<CovidInfoItem>();
    private CovidInfoItem covidItem;

    public CovidTracker(Context context) {
        this.context = context;
        covidItem = new CovidInfoItem();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Void doInBackground(Void... params) {
        // TODO Auto-generated method stub
        String address, phone;
        Document doc;
        String url = "https://covid19.saglik.gov.tr/TR-66935/genel-koronavirus-tablosu.html";

        try {

            doc = Jsoup.connect(url).ignoreContentType(true).get();

            Element elements=doc.select("script").last();


            String veri=elements.html();
            String aciklama=Jsoup.parse(veri).text();

            aciklama = aciklama.replace("// var geneldurumjson = ", "{ variables: ");
            aciklama = aciklama.replace(";//", "}");

            JSONObject jsnobject = new JSONObject(aciklama);
            Details.itemList.clear();
            DataActivity.itemList.clear();

            JSONArray jsonArray = jsnobject.getJSONArray("variables");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject explrObject = jsonArray.getJSONObject(i);

                CovidInfoItem item = new CovidInfoItem();
                item.setDate(explrObject.getString("tarih"));
                item.setDailyTest(explrObject.getString("gunluk_test"));
                item.setDailyCase(explrObject.getString("gunluk_vaka"));
                item.setDailyPatient(explrObject.getString("gunluk_hasta"));
                item.setDailyDeath(explrObject.getString("gunluk_vefat"));
                item.setDailyRecovered(explrObject.getString("gunluk_iyilesen"));
                item.setTotalTest(explrObject.getString("toplam_test"));
                item.setTotalCase(explrObject.getString("toplam_hasta"));
                item.setTotalDeath(explrObject.getString("toplam_vefat"));
                item.setTotalRecovered(explrObject.getString("toplam_iyilesen"));
                item.setTotalIntensive(explrObject.getString("toplam_yogun_bakim"));
                item.setTotalIntubated(explrObject.getString("toplam_entube"));
                item.setZaturreRate(explrObject.getString("hastalarda_zaturre_oran"));
                item.setHeavyPatients(explrObject.getString("agir_hasta_sayisi"));
                item.setBedOccupancy(explrObject.getString("yatak_doluluk_orani"));
                item.setIntensiveOccupancyRate(explrObject.getString("eriskin_yogun_bakim_doluluk_orani"));
                item.setVentilatorRate(explrObject.getString("ventilator_doluluk_orani"));
                item.setDetectionTime(explrObject.getString("ortalama_temasli_tespit_suresi"));
                item.setFilationRate(explrObject.getString("filyasyon_orani"));
                Details.itemList.add(item);
                DataActivity.itemList.add(item);
                itemList.add(item);
            }
            Log.i("JsonLog", String.valueOf(jsonArray.getJSONObject(0)));
            setCovidItem(itemList.get(0));


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

        if(context instanceof MainActivity) {


        MainActivity.totalDeathText.setText(covidItem.getTotalDeath());
        MainActivity.totalCaseText.setText(covidItem.getTotalCase());
        MainActivity.totalRecoveredText.setText(covidItem.getTotalRecovered());
        MainActivity.dailyCaseText.setText(covidItem.getDailyCase());
        MainActivity.dateText.setText(covidItem.getDate());
        }
        if(context instanceof Details) {



        }


    }

    public CovidInfoItem getCovidItem() {
        return covidItem;
    }

    public void setCovidItem(CovidInfoItem covidItem) {
        this.covidItem = covidItem;
    }

    public List<CovidInfoItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<CovidInfoItem> itemList) {
        this.itemList = itemList;
    }
}
