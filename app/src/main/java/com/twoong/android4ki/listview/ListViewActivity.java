package com.twoong.android4ki.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.twoong.android4ki.R;
import com.twoong.android4ki.listview.retrofit.ContactAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListViewActivity extends AppCompatActivity {

    public static final String TAG = ListViewActivity.class.getSimpleName();

    private List<Contact> mData;
    private GridView mGridstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        //final ListView listView = (ListView) findViewById(R.id.list_view);
        mGridstView = (GridView) findViewById(R.id.list_view);

        final ContactAdapter adapter = new ContactAdapter(mData);
        //listView.setAdapter(adapter);


//        List<Contact> data = new ArrayList<>();
//        for(int i=0; i< 100 ; i++){
//            data.add(new Contact("", "아무개" + i));
//        }

        // retrofit 준비
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ContactAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ContactAPI api = retrofit.create(ContactAPI.class);

        Call<List<Contact>> contactCall = api.getContacts();
        contactCall.enqueue(new Callback<List<Contact>>() {

            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> data = response.body();

                Log.d(TAG, "onResponse: data" + data);
                mData = data;
                mGridstView.setAdapter(new ContactAdapter(data));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Toast.makeText(ListViewActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Log.d(TAG, "onCreate: ");

/*
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            //세로
            listView.setNumColumns(1);
        } else {
            //가로
            listView.setNumColumns(2);
        }
*/
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "onSaveInstanceState: ");

        //저장
        outState.putInt("position", mGridstView.getFirstVisiblePosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d(TAG, "onRestoreInstanceState: ");

        //복원
        mGridstView.setSelection(savedInstanceState.getInt("position"));
    }
}
