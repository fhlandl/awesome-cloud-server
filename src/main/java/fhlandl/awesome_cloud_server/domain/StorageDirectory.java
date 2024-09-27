package fhlandl.awesome_cloud_server.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("D")
@Getter @Setter
public class StorageDirectory extends Storage {

}
