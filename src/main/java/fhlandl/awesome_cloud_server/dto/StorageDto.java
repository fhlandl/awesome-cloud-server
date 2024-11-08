package fhlandl.awesome_cloud_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StorageDto {
    Long id;
    Long parentId;
    String name;
}
