package es.guillemburnleesviada.ejemplo15_retrofit.pojo;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class User {
    private long id;
    private String email;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    private String avatar;

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String value) { this.firstName = value; }

    public String getLastName() { return lastName; }
    public void setLastName(String value) { this.lastName = value; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String value) { this.avatar = value; }

    @NonNull
    @Override
    public String toString() {
        return "Nombre: "+firstName+", Apellidos: "+lastName+", Email: "+email;
    }
}
