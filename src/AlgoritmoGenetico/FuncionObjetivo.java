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
    
    private static ArrayList<Zona> zonasFunction = new ArrayList<>();
    private static int donacionNecesaria;
    private GraficaDeAlgoritmo grafica = new GraficaDeAlgoritmo();
    
  
    
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
               
        ArrayList<Zona> zonasCromosomas = new ArrayList<>();
        for(int i=0;i<numeroZonas;i++){
            int cantDonacion = getCantidadDonacion(cromosoma,i);
            Zona zonaCromosoma = new Zona((i+1),cantDonacion);
            zonasCromosomas.add(zonaCromosoma);
          
        }
       
        int donacionCromosoma = 0;
        int coeficienteCeros = 0;
        int coeficienteDiferencia = 0;
        int Diferencia[] = new int[zonasFunction.size() + 1];
              
        
        for (Zona zc : zonasCromosomas) {
            donacionCromosoma += zc.getCantidadAguaRequerida();
            for (Zona zf : zonasFunction) {
                if (zc.getNumeroDeZona() == zf.getNumeroDeZona()) {
                    
                    Diferencia[zf.getNumeroDeZona()] = Math.abs(zc.getCantidadAguaRequerida()- zf.getCantidadAguaRequerida());
                    
                    coeficienteDiferencia = coeficienteDiferencia + Diferencia[zf.getNumeroDeZona()];
                    
                    if(Diferencia[zf.getNumeroDeZona()] == 0){
                        coeficienteCeros ++;
                        
                    }
                }
                
            }
        }
        
        
        int fitness = 2500 -coeficienteDiferencia;
       
        if(donacionCromosoma == donacionNecesaria){
            fitness += 500 *coeficienteCeros;
        }
        
        
        
        grafica.agregarFitness(fitness);
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
