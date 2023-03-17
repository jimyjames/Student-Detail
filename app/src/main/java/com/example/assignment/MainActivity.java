package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RadioButton male, female;
    private RadioGroup gender;
    private Spinner course, department, school;
    private EditText Fname, Mname, Lname, Nid, Reg;
    private Button save;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize all views and components
        male = findViewById(R.id.RBM);
        female = findViewById(R.id.RBF);
        gender = findViewById(R.id.RGGender);
        course = findViewById(R.id.SpCourse);
        department = findViewById(R.id.SpDepartment);
        school = findViewById(R.id.SpSchool);
        Fname = findViewById(R.id.EdtFName);
        Mname = findViewById(R.id.EdtMName);
        Lname = findViewById(R.id.EdtLName);
        Nid = findViewById(R.id.EdtId);
        Reg = findViewById(R.id.EdtReg);
        save = findViewById(R.id.BtnSubmit);
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Get the checked radio button id
                RadioButton checkedRadioButton = findViewById(checkedId);
                // Do something with the checked radio button
            }
        });
        // add radio buttons to radio group and set default value for gender


        // set on click listener for save button
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get values from all input fields
                String firstName = Fname.getText().toString().trim();
                String middleName = Mname.getText().toString().trim();
                String lastName = Lname.getText().toString().trim();
                String idNumber = Nid.getText().toString().trim();
                String regNumber = Reg.getText().toString().trim();
                String genderValue = male.isChecked() ? "Male" : "Female";
                String courseValue = course.getSelectedItem().toString().trim();
                String departmentValue = department.getSelectedItem().toString().trim();
                String schoolValue = school.getSelectedItem().toString().trim();
                RadioGroup gender = findViewById(R.id.RGGender);




                // check if any field is empty, show a toast message if any field is empty
                if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(middleName) || TextUtils.isEmpty(lastName)
                        || TextUtils.isEmpty(idNumber) || TextUtils.isEmpty(regNumber)) {
                    Toast.makeText(MainActivity.this, "Please fill in all the fields!", Toast.LENGTH_SHORT).show();
                } else {
                    // create a new Firestore document with the data
                    Map<String, Object> student = new HashMap<>();
                    student.put("first_name", firstName);
                    student.put("middle_name", middleName);
                    student.put("last_name", lastName);
                    student.put("id_number", idNumber);
                    student.put("reg_number", regNumber);
                    student.put("gender", genderValue);
                    student.put("course", courseValue);
                    student.put("department", departmentValue);
                    student.put("school", schoolValue);

                    // add the document to the "students" collection in Firestore
                    FirebaseApp.initializeApp(MainActivity.this);
                    db = FirebaseFirestore.getInstance();

                    db.collection("students")
                            .add(student)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    // showToast message and clear all input fields
                                    Intent i=new Intent(MainActivity.this,CourseDetails.class);
                                    Bundle bundle = new Bundle();
                                    Bundle bundle1 = new Bundle();
                                    bundle1.putString("reg",Reg.getText().toString());
                                    bundle.putString("selectedValue", course.getSelectedItem().toString());
                                    i.putExtras(bundle);
                                    i.putExtras(bundle1);


                                    startActivity(i);
                                    Toast.makeText(MainActivity.this, "Student added successfully!", Toast.LENGTH_SHORT).show();
                                    // clear all input fields
                                    Fname.setText("");
                                    Mname.setText("");
                                    Lname.setText("");
                                    Nid.setText("");
                                    Reg.setText("");
                                    course.setSelection(0);
                                    department.setSelection(0);
                                    school.setSelection(0);
                                    male.setChecked(true);

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // show error message
                                    Toast.makeText(MainActivity.this, "Error adding student: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }
}
