package util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    private static Session session = null;
    private User currenUser;

    public static synchronized Session getSessionInstance(){
        if(session == null)
        {
            session = new Session();
        }
        return session;
    }

    public void clear(){
        this.currenUser = null;
    }
}
