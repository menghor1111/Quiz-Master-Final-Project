package model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private Integer userId;
    private Integer roleId;
    private String roleType;
    private String username;
    private String email;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public User(Integer roleId, String username,String email, String password) {
        this.roleId = roleId;
        this.username = username;
        this.email = email;
        this.password = password;

    }


    public User(Integer userId, String username,String email, String password, Integer roleId,String roleType) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.roleType = roleType;

    }



    public User(Integer userId, String username,String email, Integer roleId,String roleType) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.roleId = roleId;
        this.roleType = roleType;

    }



    public User(String email, String password) {
        this.email = email;
        this.password = password;

    }

}
