package fhlandl.awesome_cloud_server.controller;

import fhlandl.awesome_cloud_server.dto.CreateItemDto;
import fhlandl.awesome_cloud_server.dto.CreateResultDto;
import fhlandl.awesome_cloud_server.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/storage")
public class StorageController {

    private final StorageService storageService;

    @PostMapping("/new")
    public CreateResultDto createItem(@ModelAttribute CreateItemDto createItemDto) throws IOException {
        return storageService.createItem(createItemDto);
    }
}
