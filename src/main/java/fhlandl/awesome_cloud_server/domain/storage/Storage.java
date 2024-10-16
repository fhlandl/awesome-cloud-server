package fhlandl.awesome_cloud_server.domain.storage;

import fhlandl.awesome_cloud_server.domain.BaseTimeEntity;
import fhlandl.awesome_cloud_server.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Storage extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(name = "dtype", insertable = false, updatable = false)
    private String dType;

    @Column(name = "unique_id")
    private String uniqueId;

    @Column(name = "parent_id")
    private Long parentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
