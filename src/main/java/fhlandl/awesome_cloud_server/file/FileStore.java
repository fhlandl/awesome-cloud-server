package fhlandl.awesome_cloud_server.file;

import fhlandl.awesome_cloud_server.dto.CreatedFileDto;
import fhlandl.awesome_cloud_server.vo.StoreFileVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String rootPath;

    public CreatedFileDto storeFile(StoreFileVO storeFileVO) throws IOException {
        MultipartFile multipartFile = storeFileVO.getMultipartFile();
        if (multipartFile.isEmpty()) {
            return null;
        }

        validateUserDirectory(storeFileVO.getUserName());

        String originalFilename = multipartFile.getOriginalFilename();
        String storedFileName = createStoredFileName(Objects.requireNonNull(originalFilename), storeFileVO.getUniqueId());
        String storedPath = getFullPath(storedFileName, storeFileVO.getUserName());

        multipartFile.transferTo(new File(storedPath));
        return new CreatedFileDto(originalFilename, storedFileName, storedPath);
    }

    private void validateUserDirectory(String userName) throws IOException {
        Path userPath = Paths.get(rootPath + userName);
        if (!Files.exists(userPath)) {
            Files.createDirectories(userPath);
        }
    }

    private String createStoredFileName(String originalFilename, String uniqueId) {
        int dotPos = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(dotPos + 1);
        return uniqueId + "." + ext;
    }

    private String getFullPath(String fileName, String userName) {
        return rootPath + userName + "/" + fileName;
    }
}
