package postman.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import postman.logic.Address;

@AllArgsConstructor
@Getter
public class DestinationPackage {
    String addressee;
    Address toAddress;
}
