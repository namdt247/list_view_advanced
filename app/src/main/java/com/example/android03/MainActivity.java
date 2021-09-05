package com.example.android03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IOnChildItemClick {
    private List<ContactModel> listContact = new ArrayList<>();
    private ListView lvContact;
    private ContactAdapter mAdapter;
    private ImageView ivUser;
    private TextView tvName;
    private String[] permissions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

        mAdapter = new ContactAdapter(this, listContact);
        mAdapter.registerChildItemClick(this);
        lvContact.setAdapter(mAdapter);
        permissions = new String[]{
                Manifest.permission.CALL_PHONE
        };
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, permissions,1);
        }
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ContactModel model = listContact.get(i);
                Toast.makeText(MainActivity.this, model.getName() + ": " + model.getPhone(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(){
        lvContact = (ListView) findViewById(R.id.lvContact);
        ivUser = (ImageView) findViewById(R.id.ivUser);
        tvName = (TextView) findViewById(R.id.tvName);
    }
    private void initData(){
        listContact.add(new ContactModel("Trần Trường Sơn", "0987654321", R.drawable.ic_avatar1));
        listContact.add(new ContactModel("Trần Trường A", "0987654322", R.drawable.ic_avatar2));
        listContact.add(new ContactModel("Trần Trường B", "0987654323", R.drawable.ic_avatar3));
        listContact.add(new ContactModel("Trần Trường C", "0987654324", R.drawable.ic_avatar4));
        listContact.add(new ContactModel("Trần Trường D", "0987654325", R.drawable.ic_avatar3));
        listContact.add(new ContactModel("Trần Trường E", "0987654326", R.drawable.ic_avatar1));
        listContact.add(new ContactModel("Trần Trường F", "0987654327", R.drawable.ic_avatar4));
        listContact.add(new ContactModel("Trần Trường G", "0987654328", R.drawable.ic_avatar2));
        listContact.add(new ContactModel("Trần Trường H", "0987654329", R.drawable.ic_avatar1));
        listContact.add(new ContactModel("Trần Trường I", "0987654330", R.drawable.ic_avatar4));
        listContact.add(new ContactModel("Trần Trường K", "0987654331", R.drawable.ic_avatar2));
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mAdapter.unRegisterChildItemClick();
    }

    @Override
    public void onItemChildClick(int position) {
        ContactModel contact = listContact.get(position);
        ivUser.setImageResource(contact.getImage());
        tvName.setText(contact.getName());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Calling Permission is granted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Calling Permission is denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}