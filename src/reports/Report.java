package reports;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Report
{
    public static void saveReport(File file)
    {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph tmpParagraph = document.createParagraph();
        XWPFRun tmpRun = tmpParagraph.createRun();
        tmpRun.setText("Датчик");
        tmpRun.setFontSize(18);

        try
        {
            FileOutputStream fos = new FileOutputStream(file);
            document.write(fos);
            fos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
