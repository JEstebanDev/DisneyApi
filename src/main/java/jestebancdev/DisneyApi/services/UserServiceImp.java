package jestebancdev.DisneyApi.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jestebancdev.DisneyApi.dto.LoginDTO;
import jestebancdev.DisneyApi.model.UserApp;
import jestebancdev.DisneyApi.repository.IUserRepository;
import jestebancdev.DisneyApi.security.CustomAuthenticationFilter;
import jestebancdev.DisneyApi.security.OperationUtil;
import jestebancdev.DisneyApi.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/6/2022
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements IUserService,UserDetailsService {
    @Autowired
    private IUserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User with username: " + username + " not found");
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        } else {
            log.info("User found " + username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().getAuthority()));
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    authorities);
        }
    }
    @Override
    public UserApp create(UserApp user) {
        log.info("Creating user");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String login(HttpServletRequest request, LoginDTO login) {
        UserApp user=userRepository.findByUsername(login.getUsername());
        if (bCryptPasswordEncoder.matches(login.getPassword(),user.getPassword())) {
            Algorithm algorithm = Algorithm.HMAC256(OperationUtil.keyValue().getBytes());
            return JWT.create().withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", List.of(user.getRole().getAuthority())).sign(algorithm);
        }
        return null;
    }

    public UserApp findByUsername(String username) {
        log.info("Searching user by username: " + username);
        return userRepository.findByUsername(username) != null ? userRepository.findByUsername(username) : null;
    }

    @Override
    public Collection<UserApp> read() {
        log.info("Reading users");
        return userRepository.findAll();
    }

    @Override
    public UserApp update(Long idUser, UserApp user) {
        log.info("Updating user");
        UserApp updateUser = userRepository.findById(idUser).get();
        updateUser.setIdUser(idUser);
        updateUser.setUsername(user.getUsername());
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        updateUser.setRole(user.getRole());
        return userRepository.save(updateUser);
    }


    @Override
    public boolean delete(Long idUser) {
        log.info("Deleting user");
        userRepository.deleteById(idUser);
        return true;
    }
}
