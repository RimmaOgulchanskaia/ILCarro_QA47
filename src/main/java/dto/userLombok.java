package dto;


import lombok.*;

@ToString
@Builder
@Getter
@Setter

public class userLombok {
    private String username;
    private String lastName;
    private String email;
    private String password;



}
