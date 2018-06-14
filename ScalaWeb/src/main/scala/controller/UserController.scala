package controller

import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

//@RequestMapping("cat")
@ComponentScan
@Controller
class UserController {

    @RequestMapping("/home")
    def home():String = {
        "home"
    }



}
