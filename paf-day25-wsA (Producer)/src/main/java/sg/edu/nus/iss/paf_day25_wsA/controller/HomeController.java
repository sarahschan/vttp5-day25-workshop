package sg.edu.nus.iss.paf_day25_wsA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {
    
    @GetMapping()
    public String redirect(){
        return "redirect:/order";
    }
    
}
