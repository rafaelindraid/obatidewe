package com.lupi.obatidewe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class DaftarActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> id,nmtanaman,manfaat,deskripsi,gambar;
    DBHelper DB;
    MyAdapter adapter;
    Button fabRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        fabRefresh = findViewById(R.id.fabRefresh);
        fabRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(0,0);
                startActivity(getIntent());
                overridePendingTransition(0,0);
            }
        });

        DB = new DBHelper(this);
        id = new ArrayList<>();
        nmtanaman = new ArrayList<>();
        manfaat = new ArrayList<>();
        deskripsi = new ArrayList<>();
        gambar = new ArrayList<>();


        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyAdapter(this, id, nmtanaman, manfaat, deskripsi, gambar);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();
        adapter.notifyDataSetChanged();

    }

    private void displayData() {
        Cursor cursor = DB.getData();
        if (cursor.getCount() ==  0){
            Toast.makeText(DaftarActivity.this,"TIDAK ADA DATA",Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                nmtanaman.add(cursor.getString(1));
                manfaat.add(cursor.getString(2));
                deskripsi.add(cursor.getString(3));
                gambar.add(cursor.getString(4));

            }
        }
    }

    public void Kembali(View view) {
        Intent intent = new Intent(DaftarActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}