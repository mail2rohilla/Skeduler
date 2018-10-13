package in.codingninjas.skedule.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import in.codingninjas.skedule.R;

/**
 * Created by DEEPU on 9/3/2016.
 */
public class RoutineDialog extends DialogFragment {

    public interface Clicker{
        void getPosition(int position);
        void dialogDismiss();
    }

    int position;
    Clicker mListner;
    public void setListner(Clicker listner){
        mListner = listner;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        View v = LayoutInflater.from(getActivity()).inflate(R.layout.routine_dialog_layout, null);

        String[] arr = {"1","2","3","4","5","6"};
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arr);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                savePosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(), "select one of the itms from list", Toast.LENGTH_SHORT).show();
            }
        });
        return new AlertDialog.
                Builder(getActivity())
                .setView(v)
                .setTitle("New Routine")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListner.getPosition(position);
                        dismiss();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
    }

    private void savePosition(int position){
        this.position = position;
    }

    @Override
    public void onDestroy() {
        mListner.dialogDismiss();
        super.onDestroy();
    }
}
