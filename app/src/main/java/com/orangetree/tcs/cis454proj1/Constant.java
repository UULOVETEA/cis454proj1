package com.orangetree.tcs.cis454proj1;

/**
 * Created by qiwuzou on 2/25/18.
 */

public class Constant {
    public static String name;
    private static Constant instance;
    public static Constant getInstance(){
        if (instance == null){
            instance = new Constant();
        }

        return instance;
    }
}
