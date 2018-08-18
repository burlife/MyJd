package com.example.mjd.imitate_jd.model;

import android.support.v4.app.Fragment;


import com.example.mjd.imitate_jd.fragment.ClassifyFragment;
import com.example.mjd.imitate_jd.fragment.HomeFragment;
import com.example.mjd.imitate_jd.fragment.MyFragment;
import com.example.mjd.imitate_jd.fragment.CarFragment;
import com.example.mjd.imitate_jd.fragment.FindFragment;

import java.util.ArrayList;
import java.util.List;


public class MainVpModel implements IMainVpModel{


    @Override
    public void setFragment(BackVP backVP) {
        List<Fragment> list=new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new ClassifyFragment());
        list.add(new FindFragment());
        list.add(new CarFragment());
        list.add(new MyFragment());
        backVP.getRgVp(list);
    }
}
