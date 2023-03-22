package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentDetails extends AppCompatActivity {
    Button inputstudent,selectunits,view;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
    inputstudent=findViewById(R.id.inputstudentdetails);
    selectunits=findViewById(R.id.CourseDeails);
    view=findViewById(R.id.ViewDetails);
    inputstudent.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            replaceFragment(new firstpge());
        }
    });
    selectunits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Crsedtls());
            }
        });
    view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new AllDetails());
            }
        });



}

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment);
        fragmentTransaction.commit();
    }
    }