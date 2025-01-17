package sg.edu.nus.iss.paf_day25_wsA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.paf_day25_wsA.model.Order;
import sg.edu.nus.iss.paf_day25_wsA.service.RedisService;
import sg.edu.nus.iss.paf_day25_wsA.util.JsonSerializer;
import sg.edu.nus.iss.paf_day25_wsA.util.OrderUtil;

@Controller
@RequestMapping("/order")
public class OrderController {
    
    @Autowired
    RedisService redisService;

    @Autowired
    OrderUtil orderUtil;

    @Autowired
    JsonSerializer jsonSerializer;

    @GetMapping("")
    public String showOrderForm(Model model){

        List<String> customerNames = redisService.retrieveAppNames("registrations");
        model.addAttribute("customerNames", customerNames);

        return "order";
    }


    @PostMapping("")
    public String processOrder(@RequestParam MultiValueMap<String, String> data, Model model){
        
        Order order = orderUtil.createOrder(data);
        String orderJson = jsonSerializer.pojoToJson(order);

        redisService.saveOrderToRedis(orderJson);

        return "success";
    }
}
