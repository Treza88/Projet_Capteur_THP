package fr.synergy.projet_THP.services;

import fr.synergy.projet_THP.entities.RoleEntity;
import fr.synergy.projet_THP.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Iterable<RoleEntity> findAll(){return roleRepository.findAll();}

    public Optional<RoleEntity> findById(Long id){return roleRepository.findById(id);}

    //public List<RoleEntity> findRoleById(Long id){return roleRepository.findRoleById(id);}
public Optional<RoleEntity> findRoleByName(String name){return roleRepository.findByName(name);}
}
