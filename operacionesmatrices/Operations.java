/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacionesmatrices;

//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import java.util.Random;

/**
 *
 * @author danielalvarado
 */
public class Operations {
    
    private int rows;
    private int cols;
    public int matrix[][];
    
    
    public Operations() {}
    
    
    
    public void createTransM(String _file1,String _newFileName)  {
        ReadExcel file1 = new ReadExcel();   
        try {
            WriteExcel newM = new WriteExcel();
            newM.setOutputFile(_newFileName);
            newM.writeFile();
            
            
            file1.read(_file1 + ".xls");
            int nrowsFile1 = file1.getNumRows();
            int ncolsFile1 = file1.getNumCols();
            
            System.out.println("Numero de filas: " + nrowsFile1 + "\nNumero de columnas"
            + ncolsFile1);
            
            for (int i = 0; i < nrowsFile1; i++) {
                for (int j = 0; j < ncolsFile1; j++) {
                    newM.writeInCell(j, i, file1.getNumberInCell(i, j));
                }
            
            }
            newM.closeFile();
            
        } catch (Exception e) {
            
        
        }
        
    
    }
    
    
    String multiplyM(String _file1,String _file2,String _newFileName) {
        ReadExcel file1 = new ReadExcel();
        ReadExcel file2 = new ReadExcel();
        
        try {
            file1.read(_file1 + ".xls");
            int nrowsFile1 = file1.getNumRows();
            int ncolsFile1 = file1.getNumCols();
            
            file2.read(_file2 + ".xls");
            int nrowsFile2 = file2.getNumRows();
            int ncolsFile2 = file2.getNumCols();
            
            if (nrowsFile2 != ncolsFile1) {
                String error = "No se puede hacer la multiplicacion con estas "
                        + "matrices";
                return error;
            }
            
            int newMatrixRows = ncolsFile1;
            int newMatrixCols = nrowsFile1;
            
            
            int numberOfThreads = nrowsFile1;
            List<Thread> threads = new ArrayList<>(numberOfThreads);
            
            
        
        } catch (Exception e) {
            
            String error = "Ocurrio un error";
            
        }
        
        
        
    return "Ja";
    }
    
    

    String getType(String _file1) {
        ReadExcel file1 = new ReadExcel();
        String msg = "La matriz no pudo ser identificada";
        try {
                file1.read(_file1 + ".xls");

                if (file1.esCuadrada()) {
                    msg = "La matriz es CUADRADA";
                } else if (file1.esEscalar()) {
                    msg = "La matriz es ESCALAR";
                } else if (file1.esIdentidad()) {
                    msg = "La matriz es IDENTIDAD";
                }if (file1.esMatrizColumna()) {
                    msg = "La matriz es MATRIZ COLUMNA";
                } if (file1.esMatrizFila()) {
                    msg = "La matriz es MATRIZ FILA";
                } if (file1.esNula()) {
                    msg = "La matriz es NULA";
                } if (file1.esSoloDiagonal()) {
                    msg = "La matriz es DIAGONAL";
                } if (file1.esTriangularInferior()) {
                    msg = "La matriz es TRIANGULAR INFERIOR";
                } if (file1.esTriangularSuperior()) {
                    msg = "La matriz es TRINGULAR SUPERIOR";
                }


        } catch (Exception e) {
            System.out.println("SUCEDIO UNA EXCEPCION");

        } finally {
            return msg;
        }
    }    
    
    
}
