package android.danyk;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDatosAyuda extends SQLiteOpenHelper {
    public BaseDatosAyuda(@Nullable Context context, @Nullable String dbname, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table personas(nombre varchar(20), apellido varchar(20), usuario varchar(20), contra varchar(20), email varchar(20), ciudad varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists personas");
        onCreate(db);
    }

    public long personaGuardada(String nombre, String apellido, String usuario, String contra, String email, String ciudad){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nombre", nombre);
        cv.put("apellido", apellido);
        cv.put("usuario", usuario);
        cv.put("contraseña", contra);
        cv.put("emial", email);
        cv.put("ciudad", ciudad);
        long recorddid = db.insert("personas", null, cv);
        return recorddid;
    }
}
