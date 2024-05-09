package id.ac.ui.cs.rpl.flextime.service;
import id.ac.ui.cs.rpl.flextime.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User registerUser(String username, String email, String password, String matchingPassword);

    User findByUsername(String username);
}
