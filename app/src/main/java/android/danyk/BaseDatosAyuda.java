package android.danyk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class BaseDatosAyuda extends SQLiteOpenHelper {

    public BaseDatosAyuda(@Nullable Context context, @Nullable String dbname, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table personas(Nombre varchar(20), Apellido varchar(20), Usuario varchar(20), Contra varchar(20), Email varchar(20), Ciudad varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists personas");
        onCreate(db);
    }

    public long personaGuardada(String nombre, String apellido, String usuario, String contra, String email, String ciudad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Nombre", nombre);
        cv.put("Apellido", apellido);
        cv.put("Usuario", usuario);
        cv.put("Contra", contra);
        cv.put("Email", email);
        cv.put("Ciudad", ciudad);

        try {
            return db.insert("personas", null, cv);
        } catch (SQLiteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Cursor getUsuarioPorDatos(String usuario, String contraseña) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                "Usuario",
                "Contra"
        };

        String selection = "Usuario = ? AND Contra = ?";
        String[] selectionArgs = {usuario, contraseña};
        return db.query(
                "personas",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
    }
}
