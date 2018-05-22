package reports;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import models.InputDateModel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report
{
    public static void saveReport(File file, InputDateModel inputDateModel, String img) throws IOException, InvalidFormatException, DocumentException, URISyntaxException {



        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));


        document.open();

        BaseFont bf = BaseFont.createFont("C:\\Windows\\Fonts\\ARIAL.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); //подключаем файл шрифта, который поддерживает кириллицу
        Font font = new Font(bf);

        String report = "ОТЧЕТ";

        StringBuilder stringBuilder = new StringBuilder(report);
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("Дата моделирования: "+getCurrentDate());
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("Характеристики датчика:");
        stringBuilder.append("\n");
        stringBuilder.append(" – Диаметр датчика: "+inputDateModel.getD());
        stringBuilder.append("\n");
        stringBuilder.append(" – Диаметр электрета: "+inputDateModel.getdElectret());
        stringBuilder.append("\n");
        stringBuilder.append(" – Толщина электрета: "+inputDateModel.getL1());
        stringBuilder.append("\n");
        stringBuilder.append(" – Толщина электрода: "+inputDateModel.getL3());
        stringBuilder.append("\n");
        stringBuilder.append(" – Расстояние между электретами: "+inputDateModel.getL2());
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("Модель датчика:");
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        Image img1 = Image.getInstance(img);

        img1.scaleToFit(500,500);


        document.add(new Paragraph(stringBuilder.toString(), font));
        document.add(img1);
        document.close();

//        XWPFDocument document = new XWPFDocument();
//        XWPFParagraph tmpParagraph = document.createParagraph();
//        XWPFRun tmpRun = tmpParagraph.createRun();
//
//        tmpRun.setFontSize(18);
//
//        tmpRun.setText("ОТЧЕТ");
//
//        tmpRun.setText("Дата моделирования: "+getCurrentDate());
//        tmpRun.setText("\n");
//        tmpRun.setText("\n");
//        tmpRun.setText("Характеристики датчика:");
//        tmpRun.setText("Диаметр датчика: "+inputDateModel.getDiametrCabel());
//        tmpRun.setText("Диаметр электрета: "+inputDateModel.getDiametrElectret());
//        tmpRun.setText("Толщина электрета: "+inputDateModel.getL2());
//        tmpRun.setText("Толщина электрода: "+inputDateModel.getL3());
//        tmpRun.setText("Расстояние между электретами: "+inputDateModel.getDistanceElectrets());
//        tmpRun.setText("\n");
//        tmpRun.setText("\n");
//        tmpRun.setText("Модель датчика:");
//        tmpRun.setText("\n");
//        InputStream pictureInputStream = new FileInputStream("D:\\patent.png");
//        tmpRun.addPicture(pictureInputStream, XWPFDocument.PICTURE_TYPE_PNG, "patent.png", 300, 150);
//        pictureInputStream.close();
//
//        try
//        {
//            FileOutputStream fos = new FileOutputStream(file);
//            document.write(fos);
//            fos.close();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }

    }

    private static String getCurrentDate()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
