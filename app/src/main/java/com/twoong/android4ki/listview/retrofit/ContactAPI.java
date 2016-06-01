package com.twoong.android4ki.listview.retrofit;

import com.twoong.android4ki.listview.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by twoong on 2016. 6. 1..
 */
public interface ContactAPI {

    String BASE_URL = "http://suwonsmartapp.iptime.org/test/twoong/retrofit/";
    @GET("getProfile.php")
    Call<List<Contact>> getContacts();
}
