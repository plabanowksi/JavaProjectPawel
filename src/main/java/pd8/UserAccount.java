package pd8;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
//@EqualsAndHashCode
public class UserAccount {
    private final Long id;
    private final String email;
    private final String displayName;
    private  String status;


    @Override
    public int hashCode() {
        return Objects.hash(id, email, displayName, status);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        UserAccount that = (UserAccount) obj;

        return Objects.equals(id, that.id)
            && Objects.equals(email, that.email)
            && Objects.equals(displayName, that.displayName)
            && Objects.equals(status, that.status);
    }

}