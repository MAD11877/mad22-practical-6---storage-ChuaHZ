package sg.edu.np.mad.exercise2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    public static ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.d("what","ListActivity created");

        DBHandler db = new DBHandler(this);
        users = new ArrayList<>();
        users = db.getUsers();
        Random rand = new Random();
        int upperbound = 900000;
        if (users.size() == 0){
            for(int i = 0; i < 20; i++){
                User u = (new User("Name" + rand.nextInt(upperbound)
                        ,"Description " + rand.nextInt(upperbound)
                        , i
                        , rand.nextBoolean()
                ));
                users.add(u);
                db.addUser(u);
            }
        }
        users = db.getUsers();

        SharedPreferences pref = getSharedPreferences("P03",MODE_MULTI_PROCESS);
        String text = pref.getString("Key","Not found");

        SharedPreferences.Editor editor =
                pref.edit();

        



        RecyclerView rv = findViewById(R.id.Listrv);
        UsersAdapter adapter = new UsersAdapter(users);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);


    }
}