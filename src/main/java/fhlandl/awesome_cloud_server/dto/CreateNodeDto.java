package fhlandl.awesome_cloud_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class CreateNodeDto {
    private String name;
    private String dType;
    private Long parentId;
    private MultipartFile file;
}
