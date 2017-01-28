package com.techpalle.dravidianuniversityminiproject;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.TreeSet;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistraionFragment extends Fragment {
    EditText editTextNo, editTextName, editTextEmail, editTextPassword, editTextConfirmPassword, editTextHint;
    Button buttonLogin, buttonRegister;
    SharedPreferences sp;

    public RegistraionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_registraion, container, false);
            editTextNo = (EditText) v.findViewById(R.id.edit_text_eno);
            editTextName = (EditText) v.findViewById(R.id.edit_text_ename);
            editTextEmail = (EditText) v.findViewById(R.id.edit_text_regis_email);
            editTextPassword = (EditText) v.findViewById(R.id.edit_text_regis_password);
            editTextConfirmPassword = (EditText) v.findViewById(R.id.edit_text_confirm_password);
            editTextHint = (EditText) v.findViewById(R.id.edit_text_password_hint);
            buttonRegister = (Button) v.findViewById(R.id.button_register);
            buttonLogin = (Button) v.findViewById(R.id.button_regis_login);
            sp = getActivity().getSharedPreferences("credential", 0);

            buttonRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String eno = editTextNo.getText().toString().trim();
                    String name = editTextName.getText().toString().trim().toLowerCase();
                    String email = editTextEmail.getText().toString().trim().toLowerCase();
                    String confirmPassword = editTextConfirmPassword.getText().toString().trim();
                    String password = editTextPassword.getText().toString().trim();
                    String hint = editTextHint.getText().toString().trim().toLowerCase();

                    if (eno.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                            hint.isEmpty()) {
                        Toast.makeText(getActivity(), "Please fill all details", Toast.LENGTH_SHORT).show();
                    } else {
                        if (isValidEmail(email)) {
                            if (confirmPassword.equals(password)) {
                                SharedPreferences.Editor ed = sp.edit();
                                ed.putInt("count", 1);
                                ed.putInt("eno", Integer.parseInt(eno));
                                ed.putString("name", name);
                                ed.putString("email", email);
                                ed.putString("password", confirmPassword);
                                ed.putString("hint", hint);
                                ed.commit();
                                editTextNo.setText("");
                                editTextName.setText("");
                                editTextEmail.setText("");
                                editTextPassword.setText("");
                                editTextConfirmPassword.setText("");
                                editTextHint.setText("");
                                buttonLogin.requestFocus();
                                Toast.makeText(getActivity(), "Registration Done, Please login using  our Email Id and Password",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Password does not match",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Email Address is wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.loginPage();
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
}
