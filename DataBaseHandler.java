package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Meple.Student;
import Utils.Util;

public class DataBaseHandler extends SQLiteOpenHelper {


    public DataBaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" +
                Util.KEY_ID + " INTEGER PRIMARY KEY," +
                Util.KEY_FACULTY + " TEXT," +
                Util.KEY_LAST_NAME + " TEXT," +
                Util.KEY_FIRST_NAME + " TEXT," +
                Util.KEY_AV_GRADE +  " INTEGER" + ")";
        db.execSQL(CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(db);
    }
    public void addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.KEY_FACULTY, student.getFaculty());
        contentValues.put(Util.KEY_LAST_NAME, student.getLast_name());
        contentValues.put(Util.KEY_FIRST_NAME, student.getFirst_name());
        contentValues.put(Util.KEY_AV_GRADE, student.getAv_grade());

        db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
    }

    public Student getStudent(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.KEY_ID, Util.KEY_FACULTY, Util.KEY_LAST_NAME, Util.KEY_FIRST_NAME, Util.KEY_AV_GRADE},
                Util.KEY_ID + "=?", new String[]{String.valueOf(id)},
                null,null,null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        Student student = new Student(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2),cursor.getString(3), Integer.parseInt(cursor.getString(4)));
        return student;
    }
    public List<Student> getAllStudents(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<Student> allStudents = new ArrayList<>();
        String  command = "SELECT * FROM " + Util.TABLE_NAME;

        Cursor cursor = db.rawQuery(command, null);
        if(cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setFaculty(cursor.getString(1));
                student.setLast_name(cursor.getString(2));
                student.setFirst_name(cursor.getString(3));
                student.setAv_grade(Integer.parseInt(cursor.getString(4)));

                allStudents.add(student);
            } while (cursor.moveToNext());
        }
        return allStudents;
    }
    public int updateStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.KEY_FACULTY, student.getFaculty());
        contentValues.put(Util.KEY_LAST_NAME, student.getLast_name());
        contentValues.put(Util.KEY_FIRST_NAME, student.getFirst_name());
        contentValues.put(Util.KEY_AV_GRADE, student.getAv_grade());

        return db.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "=?", new String[]{String.valueOf(student.getId())});
    }

    public void deleteStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?", new String[]{String.valueOf(student.getId())});
        db.close();
    }
    public int getStudentsCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String command = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(command, null);
        return cursor.getCount();
    }
}
