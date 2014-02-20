package com.example.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.R;

/**
 * Created by WX009-PC on 2/19/14.
 */
public class HomeFragment extends Fragment {

    private onItemClickedListener mListener;
    private LayoutInflater mLayoutInflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayoutInflater = inflater;
        View rootView = mLayoutInflater.inflate(R.layout.home_fragment, container, false);

        final Button listButton = (Button) rootView.findViewById(R.id.list_button);
        final Button mapButton = (Button) rootView.findViewById(R.id.map_button);

        listButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.onListButtonClicked();
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.onMapButtonClicked();
            }
        });
        return rootView;
    }

    public interface onItemClickedListener{
        public void onListButtonClicked();
        public void onMapButtonClicked();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof onItemClickedListener) {
            mListener = (onItemClickedListener) activity;
        } else {
            throw new ClassCastException(activity.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}