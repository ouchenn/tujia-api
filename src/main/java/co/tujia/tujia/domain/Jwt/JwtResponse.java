package co.tujia.tujia.domain.Jwt;

import co.tujia.tujia.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String jwtToken;
    private String id;
    private String name;
    private String email;
    private Role role;
}
