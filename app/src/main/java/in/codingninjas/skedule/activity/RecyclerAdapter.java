package in.codingninjas.skedule.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;

import in.codingninjas.skedule.R;

/**
 * Created by DEEPU on 9/3/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.OurHolder> {


    public interface Clicker{
        void toggleButtonSubjectStartEbdState(boolean state);
    }

    Clicker mListner;

    public void setListner(Clicker listner) {
        mListner = listner;
    }
    int count = 20;

    public class OurHolder extends RecyclerView.ViewHolder {

        ImageView img;
        public OurHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.chartView);
        }
    }


    @Override
    public OurHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        SwitchCompat toggleButton = (SwitchCompat) v.findViewById(R.id.toggleSwitch);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mListner.toggleButtonSubjectStartEbdState(b);
                Log.i("check" , "CheckChanged" + b);
            }
        });

        return new OurHolder(v);
    }

    @Override
    public void onBindViewHolder(OurHolder holder, final int position) {

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyItemRemoved(position);
                count--;
            }
        });
    }

    @Override
    public int getItemCount() {
        return count;
    }

}