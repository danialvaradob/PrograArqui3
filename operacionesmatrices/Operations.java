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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import java.util.Random;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
        String msg = "";
        
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
            
            int newMatrixRows = nrowsFile1;
            int newMatrixCols = ncolsFile2;
            
            
             
            // ... create an empty output excel file here...
            /*FileOutputStream fos = new FileOutputStream(_newFileName);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet();
            
            XSSFRow rowRef = null;
            for (int i = 0; i < newMatrixRows; i++) {
                rowRef = sheet.createRow(i);
                if (rowRef != null) {
                    for (int j = 0; j < newMatrixCols; j++) {
                        XSSFCell cellRef = rowRef.createCell(j);
                        cellRef.setCellValue("X");
                    }
                }
            }
            
            System.out.println("LLEGO");
            workbook.write(fos);*/
            
            
            ModifyExcel me = new ModifyExcel(_newFileName, newMatrixRows, newMatrixCols);
            

            
            for (int i = 0; i < newMatrixRows;i++) {
                for (int j = 0; j < newMatrixCols;j++) {
                    (new ThreadMultiply(file1,file2,i,j,me)).start();
                }
            }
            
            msg = "CREADA";
            
        
        } catch (Exception e) {
            
            msg = "ERROR";
            
        } 
        
        return msg;
        
        
    }
    
    

    String getType(String _file1) {
        ReadExcel file1 = new ReadExcel();
        String msg = "La matriz es: ";
        try {
                file1.read(_file1 + ".xls");

                if (file1.esCuadrada()) {
                    msg += "CUADRADA";
                } else {
                    msg += "RECTANGULAR";
                }if (file1.esNula()) {
                    msg += " - NULA";
                    return msg;
                } if (file1.esEscalar()) {
                    msg += " - ESCALAR";
                } else if (file1.esIdentidad()) {
                    msg += " - IDENTIDAD";
                }if(file1.esUnidad()){
                    msg += " - UNIDAD";
                } if (file1.esMatrizColumna()) {
                    msg += " - MATRIZ COLUMNA";
                } if (file1.esMatrizFila()) {
                    msg += " - MATRIZ FILA";
                } if (file1.esSoloDiagonal()) {
                    msg += " - DIAGONAL";
                } if (file1.esTriangularInferior()) {
                    msg += " - TRIANGULAR INFERIOR";
                } if (file1.esTriangularSuperior()) {
                    msg += " - TRINGULAR SUPERIOR";
                }


        } catch (Exception e) {
            System.out.println("SUCEDIO UNA EXCEPCION");

        } finally {
            return msg;
        }
    }


    public void multiplyE(String _file1,String _newFileName,int _number) {
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
                    newM.writeInCell(j, i, file1.getNumberInCell(i, j) * _number);
                }

            }
            newM.closeFile();

        } catch (Exception e) {
            System.out.println("");
        }
    }

}
