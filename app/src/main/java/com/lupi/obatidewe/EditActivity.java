package com.lupi.obatidewe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText inputNmTanaman, inputManfaat, inputGambar, inputDeskripsi;
    TextView txtId;
    Button fabSave;
    DBHelper DB;
    String id, nmtanaman, manfaat, deskripsi,gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        fabSave = findViewById(R.id.fabSave);
        DB = new DBHelper(this);

        txtId = findViewById(R.id.txtId);
        inputNmTanaman = findViewById(R.id.inputNmTanaman);
        inputManfaat = findViewById(R.id.inputManfaat);
        inputGambar = findViewById(R.id.inputGambar);
        inputDeskripsi = findViewById(R.id.inputDeskripsi);


        Bundle bundle = getIntent().getExtras();
        id  = bundle.getString("id");
        nmtanaman  = bundle.getString("nmtanaman");
        manfaat  = bundle.getString("manfaat");
        deskripsi  = bundle.getString("deskripsi");
        gambar  = bundle.getString("gambar");

        txtId.setText(id);
        inputNmTanaman.setText(nmtanaman);
        inputManfaat.setText(manfaat);
        inputGambar.setText(gambar);
        inputDeskripsi.setText(deskripsi);

        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
               updateData();
            }
        });
   }

    public void updateData(){
        String ids = txtId.getText().toString();
        String nmtanamans = inputNmTanaman.getText().toString();
        String manfaats = inputManfaat.getText().toString();
        String gambars = inputGambar.getText().toString();
        String deskripsis = inputDeskripsi.getText().toString();

        Boolean checkupdateData = DB.updateData(ids, nmtanamans, manfaats, gambars, deskripsis);

        if (checkupdateData == true){
            Toast.makeText(this, "Berhasil Diupdate",Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Gagal Diupdate",Toast.LENGTH_LONG).show();
        }


    }
}