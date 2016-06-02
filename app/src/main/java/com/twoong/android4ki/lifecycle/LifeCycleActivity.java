package com.twoong.android4ki.lifecycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.twoong.android4ki.R;

public class LifeCycleActivity extends AppCompatActivity {

    private static final String TAG = LifeCycleActivity.class.getSimpleName();

    private EditText mEditText;
    private EditText mEditText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        mEditText = (EditText) findViewById(R.id.editText);
        mEditText2 = (EditText) findViewById(R.id.editText2);

        Log.d(TAG, "onCreate: ");

        if(savedInstanceState == null){
            Log.d(TAG, "onCreate: savedInstanceState is null");
        }else{
            Log.d(TAG, "onCreate: savedInstanceState is not null");
            // 이곳에서 복원 가능
//            mEditText.setText(savedInstanceState.getString("eidt1"));
//            mEditText2.setText(savedInstanceState.getString("eidt2"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "onSaveInstanceState: ");

        //저장
        outState.putString("edit1", mEditText.getText().toString());
        outState.putString("edit2", mEditText2.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d(TAG, "onRestoreInstanceState: ");

        //복원
        mEditText.setText(savedInstanceState.getString("eidt1"));
        mEditText2.setText(savedInstanceState.getString("eidt2"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
