/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author mgordilloe
 */


public class Zona {
    
    private int numeroDeZona;
    private int cantidadAguaRequerida;

    public Zona(int numeroDeZona, int cantidadAguaRequerida) {
        this.numeroDeZona = numeroDeZona;
        this.cantidadAguaRequerida = cantidadAguaRequerida;
    }

    public int getNumeroDeZona() {
        return numeroDeZona;
    }

    public void setNumeroDeZona(int numeroDeZona) {
        this.numeroDeZona = numeroDeZona;
    }

    public int getCantidadAguaRequerida() {
        return cantidadAguaRequerida;
    }

    public void setCantidadAguaRequerida(int cantidadAguaRequerida) {
        this.cantidadAguaRequerida = cantidadAguaRequerida;
    }
   
    
}
