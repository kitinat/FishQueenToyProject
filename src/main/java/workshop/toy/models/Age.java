package workshop.toy.models;

public class Age {
    private int id;
    private String age_name;

    public Age() {
    }

    public Age(int id, String age_name) {
        this.id = id;
        this.age_name = age_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAge_name() {
        return age_name;
    }

    public void setAge_name(String age_name) {
        this.age_name = age_name;
    }
}
