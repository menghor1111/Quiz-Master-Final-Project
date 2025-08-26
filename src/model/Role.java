package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {

    private Integer roleId;
    private String roleType;

    public Role(String roleType){
        this.roleType = roleType;
    }
}
