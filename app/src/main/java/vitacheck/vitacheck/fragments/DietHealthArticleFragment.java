package vitacheck.vitacheck.fragments;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import vitacheck.vitacheck.MainActivity;
import vitacheck.vitacheck.R;

public class DietHealthArticleFragment extends Fragment {

    /*video on recylerView can be found here: https://www.youtube.com/watch?v=Wq2o4EbM74k   */
    private RecyclerView recyclerView;
    private DietHealthArticleAdapter adapter;
    private FloatingActionButton addHealthButton;
    private List<DietHealthArticleInfo> dietHealthParseList = new ArrayList<DietHealthArticleInfo>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dietHealthParseList = new ArrayList<DietHealthArticleInfo>();

        ((MainActivity) getActivity()).setTitle("Health Article");
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_diet_health, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.healthList);

        /*sets the layout on how we want the data to be displayed
        * if we wanted to we could set it to grid or staggered layout but in this case
        * we wanted it to be list going from top to bottom so we use LinearLayout*/
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ParseObject.registerSubclass(DietHealthArticleInfo.class);
        ParseQuery<DietHealthArticleInfo> query = new ParseQuery<DietHealthArticleInfo>("diet_health_article");
        query.findInBackground(new FindCallback<DietHealthArticleInfo>() {
            @Override
            public void done(List<DietHealthArticleInfo> objects, com.parse.ParseException e) {
                if (e != null) {
                    Toast.makeText(getView().getContext(), "Error " + e, Toast.LENGTH_SHORT).show();
                }

                for (DietHealthArticleInfo health: objects)
                    if(health.getUserId().equals(GlobalVariable.getUserId(getActivity())))
                {
                    DietHealthArticleInfo newhealth = new DietHealthArticleInfo();
                    newhealth.setParseID(health.getObjectId());
                    newhealth.setHealthName(health.getHealthName());
                    newhealth.setHealthURL(health.getHealthURL());
                    newhealth.setHealthDescription(health.getHealthDescription());
                    dietHealthParseList.add(newhealth);
                }
                /*have to make adapter and set here because if set outside done method and after
                data will not appear because it sets the data before it is pulled from parse
                https://www.youtube.com/watch?v=ZlUjJSE7YW4&list=PLBA5zvAwnCrCdIilqLW7tTA2Uuao1u8iH&index=5
                start at 10 min to see explanation.     -eric*/
                adapter = new DietHealthArticleAdapter(getActivity(), dietHealthParseList);
                recyclerView.setAdapter(adapter); //sets adapter to recyclerview
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }


        });




        addHealthButton = (FloatingActionButton) layout.findViewById(R.id.addHealthButton);
        addHealthButton.setOnClickListener(new View.OnClickListener() {
            Fragment fragment = null;

            @Override
            public void onClick(View v) {
                fragment = new DietAddHealthArticleFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return layout;
    }

}