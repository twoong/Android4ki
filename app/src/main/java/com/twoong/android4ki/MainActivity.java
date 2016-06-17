package com.twoong.android4ki;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.twoong.android4ki.database.DbActivity;
import com.twoong.android4ki.lifecycle.LifeCycleActivity;
import com.twoong.android4ki.listview.ListViewActivity;
import com.twoong.android4ki.service.MyIntentService;
import com.twoong.android4ki.service.MyService;

/**
 * Created by twoong on 2016. 5. 30..
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void call(View view) {
        Uri uri = Uri.parse("tel:010-7251-6688");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);

        startActivity(intent);
    }

    public void email(View view) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"inspo@naver.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "제목 없습니다");
        intent.putExtra(Intent.EXTRA_TEXT, Build.MODEL);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void listView(View view) {
        startActivity(new Intent(this, ListViewActivity.class));
    }

    public void lifeCycle(View view) {
        startActivity(new Intent(this, LifeCycleActivity.class));
    }

    public void DataBase(View view) {
        startActivity(new Intent(this, DbActivity.class));
    }

    public void service(View view) {
        startService(new Intent(this, MyService.class));
    }

    public void intentService(View view) {
        startService(new Intent(this, MyIntentService.class));
    }

    public void bindService(View view) {
        if(!mBound) {
            bindService(new Intent(this, MyService.class), mConnection, BIND_AUTO_CREATE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mBound){
            unbindService(mConnection);
            mBound = false;
        }
    }

    private boolean mBound;
    public MyService mService;
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder) service;
            mService = binder.getService();
            mBound = true;

            mService.showLog();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };
}
