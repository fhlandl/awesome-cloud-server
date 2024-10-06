package fhlandl.awesome_cloud_server.controller;

import fhlandl.awesome_cloud_server.domain.Storage;
import fhlandl.awesome_cloud_server.dto.CreateNodeDto;
import fhlandl.awesome_cloud_server.dto.CreateResultDto;
import fhlandl.awesome_cloud_server.dto.FetchDataDto;
import fhlandl.awesome_cloud_server.dto.FileSystemDto;
import fhlandl.awesome_cloud_server.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
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
        List<Storage> nodes = storageService.findNodes(TEMP_USER_ID);

        List<FileSystemDto> fileSystem = nodes.stream()
                .map((node) -> new FileSystemDto(node.getId(), node.getName(), node.getParentId()))
                .collect(Collectors.toList());
        FetchDataDto fetchDataDto = new FetchDataDto(fileSystem);
        return fetchDataDto;
    }

    @PostMapping("/new")
    public CreateResultDto createItem(@ModelAttribute CreateNodeDto createNodeDto) throws IOException {
        return storageService.saveNode(createNodeDto);
    }
}
