package org.larissa.buyeasy.formbean;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductFormBean {

    private Integer id;

    @NotEmpty(message = "Code cannot  be empty")
    private String code;

    @NotEmpty(message = "Name cannot  be empty")
    private String name;

    @NotEmpty(message = "Image Url cannot  be empty")
    private String imageUrl;

    @NotEmpty(message = "Description cannot  be empty")
    private String description;


    private Double price;
}
