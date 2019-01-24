package com.bjornspetprojects.resttraining.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "All details about the user.")
public class User {

    private Long id;
    @ApiModelProperty(notes = "Name should have at least 2 characters")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;
    @ApiModelProperty(notes = "Birthdate should always be in the past")
    @Past(message = "Birthdate should be in the past")
    private LocalDate birthDate;
}
