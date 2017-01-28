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
public class SerachStudentFragment extends Fragment {
    EditText editTextName, editTextMobile;
    Button buttonNameSearch, buttonMobileSearch;

    public SerachStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_serach_student, container, false);
        editTextName = (EditText) v.findViewById(R.id.edit_text_search_student_name);
        editTextMobile = (EditText) v.findViewById(R.id.edit_text_search_student_mobile);
        buttonNameSearch = (Button) v.findViewById(R.id.button_search_student_name);
        buttonMobileSearch = (Button) v.findViewById(R.id.button_search_student_mobile);

        buttonNameSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                MainActivity  mainActivity = (MainActivity) getActivity();
                if(name.isEmpty()){
                    Toast.makeText(getActivity(), "Please type some Name", Toast.LENGTH_SHORT).show();
                }
                else {
                    mainActivity.searchDialog(name.toLowerCase(), "name");
                }
            }
        });
        buttonMobileSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = editTextMobile.getText().toString().trim();
                MainActivity mainActivity = (MainActivity) getActivity();
                if(mobile.isEmpty()){
                    Toast.makeText(getActivity(), "Please type some Number", Toast.LENGTH_SHORT).show();
                }
                else if(10!=mobile.length()){
                    Toast.makeText(getActivity(), "Mobile number should be 10 digit", Toast.LENGTH_SHORT).show();
                }
                else{
                    mainActivity.searchDialog(mobile, "mobile");
                }
            }
        });
        return v;
    }

}
