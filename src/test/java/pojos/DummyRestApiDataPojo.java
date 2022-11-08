package pojos;

public class DummyRestApiDataPojo {
    private String employee_name;
    private Integer employee_salarty;
    private Integer employee_age;
    private String  profile_image;

    public DummyRestApiDataPojo() {
    }

    public DummyRestApiDataPojo(String employee_name, Integer employee_salarty, Integer employee_age, String profile_image) {
        this.employee_name = employee_name;
        this.employee_salarty = employee_salarty;
        this.employee_age = employee_age;
        this.profile_image = profile_image;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public Integer getEmployee_salarty() {
        return employee_salarty;
    }

    public void setEmployee_salarty(Integer employee_salarty) {
        this.employee_salarty = employee_salarty;
    }

    public Integer getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(Integer employee_age) {
        this.employee_age = employee_age;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    @Override
    public String toString() {
        return "DummyRestApiDataPojo{" +
                "employee_name='" + employee_name + '\'' +
                ", employee_salarty=" + employee_salarty +
                ", employee_age=" + employee_age +
                ", profile_image='" + profile_image + '\'' +
                '}';
    }
}
