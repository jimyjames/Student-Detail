package com.example.assignment;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewDetails extends AppCompatActivity {
    public FirebaseFirestore db;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_details);


    String reg=getIntent().getStringExtra("reg");
        listView=findViewById(R.id.LVDetails);
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
                    String Reg=document.getString("reg_number");
                    String id_number=document.getString("id_number");
                    String Department=document.getString("department");
                    String name=name0+ " " +name1+" " +name2;

                    units.add(name);
                    units.add(course);
                    units.add(Reg);
                    units.add(id_number);
                    units.add(Department);

                    String yr=document.getString("year");
                    String sem= document.getString("semester");
//                    units.add(yr);
//                    units.add(sem);

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