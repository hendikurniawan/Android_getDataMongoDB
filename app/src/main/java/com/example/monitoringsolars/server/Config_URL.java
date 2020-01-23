package com.example.monitoringsolars.server;

//This class is for storing all URLs as a model of URLs

public class Config_URL
{
    public static String base_URL           = "http://103.230.48.151:5000";

    //api yang di get dari ip dan nganu
    public static String dataHistori           = base_URL + "/api/getHistori";
    public static String dataRealtime          = base_URL + "/api/getRealtime";

}