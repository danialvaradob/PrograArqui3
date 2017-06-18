/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacionesmatrices;

/**
 *
 * @author danielalvarado
 */
import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

    private String inputFile;
    private Sheet sheet;
    private int numberOfRows;
    private int numberOfCols;

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile + ".xls";
    }

    public void read(String inputFile) throws IOException  {
        this.inputFile = inputFile+ ".xls";
        File inputWorkbook = new File(inputFile);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet
            sheet = w.getSheet(0);
            // Loop over first 10 column and lines

            /*
            for (int j = 0; j < sheet.getColumns(); j++) {
                for (int i = 0; i < sheet.getRows(); i++) {
                    Cell cell = sheet.getCell(j, i);
                    CellType type = cell.getType();
                    if (type == CellType.LABEL) {
                        System.out.println("I got a label "
                                + cell.getContents());
                    }

                    if (type == CellType.NUMBER) {
                        System.out.println("I got a number "
                                + cell.getContents());
                    }

                }
            }
            */
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
    
    int getNumberInCell(int r,int c) {
        
        Cell cell = sheet.getCell(r, c);
        int content = Integer.parseInt(cell.getContents());
        return content;
    }
    
    
    public int getNumRows() {
        
        int cont = 0;
        int rowi = 0;
        
        while (true) {
            try{
                Cell cell = sheet.getCell(rowi, 0);
                CellType type = cell.getType();
                cont++;
                rowi++;
            
            }catch (Exception e) {
                break;
            }
        
        }
        
        this.numberOfRows = cont;
        return cont;
    }
    
    public int getNumCols() {
        int cont = 0;
        int coli = 0;

        while (true) {
            try {
                Cell cell = sheet.getCell(0, coli);
                CellType type = cell.getType();
                cont++;
                coli++;
            } catch (Exception e) {
                break;
            }

        }
        this.numberOfCols = coli;
        return cont;
    }


    public boolean esCuadrada(){
        int coli = getNumCols();
        int rowi = getNumRows();

        return coli == rowi;
    }

    public boolean esNula(){
        boolean flag = true;
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumCols(); j++) {
                Cell cell = sheet.getCell(i, j);
                CellType type = cell.getType();
                if (type != CellType.EMPTY || getNumberInCell(i, j)!= 0) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    public boolean esSoloDiagonal(){

        boolean flag = true;
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumCols(); j++) {
                Cell cell = sheet.getCell(i, j);
                CellType type = cell.getType();
                if(i == j){
                    if (type == CellType.EMPTY || getNumberInCell(i, j)== 0) {
                        flag = false;
                    }
                }else{
                    if (type != CellType.EMPTY || getNumberInCell(i, j)!= 0) {
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    public boolean esTriangularSuperior(){
        boolean flag = true;
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumCols(); j++) {
                Cell cell = sheet.getCell(i, j);
                CellType type = cell.getType();
                if(i <= j){
                    if (type == CellType.EMPTY || getNumberInCell(i, j)== 0) {
                        flag = false;
                    }
                }else{
                    if (type != CellType.EMPTY || getNumberInCell(i, j)!= 0) {
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    public boolean esTriangularInferior(){
        boolean flag = true;
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumCols(); j++) {
                Cell cell = sheet.getCell(i, j);
                CellType type = cell.getType();
                if(i >= j){
                    if (type == CellType.EMPTY || getNumberInCell(i, j)== 0) {
                        flag = false;
                    }
                }else{
                    if (type != CellType.EMPTY || getNumberInCell(i, j)!= 0) {
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    public boolean esMatrizFila(){
        return getNumRows() == 1;
    }


    public boolean esMatrizColumna() {
        return getNumCols() == 1;
    }

    public boolean esEscalar(){
        int num = 0;
        boolean firstTime = true;
        boolean flag = true;
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumCols(); j++) {
                Cell cell = sheet.getCell(i, j);
                CellType type = cell.getType();
                if(i == j){
                    if(firstTime){
                        num = getNumberInCell(i,j);
                        firstTime = false;
                    }
                    if ((type == CellType.EMPTY || getNumberInCell(i, j)== 0) || getNumberInCell(i,j) != num) {
                        flag = false;
                    }
                }else{
                    if (type != CellType.EMPTY || getNumberInCell(i, j)!= 0) {
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    public boolean esIdentidad(){
        int num = 1;
        boolean flag = true;
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumCols(); j++) {
                Cell cell = sheet.getCell(i, j);
                CellType type = cell.getType();
                if(i == j){
                    if ((type == CellType.EMPTY || getNumberInCell(i, j)== 0) || getNumberInCell(i,j) != num) {
                        flag = false;
                    }
                }else{
                    if (type != CellType.EMPTY || getNumberInCell(i, j)!= 0) {
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }


    
    /*
    public static void main(String[] args) throws IOException {
        ReadExcel test = new ReadExcel();
        test.setInputFile("c:/temp/lars.xls");
        test.read();
    }
    */
}

