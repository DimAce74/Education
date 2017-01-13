package ru.itis.services;


import ru.itis.dao.AutoDao;
import ru.itis.models.Auto;

public class AutoService {
    AutoDao autoDao;

    public AutoService(AutoDao autoDao) {
        this.autoDao = autoDao;
    }

    public boolean addAuto (Auto auto) {
        autoDao.save(auto);
        return true;
    }
}
