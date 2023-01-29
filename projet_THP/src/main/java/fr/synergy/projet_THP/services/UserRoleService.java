package fr.synergy.projet_THP.services;

import fr.synergy.projet_THP.entities.UserRoleEntity;
import fr.synergy.projet_THP.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    public Iterable<UserRoleEntity> findAll(){return userRoleRepository.findAll();}
}
