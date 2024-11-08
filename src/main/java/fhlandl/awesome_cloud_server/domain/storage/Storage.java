package fhlandl.awesome_cloud_server.domain.storage;

import fhlandl.awesome_cloud_server.domain.BaseTimeEntity;
import fhlandl.awesome_cloud_server.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Storage parent;

    @OneToMany(mappedBy = "parent")
    private List<Storage> children;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public void delete() {
        this.isDeleted = true;
    }
}
