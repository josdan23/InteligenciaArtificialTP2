/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import AlgoritmoGenetico.AlgoritmoGenetico;
import AlgoritmoGenetico.ConfiguracionDelAlgoritmo;
import AlgoritmoGenetico.GraficaDeAlgoritmo;
import Model.Zona;
import java.util.ArrayList;

/**
 *
 * @author josdan
 */
public class ControllerInteligenciaArtificial {
    
    //NECESARIO PARA CONFIGURAR EL ALGORITMO
    private ConfiguracionDelAlgoritmo configuracion;
    
    
    
    
    public ControllerInteligenciaArtificial() { }
    
    public void crearConfiguracion() {
        configuracion = new ConfiguracionDelAlgoritmo();
    }
    
    public ArrayList<Integer> procesarDatos(ArrayList<Zona> zonas, int cantidadDeAguaDonada) {
        AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(configuracion);
        
        return algoritmoGenetico.ejecutar(zonas, cantidadDeAguaDonada);
    }
    
    
    public void graficar() {
        GraficaDeAlgoritmo graficarDeAlgoritmo = new GraficaDeAlgoritmo();
        graficarDeAlgoritmo.graficar();
    }
    
    
    
}
