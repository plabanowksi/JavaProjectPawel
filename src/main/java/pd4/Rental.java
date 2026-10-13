package pd4;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@RequiredArgsConstructor
@SuperBuilder
@Getter
@ToString
public class Rental {
    protected Resource resource;
    protected int days;
    private RentalStatus status;
}
