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

import com.example.dadm_21_22_parejaa_p3.BaseFragment;
import com.example.dadm_21_22_parejaa_p3.R;
import com.example.dadm_21_22_parejaa_p3.ScaffoldActivity;


public class MainMenuFragment extends BaseFragment implements View.OnClickListener {
    public MainMenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_start).setOnClickListener(this);
        view.findViewById(R.id.btn_story).setOnClickListener(this);
        view.findViewById(R.id.btn_ship).setOnClickListener(this);

        Animation pulseAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.button_pulse);
        view.findViewById(R.id.text_insert).startAnimation(pulseAnimation);

        Animation titleAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.subtitle_enter);
        view.findViewById(R.id.GameTitle).startAnimation(titleAnimation);
        view.findViewById(R.id.GameSubtitle).startAnimation(titleAnimation);
        view.findViewById(R.id.btn_start).startAnimation(titleAnimation);
        view.findViewById(R.id.btn_ship).startAnimation(titleAnimation);
        view.findViewById(R.id.btn_story).startAnimation(titleAnimation);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start) {
            ((ScaffoldActivity) getActivity()).startGame();
        }else if(v.getId() == R.id.btn_story){
            ((ScaffoldActivity) getActivity()).startStory();
        }else if(v.getId() == R.id.btn_ship){
            ((ScaffoldActivity) getActivity()).startShip();
        }
    }
}
