package ProjectBank;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
public class SaveToExcel {
    public void writeToExcel(List<Account> accountList, String filePath) {
        Workbook workbook = null;
        FileInputStream inputStream = null;
        Sheet sheet = null;
        try {
            inputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(inputStream);
            sheet = workbook.getSheet("DBtransaction");
            if (sheet == null) {
                sheet = workbook.createSheet("DBtransaction");
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Nome");
                headerRow.createCell(1).setCellValue("Data");
                headerRow.createCell(2).setCellValue("Depósito");
                headerRow.createCell(3).setCellValue("Saque");
                headerRow.createCell(4).setCellValue("Saldo");
                //headerRow.createCell(4).setCellValue("Senha");
            }

            int rowNum = sheet.getLastRowNum() + 1;
            for (Account account : accountList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(account.getName());
                // Formata a data com o formato especificado
                SimpleDateFormat formatoData = new SimpleDateFormat("dd-MM-yy");
                Date dataAtual = new Date();
                String dataFormatada = formatoData.format(dataAtual);
                row.createCell(1).setCellValue(dataFormatada);
                row.createCell(2).setCellValue(account.getDeposito());
                row.createCell(3).setCellValue(account.getSaque());
                row.createCell(4).setCellValue(account.getBalance());
                //row.createCell(5).setCellValue(account.getPassword())
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
