package com.twoong.android4ki.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twoong.android4ki.R;

/**
 * Created by twoong on 2016. 6. 20..
 */
public class ColorFragment2 extends Fragment implements View.OnClickListener {

    private OnClickMyListner mListner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color2, container, false);
        view.findViewById(R.id.red_button).setOnClickListener(this);
        view.findViewById(R.id.green_button).setOnClickListener(this);
        view.findViewById(R.id.blue_button).setOnClickListener(this);
        return view;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnClickMyListner){
//            mListner = (OnClickMyListner) context;
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListner = null;
//    }

    public void setOnMyClickListner(OnClickMyListner listner) {
        mListner = listner;
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.red_button:
//                getView().setBackgroundColor(Color.RED);
//                break;
//            case R.id.green_button:
//                getView().setBackgroundColor(Color.GREEN);
//                break;
//            case R.id.blue_button:
//                getView().setBackgroundColor(Color.BLUE);
//                break;
//        }
        if(mListner != null){
            mListner.onClick(v, getView());
        }
    }

    public interface OnClickMyListner {
        void onClick(View clickedView, View colorChangeView);
    }
}
