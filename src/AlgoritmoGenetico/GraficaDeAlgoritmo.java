/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmoGenetico;

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

    public GraficaDeAlgoritmo() {
        
    }
    
    public void graficar(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(35, "hostpitales", "1923");
        dataset.addValue(34, "Hospitales", "1933");
        dataset.addValue(23, "Hospitales", "1943");
        dataset.addValue(54, "Hospitales", "1953");
        dataset.addValue(43, "Hospitales", "1963");
        dataset.addValue(23, "Hospitales", "1973");
        
        JFreeChart lineChart = ChartFactory.createLineChart("Grafica Lineal",
                "AÑO", 
                "Numeros de hostpitales", 
                dataset, 
                PlotOrientation.VERTICAL, true, true, false);
        
        ChartPanel panel = new ChartPanel(lineChart);
        
        JFrame ventana = new JFrame("Grafica");
        ventana.setVisible(true);
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        ventana.add(panel);
    }
    
}
