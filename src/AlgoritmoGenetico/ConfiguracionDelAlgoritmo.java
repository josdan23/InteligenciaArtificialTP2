/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmoGenetico;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.MutationOperator;

/**
 *
 * @author josdan
 
 * 
 * */

/*
Esta clase se usa para configurar el objeto Configuration necesario 
para el algoritmo genentico
*/
public class ConfiguracionDelAlgoritmo {
    
    private int metodoDeCorte;
    
    private int tamanioDePoblacion;
    private int numeroDeEvoluciones;
    private int numeroDeRepeticiones;
    
    private Configuration conf; //configuration necesario para el algoritmo
    //private MutationOperator mutacion; //operador de mutacion;
    
    
    //Cuando se crea un objeto Cofiguracion, 
    //se va a instanciar el atributo conf.
    //y se instancia el Operador de Mutacion.
    public ConfiguracionDelAlgoritmo () {
        this(100, 50, 1); //llamar al constructor con paramentros 
        
    }
    
    
    
    public ConfiguracionDelAlgoritmo(int tamanioDePoblacion, int porcentajeDeMutacion, int metodoDeCorte) {
        conf = new DefaultConfiguration();
        setTamanioPoblacion(tamanioDePoblacion);
        setMutacion(porcentajeDeMutacion);
        setMetodoDeCorte(metodoDeCorte);
    }

    //METODO PARA ESTBLECER LA FITNES EN CONFIGURATION
    public void setFuncionFitness(FitnessFunction f) {
        try {
            conf.setFitnessFunction(f);
        } catch (InvalidConfigurationException ex) {
            Logger.getLogger(ConfiguracionDelAlgoritmo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //ESTABLECER EL CROMOSOMA DE MUESTRA
    public void setCromosomaDeMuestra (Chromosome chromosome) {
        try {
            conf.setSampleChromosome(chromosome);
        } catch (InvalidConfigurationException ex) {
            Logger.getLogger(ConfiguracionDelAlgoritmo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    
    //ESTABLECER EL NUMERO DE EVOLUCIONES
    public void setNumeroDeEvoluciones(int numeroDeEvoluciones) {
        this.numeroDeEvoluciones = numeroDeEvoluciones;
    }

    //OBTENER EL NUMERO DE EVOLUCIONES
    public int getNumeroDeEvoluciones() {
        return numeroDeEvoluciones;
    }
    
    //ESTABLECER EL TAMANIO DE LA POBLACION
    public void setTamanioPoblacion(int tamanio) {
        tamanioDePoblacion = tamanio;
        
        try {
            conf.setPopulationSize(tamanioDePoblacion);
        } catch (InvalidConfigurationException ex) {
            Logger.getLogger(ConfiguracionDelAlgoritmo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //establece el porcentaje de mutacion y agrega a la configuracion el operator
    public void setMutacion(int porcentaje) {
        
         try {
            MutationOperator mutacion = new MutationOperator(conf);
            
            mutacion.setMutationRate(porcentaje);
            
            conf.addGeneticOperator(mutacion);
        } catch (InvalidConfigurationException ex) {
            Logger.getLogger(ConfiguracionDelAlgoritmo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //METODO PARA OBTENER EL CONFIGURATION
    public Configuration getConfiguration(){
        return conf;
    }

    public int getMetodoDeCorte() {
        return metodoDeCorte;
    }

    public void setMetodoDeCorte(int metodoDeCorte) {
        this.metodoDeCorte = metodoDeCorte;
    }

    public int getNumeroDeRepeticiones() {
        return numeroDeRepeticiones;
    }

    public void setNumeroDeRepeticiones(int numeroDeRepeticiones) {
        this.numeroDeRepeticiones = numeroDeRepeticiones;
    }
    
    
}
