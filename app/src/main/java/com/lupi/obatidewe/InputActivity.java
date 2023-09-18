package com.lupi.obatidewe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {

    EditText inputNmTanaman, inputManfaat, inputGambar, inputDeskripsi;
    Button fabSave;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        fabSave = findViewById(R.id.fabSave);
        DB = new DBHelper(this);

        inputNmTanaman = findViewById(R.id.inputNmTanaman);
        inputManfaat = findViewById(R.id.inputManfaat);
        inputGambar = findViewById(R.id.inputGambar);
        inputDeskripsi = findViewById(R.id.inputDeskripsi);

        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });

    }
    public void insert(){
        String nmtanaman = inputNmTanaman.getText().toString();
        String manfaat = inputManfaat.getText().toString();
        String gambar = inputGambar.getText().toString();
        String deskripsi = inputDeskripsi.getText().toString();

        Boolean checkData = DB.insertData(nmtanaman, manfaat, gambar, deskripsi );

        if (checkData == true){
            Toast.makeText(this, "Berhasil Disimpan",Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Gagal Disimpan",Toast.LENGTH_LONG).show();
        }
    }
}