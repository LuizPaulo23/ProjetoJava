package ProjectBank;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SaveToExcel {
    public void writeToExcel(List<Account> accountList, String filePath) {
        Workbook workbook = null;
        FileInputStream inputStream = null;
        Sheet sheet = null;
        try {
            inputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(inputStream);
            sheet = workbook.getSheet("Contas");
            if (sheet == null) {
                sheet = workbook.createSheet("Contas");
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Nome");
                headerRow.createCell(1).setCellValue("Saldo");
            }

            int rowNum = sheet.getLastRowNum() + 1;
            for (Account account : accountList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(account.getName());
                row.createCell(1).setCellValue(account.getBalance());
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (workbook != null) {
                    workbook.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
