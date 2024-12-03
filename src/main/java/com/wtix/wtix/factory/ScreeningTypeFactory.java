package com.wtix.wtix.factory;

import com.wtix.wtix.model.screentype.ImaxScreeningType;
import com.wtix.wtix.model.screentype.LargeScreeningType;
import com.wtix.wtix.model.ScreeningType;
import com.wtix.wtix.model.screentype.StandardScreeningType;
import org.springframework.stereotype.Component;

@Component
public class ScreeningTypeFactory { ;

    public ScreeningType createScreeningType(Integer ScreeningType_id){
        return switch (ScreeningType_id) {
            case 1 -> new StandardScreeningType();
            case 2 -> new ImaxScreeningType();
            case 3 -> new LargeScreeningType();
            default -> null;
        };
    }

}
