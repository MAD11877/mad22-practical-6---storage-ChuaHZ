package sg.edu.np.mad.exercise2;


import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class User implements Serializable {
    String Name;
    String Description;
    int Id;
    boolean Followed;

    public User(){
        Random random = new Random();
        Integer Rn = random.nextInt(999999);
        Integer Rd = random.nextInt(999999);

        this.Name = "MAD" + Rn;
        this.Description = "Description" + Rd;
        this.Id = random.nextInt(99999);
        this.Followed = random.nextBoolean();
    }

    public User(String name, String desc, int id, boolean followed) {
        Name = name;
        Description = desc;
        Id = id;
        Followed = followed;

        }

    }




