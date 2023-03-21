package sg.edu.nus.iss.pizzaappredo.repo;

import java.io.IOException;
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

        //get json string from redis
    public Optional<Order> get(String orderId) throws IOException{
        String json = template.opsForValue().get(orderId);
        if(null == json|| json.trim().length() <= 0){
            return Optional.empty();
        }

        return Optional.of(Order.createOrderObject(json));
    }
}
