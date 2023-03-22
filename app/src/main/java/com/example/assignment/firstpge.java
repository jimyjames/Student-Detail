package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link firstpge#newInstance} factory method to
 * create an instance of this fragment.
 */
public class firstpge extends Fragment {
    public  RadioButton male, female;
    public RadioGroup gender;
    public Spinner course, department, school;
    public EditText Fname, Mname, Lname, Nid, Reg;
    private Button save;
    public  FirebaseFirestore db;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
   ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public firstpge() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment firstpge.
     */
    // TODO: Rename and change types and number of parameters
    public static firstpge newInstance(String param1, String param2) {
        firstpge fragment = new firstpge();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_firstpge, container, true);


            if (getArguments() != null) {
                mParam1 = getArguments().getString(ARG_PARAM1);
                mParam2 = getArguments().getString(ARG_PARAM2);
                male = view.findViewById(R.id.RBM);
                female = view.findViewById(R.id.RBF);
                gender = view.findViewById(R.id.RGGender);
                course = view.findViewById(R.id.SpCourse);
                department = view.findViewById(R.id.SpDepartment);
                school = view.findViewById(R.id.SpSchool);
                Fname = view.findViewById(R.id.EdtFName);
                Mname = view.findViewById(R.id.EdtMName);
                Lname = view.findViewById(R.id.EdtLName);
                Nid = view.findViewById(R.id.EdtId);
                Reg = view.findViewById(R.id.EdtReg);
                save = view.findViewById(R.id.BtnSubmit);
                gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // Get the checked radio button id
                        RadioButton checkedRadioButton = view.findViewById(checkedId);
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
                        RadioGroup gender = view.findViewById(R.id.RGGender);




                        // check if any field is empty, show a toast message if any field is empty
                        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(middleName) || TextUtils.isEmpty(lastName)
                                || TextUtils.isEmpty(idNumber) || TextUtils.isEmpty(regNumber)) {
                            Toast.makeText(getContext(), "Please fill in all the fields!", Toast.LENGTH_SHORT).show();
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
                            FirebaseApp.initializeApp(getContext());
                            db = FirebaseFirestore.getInstance();



                            db.collection("students")
                                    .add(student)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            // showToast message and clear all input fields
//                                            Fragment i=new Crsedtls();
////                                            Intent i=new Intent(getContext(),Crsedtls.class);
//                                            Bundle bundle = new Bundle();
//                                            Bundle bundle1 = new Bundle();
//                                            bundle1.putString("reg",Reg.getText().toString());
//                                            bundle.putString("selectedValue", course.getSelectedItem().toString());
//                                            i.getActivity().getIntent().putExtras(bundle);
//                                            i.getActivity().getIntent().putExtras(bundle1);
//
//
//                                            startActivity(i);
                                            Toast.makeText(getContext(), "Student added successfully!", Toast.LENGTH_SHORT).show();
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
                                            Toast.makeText(getContext(), "Error adding student: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                });
            }







        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_firstpge, container, false);
    }
}