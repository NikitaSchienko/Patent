import models.InputDateModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Test
{
    public static void main(String[] args)
    {

    }

    private void read()
    {
        try
        {
            File file = new File("customer.xml");
            JAXBContext context = JAXBContext.newInstance(InputDateModel.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputDateModel inputDateModel = (InputDateModel) unmarshaller.unmarshal(file);
            System.out.println(inputDateModel);
        }
        catch (JAXBException ex)
        {

        }
    }

    private void write()
    {
        InputDateModel inputDateModel = new InputDateModel();

//        inputDateModel.setTest(123.0);
//        inputDateModel.setTest2(111.0);

        try
        {
            File file = new File( "customer.xml");

            JAXBContext context = JAXBContext.newInstance(InputDateModel.class);

            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(inputDateModel, file);
            marshaller.marshal(inputDateModel, System.out);
        }
        catch (JAXBException ex)
        {
            System.err.print("Error XML");
        }
    }
}
