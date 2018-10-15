package miage.parisnanterre.fr.runwithme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUser extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 10;

    // Database Name
    private static final String DATABASE_NAME = "usersManager";


    private static final String TABLE_USER="user";

    public static final String KEY_USER = "id";
    public static final String USER_LEVEL = "level";
    public static final String USER_KM="km";


    public DatabaseUser(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_USER + " INTEGER PRIMARY KEY," +
                USER_LEVEL + " INTEGER ," +
                USER_KM + " INTEGER);";
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL("INSERT INTO "+TABLE_USER+" VALUES (0,1,0)");
    }

    public void onDestroy(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.close();
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // Create tables again
        onCreate(db);
    }

    public User getUsers() {
        System.out.println("€€€€€€€€ get all user ######");
        List<User> statisticsList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User u = new User();

                u.setId(Integer.parseInt(cursor.getString(0)));
                u.setLevel(Integer.parseInt(cursor.getString(1)));
                u.setKm((int)Double.parseDouble(cursor.getString(2)));

                statisticsList.add(u);
            } while (cursor.moveToNext());
        }

        // return contact list
        return statisticsList.get(0);
    }

    public void UpdateUser(User u){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_USER +
                "SET "+USER_LEVEL+" ="+u.getLevel()+
                        USER_KM+"= "+u.getKm()+
                "WHERE"+u.getId()+" = 0");
    }



    public void add(User us) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER,0);
        values.put(USER_KM, us.getKm());
        values.put(USER_LEVEL, us.getLevel());


        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
    }

    public void  update(User u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_KM,u.getKm()); //These Fields should be your String values of actual column names
        cv.put(USER_LEVEL,u.getLevel());
        db.update(TABLE_USER, cv, KEY_USER+"="+u.getId(), null);
    }




}