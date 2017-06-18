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
                    newM.writeInCell(i, j, file1.getNumberInCell(j, j));
                }
            
            }
            newM.closeFile();
            
        } catch (Exception e) {
        
        }
        
    
    }
    
    
    
    
    
}
