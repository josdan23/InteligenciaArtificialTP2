/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inteligenciaartificialtp2;

import AlgoritmoGenetico.AlgoritmoGenetico;
import AlgoritmoGenetico.ConfiguracionDelAlgoritmo;
import Controller.ControllerInteligenciaArtificial;
import Model.Zona;
import View.ViewConfiguracionAlgoritmo;
import View.ViewPrincipal;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author josdan
 */
public class InteligenciaArtificialTP2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        ControllerInteligenciaArtificial controlador = new ControllerInteligenciaArtificial();
        ViewPrincipal viewPrincipal = new ViewPrincipal();
        viewPrincipal.setVisible(true);
        viewPrincipal.setControlador(controlador);
        
        
        /*ViewConfiguracionAlgoritmo viewConfiguracionAlgoritmo = new ViewConfiguracionAlgoritmo();
        viewConfiguracionAlgoritmo.setVisible(true);*/
        
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de zonas:");
        int cantidadDeZonas = entrada.nextInt();
        
        int aguaNecesariaXZona;
        ArrayList<Zona> zonas = new ArrayList();
        for (int i = 0; i < cantidadDeZonas; i++) {
            System.out.printf("AGUA NECESARIA EN ZONA %d:\n", i+1);
            aguaNecesariaXZona = entrada.nextInt();
            zonas.add(new Zona(i+1, aguaNecesariaXZona));
            
            /**@TODO tengo que hacer algo*/
        }
        
        System.out.println("AGUA DISPONIBLE:");
        int aguaDisponible = entrada.nextInt();
        
        //ControllerInteligenciaArtificial controlador = new ControllerInteligenciaArtificial();
        //controlador.crearConfiguracion();
        ArrayList<Integer> solucion = controlador.procesarDatos(zonas, aguaDisponible);
        
        imprimir(zonas, solucion);
       
    }
    
    public static void imprimir(ArrayList<Zona> zonas, ArrayList<Integer> soluciones) {
         for(int i = 0; i < zonas.size(); i++) {
            System.out.printf("Zona %d:\n\tNecesita: %d\n\tDonar: %d\n",
                    zonas.get(i).getNumeroDeZona(), 
                    zonas.get(i).getCantidadAguaRequerida(),
                    soluciones.get(i).intValue());
        }
    }
    
}
