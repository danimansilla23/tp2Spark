package com.mycompany.empresa;

import java.sql.Date;
import java.util.List;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

public class MpController {
    private static MpDao mpDao = new MpDaoImpl(); 

    public static Route getAll = (Request request, Response response) -> {
        response.type("application/json");
        
        try {
            List<Mp> res = mpDao.getAll();
            return new Gson().toJson(res);
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson("Error controlador: " + e.getMessage());
        }
    };

    public static Route getById = (Request request, Response response) -> {
        response.type("application/json");
        Integer id = Integer.valueOf(request.params(":id")); 
        Mp materiaPrima = mpDao.getById(id);
        if (materiaPrima != null) {
            return new Gson().toJson(materiaPrima);
        } else {
            response.status(404); 
            return new Gson().toJson("Materia prima no encontrada.");
        }
    };

    public static Route add = (Request request, Response response) -> {
        response.type("application/json");
        
        try {
            String nombre_MP = request.queryParams("nombre_MP");
            String unidades = request.queryParams("unidades");
            String stockParam = request.queryParams("stock");
            String fecha_vto_proxParam = request.queryParams("fecha_vto_prox");
            
            if (nombre_MP == null || unidades == null || stockParam == null || fecha_vto_proxParam == null) {
                response.status(400); 
                return new Gson().toJson("Error: Todos los parámetros son requeridos.");
            }

            Double stock = Double.valueOf(stockParam);
            Date fecha_vto_prox = Date.valueOf(fecha_vto_proxParam);

            Mp materiaPrima = new Mp(null, nombre_MP, stock, unidades, fecha_vto_prox); 
            mpDao.add(materiaPrima); 

            response.status(201); 
            return new Gson().toJson("Materia prima agregada exitosamente.");
        } catch (NumberFormatException e) {
            response.status(400); 
            return new Gson().toJson("Error: Stock debe ser un número.");
        } catch (IllegalArgumentException e) {
            response.status(400); 
            return new Gson().toJson("Error: Formato de fecha inválido.");
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson("Error controlador: " + e.getMessage());
        }
    };

    public static Route update = (Request request, Response response) -> {
        response.type("application/json");
        try {
            Integer id = Integer.parseInt(request.params(":id"));
            String nombre_MP = request.queryParams("nombre_MP");
            String unidades = request.queryParams("unidades");
            Double stock = Double.parseDouble(request.queryParams("stock"));
            Date fecha_vto_prox = Date.valueOf(request.queryParams("fecha_vto_prox"));

            Mp updatedMateriaPrima = new Mp(id, nombre_MP, stock, unidades, fecha_vto_prox);
            mpDao.update(updatedMateriaPrima);
            return new Gson().toJson("Materia prima actualizada exitosamente.");
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson("Error controlador: " + e.getMessage());
        }
    };

    public static Route delete = (Request request, Response response) -> {
        response.type("application/json");
        Integer id = Integer.parseInt(request.params(":id"));
        try {
            mpDao.delete(id);
            return new Gson().toJson("Materia prima eliminada exitosamente.");
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson("Error controlador: " + e.getMessage());
        }
    };
}
