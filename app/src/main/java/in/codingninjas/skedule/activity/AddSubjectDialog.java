package in.codingninjas.skedule.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import in.codingninjas.skedule.R;

/**
 * Created by DEEPU on 9/3/2016.
 */
public class AddSubjectDialog extends android.support.v4.app.DialogFragment implements  TimePickerFragment.TimePickerInterface ,
        DatePickerFragment.DatePickerInterface{


    public interface Clicker{
        void clicked(String s);
    }

    String s = "";

    private Clicker mListner;

    public void setListner(Clicker listner)
    {
        mListner = listner;
    }

    int hour = 0;
    int min = 0;


    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(1000, ActionBar.LayoutParams.WRAP_CONTENT);
    }


    View view;
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View v  = inflater.inflate(R.layout.add_subject_layout, container, false);
//        view = v;
//        ImageButton imgBtn = (ImageButton) v.findViewById(R.id.timePicker);
//
//        imgBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////               mListner.clicked();
//                TimePickerFragment newFragment = new TimePickerFragment();
//                newFragment.setListner(AddSubjectDialog.this);
//                newFragment.show(getFragmentManager(), "datePicker");
//
//            }
//        });
//        return v;
//    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        View v =  LayoutInflater.from(getContext()).inflate(R.layout.add_subject_layout, null);
        view = v;
        ImageButton timePicker = (ImageButton) v.findViewById(R.id.timePicker);
        ImageButton datePicker = (ImageButton) v.findViewById(R.id.datePicker);
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.setListner(AddSubjectDialog.this);
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.setListner(AddSubjectDialog.this);
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        return new AlertDialog.Builder(getContext()).setTitle("SUBJECT DETAILS")
                .setView(v)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListner.clicked(s);
                        Toast.makeText(getContext(), "positive button clicked", Toast.LENGTH_SHORT).show();
                        //TODO add to recycler view
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getDialog().dismiss();
                    }
                })
                .setCancelable(false)
                .create();
    }


    @Override
    public void setTime(int hourOfDay, int minute) {
        TextView hours = (TextView) view.findViewById(R.id.hours);
        TextView minutes = (TextView) view.findViewById(R.id.minutes);
        s = s + hourOfDay + " " + minute;
        hours.setText(hourOfDay + "");
        minutes.setText(minute + "");
    }

    @Override
    public void setDate(int day, int month, int year) {
        TextView dy = (TextView) view.findViewById(R.id.day);
        TextView mth = (TextView) view.findViewById(R.id.month);
        TextView yr = (TextView) view.findViewById(R.id.year);

        s = day + " " + month + " " + year + " : ";
        dy.setText(day + "");
        mth.setText(month + "");
        yr.setText(year + "");
    }
}

