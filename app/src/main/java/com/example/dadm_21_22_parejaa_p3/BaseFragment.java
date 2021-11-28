package com.example.dadm_21_22_parejaa_p3;

import androidx.fragment.app.Fragment;


public class BaseFragment extends Fragment {
    public boolean onBackPressed() {
        return false;
    }

    protected ScaffoldActivity getScaffoldActivity () {
        return (ScaffoldActivity) getActivity();
    }
}
