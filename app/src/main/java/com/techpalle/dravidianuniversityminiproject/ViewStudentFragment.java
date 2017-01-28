package com.techpalle.dravidianuniversityminiproject;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewStudentFragment extends Fragment {
    ListView listView;
    MyDatabase myDatabase;
    Cursor cursor;
    SimpleCursorAdapter simpleCursorAdapter;

    public ViewStudentFragment() {
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
        View v =  inflater.inflate(R.layout.fragment_view_student, container, false);
        listView = (ListView) v.findViewById(R.id.list_view);
        cursor = myDatabase.queryStudent();

        simpleCursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.row, cursor,
                new String[]{"_id", "name", "mobile", "email", "subject", "desc", "date"},
                new int[]{R.id.row_id, R.id.row_name, R.id.row_mobile, R.id.row_email,
                        R.id.row_subject, R.id.row_description, R.id.row_date});
        listView.setAdapter(simpleCursorAdapter);
        return v;
    }

}
