package com.example.assignment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Crsedtls#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Crsedtls extends Fragment {
    public Spinner course, Year, semster;
    public Button save;
    public ListView listView;
    private static final String TAG = "CourseDetails"; // add this line
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Crsedtls() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Crsedtls.
     */
    // TODO: Rename and change types and number of parameters
    public static Crsedtls newInstance(String param1, String param2) {
        Crsedtls fragment = new Crsedtls();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_crsedtls, container, false);
//        setContentView(R.layout.activity_course_details);

        course = view.findViewById(R.id.SpCourse);
        Year = view.findViewById(R.id.SpYear);
        semster = view.findViewById(R.id.SpSemester);
        save = view.findViewById(R.id.BtnSave);
        listView = view.findViewById(R.id.LvUnits);

        String selectedValue = getActivity().getIntent().getStringExtra("selectedValue");
        String reg = getActivity().getIntent().getStringExtra("reg");
        Spinner spinner = view.findViewById(R.id.SpCourse);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.Courses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getPosition(selectedValue));
        String crse = course.getSelectedItem().toString();
        String yr = Year.getSelectedItem().toString();
        String sem = semster.getSelectedItem().toString();

        Year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {



            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                String sem=semster.getSelectedItem().toString();
                String yr=Year.getSelectedItem().toString();
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
                            String name = document.getString("unit_name");
                            String code = document.getString("unit_code");
                            units.add(name);
                            units.add(code);
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

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        semster.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                String sem=semster.getSelectedItem().toString();
                String yr=Year.getSelectedItem().toString();
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
//                            .whereGreaterThanOrEqualTo("unit_code", "4000")
//                            .whereLessThanOrEqualTo("unit_code", "7000");
                }

                query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot querySnapshot) {
                        ArrayList<String> units = new ArrayList<>();
                        for (QueryDocumentSnapshot document : querySnapshot) {
                            String name = document.getString("unit_name");
                            String code = document.getString("unit_code");
                            units.add(name);
                            units.add(code);
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

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        Query query;
//        if (sem.equals("1st Semester") && yr.equals("1st Year")) {
//            Toast.makeText(getContext(), "It Works", Toast.LENGTH_SHORT).show();
//        } else if (sem.equals("2nd Semester") && yr.equals("1st Year")) {
//            Toast.makeText(getContext(), "This also Works", Toast.LENGTH_SHORT).show();
//        }
//        if (sem.equals("1st Semester") && yr.equals("1st Year")) {
//            query = db.collection("units")
//                    .whereGreaterThanOrEqualTo("unit_code", "1100")
//                    .whereLessThanOrEqualTo("unit_code", "1200");
//        } else if (sem.equals("2nd Semester") && yr.equals("1st Year")) {
//            query = db.collection("units")
//                    .whereGreaterThanOrEqualTo("unit_code", "2200")
//                    .whereLessThanOrEqualTo("unit_code", "3200");
//        } else {
//            query = db.collection("units")
//                    .whereGreaterThanOrEqualTo("unit_code", "1200")
//                    .whereLessThanOrEqualTo("unit_code", "2100");
//        }
////        query = db.collection("units")
////                .whereGreaterThanOrEqualTo("unit_code", "1200")
////                .whereLessThanOrEqualTo("unit_code", "2100");
//
//        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot querySnapshot) {
//                ArrayList<String> units = new ArrayList<>();
//                for (QueryDocumentSnapshot document : querySnapshot) {
//                    String name = document.getString("unit_name");
//                    String code = document.getString("unit_code");
//                    units.add(name);
//                    units.add(code);
//                }
//                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
//                        android.R.layout.simple_list_item_1, units);
//                listView.setAdapter(adapter);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d(TAG, "Error getting documents: " + e.getMessage());
//            }
//        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference coursesRef = db.collection("students");

// Define the query based on the value you know
                Query query = coursesRef.whereEqualTo("reg_number", reg);

// Use the query to get the document you want to update
                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Define the new fields and their values
                                Map<String, Object> newData = new HashMap<>();
//                                updates.put("course",crse );
                                newData.put("year", yr);
                                newData.put("semester", sem);
                                Map<String, Object> updates = new HashMap<>();
                                updates.put("course", crse);

                                // Update the document with the new data
                                document.getReference().set(newData, SetOptions.merge());
                                Intent j = new Intent(getContext(), ViewDetails.class);
                                Bundle bundle1 = new Bundle();
                                bundle1.putString("reg", reg);
                                j.putExtras(bundle1);
                                startActivity(j);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

            }
        });










//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crsedtls, container, false);
    }
}