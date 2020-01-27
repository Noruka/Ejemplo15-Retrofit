package es.guillemburnleesviada.ejemplo15_retrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.guillemburnleesviada.ejemplo15_retrofit.adapters.AdapterUsers;
import es.guillemburnleesviada.ejemplo15_retrofit.pojo.ResponseUsers;
import es.guillemburnleesviada.ejemplo15_retrofit.pojo.User;
import es.guillemburnleesviada.ejemplo15_retrofit.retrofit_connection.ApiConnection;
import es.guillemburnleesviada.ejemplo15_retrofit.retrofit_connection.RetrofitObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private int resource = R.layout.card_layout;
    private List<User> users;
    private AdapterUsers adapterUsers;
    private RecyclerView.LayoutManager layoutManager;
    private ApiConnection connection;
    private int contador=1;
    private ResponseUsers responseUsers;
    private long paginasTotales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        users = new ArrayList<>();

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapterUsers = new AdapterUsers(this, resource, users);
        recyclerView.setAdapter(adapterUsers);

        //Construir conexion
        connection = RetrofitObject.getConnection().create(ApiConnection.class);

        Call<ResponseUsers> respuesta = connection.doGetUsersPerPage(contador+"");
        respuesta.enqueue(new Callback<ResponseUsers>() {
            @Override
            public void onResponse(Call<ResponseUsers> call, Response<ResponseUsers> response) {
                // Se ejecuta -> no me asegura que tenga los datos OK
                if (response.code() == 200){
                    responseUsers = response.body();
                    paginasTotales = responseUsers.getTotalPages();
                    users.addAll(responseUsers.getData());

                    adapterUsers.recargarDatos(users);
                    contador++;
                    Log.d("Users", "PAGINA: "+responseUsers.getPage());
                    for (User user : users) {
                        Log.d("User", user.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUsers> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);

        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.menupagina){

            Call<ResponseUsers> respuesta = connection.doGetUsersPerPage(contador+"");
            respuesta.enqueue(new Callback<ResponseUsers>() {
                @Override
                public void onResponse(Call<ResponseUsers> call, Response<ResponseUsers> response) {
                    // Se ejecuta -> pero no me asegura que tenga los datos OK
                    if ((response.code() == 200) && (contador<=paginasTotales)){
                        responseUsers = response.body();
                        users.addAll(responseUsers.getData());

                        adapterUsers.recargarDatos(users);
                        contador++;

                        Log.d("Users", "PAGINA: "+responseUsers.getPage());
                        for (User user : users) {
                            Log.d("User", user.toString());
                        }
                    }else{

                        Toast.makeText(MainActivity.this, "No hay mas paginas en la API", Toast.LENGTH_SHORT).show();
                        
                    }
                }

                @Override
                public void onFailure(Call<ResponseUsers> call, Throwable t) {

                }
            });

        }

        return true;
    }
}
