/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javax.swing.JFrame;  
import javax.swing.SwingUtilities;  
import javax.swing.WindowConstants;
  

  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.data.category.DefaultCategoryDataset;  
/**
 * Cette classe est la classe qui permet d'afficher le graphe des notes d'un élève.
 * @author ghias
 */
public class JChartLine extends JFrame {

    DefaultCategoryDataset dataset;
    
    String series1 ="";
    JFreeChart chart;
    /**
     * Creates new form JChartLine
     * @param title 
     */
    public JChartLine(String title) {
        super(title);  
    // Create dataset  
     dataset = createDataset();  
    // Create chart  
    chart = ChartFactory.createLineChart(  
        //"Graphe notes de l'elève / moyennnes de sa classe", // Chart title  
        "Graphe des notes de l'élève",
        "Numéro de la note", // X-Axis Label  
        "Valeur de la note", // Y-Axis Label  
        dataset  
        ); 
  
    ChartPanel panel = new ChartPanel(chart);  
    setContentPane(panel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    /**
     * Permet de définir un dataset en l'instanciant
     * @return 
     */
    private DefaultCategoryDataset createDataset() {  
  
    series1 = "Notes de l'élève";  
    /*String series2 = "Moyenne de sa classe";  */
  
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
  
    //Notes de l'eleve
    /*dataset.addValue(6, series1, "note 1");  
    dataset.addValue(14, series1, "note 2");  
    dataset.addValue(18, series1, "note 3");  
    dataset.addValue(12, series1, "note 4");  
    dataset.addValue(8, series1, "note 5");  
    dataset.addValue(10, series1, "note 6");  
    dataset.addValue(13, series1, "note 7");  
  
    
    //Moyennes de sa classe
    dataset.addValue(10, series2, "note 1");  
    dataset.addValue(11, series2, "note 2");  
    dataset.addValue(11, series2, "note 3");  
    dataset.addValue(8, series2, "note 4");  
    dataset.addValue(9, series2, "note 5");  
    dataset.addValue(7, series2, "note 6");  
    dataset.addValue(12, series2, "note 7");  
            */
  
    return dataset;  
  }  

public DefaultCategoryDataset getDataset (){return dataset;}
public String getSeries1(){return series1;}

   
}
