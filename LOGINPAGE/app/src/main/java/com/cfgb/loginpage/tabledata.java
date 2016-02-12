package com.cfgb.loginpage;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class tabledata extends SQLiteOpenHelper {
    private static final String REGISTER_URL = "http://gauthambala.esy.es/registration.php";
    public Context mContext;

    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 1;

    SQLiteDatabase db;
    // Labels table name
    public static final String TABLE_USER_MANAGER = "UserTable";

    // Labels Table Columns names
    public static String KEY_id = "id";
    public static String KEY_email = "email";
    public static String KEY_password = "password";


    // Database Name
    private static final String DATABASE_NAME = "crud.db";

    public tabledata(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("database Operations", "In TABLEDATA");
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        String CREATE_USER_DETAIL_TABLE = "CREATE TABLE " + TABLE_USER_MANAGER + "(" + KEY_id + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_email + " VARCHAR," + KEY_password + " VARCHAR" + ")";
        Log.d("database Operations", "In ONCREATE");
        db.execSQL(CREATE_USER_DETAIL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        Log.d("database Operations", "In ONUPGRADE");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_MANAGER);

        // Create tables again
        onCreate(db);

    }

    public void insertuser() {

        db = tabledata.this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_email, "gautham");
        values.put(KEY_password, "gautham");
        long id = db.insertOrThrow(TABLE_USER_MANAGER, null, values);
        System.out.println(values);

    }

    public void insertuser(String ID, String password, String confirmpass) {
        if (password.equals(confirmpass)) {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_email, ID);
            values.put(KEY_password, password);

            long id = db.insertOrThrow(TABLE_USER_MANAGER, null, values);
            System.out.println(values);
        } else {
            Toast.makeText(mContext, "passwords don't match", Toast.LENGTH_SHORT).show();
        }

    }

    public void validate(String ID, String password) {

        int success = 0;
        Cursor cursor = db.query(TABLE_USER_MANAGER, new String[]{KEY_id,
                        KEY_email, KEY_password}, KEY_email + "=?",
                new String[]{String.valueOf(ID)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst() && success == 0) {
            String email = cursor.getString(cursor.getColumnIndex(KEY_email));
            String pass = cursor.getString(cursor.getColumnIndex(KEY_password));
            if (email.equals(ID) && pass.equals(password)) {
                success = 1;
                Toast.makeText(mContext, "id and password match", Toast.LENGTH_SHORT).show();

            }
        } else if (success != 1) {
            Toast.makeText(mContext, "id and password invalid", Toast.LENGTH_SHORT).show();

        }
    }

    public void register(String username, String password) {
        String urlSuffix = "&username=" + username + "&password=" + password;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                loading = ProgressDialog.show(mContext.getApplicationContext(), "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(mContext, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTER_URL + s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                } catch (Exception e) {
                    return null;
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);

    }

}