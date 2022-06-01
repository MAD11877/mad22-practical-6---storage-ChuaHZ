package sg.edu.np.mad.exercise2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {

    TextView desc;
    TextView name;
    ImageView img;
    ImageView bigimg;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);

        desc = itemView.findViewById(R.id.rvdesc);
        name = itemView.findViewById(R.id.rvname);
        img = itemView.findViewById(R.id.profilepic);
        bigimg = itemView.findViewById(R.id.profilepicbig);
    }


}
