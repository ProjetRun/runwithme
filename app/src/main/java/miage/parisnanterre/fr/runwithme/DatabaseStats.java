package miage.parisnanterre.fr.runwithme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseStats extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "userManager";

    // Contacts table name
    private static final String TABLE_STATS = "stats";
    private static final String TABLE_LEVEL = "level";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    public static final String DATE = "date";
    public static final String HEURE = "heure";
    public static final String DISTANCE = "distance";
    public static final String DUREE = "duree";
    public static final String RYTHME = "rythme";
    public static final String CALORIES = "calories";

    public static final String KEY_LV = "lv";
    public static final String KEY_ACTUAL_KM = "akm";
    public static final String KEY_VALIDATE_KM ="vkm";


    public DatabaseStats(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STATS_TABLE = "CREATE TABLE " + TABLE_STATS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DATE+ " TEXT, " +
                HEURE+ " TEXT, " +
                DISTANCE+ " INTEGER, " +
                DUREE+ " INTEGER, " +
                RYTHME+ " INTEGER, " +
                CALORIES + " INTEGER);";
        String CREATE_LV_TABLE = "CREATE TABLE " + TABLE_LEVEL + "("
                + KEY_LV + " INTEGER PRIMARY KEY," +
                KEY_ACTUAL_KM + " INTEGER ," +
                KEY_VALIDATE_KM + " INTEGER);";
        db.execSQL(CREATE_STATS_TABLE);
        db.execSQL(CREATE_LV_TABLE);
        initLevel();
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEVEL);
        // Create tables again
        onCreate(db);
    }

    public void initLevel(){
        SQLiteDatabase db = this.getWritableDatabase();

        for(int i=1;i<6;i++){
            ContentValues values = new ContentValues();
            values.put(KEY_LV, i);
            values.put(KEY_ACTUAL_KM, 0);
            values.put(KEY_VALIDATE_KM, i*20);
            db.insert(TABLE_LEVEL, null, values);
        }

        db.close(); // Closing database connection
    }

    public void addStats(RunningStatistics stats) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DATE, stats.getDate());
        values.put(HEURE, stats.getHeure());
        values.put(DISTANCE, stats.getDistance());
        values.put(DUREE, stats.getDuree());
        values.put(RYTHME, stats.getRythme());
        values.put(CALORIES, stats.getCalories());


        // Inserting Row
        db.insert(TABLE_STATS, null, values);
        db.close(); // Closing database connection
    }

    public List<RunningStatistics> getAllStats() {
        System.out.println("€€€€€€€€ get all ######");
        List<RunningStatistics> statisticsList = new ArrayList<RunningStatistics>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STATS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RunningStatistics rs = new RunningStatistics();
                rs.setId(Integer.parseInt(cursor.getString(0)));
                rs.setDate(cursor.getString(1));
                rs.setHeure(cursor.getString(2));
                rs.setDistance(cursor.getString(3));
                rs.setDuree(cursor.getString(4));
                rs.setRythme(cursor.getString(5));
                rs.setCalories(cursor.getString(6));
                // Adding contact to list
                statisticsList.add(rs);
            } while (cursor.moveToNext());
        }

        // return contact list
        return statisticsList;
    }
    /*
    public int updateContact(RunningStatistics rs) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PSEUDO, user.get_pseudo());
        values.put(KEY_PASS, user.get_pass());
        values.put(KEY_EMAIL, user.get_email());

        // updating row
        return db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(user.get_id()) });
    }
    */
    public int getActualKM(int myLV){

        String selectQuery = "SELECT "+KEY_ACTUAL_KM+" FROM " + TABLE_LEVEL+" WHERE "+KEY_LV+"="+myLV;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        return 0;

    }
    public void updateLevel(int lv,int km){
        km += getActualKM(lv);
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE "+TABLE_LEVEL+"SET "+KEY_ACTUAL_KM+" ="+km+" WHERE KEY_LV ="+lv+";");

    }
    public void deleteStatistics(RunningStatistics rs) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STATS, KEY_ID + " = ?",
                new String[] { String.valueOf(rs.getId()) });
        db.close();
    }


}