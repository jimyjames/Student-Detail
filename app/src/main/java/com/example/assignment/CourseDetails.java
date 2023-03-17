package com.example.assignment;

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

public class CourseDetails extends AppCompatActivity {
    public Spinner course,Year,semster;
    public Button save;
    public ListView listView;
    private static final String TAG = "CourseDetails"; // add this line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        course=findViewById(R.id.SpCourse);
        Year=findViewById(R.id.SpYear);
        semster=findViewById(R.id.SpSemester);
        save=findViewById(R.id.BtnSave);
        listView=findViewById(R.id.LvUnits);

        String selectedValue = getIntent().getStringExtra("selectedValue");
        String reg=getIntent().getStringExtra("reg");
        Spinner spinner = findViewById(R.id.SpCourse);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Courses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getPosition(selectedValue));
        String crse=course.getSelectedItem().toString();
        String yr=Year.getSelectedItem().toString();
        String sem=semster.getSelectedItem().toString();

        // Add the following code for querying data from Firebase Cloud Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection("units");
//                .whereEqualTo("unit name", "Discrete")
//                .orderBy("unit name")
//                .limit(10);

        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot querySnapshot) {
                ArrayList<String> units = new ArrayList<>();
                for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                    String name = document.getString("unit_name");
                    units.add(name);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(CourseDetails.this,
                        android.R.layout.simple_list_item_1, units);
                listView.setAdapter(adapter);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Error getting documents: " + e.getMessage());
            }
        });

        course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                                Intent j=new Intent(CourseDetails.this,ViewDetails.class);
                                Bundle bundle1 = new Bundle();
                                bundle1.putString("reg",reg);
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
    }
}
