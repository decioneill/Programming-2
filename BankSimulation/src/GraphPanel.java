
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class GraphPanel extends JPanel {
    
    double tMax = 0;  // Max transaction value on graph
    double tMin = 0;  // Min transaction value on graph
    int numtransactions = 0;
    TransactionsHistory graphHistory;
    
    public GraphPanel(TransactionsHistory accHistory) {
        graphHistory = accHistory;
        numtransactions = graphHistory.getNumberOfTransactions();
        for (int i = 0 ; i <numtransactions ; i++) {
            if (graphHistory.getBalanceAmount(i) > tMax) {
                tMax = graphHistory.getBalanceAmount(i);
            } else if (graphHistory.getBalanceAmount(i) < tMin){
                tMin = graphHistory.getBalanceAmount(i);
            }
        }
    }
    
    /**
     * Draws a graph when called.
     * It's length and height are determined by the size of the window it occupies.
     * @param g is a java.awt class Graphics.
    */
    @Override
    public void paint(Graphics g) {        
        
//****************************
// Graph Measurements
//****************************
        
        Graphics2D g2D = (Graphics2D) g;        //g2D added to better handle placement of strings on graph.
        FontMetrics fontManager = g2D.getFontMetrics();     //provides useful information on font for borders, etc.

        
        //Border values
        int padding = 15;   //general spacing to increase whitespace
        int eastMargin = -10 - padding;  
        int westMargin = 5 + fontManager.stringWidth(String.valueOf(tMax)) + padding;  //provides adequate spacing to display amounts
        int northMargin = 0 + padding; 
        int southMargin = 0 - padding-fontManager.getHeight();  //margin widths for panel
        //End Borders
 
        //Graph Sizes
        // Y Coordinates
        int yOrigin = 0 + northMargin;   // Y coordinate of graph
        int yEnd = this.getHeight()+southMargin;     //bottom Y coordinate of graph
        double tRange = tMax - tMin;        //range of transactions
        int gRange = yEnd-yOrigin;      //display height of graph
        float gScale = (float)gRange/(float)tRange;     //scale ratio of transaction
        int g0 = (int) Math.round(yEnd - (Math.abs(tMin)*gScale));      //Y coordinate of £0 point on graph
        int yAxisPoints;    //Variable to determine number of locations on y axis
        if (gRange / fontManager.getHeight() > 10) {
            yAxisPoints = 10;
        }
        else {
            yAxisPoints = gRange / fontManager.getHeight();
        }
        // End Y Coordinates
        
        // X Coordinates
        int numPlotPoints = numtransactions;  //number of transactions to include on graph
        int xOrigin = 0 + westMargin;       //Left x coordinate of graph
        int xEnd = this.getWidth() + eastMargin;    //right x coordinate of graph
        int xWidth = (xEnd - xOrigin)/(numPlotPoints);    //width between transactions on graph
        //End X Coordinates
        //End Graph Sizes
// End Graph Measurements

//****************
//Border Lines 
//****************
        float gRangeF =(float) gRange;   //float value is used in getting an accurate location on graph
        g.setColor(Color.WHITE);    
        g.fillRect(xOrigin,yOrigin,this.getWidth()-xOrigin+eastMargin, gRange);     //Background Color
        g.setColor(Color.BLACK);
        for (int i=0; i<numPlotPoints; i++)   {        //Vertical Inner Lines   
            g.setColor(Color.LIGHT_GRAY);
            g.drawLine((i * xWidth)+xOrigin,    
                    yOrigin,                                   
                    (i * xWidth)+xOrigin,               
                    yEnd);      //background lines
            g.setColor(Color.BLACK);
            g.drawLine((i * xWidth)+xOrigin,    
                    yEnd-5,                                   
                    (i * xWidth)+xOrigin,               
                    yEnd+5);        // Y margin ticks
        }
        
        for (int i=1; i<10; i++)  {      //Horizontal Lines
            g.setColor(Color.LIGHT_GRAY);
            g.drawLine(xOrigin-2,
                    Math.round((gRangeF/10)* i) + yOrigin,
                    xEnd,
                    Math.round((gRangeF/10)* i) + yOrigin);     //background lines
            g.setColor(Color.BLACK);
            g.drawLine(xOrigin-2,       
                    Math.round((gRangeF/10)* i) + yOrigin,     
                    xOrigin+5,
                    Math.round((gRangeF/10)* i) + yOrigin);     // X margin ticks
        }
        g.drawLine(xOrigin, g0, xEnd, g0);      //graph £0 line
        g.drawLine(xOrigin, yOrigin, xEnd, yOrigin);        //TopHorizontal
        g.drawLine(xOrigin, yOrigin, xOrigin, yEnd);        //LeftVertical
        g.drawLine(xEnd, yOrigin, xEnd, yEnd);      //RightVertical
        g.drawLine(xOrigin, yEnd, xEnd, yEnd);      //BottomHorizontal
//End Border Lines

//******************
//Graph Values 
//******************
        for (int i = 0; i<numPlotPoints-1 ;i++)  {
            g.drawLine((i * xWidth) + xOrigin,
                    (int) Math.round((yEnd-((graphHistory.getBalanceAmount(i)*gScale))+(tMin * gScale))),     // of transaction
                    (xWidth*(i+1)) + xOrigin,
                    (int) Math.round(yEnd-((graphHistory.getBalanceAmount(i+1)*gScale))+(tMin * gScale)));        //coordinates of next transaction
            g.drawOval((i * xWidth) + xOrigin - 3, 
                    (int) (Math.round(yEnd-((graphHistory.getBalanceAmount(i)*gScale))+(tMin * gScale))-3),  
                    6,
                    6);      //circles transaction location
        }
        g.drawOval(((numPlotPoints-1) * xWidth) + xOrigin - 3,               
                    (int) Math.round(yEnd-((graphHistory.getBalanceAmount(numtransactions-1)*gScale))+(tMin * gScale))-3, 
                    6,
                    6);     //circles final transaction
//End Graph Values

//**************
//Axis Labels
//**************
        for (int i=0 ; i < graphHistory.getNumberOfTransactions() ; i++) {
            g2D.drawString(String.valueOf(graphHistory.getDate(i)), xOrigin+(xWidth*i)-5, yEnd+fontManager.getHeight()+2);
        }       //Draws the date on X axis
        
        String[] transactString = new String[yAxisPoints+1];
        for (int i = 0; i<=yAxisPoints; i++) {
            transactString[i] = String.format("%.2f", tMin+((tRange/10)*i));        //breaks down values from Max to Min for graph Y Axis
        }
        for (int i=0; i<yAxisPoints+1;i++) {
            g2D.drawString(transactString[i],  xOrigin - fontManager.stringWidth(transactString[i]) - 5,  yEnd - ((gRange/yAxisPoints)*i));      //determines where to draw transaction values on the Y Axis
        }
        g2D.drawString("£0.00", xOrigin+5, g0-1);  //£0.00 label
//End Axis Labels

    }//End Paint Method
}//End GraphPanel Class

                    