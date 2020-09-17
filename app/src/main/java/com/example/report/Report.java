package com.example.report;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
    int amount = 0;
    int tran_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Toolbar toolbar=findViewById(R.id.custom_toolbar);
        TextView status=findViewById(R.id.titlebar);
        toolbar.setTitle("");
        status.setText("Transaction Report");
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.my_recycler_view2);
        // mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        addItemsFromJSON();
        
        TextView revenue = findViewById(R.id.Total_revenue);
        revenue.setText("Total Revenue : " + amount+" MMK");
        TextView amount = findViewById(R.id.count);
        amount.setText("Transaction Count : " + tran_count);

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
            String str2 = result1.getString("Amount");
            String str3 = result1.getString("Phone");
            String str4 = result1.getString("Time");
            amount += Integer.parseInt(str2);
            Log.e(TAG, "" + amount);
            tran_count++;
            Child.add(new ChildTransactionDetailList(str, str2, str3, str4));
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