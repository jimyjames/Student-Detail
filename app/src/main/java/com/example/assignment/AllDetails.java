package com.example.assignment;

import static android.service.controls.ControlsProviderService.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllDetails extends Fragment {
    public FirebaseFirestore db;
    public ListView listView;
    public TextView Name,Course,School,Department,ID,Reg,Year,Semester,Gender;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AllDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static AllDetails newInstance(String param1, String param2) {
        AllDetails fragment = new AllDetails();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all_details, container, false);



        String reg=getActivity().getIntent().getStringExtra("reg");
        listView=view.findViewById(R.id.LVDetails);
        Name=view.findViewById(R.id.FName);
        Course=view.findViewById(R.id.FCourse);
        School=view.findViewById(R.id.FSchool);
        Department=view.findViewById(R.id.FDepartment);
        ID=view.findViewById(R.id.FIdNo);
        Reg=view.findViewById(R.id.FRegNo);
        Year=view.findViewById(R.id.FYear);
        Semester=view.findViewById(R.id.FSemester);
        Gender=view.findViewById(R.id.FGender);
//        lvDetails=findViewById(R.id.lvDetail);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection("students")
                .whereEqualTo("reg_number", reg);
//                .orderBy("unit name")
//                .limit(10);

        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot querySnapshot) {
                ArrayList<String> units = new ArrayList<>();
                for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                    String course = document.getString("course");
                    String name0=document.getString("first_name");
                    String name1=document.getString("middle_name");
                    String name2=document.getString("last_name");
                    String reg=document.getString("reg_number");
                    String id_number=document.getString("id_number");
                    String department=document.getString("department");
                    String gender=document.getString("school");
                    String school=document.getString("school");


                    String name=name0+ " " +name1+" " +name2;

//                    units.add(name);
//                    units.add(course);
//                    units.add(Reg);
//                    units.add(id_number);
//                    units.add(Department);

                    String yr=document.getString("year");
                    String sem= document.getString("semester");
//                    units.add(name);
//                    units.add(course);
//                    units.add(Reg);
//                    units.add(id_number);
//                    units.add(Department);
//                    units.add(yr);
//                    units.add(sem);
                    Course.setText(course);
                    Name.setText(name);
                    School.setText(school);
                    Department.setText(department);
                    Reg.setText(reg);
                    ID.setText(id_number);
                    Gender.setText(gender);
                    Semester.setText(sem);
                    Year.setText(yr);


                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    Query query;
                    if (sem.equals("1st Semester") && yr.equals("1st Year")){
                        Toast.makeText(getContext(), sem, Toast.LENGTH_SHORT).show();


                    } else if (sem.equals("2nd Semester") && yr.equals("1st Year")){
                        Toast.makeText(getContext(), "This also Works", Toast.LENGTH_SHORT).show();

                    }
                    if (sem.equals("1st Semester") && yr.equals("1st Year")) {
                        query = db.collection("units")
                                .whereGreaterThanOrEqualTo("unit_code", "1100")
                                .whereLessThanOrEqualTo("unit_code", "1200");
                    } else if (sem.equals("2nd Semester") && yr.equals("1st Year")) {
                        query = db.collection("units")
                                .whereGreaterThanOrEqualTo("unit_code", "1200")
                                .whereLessThanOrEqualTo("unit_code", "2000");
                    } else if (sem.equals("1st Semester") && yr.equals("2nd Year")) {
                        query = db.collection("units")
                                .whereGreaterThanOrEqualTo("unit_code", "2000")
                                .whereLessThanOrEqualTo("unit_code", "2200");
                    } else if (sem.equals("2nd Semester") && yr.equals("2nd Year")) {
                        query = db.collection("units")
                                .whereGreaterThanOrEqualTo("unit_code", "2200")
                                .whereLessThanOrEqualTo("unit_code", "2300");
                    } else if (sem.equals("1st Semester") && yr.equals("3rd Year")) {
                        query = db.collection("units")
                                .whereGreaterThanOrEqualTo("unit_code", "3000")
                                .whereLessThanOrEqualTo("unit_code", "3200");
                    } else if (sem.equals("2nd Semester") && yr.equals("3rd Year")) {
                        query = db.collection("units")
                                .whereGreaterThanOrEqualTo("unit_code", "3200")
                                .whereLessThanOrEqualTo("unit_code", "3300");
                    } else if (sem.equals("1st Semester") && yr.equals("4th Year")) {
                        query = db.collection("units")
                                .whereGreaterThanOrEqualTo("unit_code", "4000")
                                .whereLessThanOrEqualTo("unit_code", "4200");
                    } else if (sem.equals("2nd Semester") && yr.equals("4th Year")) {
                        query = db.collection("units")
                                .whereGreaterThanOrEqualTo("unit_code", "4200")
                                .whereLessThanOrEqualTo("unit_code", "4300");
                    } else if (yr.equals("5th Year")) {
                        query = db.collection("units");
//                            .whereGreaterThanOrEqualTo("unit_code", "1200")
//                            .whereLessThanOrEqualTo("unit_code", "2000");
                    } else {
                        query = db.collection("units");
//                    .whereGreaterThanOrEqualTo("unit_code", "4000")
//                    .whereLessThanOrEqualTo("unit_code", "7000");
                    }

                    query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot querySnapshot) {
                            ArrayList<String> units = new ArrayList<>();
                            for (QueryDocumentSnapshot document : querySnapshot) {
                                String uname = document.getString("unit_name");
                                String ucode = document.getString("unit_code");
                                units.add(uname);
                                units.add(ucode);

                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                                    android.R.layout.simple_list_item_1, units);
                            listView.setAdapter(adapter);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "Error getting documents: " + e.getMessage());
                        }
                    });


                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_list_item_1, units);
                listView.setAdapter(adapter);
            }


        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Error getting documents: " + e.getMessage());
            }
        });






        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_details, container, false);
    }
}