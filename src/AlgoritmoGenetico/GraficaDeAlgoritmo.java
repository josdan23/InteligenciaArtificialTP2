/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmoGenetico;

import java.util.ArrayList;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author josdan
 */
public class GraficaDeAlgoritmo {
    
    private static final ArrayList<Integer> arrayListFitness = new ArrayList<>();
    private static long tiempoDuracionEvolucion;
    

    public GraficaDeAlgoritmo() {
        
    }
    
    public void graficar(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        int cantidadEvoluciones = arrayListFitness.size();
        Integer duracionDeCadaEvolucion;
        duracionDeCadaEvolucion = (int)(tiempoDuracionEvolucion/cantidadEvoluciones);
        Integer time = duracionDeCadaEvolucion;
        
        for (int fitness: arrayListFitness){
        
        dataset.addValue(fitness, "fitness", time);
        time +=  duracionDeCadaEvolucion;
        
        }
        
        /*
        dataset.addValue(34, "Hospitales", "1933");
        dataset.addValue(23, "Hospitales", "1943");
        dataset.addValue(54, "Hospitales", "1953");
        dataset.addValue(43, "Hospitales", "1963");
        dataset.addValue(23, "Hospitales", "1973");
        */
        JFreeChart lineChart = ChartFactory.createLineChart("Evolucion de la fitness",
                "Tiempo", 
                "Valor de la Fitness", 
                dataset, 
                PlotOrientation.VERTICAL, true, true, false);
        
        ChartPanel panel = new ChartPanel(lineChart);
        
        JFrame ventana = new JFrame("Grafica");
        ventana.setVisible(true);
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        ventana.add(panel);
    }
    
    
    public void agregarFitness(int fitness){
        arrayListFitness.add(fitness);
    }
    
    public void setTiempoDuracionEvolucion(long tiempo){
        tiempoDuracionEvolucion = tiempo;
    }
    //TODO: agregar metodo para recibir los datos
    
    //TODO: agregar metodo que grafique con los datos

    
}
