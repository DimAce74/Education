package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.Auto;
import ru.itis.repositories.AutoRepository;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.itis.services.AutoService")
@Repository
@Transactional
public class AutoServiceImpl implements AutoService {
    @Autowired
    private AutoRepository autoRepository;

    @Transactional(readOnly = true)
    public Auto findAuto(int id) {
        return autoRepository.findOne(id);
    }

    public boolean saveAuto(Auto auto) {
        autoRepository.save(auto);
        return true;
    }

    public boolean deleteAuto(int id) {
        autoRepository.delete(id);
        return true;
    }

    @Transactional(readOnly = true)
    public List<Auto> showAllAuto() {
        return (List<Auto>) autoRepository.findAll();
    }
}
