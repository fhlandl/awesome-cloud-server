package fhlandl.awesome_cloud_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FileSystemDto {
    private Long id;
    private String name;
    private Long parentId;
}
