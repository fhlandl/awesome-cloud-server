package fhlandl.awesome_cloud_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateResultDto {
    private String name;
    private String type;
    private String uniqueId;
}
