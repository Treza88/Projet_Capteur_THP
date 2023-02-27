package fr.synergy.projet_THP.services;

import fr.synergy.projet_THP.entities.BackUserEntity;
import fr.synergy.projet_THP.repositories.BackUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BackUserService {

    @Autowired
    private BackUserRepository backUserRepository;

    public Iterable<BackUserEntity> findAll(){return backUserRepository.findAll();}

    public BackUserEntity save(BackUserEntity userAdmin){return backUserRepository.save(userAdmin);}

    public Optional<BackUserEntity> findById(Long id){return backUserRepository.findById(id);}

    public void  deleteById(Long id){backUserRepository.deleteById(id);}
}
