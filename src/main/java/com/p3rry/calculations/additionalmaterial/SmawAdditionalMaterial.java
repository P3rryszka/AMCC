package com.p3rry.calculations.additionalmaterial;

import com.p3rry.calculations.calculationsmanagement.IAdditionalMaterialOperations;
import com.p3rry.consts.Properties;
import com.p3rry.utlis.InputMessages;

import java.util.Optional;

public class SmawAdditionalMaterial implements IAdditionalMaterialOperations {
    public static final double EFFECTIVE_ELECTRODE_LENGTH_FACTOR = 0.875;

    private double effectiveElectrodeLength;
    private double electrodeDiameter;
    private double density;

    public SmawAdditionalMaterial(double electrodeLength, double electrodeDiameter,
                                  double density) {
        this.effectiveElectrodeLength = Optional.of(electrodeLength * EFFECTIVE_ELECTRODE_LENGTH_FACTOR)
                .filter(el -> el > Properties.ELECTRODE_LENGTH_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(Properties.ELECTRODE_LENGTH_LIMIT, "<=");
                    return new IllegalArgumentException("Electrode length cannot be <= " +
                            Properties.ELECTRODE_LENGTH_LIMIT);
                });

        this.electrodeDiameter = Optional.of(electrodeDiameter)
                .filter(ed -> ed > Properties.ELECTRODE_DIAMETER_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(Properties.ELECTRODE_DIAMETER_LIMIT, "<=");
                    return new IllegalArgumentException("Electrode diameter cannot be <= " +
                           Properties.ELECTRODE_DIAMETER_LIMIT);
                });

        this.density = Optional.of(density)
                .filter(d -> d > Properties.DENSITY_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(Properties.DENSITY_LIMIT, "<=");
                    return new IllegalArgumentException("Density cannot be <= " +
                            Properties.DENSITY_LIMIT);
                });
    }

    @Override
    public double calculateNeededAdditionalMaterial(double jointMass) {
        return jointMass / (calculateCylinderVolume(density, electrodeDiameter, effectiveElectrodeLength) *
                Properties.DEPOSITED_METAL_YIELD * Properties.WELD_SPATTER_FACTOR *
                Properties.DESTROY_FACTOR);
    }
}
