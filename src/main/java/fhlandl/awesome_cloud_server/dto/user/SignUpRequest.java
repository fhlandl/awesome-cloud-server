package fhlandl.awesome_cloud_server.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String loginId;
    private String password;
}
