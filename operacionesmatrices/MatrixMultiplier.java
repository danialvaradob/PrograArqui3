/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacionesmatrices;

import jxl.Sheet;

/**
 *
 * @author danielalvarado
 */

class MatrixMultiplier implements Runnable {

    private Sheet sheet;
    private int position;
    ReadExcel file1;
    ReadExcel file2;
    WriteExcel newFile;
    
    

    public MatrixMultiplier(ReadExcel _file1, ReadExcel _file2,WriteExcel _newFile ,Sheet _sheet,int _pos){
        this.sheet = _sheet;
        this.position = _pos;
        this.file1 = _file1;
        this.file2 = _file2;
        this.newFile = _newFile;
    }

    @Override
    public void run() {
        
       
    }
    
    
}
