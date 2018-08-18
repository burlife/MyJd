package com.example.mjd.imitate_jd.mvp.my.login.view;

import com.example.mjd.imitate_jd.mvp.my.login.bean.Loginbean;

public interface ILoginView {
    public String getname();
    public String getpass();
    public void getData(Loginbean bean);
}
