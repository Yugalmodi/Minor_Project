package com.techpalle.dravidianuniversityminiproject;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FragmentManager manager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null){
            WelcomeFragment welcomeFragment = new WelcomeFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            PhdDetailsFragment phdDetailsFragment = new PhdDetailsFragment();
            transaction.add(R.id.container,welcomeFragment);
            transaction.commit();
        }
    }

    public void loginPage(){

        LoginFragment loginFragment =new LoginFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, loginFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void registrationPage(){
        RegistraionFragment registraionFragment = new RegistraionFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, registraionFragment);
        transaction.commit();
    }

    public void homePage(){
        HomeMainFragment homeFragment = new HomeMainFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, homeFragment);
        transaction.commit();
    }

    public void phdPage(String sub){
        PhdDetailsFragment phdDetailsFragment = new PhdDetailsFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle b = new Bundle();
        switch (sub){
            case "MCA":
                b.putString("sub", sub);
                phdDetailsFragment.setArguments(b);
                break;
            case "PhD":
                b.putString("sub", sub);
                phdDetailsFragment.setArguments(b);
                break;
            case "B. Tech":
                b.putString("sub", sub);
                phdDetailsFragment.setArguments(b);
                break;
            case "M. phil":
                b.putString("sub", sub);
                phdDetailsFragment.setArguments(b);
                break;
        }
        transaction.replace(R.id.container, phdDetailsFragment);;
        transaction.commit();
    }

    public void dateValue(int year, int month, int date) {
        PhdDetailsFragment phdDetailsFragment = new PhdDetailsFragment();
        Bundle b =new Bundle();
        b.putInt("date", date);
        b.putInt("month", month);
        b.putInt("year", year);
        phdDetailsFragment.setArguments(b);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, phdDetailsFragment);;
        transaction.commit();
        Toast.makeText(MainActivity.this, date+"-"+month+"-"+year, Toast.LENGTH_SHORT).show();
    }

    public void searchPage(){
        SerachStudentFragment searchStudentFragment = new SerachStudentFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, searchStudentFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void searchDialog(String value, String valueType){
        SearchStudentDialogFragment searchStudentDialogFragment = new SearchStudentDialogFragment();
        Bundle b = new Bundle();
        b.putString("value", value);
        b.putString("valueType", valueType);
        searchStudentDialogFragment.setArguments(b);
        searchStudentDialogFragment.show(getSupportFragmentManager(), null);
    }
    public void emailComposerPage(String email){
        EmailComposerFragment emailComposerFragment = new EmailComposerFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle b = new Bundle();
        b.putString("email", email);
        emailComposerFragment.setArguments(b);
        transaction.replace(R.id.container, emailComposerFragment);
        transaction.commit();
    }
    public void viewPage(){
        ViewStudentFragment viewStudentFragment = new ViewStudentFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, viewStudentFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
