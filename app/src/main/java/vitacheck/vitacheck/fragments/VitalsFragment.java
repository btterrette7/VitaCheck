package vitacheck.vitacheck.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vitacheck.vitacheck.MainActivity;
import vitacheck.vitacheck.R;

public class VitalsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setTitle("Vitals");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vitals, container, false);
    }
}