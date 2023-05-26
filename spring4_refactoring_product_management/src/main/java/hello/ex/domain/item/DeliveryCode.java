package hello.ex.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryCode {
    private String userCode;
    private String systemCode;

    public DeliveryCode(String userCode, String systemCode) {
        this.userCode = userCode;
        this.systemCode = systemCode;
    }
}