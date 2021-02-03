package com.tohandesign.turkeycovidtracker;

public class CovidInfoItem {


    private String date;
    private String DailyTest;
    private String DailyCase;
    private String DailyPatient;
    private String DailyDeath;
    private String DailyRecovered;
    private String TotalTest;
    private String TotalCase;
    private String TotalPatient;
    private String TotalDeath;
    private String TotalRecovered;
    private String TotalIntensive;
    private String TotalIntubated;
    private String ZaturreRate;
    private String HeavyPatients;
    private String BedOccupancy;
    private String IntensiveOccupancyRate;
    private String VentilatorRate;
    private String DetectionTime;
    private String FilationRate;


    public CovidInfoItem() {

    }

    public String getDetectionTime() {
        return DetectionTime;
    }

    public void setDetectionTime(String detectionTime) {
        DetectionTime = detectionTime;
    }

    public String getFilationRate() {
        return FilationRate;
    }

    public void setFilationRate(String filationRate) {
        FilationRate = filationRate;
    }

    public String getTotalDeath() {
        return TotalDeath;
    }

    public void setTotalDeath(String totalDeath) {
        TotalDeath = totalDeath;
    }

    public String getDailyTest() {
        return DailyTest;
    }

    public void setDailyTest(String dailyTest) {
        DailyTest = dailyTest;
    }

    public String getTotalTest() {
        return TotalTest;
    }

    public void setTotalTest(String totalTest) {
        TotalTest = totalTest;
    }

    public String getDailyCase() {
        return DailyCase;
    }

    public void setDailyCase(String dailyCase) {
        DailyCase = dailyCase;
    }

    public String getDailyPatient() {
        return DailyPatient;
    }

    public void setDailyPatient(String dailyPatient) {
        DailyPatient = dailyPatient;
    }

    public String getDailyDeath() {
        return DailyDeath;
    }

    public void setDailyDeath(String dailyDeath) {
        DailyDeath = dailyDeath;
    }

    public String getDailyRecovered() {
        return DailyRecovered;
    }

    public void setDailyRecovered(String dailyRecovered) {
        DailyRecovered = dailyRecovered;
    }

    public String getTotalCase() {
        return TotalCase;
    }

    public void setTotalCase(String totalCase) {
        TotalCase = totalCase;
    }

    public String getTotalPatient() {
        return TotalPatient;
    }

    public void setTotalPatient(String totalPatient) {
        TotalPatient = totalPatient;
    }

    public String getTotalRecovered() {
        return TotalRecovered;
    }

    public void setTotalRecovered(String totalRecovered) {
        TotalRecovered = totalRecovered;
    }

    public String getTotalIntensive() {
        return TotalIntensive;
    }

    public void setTotalIntensive(String totalIntensive) {
        TotalIntensive = totalIntensive;
    }

    public String getTotalIntubated() {
        return TotalIntubated;
    }

    public void setTotalIntubated(String totalIntubated) {
        TotalIntubated = totalIntubated;
    }

    public String getZaturreRate() {
        return ZaturreRate;
    }

    public void setZaturreRate(String zaturreRate) {
        ZaturreRate = zaturreRate;
    }

    public String getHeavyPatients() {
        return HeavyPatients;
    }

    public void setHeavyPatients(String heavyPatients) {
        HeavyPatients = heavyPatients;
    }

    public String getBedOccupancy() {
        return BedOccupancy;
    }

    public void setBedOccupancy(String bedOccupancy) {
        BedOccupancy = bedOccupancy;
    }

    public String getIntensiveOccupancyRate() {
        return IntensiveOccupancyRate;
    }

    public void setIntensiveOccupancyRate(String intensiveOccupancyRate) {
        IntensiveOccupancyRate = intensiveOccupancyRate;
    }

    public String getVentilatorRate() {
        return VentilatorRate;
    }

    public void setVentilatorRate(String ventilatorRate) {
        VentilatorRate = ventilatorRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
