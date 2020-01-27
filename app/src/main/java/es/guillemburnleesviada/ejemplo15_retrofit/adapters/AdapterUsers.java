package es.guillemburnleesviada.ejemplo15_retrofit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.guillemburnleesviada.ejemplo15_retrofit.R;
import es.guillemburnleesviada.ejemplo15_retrofit.pojo.User;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.Card> {

    private Context context;
    private int resource;
    private List<User> users;

    public AdapterUsers(Context context, int resource, List<User> users) {
        this.context = context;
        this.resource = resource;
        this.users = users;
    }

    /**
     * Montar el objeto card a partir del View del layout
     * */



    @NonNull
    @Override
    public Card onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(context).inflate(resource, null);
        Card card = new Card(cardView);
        return card;
    }

    /**
     * Dar Datos a los elementos del card
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull Card holder, int position) {
        User user = users.get(position);
        holder.lblNombre.setText(user.getFirstName());
        holder.lblApellidos.setText(user.getLastName());
        holder.lblEmail.setText(user.getEmail());
        // FOTO -> String -> URL de un servidor
        Picasso.get()
                .load(user.getAvatar())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgUser);

    }

    public void recargarDatos(List<User> users){
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    //Mapea el layout en un objeto de tipo card
    class Card extends RecyclerView.ViewHolder{

        TextView lblNombre, lblApellidos, lblEmail;
        ImageView imgUser;

        public Card(@NonNull View itemView) {
            super(itemView);
            lblNombre = itemView.findViewById(R.id.lblNombreCard);
            lblApellidos = itemView.findViewById(R.id.lblApellidosCard);
            lblEmail = itemView.findViewById(R.id.lblEmailCard);
            imgUser = itemView.findViewById(R.id.imgPersonaCard);
        }
    }

}
