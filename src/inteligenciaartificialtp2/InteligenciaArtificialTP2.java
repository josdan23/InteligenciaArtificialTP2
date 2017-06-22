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
import Services.Servidor;
import View.ViewConfiguracionAlgoritmo;
import View.ViewPrincipal;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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
        
        Servidor servidor = new Servidor();
        servidor.iniciarServer();
        String mensaje = servidor.procesarPeticion();
        
        //servidor.cerrarConexion();
        
        int aguaDisponible = obtenerAguaDisponible(mensaje);
        System.out.println("agua disponible:" + aguaDisponible);
        
        ArrayList<Zona> zonas = obtenerZonas(mensaje);
        
        for (Zona zona : zonas) {
            System.out.println("zona numero: " + zona.getNumeroDeZona());
            System.out.println("agua requerida: " + zona.getCantidadAguaRequerida());
        }
        
        
     
        ArrayList<Integer> solucion = controlador.procesarDatos(zonas, aguaDisponible);
        String respuesta = convertirJson(solucion);
        
       
        servidor.responderCliente(respuesta);
        servidor.cerrarConexion();
        
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
    
    
    private static int obtenerAguaDisponible(String mensaje) {
       
        JsonParser parser = new JsonParser();
        
        JsonElement element = parser.parse(mensaje);
        
        String aguaDisponible = element.getAsJsonObject().get("cantidadDonada").getAsString();
        
        return Integer.parseInt(aguaDisponible);
    }
    
    private static ArrayList<Zona> obtenerZonas(String mensaje) {
        
        String zonasJson = mensaje.substring(29, mensaje.length()-1);
        
        Gson gson = new Gson();
        
        Zona[] zonas = gson.fromJson(zonasJson, Zona[].class);
        
        return convertToArrayList(zonas);
    }
    
    private static ArrayList<Zona> convertToArrayList(Zona[] zonas) {
        
        ArrayList<Zona> zonasArrayList = new ArrayList();
        
        for (Zona zona : zonas) {
            zonasArrayList.add(zona);
        }
        
        return zonasArrayList;
    }

    private static String convertirJson(ArrayList<Integer> solucion) {
        Gson gson = new Gson();
        
        String mensaje = gson.toJson(solucion);
        System.out.println(mensaje);
        return mensaje;
    }
    
}
