package com.ustb.evaluation.mod01common.utils.IOData;

import com.ustb.evaluation.mod01common.exception.PromptException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

//任何一个单元格，如何没有输入数据、公式等，则获取此单元格时，得到null

public class ExcelReadWrite {

    public static XSSFWorkbook getWorkbook(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new PromptException("要读取的文件不存在！");
        }

        XSSFWorkbook wb = null;
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            wb = new XSSFWorkbook(in);
            return wb;
        } catch (IOException e) {
            throw new PromptException("读取文件错误，请检查是否是真正的excel文件！");
        }
    }

    public static String readCell(XSSFCell cell) {
        if (cell == null) return "";
        switch (cell.getCellTypeEnum()) {
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return cell.getStringCellValue();
        }
    }

}
