package in.codingninjas.skedule.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import in.codingninjas.skedule.R;

/**
 * Created by DEEPU on 9/3/2016.
 */
public class TranslucentActivity extends Activity implements RoutineDialog.Clicker{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translucent);

        RoutineDialog routineDialog = new RoutineDialog();
        routineDialog.setListner(this);
        routineDialog.show(getFragmentManager(), "tag");
    }

    @Override
    public void getPosition(int position) {
        Intent b = new Intent();
        b.putExtra("no_of_subjects", position);
        setResult(Constants.RESULT_CODE_FOR_ROUTINE_DIALOG, b);
    }

    @Override
    public void dialogDismiss() {
        this.finish();
    }
}