package sg.edu.np.mad.exercise2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
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

        DbHandler dbHandler = new DbHandler(this, "userDB.db", null, 1);
        dbHandler.addUsers();

        ArrayList<User> Userlist = new ArrayList<>();
        Userlist = dbHandler.getUser();

        RecyclerView recyclerView = findViewById(R.id.Listrv);
        UsersAdapter usersAdapter = new UsersAdapter(ListActivity.this, Userlist, dbHandler);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(usersAdapter);

    }
}