package com.example.anish.crud.Main;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anish.crud.DAOapp;
import com.example.anish.crud.Models.DaoSession;
import com.example.anish.crud.Models.Hinter;
import com.example.anish.crud.Models.HinterAdapter;
import com.example.anish.crud.Models.HinterDao;
import com.example.anish.crud.R;
import com.example.anish.crud.Services.DAOservices;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tabHost) TabHost tabHost;
    @BindView(R.id.input_website) EditText input_website;
    @BindView(R.id.input_username) EditText input_username;
    @BindView(R.id.button_add) Button button_insert;
    @BindView(R.id.input_hint) EditText input_hint;
    @BindView(R.id.input_email) EditText input_email;
    @BindView(R.id.searchBar) EditText searchBar;
    @BindView(R.id.textViewRecordCount) TextView textViewRecordCount;
    @BindView(R.id.output_records_recyclerview) RecyclerView recyclerView;

    private HinterDao hinterDao;
    private DAOservices daoservices;
    private TabHost.TabSpec spec;
    private LinearLayoutManager mLayoutManager;
    private HinterAdapter hinterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        daoservices = new DAOservices();
        if (daoservices == null){
            Log.d("daoservices", "onCreate: null");
        }
        ButterKnife.bind(this);
        tabHost.setup();
        loadTabOne();
        loadTabTwo();
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

    }

    public void loadTabOne() {
        spec = tabHost.newTabSpec("Insert");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Insert");
        tabHost.addTab(spec);
    }

    public void loadTabTwo(){
        spec=tabHost.newTabSpec("View");
        spec.setContent(R.id.tab2);
        spec.setIndicator("View");
        tabHost.addTab(spec);
        countRecords();
        readRecords();
    }

    @OnClick(R.id.button_add)
    public void add(View view){
        for(Hinter item : readRecords()){
            if(item.getWebsite() == input_website.getText().toString()){
                Toast.makeText(MainActivity.this, "Website already in DB", Toast.LENGTH_LONG).show();
                return;
            }
        }
        Hinter hinter = new Hinter();
        hinter.setWebsite(input_website.getText().toString());
        hinter.setUsername(input_username.getText().toString());
        hinter.setEmail(input_email.getText().toString());
        hinter.setHint(input_hint.getText().toString());
        boolean checkIfInserted=daoservices.insertInDB(hinter);
        if(checkIfInserted) {
            Toast.makeText(MainActivity.this, "Inserted in DB", Toast.LENGTH_LONG).show();
            countRecords();
        }
    }

    @OnTextChanged(value = R.id.searchBar,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void afterTextChanged(Editable editable){
        List<Hinter> hintsList=daoservices.searchByWebsiteInDB(searchBar.getText().toString());
        Toast.makeText(MainActivity.this, "Found "+hintsList.size()+" items", Toast.LENGTH_LONG).show();
        // specify an adapter (see also next example)
        hinterAdapter = new HinterAdapter(hintsList);
        recyclerView.setAdapter(hinterAdapter);
    }

    public void countRecords() {
        int noOfRecords = daoservices.getCountFromDB();
        textViewRecordCount.setText(noOfRecords + " records found.");
    }


    public List<Hinter> readRecords() {
        List<Hinter> hintersList = daoservices.readAllFromDB();
        return  hintersList;
    }
}
