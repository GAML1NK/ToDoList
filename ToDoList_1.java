import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.Scanner;

public class ToDoList_1 {
    public static void main(String[] args) {
       
        ToDoList_2[] calisanlar = {
            new ToDoList_2("Ahmet", "Şoför", "Araba kullanmak"),
            new ToDoList_2("Ayşe", "Mühendis", "Projeleri geliştirmek"),
            new ToDoList_2("Mehmet", "Halkla İlişkiler", "İletişim kurmak"),
            new ToDoList_2("Fatma", "Muhasebe", "Faturaları düzenlemek")
        };

        
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Calisan Bilgileri");

            
            int rowNum = 0;
            for (ToDoList_2 calisan : calisanlar) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(calisan.getIsim());
                row.createCell(1).setCellValue(calisan.getPozisyon());
                row.createCell(2).setCellValue(calisan.getTodoList());
            }

           
            FileOutputStream fileOut = new FileOutputStream("calisanlar.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Excel dosyası oluşturuldu.");

            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Lütfen isim giriniz: ");
            String arananIsim = scanner.nextLine();

            
            FileInputStream fileIn = new FileInputStream("calisanlar.xlsx");
            workbook = WorkbookFactory.create(fileIn);
            sheet = workbook.getSheetAt(0);
            boolean isimBulundu = false;
            System.out.println("\nGünün yapılacakları:");
            for (Row row : sheet) {
                if (row.getCell(0).toString().equalsIgnoreCase(arananIsim)) {
                    isimBulundu = true;
                    System.out.println("İsim: " + row.getCell(0));
                    System.out.println("Pozisyon: " + row.getCell(1));
                    System.out.println("Yapılacaklar: " + row.getCell(2));
                    break;
                }
            }
            if (!isimBulundu) {
                System.out.println("Girdiğiniz isme uygun kayıt bulunamadı.");
            }
            workbook.close();
            fileIn.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ToDoList_2 {
    private String isim;
    private String pozisyon;
    private String todoList;

    public ToDoList_2(String isim, String pozisyon, String todoList) {
        this.isim = isim;
        this.pozisyon = pozisyon;
        this.todoList = todoList;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getPozisyon() {
        return pozisyon;
    }

    public void setPozisyon(String pozisyon) {
        this.pozisyon = pozisyon;
    }

    public String getTodoList() {
        return todoList;
    }

    public void setTodoList(String todoList) {
        this.todoList = todoList;
    }
}
