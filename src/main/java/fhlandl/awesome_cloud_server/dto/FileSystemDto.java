package fhlandl.awesome_cloud_server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FileSystemDto {
    private Long id;
    private String name;
    @JsonProperty("dType")
    private String dType;
    private Long parentId;
    private String lastModifiedAt;
    private String userName;
}
