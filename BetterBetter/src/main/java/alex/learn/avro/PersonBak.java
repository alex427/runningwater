package alex.learn.avro;

public class PersonBak {

    private String id;
    private String name;
    private int favnumber;
    private String favcolor;

    public PersonBak(){

    }

    public PersonBak(String id, String name, int favnumber, String favcolor) {
        this.id = id;
        this.name = name;
        this.favnumber = favnumber;
        this.favcolor = favcolor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFavnumber() {
        return favnumber;
    }

    public void setFavnumber(int favnumber) {
        this.favnumber = favnumber;
    }

    public String getFavcolor() {
        return favcolor;
    }

    public void setFavcolor(String favcolor) {
        this.favcolor = favcolor;
    }
}
