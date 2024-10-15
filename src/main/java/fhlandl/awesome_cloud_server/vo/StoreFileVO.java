package fhlandl.awesome_cloud_server.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Getter
public class StoreFileVO {
    private final MultipartFile multipartFile;
    private final long userId;
    private final String uniqueId;
}
