package com.example.mjd.imitate_jd.utils;


public interface OnNetListenter<T> {
public void onSueecss(T t);
public void onFailure(Exception e);
}
