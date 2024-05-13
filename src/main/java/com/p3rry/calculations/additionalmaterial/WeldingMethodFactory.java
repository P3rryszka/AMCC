package com.p3rry.calculations.additionalmaterial;

import com.p3rry.calculations.calculationsmanagement.IAdditionalMaterialOperations;
import lombok.NonNull;

public class WeldingMethodFactory {
    private static final String message = "Invalid welding method type or amount of parameters!";
    
    public static IAdditionalMaterialOperations createWeldingMethod(@NonNull String jointType, double wireSpoolMass) {
        if(jointType.equalsIgnoreCase("G"))
            return new GmawAdditionalMaterial(wireSpoolMass);
        else 
            throw new IllegalArgumentException(message);
    }
    
    public static IAdditionalMaterialOperations createWeldingMethod(@NonNull String jointType, double electrodeLength,
                                                                    double electrodeDiameter) {
        if(jointType.equalsIgnoreCase("S"))
            return new SmawAdditionalMaterial(electrodeLength, electrodeDiameter);
        else
            throw new IllegalArgumentException(message);
    }
}