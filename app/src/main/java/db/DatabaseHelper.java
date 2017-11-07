package db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by gshestakov on 11/7/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH;
    private static final String DATABASE_NAME = "here.db";
    private Context myContext;
    private static  int dbVersion = 14;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, dbVersion);
        this.myContext=context;
        DB_PATH =context.getFilesDir().getPath() + DATABASE_NAME;
        File file = new File(DB_PATH);
        if (!file.exists())
            create_db();
        else
            this.getReadableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            File file = new File(DB_PATH);
            if (file.exists())
                file.delete();
            create_db();
        }
    }

    void create_db(){
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
//                try {
//                    this.getReadableDatabase();
//                }
//                catch (Exception e) {
//                    Toast.makeText(myContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }

                myInput = myContext.getAssets().open(DATABASE_NAME);

                String outFileName = DB_PATH;


                myOutput = new FileOutputStream(outFileName);


                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        }
        catch(IOException ex){
            Log.d("DatabaseHelper", ex.getMessage());
        }
    }
    public SQLiteDatabase open()throws SQLException {

        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
}
