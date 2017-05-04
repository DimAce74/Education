package services;


import com.google.inject.Inject;
import dao.AutoDao;
import models.Auto;

import java.util.List;

public class AutoService {
    @Inject
    private AutoDao autoDao;

    public Auto findAuto (int id) {
        return autoDao.find(id);
    }

    public boolean addAuto (Auto auto) {
        autoDao.save(auto);
        return true;
    }


    public boolean deleteAuto (int id) {
        autoDao.delete(id);
        return true;
    }

    public List<Auto> showAllAuto () {
        return autoDao.findAll();
    }

}
