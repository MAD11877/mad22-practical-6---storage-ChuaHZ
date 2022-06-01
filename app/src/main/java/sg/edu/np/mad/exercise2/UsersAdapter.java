package sg.edu.np.mad.exercise2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.OptIn;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {
    ArrayList<User> users;
    Context c;
    public UsersAdapter(Context context, ArrayList<User> user) {
        c = context;
        user = users;
    }

    public UsersAdapter(ArrayList<User> users) {this.users = users;}

    @Override
    public int getItemViewType(int position) {return 0;}

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;
        item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile,null,false);
        Log.d("w","onCreate");


        return new UserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User s = users.get(position);
        holder.name.setText(s.Name);
        holder.desc.setText((s.Description));
        Log.d("HIHI","OnBind"+position);
                if(s.Name.endsWith("7")){
                    holder.bigimg.setVisibility(View.VISIBLE);
                }
                else{
                    holder.bigimg.setVisibility(View.GONE);
                }
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                Log.d("ProfileClick","Clicked" + pos);
                AlertDialog.Builder ab
                        = new AlertDialog.Builder(v.getContext());

                ab.setTitle("Profile");
                ab.setMessage(s.Name);
                ab.setPositiveButton("View", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent to_profile = new Intent(v.getContext(),MainActivity.class);
//                                to_profile.putExtra("msg",)
                                to_profile.putExtra("name",s.Name);
                                to_profile.putExtra("desc",s.Description);
                                to_profile.putExtra("pos",s.Followed);
//                                to_profile.putExtra("full",(Serializable) s);
                                v.getContext().startActivity(to_profile);
                            }
                        });
                ab.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                ab.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
