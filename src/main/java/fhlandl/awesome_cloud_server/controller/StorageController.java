package fhlandl.awesome_cloud_server.controller;

import fhlandl.awesome_cloud_server.domain.storage.Storage;
import fhlandl.awesome_cloud_server.domain.storage.StorageFile;
import fhlandl.awesome_cloud_server.dto.CreateNodeDto;
import fhlandl.awesome_cloud_server.dto.CreateResultDto;
import fhlandl.awesome_cloud_server.dto.FetchDataDto;
import fhlandl.awesome_cloud_server.dto.FileSystemDto;
import fhlandl.awesome_cloud_server.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/storage")
public class StorageController {

    private final StorageService storageService;

    @GetMapping("/file-system")
    public FetchDataDto fetchData() {
        // ToDo: apply filtering by user id
        long TEMP_USER_ID = 0L;
        String TEMP_USER_NAME = "user_name";
        List<Storage> nodes = storageService.findNodes(TEMP_USER_ID);

        List<FileSystemDto> fileSystem = nodes.stream()
            .map((node) -> new FileSystemDto(
                node.getId(),
                node.getName(),
                node.getDType(),
                node.getParentId(),
                node.getLastModifiedAt().format(DateTimeFormatter.ofPattern("yyyy.M.d a h:m")),
                TEMP_USER_NAME))
            .collect(Collectors.toList());

        FetchDataDto fetchDataDto = new FetchDataDto(fileSystem);
        return fetchDataDto;
    }

    @PostMapping("/new")
    public CreateResultDto createItem(@ModelAttribute CreateNodeDto createNodeDto) throws IOException {
        // ToDo: Get user id from session
        return storageService.saveNode(0L, createNodeDto);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws MalformedURLException {
        StorageFile fileMeta = (StorageFile) storageService.findNode(id);
        String storedPath = fileMeta.getStoredPath();
        String fileName = fileMeta.getName();

        Path normalizedPath = Paths.get(storedPath).normalize();
        UrlResource resource = new UrlResource(normalizedPath.toUri());

        String encodedFileName = UriUtils.encode(fileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
            .body(resource);
    }
}
