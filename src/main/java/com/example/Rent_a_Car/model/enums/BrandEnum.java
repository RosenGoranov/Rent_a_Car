package com.example.Rent_a_Car.model.enums;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.Rent_a_Car.model.enums.ModelEnum.*;

public enum BrandEnum {

    AUDI(Sets.newHashSet(A1,A2,A3,A4,A5,A6,A7,A8)),
    VW(Sets.newHashSet(Arteon,Bora,Caddy,Corrado,Golf,Lupo,Passat)),
    MERCEDES_BENZ(Sets.newHashSet(A140,B150,C160,CL230,CLA180,CLC160,CLK55AMJ,E200)),
    BMW(Sets.newHashSet(X1,X2,X3,X4,Z1,Z3,Z4,Z8)),
    TOYOTA(Sets.newHashSet(Auris,Avensis,Corolla,Hilux,Rav4,Sequoia,Verso,Yaris)),
    OPEL(Sets.newHashSet( Astra,Calibra,Corsa,Insignia,Mokka,Omega,Vectra,Zafira
    )),
    PEUGEOT(Sets.newHashSet(Bipper, Expert, Partner, RCZ, Range, Rifter, Traveler, S5008));

    private final Set<ModelEnum> model;


    BrandEnum(Set<ModelEnum> model) {
        this.model = model;
    }

    public Set<ModelEnum> getModel() {

        return model;
    }
}
