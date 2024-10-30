package com.apifed.buscador.services.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.apifed.buscador.entitys.Autos;
import com.apifed.buscador.extra.ResultadoBusqueda;
import com.apifed.buscador.repositorys.IautosRepository;
import com.apifed.buscador.services.IbusquedaServices;

@Service
public class busquedaServicesImp implements IbusquedaServices {

    private IautosRepository autosRepository;

    public busquedaServicesImp(IautosRepository autosRepository) {
        this.autosRepository = autosRepository;
    }

    @Override
    public Autos getAuto(String busqueda) {
        // Reemplazamos las comas por puntos
        busqueda = busqueda.replace(",", ".");
        
        // Convertimos la búsqueda a mayúsculas
        String busquedaUpper = busqueda.toUpperCase();
        System.out.println("Búsqueda en mayúsculas: " + busquedaUpper);

        // Intentamos encontrar el año en la búsqueda
        Integer anio = null;
        Pattern yearPattern = Pattern.compile("\\b(\\d{4})\\b");
        Matcher matcher = yearPattern.matcher(busquedaUpper);
        if (matcher.find()) {
            anio = Integer.parseInt(matcher.group(1));
        }

        // Verificamos si tenemos el año, ya que es obligatorio
        if (anio == null) {
            System.out.println("Año no encontrado en la búsqueda.");
            return null;
        } else {
            System.out.println("Año encontrado: " + anio);
        }

        // Dividimos la búsqueda para encontrar la marca y el modelo
        String[] keywords = busquedaUpper.split(" ");
        String marca = keywords[0]; // asumimos que la primera palabra es la marca
        String modelo = busquedaUpper.replace(marca, "").replace(String.valueOf(anio), "").trim();

        // Dividimos el modelo en palabras clave
        String[] modeloKeywords = modelo.split(" ");
        String keyword1 = modeloKeywords.length > 0 ? modeloKeywords[0] : "";
        String keyword2 = modeloKeywords.length > 1 ? modeloKeywords[1] : "";
        String keyword3 = modeloKeywords.length > 2 ? modeloKeywords[2] : "";
        String keyword4 = modeloKeywords.length > 3 ? modeloKeywords[3] : "";
        String keyword5 = modeloKeywords.length > 4 ? modeloKeywords[4] : "";

        // Realizamos la consulta en el repositorio
        List<Autos> autos = autosRepository.findClosestMatch(marca, anio, keyword1, keyword2, keyword3, keyword4, keyword5);

        // Retornamos el primer auto encontrado
        return autos.stream().findFirst().orElse(null);
    }

    public List<ResultadoBusqueda> getAutos(List<String> busquedas) {
        List<ResultadoBusqueda> resultados = new ArrayList<>();

        for (String busqueda : busquedas) {
            Autos autoEncontrado = getAuto(busqueda);
            if (autoEncontrado != null) {
                resultados.add(new ResultadoBusqueda(autoEncontrado, null));
            } else {
                resultados.add(new ResultadoBusqueda(null, "No se encontró el vehículo para la búsqueda: " + busqueda));
            }
        }

        return resultados;
    }
}