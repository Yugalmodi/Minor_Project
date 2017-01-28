package com.techpalle.dravidianuniversityminiproject;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.view.View;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchStudentDialogFragment extends DialogFragment {
    MyDatabase myDatabase;
    String valueType, value;
    StringBuilder stringBuilderName, stringBuilderMobile;

    public SearchStudentDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDatabase = new MyDatabase(getActivity());
        myDatabase.open();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Dialog d = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Student Detail");
        Bundle b = getArguments();
        valueType = b.getString("valueType");
        value = b.getString("value");
        stringBuilderName = new StringBuilder();
        switch (valueType) {
            case "mobile":
                //Error 2:- Cursor never null
                //Error 3:- Dialog not getting Close
                final Cursor c = myDatabase.queryMobile(value);
                if(c!=null){
                    if(c.moveToNext()){
                        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_search_student_dialog, null);
                        TextView no = (TextView) v.findViewById(R.id.dialog_no);
                        TextView name = (TextView) v.findViewById(R.id.dialog_name);
                        TextView mobile = (TextView) v.findViewById(R.id.dialog_mobile);
                        TextView email = (TextView) v.findViewById(R.id.dialog_email);
                        TextView subject = (TextView) v.findViewById(R.id.dialog_subject);
                        TextView desc = (TextView) v.findViewById(R.id.dialog_description);
                        TextView date = (TextView) v.findViewById(R.id.dialog_date);
                        no.setText("No:- "+c.getString(c.getColumnIndex("no")));
                        name.setText("Name:- "+c.getString(c.getColumnIndex("name")));
                        mobile.setText("Mobile:- "+c.getString(c.getColumnIndex("mobile")));
                        email.setText("Email:- "+c.getString(c.getColumnIndex("email")));
                        subject.setText("Subject:- "+c.getString(c.getColumnIndex("subject")));
                        desc.setText("Description:- "+c.getString(c.getColumnIndex("desc")));
                        date.setText("Date of Joining:- "+c.getString(c.getColumnIndex("date")));
                        builder.setView(v);
                        builder.setNeutralButton("Text", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse("sms:"+c.getString(c.getColumnIndex("mobile"))));
                                startActivity(intent);
                            }
                        });
                        builder.setPositiveButton("Call", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:"+c.getString(c.getColumnIndex("mobile"))));
                                startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("EMAIL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MainActivity mainActivity = (MainActivity) getActivity();
                                mainActivity.emailComposerPage(c.getString(c.getColumnIndex("email")));
                                onDestroyView();
                            }
                        });
                    }
                    else {
                        builder.setMessage(value+", number not available");
                        builder.setNegativeButton("OK", null);
                    }
                }
                break;

            case "name":
                final Cursor c1 = myDatabase.queryName(value);
                if(c1!=null){
                    if(c1.moveToNext()){
                        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_search_student_dialog, null);
                        TextView no = (TextView) v.findViewById(R.id.dialog_no);
                        TextView name = (TextView) v.findViewById(R.id.dialog_name);
                        TextView mobile = (TextView) v.findViewById(R.id.dialog_mobile);
                        TextView email = (TextView) v.findViewById(R.id.dialog_email);
                        TextView subject = (TextView) v.findViewById(R.id.dialog_subject);
                        TextView desc = (TextView) v.findViewById(R.id.dialog_description);
                        TextView date = (TextView) v.findViewById(R.id.dialog_date);
                        no.setText("No:- "+c1.getString(c1.getColumnIndex("no")));
                        name.setText("Name:- "+c1.getString(c1.getColumnIndex("name")));
                        mobile.setText("Mobile:- "+c1.getString(c1.getColumnIndex("mobile")));
                        email.setText("Email:- "+c1.getString(c1.getColumnIndex("email")));
                        subject.setText("Subject:- "+c1.getString(c1.getColumnIndex("subject")));
                        desc.setText("Description:- "+c1.getString(c1.getColumnIndex("desc")));
                        date.setText("Date of Joining:- "+c1.getString(c1.getColumnIndex("date")));
                        builder.setView(v);
                        builder.setNeutralButton("Text", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse("sms:"+c1.getString(c1.getColumnIndex("mobile"))));
                                startActivity(intent);
                            }
                        });
                        builder.setPositiveButton("Call", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:"+c1.getString(c1.getColumnIndex("mobile"))));
                                startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("EMAIL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MainActivity mainActivity = (MainActivity) getActivity();
                                mainActivity.emailComposerPage(c1.getString(c1.getColumnIndex("email")));
                                onDestroyView();
                            }
                        });
                    }
                    else {
                        builder.setMessage("Mr. "+value+", not available");
                        builder.setPositiveButton("OK", null);
                    }
                }
                break;
        }
        d = builder.create();
        return d;
    }

    @Override
    public void onDestroy() {
        myDatabase.close();
        super.onDestroy();
    }
}
