package com.app.etouchcare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by guilherme on 2016-11-04.
 */

public class PatientModel {
    //add properties
    public String Name;
    public String Id;
    public String Room;
    public String Diagnosis;
    public Integer Group;

    public List<PatientModel> GetTop3Patients()
    {
        List<PatientModel> patients = new ArrayList<>();

        PatientModel patientModel = new PatientModel();
        patientModel.Id = "574743";
        patientModel.Group = 0;
        patientModel.Name = "Manuel Rodriguez";
        patientModel.Room = "34";
        patientModel.Diagnosis = "Flu";

        PatientModel patientModel2 = new PatientModel();
        patientModel2.Id = "574385";
        patientModel2.Group = 0;
        patientModel2.Name = "Andrea Smith";
        patientModel2.Room = "43";
        patientModel2.Diagnosis = "Broken Leg";

        PatientModel patientModel3 = new PatientModel();
        patientModel3.Id = "5747434";
        patientModel3.Group = 0;
        patientModel3.Name = "Michael Phelps";
        patientModel3.Room = "44";
        patientModel3.Diagnosis = "Migraines";

        patients.add(patientModel);
        patients.add(patientModel2);
        patients.add(patientModel3);

        return patients;
    }

    //bind some mock data
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    //Setup List Data:
    public HashMap<String, List<String>> prepareListData() {

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
        return listDataChild;
    }
}
