import java.util.function.BooleanSupplier;

public class User extends Person
{

    private String deliveryAddress;
   private Boolean isLogged =false;
    public static int numberOfUser = 0;

    User(){

    }

    User(String userName , String email , String password ,String deliveryAddress){

        super.userName = userName;
        super.email = email;
        super.password = password;
        this.deliveryAddress = deliveryAddress;
    }

    public void setLogged() {
        isLogged = true;
    }

    public Boolean getLogged() {
        return isLogged;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }


    public String getDeliveryAddress() {
        return deliveryAddress;
    }

public String toString (){
        return getUserName()+","+getEmail()+","+getPassword()+","+deliveryAddress;
}
}
