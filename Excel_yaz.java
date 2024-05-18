import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_yaz {
    public static void main(String[] args) {
       Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Veri Sayfası");
        Object[][] veri = {
            {"İsim", "Soyisim", "Pozisyon"},
            {"Ahmet", "Yılmaz", "Şöför"},
            {"Ayşe", "Kara", "Muhendis"},
            {"Mehmet", "Demir", "Halkla İlişkiler"},
            {"Fatma", "Şahin", "Muhasebe"}
        };

        int satirNumarasi = 0;
        for (Object[] satirVerisi : veri) {
            Row satir = sheet.createRow(satirNumarasi++);
            int sütunNumarasi = 0;
            for (Object veriHücresi : satirVerisi) {
                Cell hücre = satir.createCell(sütunNumarasi++);
                if (veriHücresi instanceof String) {
                    hücre.setCellValue((String) veriHücresi);
                } else if (veriHücresi instanceof Integer) {
                    hücre.setCellValue((Integer) veriHücresi);
                }
            }
        }

        try {
            FileOutputStream file = new FileOutputStream("yeni_veriler.xlsx");
            workbook.write(file);
            file.close();
            workbook.close();
            System.out.println("Excel dosyası oluşturuldu.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}