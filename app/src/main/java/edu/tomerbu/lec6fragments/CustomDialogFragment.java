package edu.tomerbu.lec6fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomDialogFragment extends AppCompatDialogFragment {
    private Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_custom_dialog, container, false);

        //set Width
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        widthPixels *= 0.8; //80 Percent (casting to int due to Double 0.8)

        int heightPixels = (int) (getResources().getDisplayMetrics().heightPixels * 0.3);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(widthPixels, heightPixels);
        v.setLayoutParams(layoutParams);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn = view.findViewById(R.id.button_ok);
        btn.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Dismiss clicked", Toast.LENGTH_SHORT).show();
            dismiss();
        });
    }
}
