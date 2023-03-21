package sg.edu.nus.iss.pizzaappredo.repo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.pizzaappredo.model.Order;

@Repository
public class PizzaRepository {
    
    //autowired in a bean.
    @Autowired @Qualifier("order")

    private RedisTemplate<String, String> template;

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private Optional<Integer> redisPort;

    @Value("${spring.data.redis.username}")
    private String redisUsername;

    @Value("${spring.data.redis.password}")
    private String redisPassword;


    public void save(Order order){
        this.template.opsForValue().set(order.getOrderId(), order.toJSON().toString());
    }
}
