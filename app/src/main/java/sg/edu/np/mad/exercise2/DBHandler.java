package sg.edu.np.mad.exercise2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(Context c){
        super(c,"Users.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql = "CREATE TABLE Profiles " +
                "(keyID integer primary key autoincrement," +
                " Username string" +
                ", Description string, Id integer, Followed BOOL)";
        db.execSQL(sql);
        Random rand = new Random();
        for(int i =0; i <20; i ++){
            User u = new User("Name"+rand.nextInt(99999999)
                    ,"Description"+rand.nextInt(99999999),i+1, rand.nextBoolean());
            String statement = "Insert into Profiles " +
                    "(Username, Description, Id, Followed) " +
                    "Values( \"" + u.Name + "\",\"" + u.Description + "\"," + u.Id + ",\"" + u.Followed + "\")";
            db.execSQL(statement);
        }
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Profiles");
        onCreate(db);

//        switch(oldVersion){
//            case 1:
//                // alter table and add datetime
//            case 2:
//                // alter table add seen
//            case 3:
//                // alter table add reaction
//        } // Since there is no break in the switch case, they will continue doing the subsequent
//        // cases and will continuously upgrade to the latest version.

    }

    public void addUser(User u){
//        ContentValues values = new ContentValues();
//        values.put("Username",user.Name);
//        values.put("Description",user.Description);
//        values.put("Id",user.Id);
//        values.put("Followed",user.Followed);
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO User VALUES(\""+u.Name+"\", \""+u.Description+"\", \""+u.Id+"\", \""+!u.Followed+"\")");
        db.close();
    }

    public ArrayList<User> getUsers(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from Profiles",null);

        while (cursor.moveToNext()){
            User u = new User(cursor.getString(1),cursor.getString(2),
                    cursor.getInt(3),cursor.getWantsAllOnMoveCalls());
            users.add(u);
        }
        db.close();
        cursor.close();
        return users;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE Profiles SET Followed = \"" + !user.Followed + "\" " + "WHERE Id = \"" + user.Id + "\"");
        db.close();
        Log.d("what","this user is "+ !user.Followed);
    }

}
