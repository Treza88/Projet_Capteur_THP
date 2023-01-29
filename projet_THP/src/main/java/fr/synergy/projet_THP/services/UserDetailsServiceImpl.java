package fr.synergy.projet_THP.services;

import fr.synergy.projet_THP.apiJoinClass.MyUserDetails;
import fr.synergy.projet_THP.entities.BackUserEntity;
import fr.synergy.projet_THP.entities.UserRoleEntity;
import fr.synergy.projet_THP.repositories.BackUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private BackUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        BackUserEntity user = userRepository.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }
    public Iterable<BackUserEntity> findAll(){return userRepository.findAll();}

}
