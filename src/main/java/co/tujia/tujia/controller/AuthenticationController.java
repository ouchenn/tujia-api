package co.tujia.tujia.controller;

import co.tujia.tujia.domain.Jwt.JwtRequest;
import co.tujia.tujia.domain.Jwt.JwtResponse;
import co.tujia.tujia.domain.User;
import co.tujia.tujia.service.impl.UserDetailsImpl;
import co.tujia.tujia.service.impl.UserServiceImpl;
import co.tujia.tujia.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserServiceImpl userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    /*
    @EventListener(ApplicationReadyEvent.class)
    public void create() {
        System.out.println("****************************************************** Creating User");
        User user = new User();
        user.setEmail("admin@tujia.co");
        user.setName("Administrator");
        user.setPassword("ADMIN@");
        user.setIsActive(true);
        userService.save(user);
        System.out.println("****************************************************** User created!");
    }
     */

    /**
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody JwtRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetailsImpl userDetails = (UserDetailsImpl) userService.loadUserByUsername(request.getEmail());
        final String token = jwtUtil.generateToken(userDetails);

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setJwtToken(token);
        jwtResponse.setEmail(userDetails.getEmail());
        jwtResponse.setId(userDetails.getId());
        jwtResponse.setName(userDetails.getName());
        jwtResponse.setRole(userDetails.getRole());

        return ResponseEntity.ok(jwtResponse);
    }

    /**
     *
     * @param userRequest
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User userRequest) {

        Optional<User> getUserByEmail = userService.findByEmail(userRequest.getEmail());

        if (getUserByEmail.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else
            try {
                return new ResponseEntity<>(userService.save(userRequest), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
}
