package vitacheck.vitacheck.fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import vitacheck.vitacheck.R;

/**
 * Created by btter_000 on 11/28/2015.
 */
public class DietHealthArticleAdapter extends RecyclerView.Adapter<DietHealthArticleAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    /*i used emptyList() so that there is no null expect error if data does not load*/
    private List<DietHealthArticleInfo> dietHealthList;//= Collections.emptyList();

    public DietHealthArticleAdapter(Context context, List<DietHealthArticleInfo> data){
        /*inflates the recyclerview_doctor_row xml file*/
        inflater=LayoutInflater.from(context);
        this.dietHealthList=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    /*called when recyclerView needs a new RecyclerView.ViewHolder of a given type to represent an item*/
        /*view represents the root of the recyclerview_doctor_row xml file*/
        View view=inflater.inflate(R.layout.recyclerview_diet_health_row,parent, false);

        /*passes the view (root) to the view holder class. view holder class is at the bottom*/
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        /*gets data, notice current is a doctor info object class*/
        DietHealthArticleInfo current = dietHealthList.get(position);

        /*sets data that will be displayed in the recyclerview_doctor_row xml file*/
        holder.dietHealthName.setText(current.getHealthName());
        holder.dietHealthURL.setText(current.getHealthURL());
        //holder.dietHealthDescription.setText(current.getHealthDescription());
    }

    @Override
    public int getItemCount() {   return dietHealthList.size();}

    public void deleteHealth(int position){
        /*look up notifyDataSetChanged() method (use as last resort) instead use
        * more specific changes, read more on it you'll find out*/
        dietHealthList.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        /*stuff that i want to be displayed in the recyclerview*/
        TextView dietHealthName;
        TextView dietHealthURL;
        //TextView dietHealthDescription;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            dietHealthName= (TextView) itemView.findViewById(R.id.healthNameView);
            dietHealthURL= (TextView) itemView.findViewById(R.id.healthURLView);
            //dietHealthDescription= (TextView) itemView.findViewById(R.id.HealthDescriptionView);
            dietHealthName.setOnClickListener(this);
            dietHealthURL.setOnClickListener(this);
            //dietHealthDescription.setOnClickListener(this);
        }

        @Override
        /*video on how to handle recycler clicks found here: https://www.youtube.com/watch?v=zE1E1HOK_E4   */
        public void onClick(View v) {
            deleteHealth(getPosition());
        }
    }

}//end of RecipeAdapter class