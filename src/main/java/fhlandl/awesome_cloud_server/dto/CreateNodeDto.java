package fhlandl.awesome_cloud_server.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateNodeDto {
    private String name;
    private String userName;
    private String type;
    private String path;
    private MultipartFile file;
}
