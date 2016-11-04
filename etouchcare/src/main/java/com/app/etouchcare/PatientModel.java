package com.app.etouchcare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by guilherme on 2016-11-04.
 */

public class PatientModel {


    public List<String> listDataHeader;
    public HashMap<String, List<String>> listDataChild;
    //Setup List Data:
    private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        //Add Header data: Level 1
        listDataHeader.add("Manuel Rodriguez, ID: 574743");
        listDataHeader.add("Andrea Smith, ID: 574385");
        listDataHeader.add("Michael Phelps, ID: 5747434");

        //Add Child Data: Level 2
        List<String> ManuelRodriguez = new ArrayList<String>();
        ManuelRodriguez.add("Room: #34");
        ManuelRodriguez.add("Diagnosis: Flu");

        List<String> AndreaSmith = new ArrayList<String>();
        AndreaSmith.add("Room: #43");
        AndreaSmith.add("Diagnosis: Broken Leg");

        List<String> MichaelPhelps = new ArrayList<String>();
        MichaelPhelps.add("Room: #44");
        MichaelPhelps.add("Diagnosis: Migraines");

        //(Heather, Child Data)
        listDataChild.put(listDataHeader.get(0), ManuelRodriguez);
        listDataChild.put(listDataHeader.get(1), AndreaSmith);
        listDataChild.put(listDataHeader.get(2), MichaelPhelps);
    }
}
