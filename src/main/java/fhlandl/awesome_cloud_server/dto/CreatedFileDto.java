package fhlandl.awesome_cloud_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatedFileDto {

    private String originalFileName;
    private String storedFileName;
    private String storedPath;
}
