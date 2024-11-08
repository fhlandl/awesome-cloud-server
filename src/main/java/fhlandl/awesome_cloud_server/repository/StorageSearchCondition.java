package fhlandl.awesome_cloud_server.repository;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StorageSearchCondition {

    private Long userId;
    private Boolean includeDeleted;
}
