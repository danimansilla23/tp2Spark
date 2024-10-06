/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.empresa;

import static spark.Spark.*;


public class Main {
    public static void main(String[] args) {
        try {
            Sql2oDAO.getSql2o();
            System.out.println("Conexión exitosa a la base de datos");
        } catch (Exception e) {
            System.err.println("No se pudo conectar a la base de datos: " +
                    e.getMessage());
        }

        get("/getMateriasPrimas",MpController.getAll);
        get("/getMateriaPrima/:id", MpController.getById);
        delete("/deleteMateriaPrima/:id", MpController.delete);
        put("/updateMateriaPrima/:id", MpController.update);
        post("/addMateriaPrima", MpController.add);

    }
}
