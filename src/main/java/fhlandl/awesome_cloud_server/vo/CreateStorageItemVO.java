package fhlandl.awesome_cloud_server.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Getter
public class CreateStorageItemVO {
    private String name;
    private String dType;
    private Long fileSize;
}
