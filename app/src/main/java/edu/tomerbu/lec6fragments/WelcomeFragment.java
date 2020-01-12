package edu.tomerbu.lec6fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {
    //we want to get the userName from the creating activity:
    private String userName;
    private static final String ARG_USERNAME = "mUserName"; //psfs
    //we cant use a constructor:
    //give parameters to the fragment:

    //a factory method: a static method that returns an object of Type "WelcomeFragment"
    public static WelcomeFragment newInstance(String userName) {
        WelcomeFragment frag = new WelcomeFragment();

        //bundle is a data structure: key, value pairs

        //Maps that have String keys
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, userName);

        frag.setArguments(args);
        return frag;
    }


    //    public static WelcomeFragment newInstance(String userName, int x) {
//        Bundle args = new Bundle();
//        args.putString("mName", userName);
//        args.putInt("score", x);
//        WelcomeFragment fragment = new WelcomeFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }
    private void aside() {
        //Map is KEY, VALUE data structure:
        //map is a data structure (like a List), instead of an index we have a KEY
        Map<String, String> map = new HashMap<>();
        map.put("Japan", "Tokyo");
        map.put("France", "Paris");
        map.put("Britain", "London");

        //very efficient:!
        String result = map.get("Japan");


        //Set prevents duplicates
        Set<String> names = new HashSet<>();
        names.add("Leonid");
        names.add("Leonid");

        //count = 1
        System.out.println(names.size());
    }

    private void practice() {
        //given an array:
        Integer[] arr = {1, 2, 0, 1, 2, 8, 9, 0};
        //remove all duplicates

        List<Integer> intsList = Arrays.asList(arr);

        HashSet<Integer> set = new HashSet<>(intsList); //work with sets

        intsList = new ArrayList<>(set); //removed all duplicates
    }

    private void sets() {
        Set<Integer> values = new HashSet<>();
        values.add(9);
        values.add(8);
        values.add(9);//9 is rejected -> sets prevent duplicates.


        //does our set contain 9:
        boolean contains = values.contains(9);


        //sets are iterable:
        //show all values in your set:
        for (Integer value : values) {
            System.out.println(value);
        }
    }

    public WelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    /**
     * <b>using the parameters</b>:
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //usage:
        Bundle args = getArguments();
        if (args != null) {
            userName = args.getString(ARG_USERNAME);
            Toast.makeText(getContext(), userName, Toast.LENGTH_SHORT).show();
        }


        //showDialog();

        //1) init the dialog fragment
        CustomDialogFragment cdf = new CustomDialogFragment();
        //2) call the show method
        cdf.show(getChildFragmentManager(), "Custom Dialog");
    }

    private void showDialog() {
        if (getContext() == null) return;

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());


        //inflater is an android os object that helps us parse XML File into a View Object:
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.custom_layout, null, false);
        //builder.setCustomTitle(v);

        builder.setView(v);

        //View v = ?
//        builder.setCustomTitle() //View
//        builder.setView() //replaces the message area with a custom view


        builder./*setTitle().setIcon()*/
                setMessage("Dialogs are here :)");

        //click outside does not dismiss the dialog:
        builder.setCancelable(false);

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//               if (which == DialogInterface.BUTTON_POSITIVE){ }

                Toast t = Toast.makeText(getContext(), "Done was clicked", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                t.show();
                dialog.dismiss();
            }
        });

        //show the dialog:
        builder.show();
    }

    private void showNoInternetDialog() {
        if (getContext() == null) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.
                setTitle("No Internet Connection").
                setMessage("The app requires an Internet Connection").
                setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0); //quit the app:
                    }
                }).
                setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        startActivity(intent);
                    }
                }).setCancelable(false);

        builder.show();
    }

    private void showSingleChoice() {
        final String[] choices = {"Red", "Green", "Blue", "Orange"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Pick A color");

        builder.setItems(choices, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String choice = choices[which];

                Toast.makeText(getContext(), choice, Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }


    private void showMultiChoice() {
        final String[] choices = {"Onions", "Tomatoes", "Olives"};
        final boolean[] userChoices = {false, false, false};
        if (getContext() == null) return;

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Choose Your Toppings");
        builder.setMultiChoiceItems(choices, userChoices, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                //
            }
        });

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                StringBuilder summary = new StringBuilder(); //StringBuilder is a better choice
                //show summary:
                for (int i = 0; i < userChoices.length; i++) {
                    if (userChoices[i]) {
                        summary.append(choices[i]).append("\n");
                    }
                }
                Toast.makeText(getContext(), summary, Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
        //builder.setMultiChoiceItems(choices, userChoices, new DialogInterface.OnClickListener{})
    }
}
