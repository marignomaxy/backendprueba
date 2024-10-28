package com.apifed.buscador.services.imp;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.apifed.buscador.entitys.Autos;
import com.apifed.buscador.repositorys.IautosRepository;
import com.apifed.buscador.services.IbusquedaServices;

@Service
public class busquedaServicesImp implements IbusquedaServices {

    private IautosRepository autosRepository;

    public busquedaServicesImp(IautosRepository autosRepository) {
        this.autosRepository = autosRepository;
    }
    
    public Autos getAuto(String busqueda) {
       // Convertimos la búsqueda a mayúsculas
       String busquedaUpper = busqueda.toUpperCase();

       // Intentamos encontrar el año en la búsqueda
       Integer anio = null;
       Pattern yearPattern = Pattern.compile("\\b(\\d{4})\\b");
       Matcher matcher = yearPattern.matcher(busquedaUpper);
       if (matcher.find()) {
           anio = Integer.parseInt(matcher.group(1));
       }

       // Verificamos si tenemos el año, ya que es obligatorio
       if (anio == null) {
           return null;
       }

       // Dividimos la búsqueda para encontrar la marca y el modelo
       String[] keywords = busquedaUpper.split(" ");
       String marca = keywords[0]; // asumimos que la primera palabra es la marca
       String modelo = busquedaUpper.replace(marca, "").replace(String.valueOf(anio), "").trim();

       // Realizamos la consulta en el repositorio
       List<Autos> autos = autosRepository.findClosestMatch(modelo, marca, anio);

       // Retornamos el primer auto encontrado
       return autos.stream().findFirst().orElse(null);
   }

}
