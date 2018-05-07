package xml;

import models.InputDateModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ProcedureModel
{
    private static void save(String path, InputDateModel inputDateModel)
    {
        try
        {
            File file = new File( path);

            JAXBContext context = JAXBContext.newInstance(InputDateModel.class);

            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(inputDateModel, file);
            marshaller.marshal(inputDateModel, System.out);
        }
        catch (JAXBException ex)
        {
            System.err.print(ex);
        }
    }

    private static InputDateModel load(String path)
    {
        InputDateModel inputDateModel = null;
        try
        {
            File file = new File(path);
            JAXBContext context = JAXBContext.newInstance(InputDateModel.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            inputDateModel = (InputDateModel) unmarshaller.unmarshal(file);
            //System.out.println(inputDateModel);
        }
        catch (JAXBException ex)
        {
            System.err.print(ex);
        }
        return inputDateModel;
    }
}
