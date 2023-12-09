import java.util.Scanner;

public class Dish {
    private String name;
    private float price;
    private String description;

    Dish(String name,float price,String description){
        this.name=name;
        this.price=price;
        this.description=description;
    }

    public Dish (String name, float price)
    {
        this.name=name;
        this.price=price;
    }
    public Dish (String name)
    {
        this.name=name;
    }
    public String toString(){
        return name+","+price+","+description;
    }


    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public void display(){

        System.out.println("Name: "+name);
        System.out.println("Price: "+price);
        System.out.println("Description: "+description);

    }

    public float getPrice() {
        return price;
    }
}

