/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmoGenetico;

import Model.Zona;
import java.util.ArrayList;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

/**
 *
 * @author mgordilloe
 */
    public class FuncionObjetivo extends FitnessFunction {
    
    private static ArrayList<Zona> zonasFunction = new ArrayList<Zona>();
    //private static ArrayList<Zona> zonasCromosomas;
    private static int donacionNecesaria;
    
  
    public FuncionObjetivo(ArrayList<Zona> zonas, int donacion){
         
        zonasFunction = zonas;
        donacionNecesaria = donacion;
    }

    public FuncionObjetivo() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    protected double evaluate(IChromosome cromosoma) {
        
        int numeroZonas = cromosoma.size();
        //double fitness = 0;
        
        for (int i = 0; i < zonasFunction.size(); i++) {
            //System.out.printf("Necesidades de la zona %d: %d\n", i, zonasFunction.get(i).getCantidadAguaRequerida());
            //System.out.printf("Valor del cromosoma: %d\n", cromosoma.getGene(i).getAllele());
        }
        
        ArrayList<Zona> zonasCromosomas = new ArrayList<>();
        for(int i=0;i<numeroZonas;i++){
            int cantDonacion = getCantidadDonacion(cromosoma,i);
            Zona zonaCromosoma = new Zona((i+1),cantDonacion);
            
            zonasCromosomas.add(zonaCromosoma);
          //  System.out.println("Numer: " + zonasCromosomas.get(i).getNumero() + " Agua: " + zonasCromosomas.get(i).getCantAgua());
        }
        int diferencia;
        

        //diferencia = zonasCromosomas.get(0).getCantAgua() - zonasFunction.get(0).getCantAgua();
        //int fitness = (500 - diferencia);
         int donacionCromosoma = 0;
        //System.out.println("1: "+ fitness);
        int coeficienteCeros = 0;
        int coeficienteDiferencia = 0;
        for (Zona zc : zonasCromosomas) {
            donacionCromosoma += zc.getCantidadAguaRequerida();
            for (Zona zf : zonasFunction) {
                if (zc.getNumeroDeZona() == zf.getNumeroDeZona()) {
                    
                    diferencia = Math.abs(zc.getCantidadAguaRequerida()- zf.getCantidadAguaRequerida());
                    
                    //System.out.println("gen agua"+ zc.getCantAgua() + "agua pedida" + zf.getCantAgua());
                    //System.out.println(diferencia);
                    coeficienteDiferencia = (500 - diferencia);
                    if(diferencia==0){
                        coeficienteCeros ++;
                    }
                }
                
            }
        }
        
        int fitness = 10*coeficienteCeros + 5 * coeficienteDiferencia;
        //System.out.println("2: "+fitness);
        if(donacionCromosoma == donacionNecesaria){
            fitness = fitness + 1000;
           
        }
        // System.out.println("3: " + fitness);
        //System.out.println(fitness);
        
        return fitness;
    }
    
    
    
    public static String getCadena(IChromosome mejorCromosoma){
        String cadena = "";
        int numeroZonas = mejorCromosoma.size();
        
        cadena += "El mejor fitness es con el valor: " + mejorCromosoma.getFitnessValue();
        int donacionreal=0;
        for(int i=0;i<numeroZonas;i++){
            int cantDonacion = getCantidadDonacion(mejorCromosoma,i);
            cadena += "\n En la zona " + (i+1) + " se necesitaba: "+ zonasFunction.get(i).getCantidadAguaRequerida()+" y se dono: " + cantDonacion; 
           donacionreal = donacionreal + cantDonacion;
        }
         cadena += "\n Con un total de: "+ donacionreal; 
        return cadena;
        
    }
    public static int getCantidadDonacion(IChromosome cromosoma, int numeroZona) {
        Integer CantidadDonZona = (Integer) cromosoma.getGene(numeroZona).getAllele();
        return CantidadDonZona.intValue();
    }
}
