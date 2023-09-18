package com.lupi.obatidewe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {

    TextView txtnmtanaman, txtmanfaat, txtdeskripsi, txtId;
    ImageView txtgambar;
    String id, nmtanaman, manfaat, deskripsi,gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        txtId = findViewById(R.id.txtId);
        txtnmtanaman = findViewById(R.id.txtNmTanaman);
        txtmanfaat = findViewById(R.id.txtManfaat);
        txtdeskripsi = findViewById(R.id.txtDeskripsi);
        txtgambar = findViewById(R.id.txtGambar);

        Bundle bundle = getIntent().getExtras();
        id  = bundle.getString("id");
        nmtanaman  = bundle.getString("nmtanaman");
        manfaat  = bundle.getString("manfaat");
        deskripsi  = bundle.getString("deskripsi");
        gambar  = bundle.getString("gambar");

        txtnmtanaman.setText(nmtanaman);
        txtmanfaat.setText(manfaat);
        txtdeskripsi.setText(deskripsi);

        Glide.with(getApplicationContext()).load(gambar).placeholder(R.drawable.ic_image).into(txtgambar);


    }
}