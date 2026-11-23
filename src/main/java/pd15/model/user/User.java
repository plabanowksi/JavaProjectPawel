package pd15.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    public Long id;
    public String name;
    public String username;
    public String email;
    public String phone;
    public int age;
    public String website;
    public String country;
    public Address address;
}
