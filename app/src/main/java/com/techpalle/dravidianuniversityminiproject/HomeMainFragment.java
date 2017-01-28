package com.techpalle.dravidianuniversityminiproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeMainFragment extends Fragment {
    Button buttonPhdDetails, buttonMphilDetails, buttonBTech, buttonMCA;
    ImageView imageViewView, imageViewSearch;
    MainActivity mainActivity;

    public HomeMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_main, container, false);
        buttonPhdDetails = (Button) v.findViewById(R.id.button_enter_phd_details);
        buttonMphilDetails = (Button) v.findViewById(R.id.button_enter_mphil_details);
        buttonBTech = (Button) v.findViewById(R.id.button_btech);
        buttonMCA = (Button) v.findViewById(R.id.button_mca);
        imageViewSearch = (ImageView) v.findViewById(R.id.image_search);
        imageViewView = (ImageView) v.findViewById(R.id.image_view);
        mainActivity = (MainActivity) getActivity();

        buttonPhdDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sub = "PhD";
                mainActivity.phdPage(sub);
            }
        });
        buttonMphilDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sub = "M. phil";
                mainActivity.phdPage(sub);
            }
        });
        buttonBTech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sub = "B. Tech";
                mainActivity.phdPage(sub);
            }
        });
        buttonMCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sub = "MCA";
                mainActivity.phdPage(sub);
            }
        });

        imageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.searchPage();
            }
        });

        imageViewView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.viewPage();
            }
        });
        return v;
    }

}
