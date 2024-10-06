package fhlandl.awesome_cloud_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class FetchDataDto {
    private List<FileSystemDto> fileSystem;
}
