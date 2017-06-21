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
    private static long tiempoTotalEvolucion = 0;
    

    public GraficaDeAlgoritmo() {
        
    }
    
    public void graficar(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        double cantidadEvoluciones = arrayListFitness.size();
        
        Integer duracionDeCadaEvolucion;
        double division;
        division = (tiempoTotalEvolucion/cantidadEvoluciones)*10000;
        
        duracionDeCadaEvolucion = (int) division;
        
        Integer time = duracionDeCadaEvolucion;
        
        for (int i = 1; i<= arrayListFitness.size();i+=10000){
        
        dataset.addValue(arrayListFitness.get(i).intValue(), "fitness", time);
        time = time +  duracionDeCadaEvolucion;
        
        }
        
        
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
    
    
    public void agregarFitness(Integer fitness){
        arrayListFitness.add(fitness);
    }

    public ArrayList<Integer> getArrayListFitness() {
        return arrayListFitness;
    }

   
    
    
    public void setTiempoDuracionEvolucion(long tiempo){
        tiempoTotalEvolucion = tiempo;
    }
    //TODO: agregar metodo para recibir los datos
    
    //TODO: agregar metodo que grafique con los datos

    
}
