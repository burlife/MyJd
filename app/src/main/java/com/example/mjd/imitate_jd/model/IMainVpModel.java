package com.example.mjd.imitate_jd.model;

import android.support.v4.app.Fragment;

import java.util.List;

public interface IMainVpModel {
    public void setFragment(BackVP backVP);
    public interface BackVP{
        public void getRgVp(List<Fragment> list);
    }
}
