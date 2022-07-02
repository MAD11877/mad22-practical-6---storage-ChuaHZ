package sg.edu.np.mad.exercise2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Button btn;
    boolean flag;
    UsersAdapter what;
    ListActivity rv;
    User user = new User("Guest","PCG :c",1,false);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user = (User)getIntent().getSerializableExtra("Obj");


        TextView hello = findViewById(R.id.Hello);
        hello.setText("Hello" + user.Name);

        TextView desc = findViewById(R.id.desc);
        desc.setText((user.Description));
        flag = true;

        Intent receiveIntent = getIntent();
        int message = receiveIntent.getIntExtra("key", 123);

        TextView hi = findViewById(R.id.Hello);
        hi.setText("MAD " + message);

        // Week 4 start
        ArrayList<User> users = new ArrayList<>();


        hello.setText(user.Name);
        desc.setText(user.Description);
        Button follow = findViewById(R.id.Follow);

        Log.d("userfollow1","User is " + user.Followed);
        if (user.Followed){
            follow.setText("Unfollow");
        }
        else{
            follow.setText("Follow");
        }
        Log.d("userfollow2","User is " + user.Followed);
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.Followed = !user.Followed;
                DbHandler dbHandler = new DbHandler(MainActivity.this, "userDB.db", null, 1);
                dbHandler.updateUser(user);

                follow.setText(!user.Followed ? "Follow": "Unfollow");
                Toast.makeText(getApplicationContext(),!user.Followed ? "Unfollowed": "Followed", Toast.LENGTH_SHORT ).show();
            }
        });

    }

//    public void onClick(View v){
//        Log.d("Clickpos","Clicked profile" + v);
//        if (user.Followed){
//            btn.setText("Unfollow");
//            user.Followed = false;
//            Toast.makeText(this, "Followed", Toast.LENGTH_SHORT).show();
//        }
//        else{
//            btn.setText("Follow");
//            user.Followed = true;
//            Toast.makeText(this, "Unfollowed", Toast.LENGTH_SHORT).show();
//        }
//
//        Intent to_adapter = new Intent
//                (MainActivity.this,UsersAdapter.class);
//        to_adapter.putExtra("userid",user.Id);
//        Log.d("OrigID","User ID from mainAct is "+user.Id);
//        to_adapter.putExtra("userfollow",user.Followed);
//        Log.d("a",""+user.Followed);
//
//        ListActivity.users.get(user.Id - 1).Followed = !user.Followed;
//
//        Log.d("a",""+ListActivity.users.get(user.Id -1).Followed);
//
//
//    }



}