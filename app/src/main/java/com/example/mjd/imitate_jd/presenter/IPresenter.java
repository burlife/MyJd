package com.example.mjd.imitate_jd.presenter;



public interface IPresenter<T> {
    public void attch(T view);
    public void dettch();
}
