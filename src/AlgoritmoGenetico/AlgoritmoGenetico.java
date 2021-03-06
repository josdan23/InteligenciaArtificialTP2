/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmoGenetico;

import Model.Zona;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgap.Chromosome;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.IntegerGene;

/**
 *
 * @author josdan
 */

public class AlgoritmoGenetico {
    
    ConfiguracionDelAlgoritmo conf;
    GraficaDeAlgoritmo grafica = new GraficaDeAlgoritmo();
    
    public AlgoritmoGenetico(ConfiguracionDelAlgoritmo conf){
        this.conf = conf;
    }
    
    public ArrayList<Integer> ejecutar(ArrayList<Zona> zonas, int donaciones) {
        
        //CREA UNA FUNCION FITNESS Y LA ASINGA A LA CONFIGURACION
        FuncionObjetivo funcionFitness = new FuncionObjetivo(zonas, donaciones);
        conf.setFuncionFitness(funcionFitness);
        
        
        //CREA EL CROMOSOMA DE MUESTRA Y LO ASIGNA A LA CONFIGURACION
        Chromosome cromosomaDeMuestra = crearCromosoma (zonas);
            
        //mostrarValoresDeCromosomas(cromosomaDeMuestra);
        
        conf.setCromosomaDeMuestra(cromosomaDeMuestra);        
        
        try {
            
            //CREAR LA POBLACION INICIAL
            Genotype poblacion = Genotype.randomInitialGenotype(conf.getConfiguration());
            
            //TODO Hacer el metodo de corte
            //EVALUAR LA POBLACION
            
            long timeStart = System.currentTimeMillis();
            
            //determina el metodo de corte
            if(conf.getMetodoDeCorte() == 1){
                metodoDeCorteEvolucionaHastaX(poblacion);
            } else
                metodoDeCorteEvolucionarHastaXRepetidos(poblacion);
            
            
            //for (int i = 0; i< conf.getNumeroDeEvoluciones(); i++) {
              //  poblacion.evolve();
            //}
                //TODO: llamar al metodo de Graficar funcion y pasar el valor de fitness
                //System.out.printf("ValorFitness: %f\n", poblacion.getFittestChromosome().getFitnessValue());
            //}
            
            
            //Calcula el tiempo de evolucion del algoritmo para graficar despues
            long timeEnd = System.currentTimeMillis();
            long tiempoTotal = timeEnd - timeStart;
            grafica.setTiempoDuracionEvolucion(tiempoTotal);
            
            //OBTENER EL MEJOR CROMOSOMA DE LA POBLACION
            IChromosome mejorCromosoma = poblacion.getFittestChromosome();
            
            
            //AGREGA EL VALOR DE CADA GEN EN ARRAY QUE REPRESENTA LA SOLUCION
            ArrayList<Integer> solucionesXZona = new ArrayList();
            
            for (int i = 0; i < mejorCromosoma.size(); i++) {
                solucionesXZona.add((Integer) mejorCromosoma.getGene(i).getAllele());
            }
            
            
            return solucionesXZona;
            
        } catch (InvalidConfigurationException ex) {
            Logger.getLogger(AlgoritmoGenetico.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
    //crea un cromosoma a partir de un array de zonas afectadas
    public Chromosome crearCromosoma(ArrayList<Zona> zonas) {
        
        Gene[] genes = crearGenes(zonas);
        
        try {
            Chromosome cromosoma = new Chromosome(conf.getConfiguration(), genes);
            return cromosoma;
        } catch (InvalidConfigurationException ex) {
            Logger.getLogger(AlgoritmoGenetico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    
    //metodo para crear los genes de un cromosomas a partir de una array de zonas.
    private Gene[] crearGenes(ArrayList<Zona> zonas){
        
        Gene[] genes = new Gene[zonas.size()];
        
        try {                
            for (int i = 0; i < genes.length; i++) {
                
                //genes[i] = new IntegerGene(conf.getConfiguration(), 0, zonas.get(i).getCantidadAguaRequerida());
                genes[i] = new IntegerGene(conf.getConfiguration(), 0, zonas.get(i).getCantidadAguaRequerida());
            }
        } catch (InvalidConfigurationException ex) {
            Logger.getLogger(AlgoritmoGenetico.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return genes;
    }
    
    
    
    //METODO DE CORTE 1: EVOLUCIONAR HASTA UN NUMERO DE VECES
    public IChromosome metodoDeCorteEvolucionaHastaX (Genotype poblacion) {
        
        IChromosome cromosomaMasApto = poblacion.getFittestChromosome();
            double cromosomaMasApto1 = poblacion.getFittestChromosome().getFitnessValue();
            double cromosomaMasApto2 = cromosomaMasApto1;
            for (int i = 1; i < conf.getNumeroDeEvoluciones(); i++) {
                poblacion.evolve();
                cromosomaMasApto1 = poblacion.getFittestChromosome().getFitnessValue();
                if (cromosomaMasApto1 == cromosomaMasApto2) {
                    cromosomaMasApto = poblacion.getFittestChromosome();
                }
            }
        
        return poblacion.getFittestChromosome();
    }
    
    //METODO DE CORTE 2: EVOLUCIONAR HASTA QUE LA SOLUCION SE REPITA UN NUMERO DE VECES
    public IChromosome metodoDeCorteEvolucionarHastaXRepetidos (Genotype poblacion) {
        
        poblacion.evolve();
            IChromosome cromosomaMasApto = poblacion.getFittestChromosome();
            int iter = 1;
            double cromosomaMasApto1 = poblacion.getFittestChromosome().getFitnessValue();
            double cromosomaMasApto2 = cromosomaMasApto1;
            int i = 1;
            do {
                poblacion.evolve();
                cromosomaMasApto1 = poblacion.getFittestChromosome().getFitnessValue();
                if (cromosomaMasApto1 == cromosomaMasApto2) {
                    i++;
                } else {
                    if (cromosomaMasApto2 < cromosomaMasApto1) {
                        cromosomaMasApto = poblacion.getFittestChromosome();
                        cromosomaMasApto2 = cromosomaMasApto1;
                        i = 1;
                    } else {
                        cromosomaMasApto2 = cromosomaMasApto1;
                    }
                }
                iter++;
            } while (i != conf.getNumeroDeRepeticiones());

            System.out.println("numeros de iteraciones: " + iter);
      
              
        return poblacion.getFittestChromosome();
    }

    private void mostrarValoresDeCromosomas(Chromosome cromosomaDeMuestra) {
        
        Integer valorDeGen = (Integer) cromosomaDeMuestra.getGene(0).getAllele();
        
        System.out.println(valorDeGen.intValue());
        
    }
    
    
    
}
