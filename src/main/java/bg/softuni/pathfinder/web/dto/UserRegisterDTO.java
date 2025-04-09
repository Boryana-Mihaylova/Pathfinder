package bg.softuni.pathfinder.web.dto;


import jakarta.validation.constraints.*;


public class UserRegisterDTO {


    @NotBlank
    private String username;

    @NotEmpty
    @Size(min = 5)
    private String fullName;

    @Min(0)
    @Max(90)
    private String age;

    @Email
    private String email;
    @Size(min = 5)
    private String password;
    private String confirmPassword;





    }


