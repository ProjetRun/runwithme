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
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "userManager";

    // Contacts table name
    private static final String TABLE_STATS = "stats";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    public static final String DATE = "date";
    public static final String HEURE = "heure";
    public static final String DISTANCE = "distance";
    public static final String DUREE = "duree";
    public static final String RYTHME = "rythme";
    public static final String CALORIES = "calories";
    public static final String UNITEMESURE = "uniteMesure";


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
                CALORIES + " INTEGER," +
                UNITEMESURE + " INTEGER)";
        db.execSQL(CREATE_STATS_TABLE);
    }

    public void onDestroy(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_STATS);
        db.close();
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATS);
        // Create tables again
        onCreate(db);
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
        values.put(UNITEMESURE, stats.getUniteMesure());


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
                rs.setUniteMesure(cursor.getString(7));
                // Adding contact to list
                statisticsList.add(rs);
            } while (cursor.moveToNext());
        }

        return statisticsList;
    }


    public void deleteStatistics(RunningStatistics rs) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STATS, KEY_ID + " = ?",
                new String[] { String.valueOf(rs.getId()) });
        db.close();
    }


}