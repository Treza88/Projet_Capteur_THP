package fr.synergy.projet_THP.services;

import fr.synergy.projet_THP.entities.SensCaveEntity;
import fr.synergy.projet_THP.entities.SensExtEntity;
import fr.synergy.projet_THP.repositories.SensExtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensExtService {

    @Autowired
    private SensExtRepository sensExtRepository;

    public SensExtEntity save(SensExtEntity sensExt){return sensExtRepository.save(sensExt);}

    public Iterable<SensExtEntity> findAll(){return  sensExtRepository.findAll();}

    public Optional<SensExtEntity> findById(Long id){return sensExtRepository.findById(id);}

    public void deleteById(Long id){sensExtRepository.deleteById(id);}
    public Iterable<SensExtEntity> findByIdInvOrderLast24() {
        return sensExtRepository.findByIdInvOrderLast24();
    }


}
