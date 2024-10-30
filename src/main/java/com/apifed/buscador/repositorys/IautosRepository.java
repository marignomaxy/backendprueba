package com.apifed.buscador.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apifed.buscador.entitys.Autos;

public interface IautosRepository extends JpaRepository<Autos, Long> {

   @Query("SELECT a FROM Autos a " +
          "WHERE LOWER(a.DE_MARCA) = LOWER(:marca) " +
          "AND a.ANIO = :anio " +
          "AND (" +
          "  LOWER(a.DE_MODELO) LIKE LOWER(CONCAT('%', :keyword1, '%')) " +
          "  AND LOWER(a.DE_MODELO) LIKE LOWER(CONCAT('%', :keyword2, '%')) " +
          "  AND LOWER(a.DE_MODELO) LIKE LOWER(CONCAT('%', :keyword3, '%')) " +
          "  AND LOWER(a.DE_MODELO) LIKE LOWER(CONCAT('%', :keyword4, '%')) " +
          "  AND LOWER(a.DE_MODELO) LIKE LOWER(CONCAT('%', :keyword5, '%')) " +
          ")")
   List<Autos> findClosestMatch(@Param("marca") String marca, 
                                @Param("anio") Integer anio,
                                @Param("keyword1") String keyword1,
                                @Param("keyword2") String keyword2,
                                @Param("keyword3") String keyword3,
                                @Param("keyword4") String keyword4,
                                @Param("keyword5") String keyword5);
}

