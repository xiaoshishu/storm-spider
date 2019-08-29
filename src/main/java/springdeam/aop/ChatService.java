package springdeam.aop;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @AuthPermission
    public void chat(int userId){
        System.out.println("我要聊天 来自userId 为:" + userId);
    }


}
