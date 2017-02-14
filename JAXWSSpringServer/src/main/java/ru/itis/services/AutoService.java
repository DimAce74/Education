package ru.itis.services;

import ru.itis.models.Auto;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface AutoService {
    @WebMethod
    Auto findAuto (int id);

    @WebMethod
    boolean saveAuto (Auto auto);

    @WebMethod
    boolean deleteAuto (int id);

    @WebMethod
    List<Auto> showAllAuto ();

}
