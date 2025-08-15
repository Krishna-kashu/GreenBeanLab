package com.mywork.toaster_tune.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class ToasterDTO {

    @NotBlank(message = "Model name cannot be blank")
    @Size(min = 2, max = 50, message = "Model name must be between 2 and 50 characters")
    private String modelName;

    @NotBlank(message = "Brand cannot be blank")
    private String brand;

    @Min(value = 1, message = "Slices should be at least 1")
    private Integer slices;

    @NotNull(message = "Price cannot be null")
    @Min(value = 100, message = "Price should be at least 100")
    private Double price;

    @NotNull(message = "Please upload an image")
    private MultipartFile multipartFile;

}
