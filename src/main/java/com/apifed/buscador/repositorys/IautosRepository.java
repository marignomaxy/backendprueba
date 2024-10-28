package com.apifed.buscador.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apifed.buscador.entitys.Autos;

public interface IautosRepository  extends JpaRepository<Autos, Integer> {

       @Query("SELECT a FROM Autos a " +
       "WHERE LOWER(a.DE_MODELO) LIKE LOWER(CONCAT('%', :modelo, '%')) " +
       "AND LOWER(a.DE_MARCA) = LOWER(:marca) " +
       "AND a.ANIO = :anio " +
       "ORDER BY ABS(a.ANIO - :anio) ASC")
    List<Autos> findClosestMatch(@Param("modelo") String modelo, 
                                 @Param("marca") String marca, 
                                 @Param("anio") Integer anio);

}
