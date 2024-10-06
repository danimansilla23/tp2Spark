package com.mycompany.empresa;

import java.sql.Date;

public class Mp {
    private Integer id_MateriaPrima;
    private String nombre_MP;
    private String unidades;
    private Double stock;
    private Date fecha_vto_prox;

    // Constructor
public Mp(Integer id_MateriaPrima, String nombre_MP, Double stock, String unidades, Date fecha_vto_prox) {
    this.id_MateriaPrima = id_MateriaPrima;
    this.nombre_MP = nombre_MP;
    this.stock = stock;
    this.unidades = unidades;
    this.fecha_vto_prox = fecha_vto_prox;
    }

    // Getters
    public Integer getid_MateriaPrima() {
        return id_MateriaPrima;
    }

    public String getNombre_MP() {
        return nombre_MP;
    }

    public Double getstock() {
        return stock;
    }

    public String getunidades() {
        return unidades;
    }

    public Date getvencimiento() {
        return fecha_vto_prox;
    }

    
    

}
