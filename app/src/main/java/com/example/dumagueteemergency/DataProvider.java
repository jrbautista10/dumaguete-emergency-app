package com.example.dumagueteemergency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataProvider {

    public static HashMap<String, List<String>> getInfo()
    {
        HashMap<String, List<String>> EmergencyDetails = new HashMap<String, List<String>>();

        List<String> EMS = new ArrayList<String>();
        EMS.add("Globe Mobile: +63-905-518-6917");
        EMS.add("SUN Mobile: +63-922-880-8897");
        EMS.add("Cruztelco Landline: +63-35-225-9110");
        EMS.add("Globe Landline: +63-35-422-9110");
        EMS.add("ONERescueEMS Facebook"); // https://www.facebook.com/ONERescueEMS
        EMS.add("ONERescueEMS Messenger"); // https://m.me/ONERescueEMS

        List<String> Fire_Department = new ArrayList<String>();
        Fire_Department.add("Cruztelco Landline: +63-35-225-3445");
        Fire_Department.add("Globe Landline: +63-35-422-9672");
        Fire_Department.add("Globe Mobile: +63-977-198-1900");
        Fire_Department.add("Smart Mobile: +63-961-199-8377");

        List<String> PNP= new ArrayList<String>();
        PNP.add("Globe Mobile: +63-917-933-0022");
        PNP.add("Smart Mobile: +63-929-200-6999");
        PNP.add("Cruztelco Landline: +63-35-225-1766");

        List<String> NORECO = new ArrayList<String>();
        NORECO.add("Cruztelco Landline: +63-225-4830");
        NORECO.add("Globe Landline: +63-422-6522");
        NORECO.add("Globe Mobile : ±63-917-322-4237");
        NORECO.add("norecotwo Facebook"); // https://www.facebook.com/norecotwo
        NORECO.add("noreco2dumaguete Messenger"); // https://m.me/noreco2dumaguete

        List<String> CDRRMO = new ArrayList<String>();
        CDRRMO.add("Globe Mobile: +63-916-823-8193");
        CDRRMO.add("Cruztelco Landline 1 : +63-35-226-3483");
        CDRRMO.add("Cruztelco Landline 2: +63-35-225-1911");

        EmergencyDetails.put("ONE Rescue EMS", EMS);
        EmergencyDetails.put("DGTE Fire Department", Fire_Department);
        EmergencyDetails.put("DGTE PNP", PNP);
        EmergencyDetails.put("Noreco 2 Consumer Welfare Desk", NORECO);
        EmergencyDetails.put("Dumaguete City CDRRMO", CDRRMO);

        return EmergencyDetails;

    }
}
