package com.techpalle.dravidianuniversityminiproject;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    EditText editTextEmail, editTextPassword, editTextForgetPassword;
    Button buttonLogin, buttonForgotPassword;
    SharedPreferences sp;
    int count = 0;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        editTextEmail = (EditText) v.findViewById(R.id.edit_text_login_user_name);
        editTextPassword = (EditText) v.findViewById(R.id.edit_text_login_password);
        editTextForgetPassword = (EditText) v.findViewById(R.id.edit_text_forgot_pass_email);
        buttonLogin = (Button) v.findViewById(R.id.button_login_login);
        buttonForgotPassword = (Button) v.findViewById(R.id.button_forgot_password);
        sp = getActivity().getSharedPreferences("credential", 0);
        count = sp.getInt("count", 0);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String savedEmail = sp.getString("email", null);
                String savedPassword = sp.getString("password", null);
                String email = editTextEmail.getText().toString().trim().toLowerCase();
                String password = editTextPassword.getText().toString().trim();
                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(getActivity(), "Please fill email ID and password", Toast.LENGTH_SHORT).show();
                }
                else if(count == 1)
                {
                    if (savedEmail.equals(email) && savedPassword.equals(password))
                    {
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.homePage();
                    }
                    else{
                        Toast.makeText(getActivity(),"Please check your email OR password", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getActivity(), "Please Register First", Toast.LENGTH_SHORT).show();
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.registrationPage();
                }

            }
        });

        buttonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return v;
    }

}
