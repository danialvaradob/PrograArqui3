/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacionesmatrices;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author danielalvarado
 */
public class ThreadMultiply extends Thread {
    private ReadExcel matrixA;
    private ReadExcel matrixB;
    private int positionI;
    private int positionJ;
    private ModifyExcel me;
    
    public ThreadMultiply(ReadExcel _matrixA, ReadExcel _matrixB, int _positionI, 
            int _positionJ, ModifyExcel _me) throws IOException {
        
        matrixA = _matrixA;
        matrixB = _matrixB;
        positionI = _positionI;
        positionJ = _positionJ;
        me = _me;
    }
    
    @Override
    public void run() {
        if (matrixA != null && matrixB != null && me != null) {
            int result = 0;
            for (int index = 0; index < matrixA.getNumCols(); index++) {
                result += matrixA.getNumberInCell(positionI, index) * matrixB.getNumberInCell(index, positionJ);
                //System.out.printf("Hilo [%d,%d], values[%d, %d]", positionI, positionJ,matrixA.getNumberInCell(positionI, index), matrixB.getNumberInCell(index, positionJ));
            }
            
            System.out.println("Hilo " + positionI + ", " +positionJ + " Resultado: "
            + result);
  
            try {
                me.write(positionI, positionJ, String.valueOf(result));
            } catch (Exception ex) {
                Logger.getLogger(ThreadMultiply.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    
    
}
