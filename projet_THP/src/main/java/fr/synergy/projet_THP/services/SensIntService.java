package fr.synergy.projet_THP.services;

import fr.synergy.projet_THP.entities.SensIntEntity;
import fr.synergy.projet_THP.repositories.SensIntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensIntService {
    @Autowired
    private SensIntRepository sensIntRepository;

    public SensIntEntity save(SensIntEntity sensInt){return sensIntRepository.save(sensInt);}

    public  void deleteById(Long id){sensIntRepository.deleteById(id);}

    public Iterable<SensIntEntity>findAll(){return sensIntRepository.findAll();}

    public Optional<SensIntEntity> findById(Long id){return  sensIntRepository.findById(id);}
}
