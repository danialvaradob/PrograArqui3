/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacionesmatrices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author danielalvarado
 */
public class ModifyExcel {
    
    private String fileName;
    
    public ModifyExcel(String _fileName, int _matrixRows, int _matrixCols) throws Exception {
        FileOutputStream fos = new FileOutputStream(fileName = _fileName);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();

        XSSFRow rowRef = null;
        for (int i = 0; i < _matrixRows; i++) {
            rowRef = sheet.createRow(i);
            if (rowRef != null) {
                for (int j = 0; j < _matrixCols; j++) {
                    XSSFCell cellRef = rowRef.createCell(j);
                    cellRef.setCellValue("X");
                }
            }
        }
        
        workbook.write(fos);
        fos.close();
    }
    
    public String getFileName() {
        return fileName;
    }
    
    
    synchronized void write(int _positionI, int _positionJ, String _value) throws Exception {
        // ... read
        FileInputStream fis = new FileInputStream(fileName);
        if (fis != null) {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet worksheet = workbook.getSheetAt(0);
         
            // ... modify
            workbook.getSheetAt(0).getRow(_positionI).getCell(_positionJ).setCellValue(_value);
            
            // ... write
            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();
        }
    }
    
}
