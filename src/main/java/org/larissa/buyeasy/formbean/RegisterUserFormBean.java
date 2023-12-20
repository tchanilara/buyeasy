package org.larissa.buyeasy.formbean;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class RegisterUserFormBean {
    Integer id;

    @Email(message = "Email must be a valid address")
    @NotEmpty(message = "Email cannot  be empty")
    @Length(max = 45, message = "Email must be less than 45 characters")
    String email;

    @NotEmpty(message = "Password cannot be empty")
    @Length(min = 8, message = "Password must be at least 8 characters")
    @Length(max = 45, message = "Password must be less than 45 characters")
    String password;

    @NotEmpty(message = "Confirm Password cannot be empty")
    @Length(min = 8, message = "Confirm Password must be at least 8 characters")
    @Length(max = 45, message = "Confirm Password must be less than 45 characters")
    String password1;

    @NotEmpty(message = "First Name is required")
    @Length(max = 45, message = "First Name must be less than 45 characters")
    private String firstName;

    @NotEmpty(message = "Last Name is required")
    @Length(max = 45, message = "Last Name must be less than 45 characters")
    private String lastName;

    @Length(max = 45, message = "Phone must be less than 45 characters")
    private String phone;

    @Length(max = 5, message = "Zip code must be less than 5 characters")
    @Length(min = 5, message = "Zip code must be at least 5 characters")
    private String zipCode;
}
