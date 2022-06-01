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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn;
    boolean flag;
    UsersAdapter what;
    ListActivity rv;
    User user = new User("Guest","PCG :c",1,false);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.Follow);
        btn.setOnClickListener(this);


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
        Intent from_profile = getIntent();
        user.Name = from_profile.getStringExtra("name");
        user.Description= from_profile.getStringExtra("desc");
        user.Followed = ListActivity.users.get(user.Id - 1).Followed;



        hello.setText(user.Name);
        desc.setText(user.Description);

        if (user.Followed){
            btn.setText("Unfollow");
            user.Followed = false;
        }
        else{
            btn.setText("Follow");
            user.Followed = true;
        }

    }

    DBHandler db = new DBHandler(this);

    public void onClick(View v){
        Log.d("Clickpos","Clicked profile" + v);
        if (user.Followed){
            btn.setText("Unfollow");
            user.Followed = false;
            Toast.makeText(this, "Followed", Toast.LENGTH_SHORT).show();
            db.updateUser(user);
        }
        else{
            btn.setText("Follow");
            user.Followed = true;
            Toast.makeText(this, "Unfollowed", Toast.LENGTH_SHORT).show();
            db.updateUser(user);
        }


        Intent to_adapter = new Intent
                (MainActivity.this,UsersAdapter.class);
        to_adapter.putExtra("userid",user.Id);
        Log.d("OrigID","User ID from mainAct is "+user.Id);
        to_adapter.putExtra("userfollow",user.Followed);


        ListActivity.users.get(user.Id - 1).Followed = !user.Followed;



    }



}