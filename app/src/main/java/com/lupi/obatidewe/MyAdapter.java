package com.lupi.obatidewe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList id, nmtanaman, manfaat, deskripsi, gambar;
    SQLiteDatabase sqLiteDatabase;

    public MyAdapter(Context context, ArrayList id, ArrayList nmtanaman, ArrayList manfaat, ArrayList deskripsi, ArrayList gambar) {
        this.context = context;
        this.id = id;
        this.nmtanaman = nmtanaman;
        this.manfaat = manfaat;
        this.deskripsi = deskripsi;
        this.gambar = gambar;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txtId.setText(String.valueOf(id.get(holder.getAdapterPosition())));
        holder.txtNmTanaman.setText(String.valueOf(nmtanaman.get(holder.getPosition())));
        holder.txtManfaat.setText(String.valueOf(manfaat.get(holder.getPosition())));
        holder.txtDeskripsi.setText(String.valueOf(deskripsi.get(holder.getPosition())));
        holder.txtGambar.setText(String.valueOf(gambar.get(holder.getPosition())));


        holder.btnDelete.setOnClickListener((view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Konfirmasi !");
            builder.setCancelable(false);
            builder.setMessage("Apakah Tanaman " + nmtanaman.get(holder.getPosition()).toString() + " jadi dihapus ?");

            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    DBHelper dbHelper = new DBHelper(context);
                    sqLiteDatabase = dbHelper.getReadableDatabase();
                    long delele = sqLiteDatabase.delete("ObatiTb", "id="+id.get(position).toString(),null);
                    if (delele != -1){
                        Toast.makeText(context, "Data berhasil dihapus",Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(context, "Data gagal dihapus",Toast.LENGTH_SHORT).show();
                    }
                    notifyItemRemoved(position);
                    notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("Tidak", null);
            builder.show();
        }));

        holder.btnEdit.setOnClickListener((view -> {
            String ids = holder.txtId.getText().toString();
            String nmtanamans = holder.txtNmTanaman.getText().toString();
            String manfaats = holder.txtManfaat.getText().toString();
            String gambars = holder.txtGambar.getText().toString();
            String deskripsis = holder.txtDeskripsi.getText().toString();

            Intent intent = new Intent(context, EditActivity.class);
            intent.putExtra("id", ids);
            intent.putExtra("nmtanaman", nmtanamans);
            intent.putExtra("manfaat", manfaats);
            intent.putExtra("gambar", gambars);
            intent.putExtra("deskripsi", deskripsis);
            context.startActivity(intent);
        }));

        holder.txtHistori.setOnClickListener((view -> {
            String ids = holder.txtId.getText().toString();
            String nmtanamans = holder.txtNmTanaman.getText().toString();
            String manfaats = holder.txtManfaat.getText().toString();
            String gambars = holder.txtGambar.getText().toString();
            String deskripsis = holder.txtDeskripsi.getText().toString();

            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("id", ids);
            intent.putExtra("nmtanaman", nmtanamans);
            intent.putExtra("manfaat", manfaats);
            intent.putExtra("gambar", gambars);
            intent.putExtra("deskripsi", deskripsis);
            context.startActivity(intent);
        }));

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtNmTanaman, txtId, txtManfaat, txtDeskripsi,txtGambar;
        CardView txtHistori;

        LinearLayout layoutHeader;
        Button btnDelete, btnEdit;

        public MyViewHolder(View itemView) {
            super(itemView);
            layoutHeader = itemView.findViewById(R.id.layoutHeader);
            txtId = itemView.findViewById(R.id.txtId);
            txtNmTanaman = itemView.findViewById(R.id.txtNmTanaman);
            txtManfaat = itemView.findViewById(R.id.txtManfaat);
            txtGambar = itemView.findViewById(R.id.txtGambar);
            txtDeskripsi = itemView.findViewById(R.id.txtDeskripsi);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            txtHistori = itemView.findViewById(R.id.txtHistori);
        }
    }
}