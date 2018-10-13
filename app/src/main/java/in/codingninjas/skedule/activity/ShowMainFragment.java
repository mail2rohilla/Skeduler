package in.codingninjas.skedule.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import in.codingninjas.skedule.R;

/**
 * Created by DEEPU on 9/3/2016.
 */
public class ShowMainFragment extends Fragment implements RecyclerAdapter.Clicker,
        BottomSheetFragment.BottomSheetInterface,
        AddSubjectDialog.Clicker{

    public ShowMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View mainView =  inflater.inflate(R.layout.fragment_show_main, container, false);



        RecyclerView rc = (RecyclerView)mainView.findViewById(R.id.recyclerView);
        RecyclerAdapter adapter  = new RecyclerAdapter();
        adapter.setListner(this);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rc.setLayoutManager(lm);
        rc.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) mainView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                BottomSheetFragment bottomSheetDialogFragment = new BottomSheetFragment();
                bottomSheetDialogFragment.show(getActivity().getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
                bottomSheetDialogFragment.setListner(ShowMainFragment.this);

            }
        });
        return mainView;
    }


    @Override
    public void toggleButtonSubjectStartEbdState(boolean state) {
        //TODO state of suject whw=ether reading or noy recieived

    }

    public void bottomSheetClicked(int no) {
        switch(no)
        {
            case Constants.NEW_ROUTINE_CLICKED:
                startActivityForResult(new Intent(getContext(), TranslucentActivity.class),
                        Constants.REQUEST_CODE_FOR_ROUTINE_DIALOG);

                break;

            case Constants.NEW_SUBJECT_CLICKED:
                showDialog();
                break;

        }
    }

    void showDialog()
    {
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
        android.app.Fragment prev = getActivity().getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.

        AddSubjectDialog newFragment = new AddSubjectDialog();
        newFragment.setListner(this);
        newFragment.show(getActivity().getSupportFragmentManager(), "tag");
    }

    @Override
    public void clicked(String s) {
        Toast.makeText(getContext(), s , Toast.LENGTH_SHORT).show();

//        TimePickerFragment newFragment = new TimePickerFragment();
//        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
}



