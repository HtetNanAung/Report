package com.example.report;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.report.adapter.JSONParentAdapter;
import com.example.report.pojo.ChildTransactionDetailList;
import com.example.report.pojo.ParentTransactionDetailList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Report extends AppCompatActivity {

    private RecyclerView mRecyclerView;


    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    List<ParentTransactionDetailList> plist = new ArrayList<>();
    private static final String TAG = "MainActivity";
    private String date1 = "";
    private JSONArray result;
    private JSONObject result1;
    private JSONArray jsonArray;
    List<ParentTransactionDetailList> itemlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        mRecyclerView = findViewById(R.id.my_recycler_view2);
        // mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        addItemsFromJSON();

        JSONParentAdapter parentItemAdapter = null;
        try {
            parentItemAdapter = new JSONParentAdapter(Parent(), getApplicationContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRecyclerView.setAdapter(parentItemAdapter);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void addItemsFromJSON() {
        try {
            String jsonDataString = readJSONDataFromFile();
            jsonArray = new JSONArray(jsonDataString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject itemObj = jsonArray.getJSONObject(i);
                result = itemObj.getJSONArray("data");
                //Log.e(TAG,"I am result length"+jsonArray.length());
                date1 = itemObj.getString("date");
                Parent();
            }

        } catch (IOException | JSONException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }

    private List<ParentTransactionDetailList> Parent() throws JSONException {
        ParentTransactionDetailList item = new ParentTransactionDetailList(date1, Child());
        itemlist.add(item);
        Log.e(TAG, "I am Parent Size" + itemlist.size());
        return itemlist;
    }

    private List<ChildTransactionDetailList> Child() throws JSONException {
        List<ChildTransactionDetailList> Child = new ArrayList<>();
        JSONArray arr = result;
        for (int i = 0; i < result.length(); i++) {
            result1 = result.getJSONObject(i);
            String str = result1.getString("TransactionID");
            String str2 = result1.getString("Name");
            Child.add(new ChildTransactionDetailList(str, str2));
        }
        return Child;
    }

    private String readJSONDataFromFile() throws IOException {

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {
            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.selldate);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));

            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);

            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }

        }
        return new String(builder);
    }
}