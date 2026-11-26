package pd15.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
    public String street;
    public String suite;
    public String city;
    public String zipcode;
    public Geo geo;
}
