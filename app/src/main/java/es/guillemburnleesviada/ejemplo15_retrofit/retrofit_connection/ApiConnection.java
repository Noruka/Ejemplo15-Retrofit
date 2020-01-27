package es.guillemburnleesviada.ejemplo15_retrofit.retrofit_connection;

import es.guillemburnleesviada.ejemplo15_retrofit.pojo.ResponseUsers;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiConnection {

    // Call<DATO> -> Uso Call como intermediario entre String JSON y el Objeto Real

    @GET("/api/users")
    Call<ResponseUsers> doGetUsers();

    @GET("/api/users?")
    Call<ResponseUsers> doGetUsersPerPage(@Query("page") String pagina);

}
