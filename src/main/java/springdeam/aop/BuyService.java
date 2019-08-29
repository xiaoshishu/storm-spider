package springdeam.aop;


import org.springframework.stereotype.Service;

@Service
public class BuyService {

    @AuthPermission
    public void buyItem(int userId){
        System.out.println("我要买东西 来自 userId 为 :" + userId);
    }


}
