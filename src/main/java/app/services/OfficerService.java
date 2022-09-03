package app.services;

import app.models.Officer;
import app.repositories.OfficerRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OfficerService implements UserDetailsService {

    private OfficerRepo officerRepo;

    private PasswordEncoder passwordEncoder;

    public OfficerService(OfficerRepo officerRepo, PasswordEncoder passwordEncoder) {
        this.officerRepo = officerRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Officer> findAllOfficer(){
        return (List<Officer>) officerRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Officer> optional = officerRepo.findByUsername(username);
        return optional.orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    @Transactional
    public void saveOfficer(Officer officer) {
        officer.setPassword(passwordEncoder.encode(officer.getPassword()));
        officerRepo.save(officer);
    }

    public boolean isUsernameAlreadyInUse(Officer newofficer) {
        List<Officer> officers = findAllOfficer();

        for (Officer officer : officers) {
            if (newofficer.getUsername().equals(officer.getUsername())) {
                return true;
            }
        }

        return false;
    }
}
