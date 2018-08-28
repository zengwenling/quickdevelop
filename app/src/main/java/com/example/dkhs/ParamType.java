package com.example.dkhs;

import android.util.Log;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ParamType<T> {
    private Class clazz;
    public ParamType(){
       Type type= getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
           Type type2= ((ParameterizedType) type).getActualTypeArguments()[0];
            if(type2 instanceof  Class){
                clazz=(Class)type2;
            }else{
                Log.e("ljj",type2.toString());
                Log.e("ljj",type2.getClass().toString());
                Log.e("ljj",((ParameterizedType) type2).getRawType().toString());
                Log.e("ljj",((ParameterizedType) type2).getActualTypeArguments()[0].toString());
            }
        }
    }

    public void logParamType(){
        Log.e("ljj",clazz.toString());
    }

}
