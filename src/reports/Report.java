package reports;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import models.InputDateModel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import pojo.DataRoport;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report
{
    public static void saveReport(DataRoport dataRoport, File file, InputDateModel inputDateModel, String nameChartOne, String nameChartTwo, String nameChartThree, String nameChartFour, String nameChartFive) throws IOException, InvalidFormatException, DocumentException, URISyntaxException {



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
        stringBuilder.append("Параметры моделирования: ");
        stringBuilder.append("\n");
        stringBuilder.append("Диаметр кабеля: "+dataRoport.getdCabel());
        stringBuilder.append("\n");
        stringBuilder.append("Деформация: "+dataRoport.getDeformation());
        stringBuilder.append("\n");
        stringBuilder.append("Напряженность: "+dataRoport.getE());
        stringBuilder.append("\n");
        stringBuilder.append("Диэлектрическая проницаемость: "+dataRoport.getEps());
        stringBuilder.append("\n");
        stringBuilder.append("Ошибка: "+dataRoport.getError());
        stringBuilder.append("\n");
        stringBuilder.append("Сила взаимодействия электретов: "+dataRoport.getF());
        stringBuilder.append("\n");
        stringBuilder.append("Регистрируемая сила:"+dataRoport.getFmax());
        stringBuilder.append("\n");
        stringBuilder.append("Толщина электрода: "+dataRoport.getL1());
        stringBuilder.append("\n");
        stringBuilder.append("Длина релаксора: "+dataRoport.getL2());
        stringBuilder.append("\n");
        stringBuilder.append("Толщина электрета: "+dataRoport.getL3());
        stringBuilder.append("\n");
        stringBuilder.append("Напряжение: "+dataRoport.getU());
        stringBuilder.append("\n");
        stringBuilder.append("Радиус электрета: "+dataRoport.getR());
        stringBuilder.append("\n");
        stringBuilder.append("Плотность дипольного момента: "+dataRoport.getP_());
        stringBuilder.append("\n");
        stringBuilder.append("Диаметр кабеля: "+dataRoport.getP());
        stringBuilder.append("\n");
        stringBuilder.append("Электрострикционный коэффициент:"+dataRoport.getM());
        stringBuilder.append("\n");
        stringBuilder.append("Длина измеряемого участка: "+dataRoport.getLf());
        stringBuilder.append("\n");
        stringBuilder.append("Длина волны: "+dataRoport.getLamda());
        stringBuilder.append("\n");
        stringBuilder.append("Длина отраженной волны: "+dataRoport.getLamdaRes());
        stringBuilder.append("\n");
        stringBuilder.append("Период решетки: "+dataRoport.getPeriod());
        stringBuilder.append("\n");
        stringBuilder.append("Диаметр кабеля: "+dataRoport.getNeff());


        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("График:");
        stringBuilder.append("\n");
        stringBuilder.append("\n");

        Image imgChartOne = Image.getInstance(nameChartOne);
        Image imgChartTwo = Image.getInstance(nameChartTwo);
        Image imgChartThree = Image.getInstance(nameChartThree);
        Image imgChartFour = Image.getInstance(nameChartFour);
        Image imgChartFive = Image.getInstance(nameChartFive);

        Image imgPatent = Image.getInstance("src/views/image/patent.png");

        imgChartOne.scaleToFit(200,200);
        imgChartTwo.scaleToFit(200,200);
        imgChartThree.scaleToFit(200,200);
        imgChartFour.scaleToFit(200,200);
        imgChartFive.scaleToFit(300,300);


        document.add(new Paragraph(stringBuilder.toString(), font));
//        document.add(imgChartOne);
//        document.add(imgChartTwo);
//        document.add(imgChartThree);
//        document.add(imgChartFour);
        document.add(imgChartFive);

        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("Модель датчика:");

        imgPatent.scaleToFit(500,500);


        document.add(imgPatent);
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
