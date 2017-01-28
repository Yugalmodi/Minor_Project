package com.techpalle.dravidianuniversityminiproject;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhdDetailsFragment extends Fragment {
    EditText editTextNo, editTextName, editTextMobile, editTextEmail, editTextSubject, editTextDescription, editTextDate;
    Button buttonDate, buttonSave, buttonBack;
    MainActivity mainActivity;
    MyDatabase myDatabase;
    String no, name,mobile, email, subject, description, date;
    SharedPreferences sp;

    public PhdDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDatabase = new MyDatabase(getActivity());
        myDatabase.open();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phd_details, container, false);
        editTextNo = (EditText) v.findViewById(R.id.edit_text_student_no);
        editTextName = (EditText) v.findViewById(R.id.edit_text_student_ename);
        editTextMobile = (EditText) v.findViewById(R.id.edit_text_mobile);
        editTextEmail = (EditText) v.findViewById(R.id.edit_text_student_email);
        editTextSubject = (EditText) v.findViewById(R.id.edit_text_subject);
        editTextDescription = (EditText) v.findViewById(R.id.edit_text_description);
        editTextDate = (EditText) v.findViewById(R.id.edit_text_date);
        buttonDate = (Button) v.findViewById(R.id.button_date_picker);
        buttonSave = (Button) v.findViewById(R.id.button_save);
        buttonBack = (Button) v.findViewById(R.id.button_back);
        mainActivity = (MainActivity) getActivity();
        final Bundle b = getArguments();
        String sub = b.getString("sub");
        editTextSubject.setText(sub);
        editTextSubject.setEnabled(false);

        sp = getActivity().getSharedPreferences("date", 0);
        if(sp.getString("no", null) != null && sp.getString("name", null) != null)
        {
            editTextNo.setText(sp.getString("no", null));
            editTextName.setText(sp.getString("name", null));
            editTextMobile.setText(sp.getString("mobile", null));
            editTextEmail.setText(sp.getString("email", null));
            editTextSubject.setText(sp.getString("subject", null));
            editTextDescription.setText(sp.getString("desc", null));
        }
        Bundle b1 = getArguments();
        if(b1!=null) {
            int date = b1.getInt("date");
            int month = b1.getInt("month");
            int year = b1.getInt("year");
            editTextDate.setText(date + "-" + month + "-" + year);
            editTextDate.setEnabled(false);
        }
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                no = editTextNo.getText().toString().trim();
                name = editTextName.getText().toString().trim().toLowerCase();
                mobile = editTextMobile.getText().toString().trim();
                email = editTextEmail.getText().toString().trim().toLowerCase();
                subject = editTextSubject.getText().toString().trim().toLowerCase();
                description = editTextDescription.getText().toString().trim().toLowerCase();
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("no", no);
                ed.putString("name", name);
                ed.putString("mobile", mobile);
                ed.putString("email", email);
                ed.putString("subject", subject);
                ed.putString("desc", description);
                ed.commit();
                DateDialogFragment dateDialogFragment = new DateDialogFragment();
                dateDialogFragment.show(getActivity().getSupportFragmentManager(), null);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                no = editTextNo.getText().toString().trim();
                name = editTextName.getText().toString().trim().toLowerCase();
                mobile = editTextMobile.getText().toString().trim();
                email = editTextEmail.getText().toString().trim().toLowerCase();
                subject = editTextSubject.getText().toString().trim().toLowerCase();
                description = editTextDescription.getText().toString().trim().toLowerCase();
                String date = editTextDate.getText().toString().trim();
                if(no.isEmpty() || name.isEmpty() || mobile.isEmpty() || email.isEmpty() || subject.isEmpty()
                        || description.isEmpty() || date.isEmpty())
                {
                    Toast.makeText(getActivity(), "Please fill all columns", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (10==mobile.length()){
                        if (isValidEmail(email)){
                            Cursor c = myDatabase.queryMobile(mobile);
                            Cursor c1 = myDatabase.queryEmail(email);
                            if(c!=null || c1!=null){
                                if(c.moveToNext()){
                                    Toast.makeText(getActivity(), "Mobile number already registered, Please use another number",
                                            Toast.LENGTH_SHORT).show();
                                }
                                else if(c1.moveToNext()) {
                                    Toast.makeText(getActivity(), "Email already registered, Please Use Another Email ID",
                                            Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    myDatabase.insertStudent(no, name, mobile, email, subject, description, date);
                                    SharedPreferences.Editor ed = sp.edit();
                                    ed.clear();
                                    ed.commit();
                                    editTextNo.setText(""); editTextName.setText("");editTextMobile.setText("");
                                    editTextEmail.setText("");editTextDate.setText("");editTextDescription.setText("");
                                    buttonBack.requestFocus();
                                    Toast.makeText(getActivity(), "Entry Done", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        else {
                            Toast.makeText(getActivity(), "email is not correct", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getActivity(), "Mobile number should be 10 Digit", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.homePage();
            }
        });
        return v;
    }

    public final static boolean isValidEmail(CharSequence target)
    {
        if (TextUtils.isEmpty(target))
        {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    @Override
    public void onDestroy() {
        myDatabase.close();
        super.onDestroy();
    }
}
