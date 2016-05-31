package com.twoong.android4ki;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.twoong.android4ki.listview.ListViewActivity;

/**
 * Created by twoong on 2016. 5. 30..
 */
public class MainActivity extends AppCompatActivity{

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
    public void email(View view){

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"inspo@naver.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "제목 없습니다");
        intent.putExtra(Intent.EXTRA_TEXT, Build.MODEL);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void listView(View view) {
        startActivity(new Intent(this, ListViewActivity.class));
    }
}
