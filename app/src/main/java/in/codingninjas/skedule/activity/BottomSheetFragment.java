package in.codingninjas.skedule.activity;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import in.codingninjas.skedule.R;

/**
 * Created by DEEPU on 9/3/2016.
 */
public class BottomSheetFragment extends BottomSheetDialogFragment {



    public interface BottomSheetInterface {
        void bottomSheetClicked(int no);
    }

    BottomSheetInterface mListner;
    public void setListner(BottomSheetInterface listner) {
        mListner = listner;
    }


    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {


        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                Log.i("BottomSheet", "1" + newState);
                Toast.makeText(getActivity(), "state : HIDDEN", Toast.LENGTH_SHORT).show();
//                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }

    };

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);


        View contentView = View.inflate(getContext(), R.layout.fragment_bottom_sheet, null);
        TextView newSubject = (TextView) contentView.findViewById(R.id.newSubjectTextView);
        TextView newRoutine = (TextView) contentView.findViewById(R.id.newRoutineTextView);

        newSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("newSub" , "newSub Clicked");
                mListner.bottomSheetClicked(Constants.NEW_SUBJECT_CLICKED);
                dismiss();

            }
        });

        newRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("newSub" , "newRoutine Clicked");
                mListner.bottomSheetClicked(Constants.NEW_ROUTINE_CLICKED);
                dismiss();
            }
        });

        dialog.setContentView(contentView);


        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();


        if( behavior != null && behavior instanceof BottomSheetBehavior ) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }

}
