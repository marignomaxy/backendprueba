package com.apifed.buscador.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apifed.buscador.entitys.Autos;
import com.apifed.buscador.extra.ResultadoBusqueda;
import com.apifed.buscador.services.IbusquedaServices;



@RestController
@RequestMapping("/api")
public class busquedaController {

    private IbusquedaServices busquedaServices;

    public busquedaController(IbusquedaServices busquedaServices) {
        this.busquedaServices = busquedaServices;
    }


    @PostMapping("/busqueda")
    public ResponseEntity<?> getAutos(@RequestBody List<String> busquedas) {
        if (busquedas == null || busquedas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La lista de búsquedas no puede estar vacía.");
        }

        List<ResultadoBusqueda> resultados = new ArrayList<>();

        for (String busqueda : busquedas) {
            if (busqueda == null || busqueda.trim().isEmpty()) {
                continue;
            }
            Autos autoEncontrado = busquedaServices.getAuto(busqueda);
            if (autoEncontrado != null) {
                resultados.add(new ResultadoBusqueda(autoEncontrado, null));
            } else {
                resultados.add(new ResultadoBusqueda(null, "No se encontró el vehículo para la búsqueda: " + busqueda));
            }
        }

        if (resultados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún vehículo que coincida con las búsquedas.");
        }


        return ResponseEntity.ok(resultados);
    }
    

}
