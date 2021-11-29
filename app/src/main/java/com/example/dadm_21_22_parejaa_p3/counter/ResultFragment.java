package com.example.dadm_21_22_parejaa_p3.counter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.dadm_21_22_parejaa_p3.BaseFragment;
import com.example.dadm_21_22_parejaa_p3.R;
import com.example.dadm_21_22_parejaa_p3.ScaffoldActivity;

public class ResultFragment extends BaseFragment implements View.OnClickListener{
    public ResultFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_result, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);//result_title
        view.findViewById(R.id.btn_resultExit).setOnClickListener(this);
        TextView titulo = (TextView) view.findViewById(R.id.result_title);
        TextView info = (TextView) view.findViewById(R.id.result_info);
        if(((ScaffoldActivity)getActivity()).getResult()){
            titulo.setText("VICTORY");
            info.setText("A total success! Great job on this mission, captain!");
        }else{
            titulo.setText("LOSE");
            info.setText("Mission failed... We will get them next time, captain!");
        }

    }

    @Override
    public void onClick(View v) {
        ((ScaffoldActivity)getActivity()).exitGame();
    }
}