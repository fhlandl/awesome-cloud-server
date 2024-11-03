package fhlandl.awesome_cloud_server.domain.user;

import fhlandl.awesome_cloud_server.domain.storage.Storage;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "login_id", nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Storage> storages = new ArrayList<>();

    @Column(nullable = false)
    private String name;

//    private String email;
//
//    @Column(name = "phone_number")
//    private String phoneNumber;
//
//    private Date birthday;

    @Builder
    public User(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }
}
