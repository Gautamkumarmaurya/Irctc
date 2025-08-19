package com.subString.irctc.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StationDTO {

    private Long id;

    @NotBlank(message = "station code is required")
    @Size(min = 3 , max = 10 , message = "size must from 3 to 10 character")
    private String code;

    @NotBlank(message = "station name is required")
    private String name;

    @NotBlank(message = "station city is required")
    private String city;

    @NotBlank(message = "station state is required")
    private String state;

}
