package com.lupi.obatidewe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Obatidewe.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE ObatiTb(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nmtanaman TEXT, " +
                "manfaat TEXT, " +
                "gambar TEXT, " +
                "deskripsi TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("DROP TABLE IF EXISTS ObatiTb");
    }

    public boolean insertData(String nmtanaman, String manfaat, String gambar, String deskripsi){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nmtanaman", nmtanaman);
        cv.put("manfaat", manfaat);
        cv.put("gambar", gambar);
        cv.put("deskripsi", deskripsi);
        long result = DB.insert("ObatiTb",null, cv);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM ObatiTb", null);
        return  cursor;
    }

    public boolean updateData(String id, String nmtanaman, String manfaat, String gambar, String deskripsi){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nmtanaman", nmtanaman);
        cv.put("manfaat", manfaat);
        cv.put("gambar", gambar);
        cv.put("deskripsi", deskripsi);

        long result = DB.update("ObatiTb", cv, "id=?", new String[]{id});
        if (result == -1){
            return false;
        } else {
            return true;
        }

    }


}
