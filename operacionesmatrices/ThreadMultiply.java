/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacionesmatrices;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.write.WriteException;


/**
 *
 * @author danielalvarado
 */
public class ThreadMultiply extends Thread {
    private ReadExcel matrixA;
    private ReadExcel matrixB;
    private int positionI;
    private int positionJ;
    private WriteExcel newMatrix;
    
    
    public ThreadMultiply(ReadExcel _matrixA, ReadExcel _matrixB, int _positionI, 
            int _positionJ, String _newMatrixName) throws IOException {
        
        matrixA = _matrixA;
        matrixB = _matrixB;
        positionI = _positionI;
        positionJ = _positionJ;
        newMatrix = new WriteExcel();
        newMatrix.setOutputFile(_newMatrixName);
        try {
            newMatrix.writeFile();
        } catch (WriteException ex) {
            Logger.getLogger(ThreadMultiply.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void run() {
        if (matrixA != null && matrixB != null && newMatrix != null) {
            int result = 0;
            for (int index = 0; index < matrixA.getNumCols(); index++) {
                result += matrixA.getNumberInCell(positionI, index) * matrixB.getNumberInCell(index, positionJ);
                System.out.printf("Hilo [%d,%d], values[%d, %d]", positionI, positionJ,matrixA.getNumberInCell(positionI, index), matrixB.getNumberInCell(index, positionJ));
            }
            System.out.println("Hilo " + positionI + ", " +positionJ + " Resultado: "
            + result);
            /*
            newMatrix.writeInCell(positionI, positionJ, result);
            try {
                newMatrix.closeFile();
            } catch (IOException ex) {
                Logger.getLogger(ThreadMultiply.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WriteException ex) {
                Logger.getLogger(ThreadMultiply.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
        }
    }
    
    
    
}
