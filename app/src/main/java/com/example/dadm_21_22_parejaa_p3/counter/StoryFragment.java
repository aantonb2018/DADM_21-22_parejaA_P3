package com.example.dadm_21_22_parejaa_p3.counter;

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

public class StoryFragment extends BaseFragment implements View.OnClickListener {

    public StoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_story, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_goback).setOnClickListener(this);

        /*
        Animation pulseAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.button_pulse);
        view.findViewById(R.id.text_insert).startAnimation(pulseAnimation);*/

        Animation titleAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.subtitle_enter);
        view.findViewById(R.id.story_title).startAnimation(titleAnimation);

        Animation subtitleAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.subtitle_enter);
        view.findViewById(R.id.story_info).startAnimation(subtitleAnimation);
    }

    @Override
    public void onClick(View v) {
        ((ScaffoldActivity)getActivity()).navigateBack();
    }
}