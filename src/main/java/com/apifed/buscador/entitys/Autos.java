package com.apifed.buscador.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Autos")

public class Autos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INDEX")
    private Integer ID;

    @Column(name = "DE_MODELO")
    private String DE_MODELO;

    @Column(name = "DE_MARCA")
    private String DE_MARCA;

    @Column(name = "ANIO")
    private Integer ANIO;

    @Column(name = "CD_MARCA")
    private Integer CD_MARCA;

    @Column(name = "CD_MODELO")
    private Integer CD_MODELO;

    @Column(name = "CD_TIPODEVEHICULO")
    private Integer CD_TIPODEVEHICULO;

    @Column(name = "DR_TIPODEVEHICULO")
    private String DR_TIPODEVEHICULO;

}
