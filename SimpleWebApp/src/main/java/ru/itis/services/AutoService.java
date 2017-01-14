package ru.itis.services;


import ru.itis.dao.AutoDao;
import ru.itis.models.Auto;

import java.util.List;

public class AutoService {
    AutoDao autoDao;

    public AutoService(AutoDao autoDao) {
        this.autoDao = autoDao;
    }

    public Auto findAuto (int id) {
        return autoDao.find(id);
    }

    public boolean addAuto (Auto auto) {
        autoDao.save(auto);
        return true;
    }

    public boolean updateAuto (Auto auto) {
        autoDao.update(auto);
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
