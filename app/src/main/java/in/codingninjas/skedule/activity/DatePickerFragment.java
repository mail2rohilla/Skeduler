package in.codingninjas.skedule.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by DEEPU on 9/3/2016.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {


    public void setListner(DatePickerInterface listner){
        mListner = listner;
    }
    public interface DatePickerInterface{
        void setDate(int day, int month, int year);
    }

    DatePickerInterface mListner;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        mListner.setDate(day, month, year);
//        Toast.makeText(getContext(), year + " " + month + " " + day , Toast.LENGTH_SHORT).show();
    }
}

