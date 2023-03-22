package com.example.assignment;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class ViewDetails extends AppCompatActivity {
    public FirebaseFirestore db;
    public ListView listView, lvDetails;
    public TextView Name,Course,School,Department,ID,Reg,Year,Semester,Gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_details);


    String reg=getIntent().getStringExtra("reg");
        listView=findViewById(R.id.LVDetails);
        Name=findViewById(R.id.FName);
        Course=findViewById(R.id.FCourse);
        School=findViewById(R.id.FSchool);
        Department=findViewById(R.id.FDepartment);
        ID=findViewById(R.id.FIdNo);
        Reg=findViewById(R.id.FRegNo);
        Year=findViewById(R.id.FYear);
        Semester=findViewById(R.id.FSemester);
        Gender=findViewById(R.id.FGender);
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
                            Toast.makeText(getBaseContext(), sem, Toast.LENGTH_SHORT).show();


                        } else if (sem.equals("2nd Semester") && yr.equals("1st Year")){
                            Toast.makeText(getBaseContext(), "This also Works", Toast.LENGTH_SHORT).show();

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
                                ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
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
                ArrayAdapter<String> adapter = new ArrayAdapter<>(ViewDetails.this,
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
}