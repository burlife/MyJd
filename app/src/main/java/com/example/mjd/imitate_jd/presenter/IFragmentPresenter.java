package com.example.mjd.imitate_jd.presenter;

public interface IFragmentPresenter<T> {
    public void attch(T view);
    public void dettch();
}
