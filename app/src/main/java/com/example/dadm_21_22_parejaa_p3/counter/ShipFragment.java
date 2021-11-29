package com.example.dadm_21_22_parejaa_p3.counter;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dadm_21_22_parejaa_p3.BaseFragment;
import com.example.dadm_21_22_parejaa_p3.R;
import com.example.dadm_21_22_parejaa_p3.ScaffoldActivity;

public class ShipFragment extends BaseFragment implements View.OnClickListener {

    private ImageButton buttons[] = new ImageButton[3];
    private TextView texts[] = new TextView[3];

    public ShipFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ship, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_gobackship).setOnClickListener(this);
        buttons[0] = (ImageButton)view.findViewById(R.id.btn_interprise);
        buttons[0].setOnClickListener(this);
        texts[0] = (TextView) view.findViewById(R.id.text_interprise);
        buttons[1] = (ImageButton)view.findViewById(R.id.btn_xfeather);
        buttons[1].setOnClickListener(this);
        texts[1] = (TextView) view.findViewById(R.id.text_xfeather);
        buttons[2] = (ImageButton)view.findViewById(R.id.btn_apellonxiii);
        buttons[2].setOnClickListener(this);
        texts[2] = (TextView) view.findViewById(R.id.text_apellonxii);

        changeOpacity(((ScaffoldActivity)getActivity()).getSelectedShip());

        Animation titleAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.subtitle_enter);
        view.findViewById(R.id.ship_layout).startAnimation(titleAnimation);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_interprise) {
            changeOpacity(0);
            ((ScaffoldActivity)getActivity()).setSelectedShip(0);
        }else if (v.getId() == R.id.btn_xfeather) {
            changeOpacity(1);
            ((ScaffoldActivity)getActivity()).setSelectedShip(1);
        }else if (v.getId() == R.id.btn_apellonxiii) {
            changeOpacity(2);
            ((ScaffoldActivity)getActivity()).setSelectedShip(2);
        }else if(v.getId() == R.id.btn_gobackship){
            ((ScaffoldActivity)getActivity()).navigateBack();
        }

    }

    public void changeOpacity(int option){
        switch(option){
            case 0:
                buttons[0].getBackground().setAlpha(255);
                texts[0].setTextColor(Color.argb(255, 255, 255, 255));
                buttons[1].getBackground().setAlpha(75);
                texts[1].setTextColor(Color.argb(75, 255, 255, 255));
                buttons[2].getBackground().setAlpha(75);
                texts[2].setTextColor(Color.argb(75, 255, 255, 255));
                break;
            case 1:
                buttons[0].getBackground().setAlpha(75);
                texts[0].setTextColor(Color.argb(75, 255, 255, 255));
                buttons[1].getBackground().setAlpha(255);
                texts[1].setTextColor(Color.argb(255, 255, 255, 255));
                buttons[2].getBackground().setAlpha(75);
                texts[2].setTextColor(Color.argb(75, 255, 255, 255));
                break;
            case 2:
                buttons[1].getBackground().setAlpha(75);
                texts[1].setTextColor(Color.argb(75, 255, 255, 255));
                buttons[0].getBackground().setAlpha(75);
                texts[0].setTextColor(Color.argb(75, 255, 255, 255));
                buttons[2].getBackground().setAlpha(255);
                texts[2].setTextColor(Color.argb(255, 255, 255, 255));
                break;
        }
    }

}