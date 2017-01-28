package com.techpalle.dravidianuniversityminiproject;


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
public class EmailComposerFragment extends Fragment {
    EditText editTextTo, editTextSubject, editTextComposer;
    Button buttonSend, buttonClose;

    public EmailComposerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_email_composer, container, false);
        editTextTo = (EditText) v.findViewById(R.id.compose_email_to);
        editTextSubject = (EditText) v.findViewById(R.id.compose_email_subject);
        editTextComposer = (EditText) v.findViewById(R.id.compose_email_text);
        buttonSend = (Button) v.findViewById(R.id.button_send);
        buttonClose = (Button) v.findViewById(R.id.button_close);
        editTextSubject.requestFocus();
        Bundle b = getArguments();
        String email = b.getString("email");
        editTextTo.setText(email);
        editTextTo.setEnabled(false);
        editTextComposer.setText("\n"+"\n"+"\n"+"\n"+ "\n"+"\n"+ "\n"+"Sent by YUGAL's Mobile");
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Message Sent", Toast.LENGTH_SHORT).show();
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.homePage();
            }
        });

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.homePage();
            }
        });

        return v;
    }

}
