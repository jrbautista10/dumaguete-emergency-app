package com.example.dumagueteemergency;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    HashMap<String, List<String>> Emergency_category;
    List<String> Emergency_list;
    ExpandableListView Exp_list;
    EmergencyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Exp_list = (ExpandableListView) findViewById(R.id.exp_list);
        Emergency_category = DataProvider.getInfo();
        Emergency_list = new ArrayList<String>(Emergency_category.keySet());
        adapter = new EmergencyAdapter(this, Emergency_category, Emergency_list);
        Exp_list.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();
        String sourceString = "<b>DGTE</b> Emergency App";

        actionBar.setTitle(Html.fromHtml(sourceString));

        Exp_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String parentText = Emergency_list.get(groupPosition);

                String childText = Emergency_category.get(Emergency_list.get(groupPosition)).get(childPosition);

                if (childText.contains("Facebook")) {
                    String[] a = childText.split(" ");

                    String fbID = "";
                    if (parentText.equals("ONE Rescue EMS")) {
                        fbID = "320439538083559";
                    } else if (parentText.equals("NORECO")) {
                        fbID = "215954525131894";
                    }
                    openFacebook(a[0], fbID);
                } else if (childText.contains("Messenger")) {
                    String[] a = childText.split(" ");
                    openMessenger(a[0]);
                } else {
                    String[] a = childText.split("\\+");
//                    Toast.makeText(getBaseContext(), a[1], Toast.LENGTH_LONG).show();
                    call("+" + a[1]);
                }

                return false;
            }
        });


//        Exp_list.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            int previousGroup = -1;
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                if(groupPosition != previousGroup)
//                    Exp_list.collapseGroup(previousGroup);
//                previousGroup = groupPosition;
//            }
//        });

    }

    public void call(String number) {
        final int REQUEST_PHONE_CALL = 1;
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + number));
        //-----------------checks for permission before placing the call--------------------
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
            } else {
                //-------------------------------places the call-----------------------------------
                startActivity(callIntent);
            }
        }
    }

    public void openFacebook(String url, String id) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + id));
            startActivity(intent);
        } catch (Exception e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + url));
            startActivity(intent);
        }
    }

    public void openMessenger(String messengerID) {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setPackage("com.facebook.orca");
            intent.setData(Uri.parse("https://m.me/" + messengerID));
            startActivity(intent);
        } catch (Exception e) {
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + url));
//            startActivity(intent);
        }
    }


}
