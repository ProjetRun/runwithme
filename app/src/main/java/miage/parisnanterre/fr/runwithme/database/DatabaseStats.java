package miage.parisnanterre.fr.runwithme.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

import miage.parisnanterre.fr.runwithme.MarathonTraining.MarathonTrainingActivity;
import miage.parisnanterre.fr.runwithme.MarathonTraining.Seance;
import miage.parisnanterre.fr.runwithme.badges.Badge;
import miage.parisnanterre.fr.runwithme.running.RunningStatistics;

public class DatabaseStats extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 14;

    // Database Name
    private static final String DATABASE_NAME = "userManager";

    // Stats table name
    private static final String TABLE_STATS = "stats";
    // Badges table name
    private static final String TABLE_BADGES = "badges";
    public static final String DB_TABLE="Seance";

    // Stats Table Columns names
    private static final String KEY_ID = "id";
    public static final String DATE = "date";
    public static final String HEURE = "heure";
    public static final String DISTANCE = "distance";
    public static final String DUREE = "duree";
    public static final String RYTHME = "rythme";
    public static final String CALORIES = "calories";
    public static final String UNITEMESURE = "uniteMesure";

    // Badges Table Columns names
    public static final String NUMERO_ID = "numero";
    public static final String NOM = "nom";

    //public static final int ID = 0;
    public static final String NUM_SEMAINE_COLUMN = "numSemaine";
    public static final String NUM_SEANCE_COLUMN = "NumSeance";
    public static final String TYPE_SEANCE_COLUMN = "typeSeance";
    public static final String CONTENU_COLUMN = "contenuSeance";
    public static final String CHECKED_SEANCE = "checkedSeance";
    public static final String DUREE_run = "duree";


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

        String CREATE_BADGES_TABLE = "CREATE TABLE " + TABLE_BADGES + "(" +
                NUMERO_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NOM+ " TEXT);";
        String query = String.format("CREATE TABLE %s " +
                        "(ID INTEGER PRIMARY KEY," +
                        "%s INTEGER NOT NULL, " +
                        "%s INTEGER NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s INTEGER, " +
                        "%s INTEGER);",
                DB_TABLE,
                //ID,
                NUM_SEMAINE_COLUMN,
                NUM_SEANCE_COLUMN,
                TYPE_SEANCE_COLUMN,
                CONTENU_COLUMN,
                CHECKED_SEANCE,
                DUREE_run);
        db.execSQL(query);
        db.execSQL(CREATE_STATS_TABLE);
        db.execSQL(CREATE_BADGES_TABLE);

        Log.i("DATABASE","onCreate invoked");
    }


    public void onDestroy(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_STATS);
        db.execSQL("DELETE FROM " + TABLE_BADGES);
        db.close();
        Log.i("DATABASE","onDestroy invoked");

    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BADGES);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        // Create tables again
        onCreate(db);
        Log.i("DATABASE","onUpgrade invoked");

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
        Log.i("DATABASE","addStats invoked");
    }

    public void addBadge(Badge badge) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NUMERO_ID, badge.getNumero());
        values.put(NOM, badge.getNom());

        // Inserting Row
        db.insert(TABLE_BADGES, null, values);
        db.close(); // Closing database connection
        Log.i("DATABASE","addBadge invoked");
    }


    public void deleteallseances(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from "+ DB_TABLE);
        db.close();
    }

    public void insertNewSeance(Seance seance){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NUM_SEANCE_COLUMN,seance.getNumSeance());
        values.put(NUM_SEMAINE_COLUMN,seance.getNumSemaine());
        values.put(TYPE_SEANCE_COLUMN,seance.getTypeSeance());
        values.put(CONTENU_COLUMN,seance.getContenuSeance());
        values.put(CHECKED_SEANCE,0);
        values.put(DUREE_run,seance.getMinutes());

        db.insertWithOnConflict(DB_TABLE,null,values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void insertSeanceChecked(Seance seance){
        Log.e("DBHelper_Checked", "insertSeanceChecked  :  " + seance.isChecked());
        SQLiteDatabase db= this.getWritableDatabase();
        int isChecked;
        if(seance.isChecked() == false)
            isChecked = 1;
        else
            isChecked = 0;
        String sql = "UPDATE "+DB_TABLE +" SET " + CHECKED_SEANCE+"="+isChecked+" WHERE id="+seance.getId();
        Log.e("DBHelper_request", "insertSeanceChecked  :  " + sql);
        db.execSQL(sql);
        db.close();
    }

    public List<Badge> getAllBadges() {
        System.out.println("*******************get all badges************************");
        List<Badge> badgesList = new ArrayList<Badge>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BADGES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Badge badge = new Badge();
                //badge.setId(Integer.parseInt(cursor.getString(0)));
                badge.setNumero(cursor.getInt(0));
                badge.setNom(cursor.getString(1));
                // Adding contact to list
                badgesList.add(badge);
            } while (cursor.moveToNext());
        }
        Log.i("DATABASE","getAllBadges invoked");
        return badgesList;
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

        Log.i("DATABASE","getAllStats invoked");
        return statisticsList;
    }


    public void deleteStatistics(RunningStatistics rs) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STATS, KEY_ID + " = ?",
                new String[] { String.valueOf(rs.getId()) });
        db.close();
    }

    public void deleteBadgese(Badge badge) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BADGES, NUMERO_ID + " = ?",
                new String[] { String.valueOf(badge.getNumero()) });
        db.close();
    }

    public ArrayList<Seance> getTaskList(){
        ArrayList<Seance> seances = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query= "SELECT * FROM "+ DB_TABLE +";";
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext()){
            Seance seance = new Seance();
            //String data = cursor.getString(cursor.getColumnIndex("data"));//
            //       seance.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            seance.setContenuSeance(cursor.getString(cursor.getColumnIndex(CONTENU_COLUMN)));
            seance.setNumSeance(cursor.getInt(cursor.getColumnIndex(NUM_SEANCE_COLUMN)));
            seance.setNumSemaine(cursor.getInt(cursor.getColumnIndex(NUM_SEMAINE_COLUMN)));
            seance.setTypeSeance(cursor.getString(cursor.getColumnIndex(TYPE_SEANCE_COLUMN)));
            seance.setChecked(cursor.getInt(cursor.getColumnIndex(CHECKED_SEANCE))>0);
            seance.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            //boolean value = cursor.getInt(boolean_column_index) > 0;
            seances.add(seance);
        }
        cursor.close();
        db.close();
        return seances;
    }

    public ArrayList<Seance> getSeanceList1(){
        ArrayList<Seance> seances = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String typeEntrainement = "Debuter";
        Cursor cursor = db.rawQuery("SELECT * FROM "+ DB_TABLE +" WHERE "+TYPE_SEANCE_COLUMN+" = '"+typeEntrainement+"'", null);
        while(cursor.moveToNext()){
            Seance seance = new Seance();
            //String data = cursor.getString(cursor.getColumnIndex("data"));//
            //       seance.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            seance.setContenuSeance(cursor.getString(cursor.getColumnIndex(CONTENU_COLUMN)));
            seance.setNumSeance(cursor.getInt(cursor.getColumnIndex(NUM_SEANCE_COLUMN)));
            seance.setNumSemaine(cursor.getInt(cursor.getColumnIndex(NUM_SEMAINE_COLUMN)));
            seance.setTypeSeance(cursor.getString(cursor.getColumnIndex(TYPE_SEANCE_COLUMN)));
            seance.setChecked(cursor.getInt(cursor.getColumnIndex(CHECKED_SEANCE))==0);
            seance.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            //boolean value = cursor.getInt(boolean_column_index) > 0;
            seances.add(seance);
        }
        cursor.close();
        db.close();
        return seances;
    }
}