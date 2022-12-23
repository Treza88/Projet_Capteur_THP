package fr.synergy.projet_THP.Services;

import fr.synergy.projet_THP.entities.SensCaveEntity;
import fr.synergy.projet_THP.repositories.SenCaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensCaveService {

    @Autowired
    private  SenCaveRepository sensCaveRepository;

    public SensCaveEntity Save(SensCaveEntity sensCave){return sensCaveRepository.save(sensCave);}

    public Iterable<SensCaveEntity> findAll(){return sensCaveRepository.findAll();}


    public Optional<SensCaveEntity> findById(Long id) {return sensCaveRepository.findById(id);
    }
    public Iterable<SensCaveEntity> findByIdInvOrderLast24() {
        return sensCaveRepository.findByIdInvOrderLast24();
    }

}
