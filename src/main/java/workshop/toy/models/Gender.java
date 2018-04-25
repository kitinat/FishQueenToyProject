package workshop.toy.models;

public class Gender {
    private int id;
    private String gender_name;

    public Gender(int id, String gender_name) {
        this.id = id;
        this.gender_name = gender_name;
    }

    public String getGender_name() {
        return gender_name;
    }

    public void setGender_name(String gender_name) {
        this.gender_name = gender_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
