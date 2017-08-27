package nju.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Srf on 2017/3/7
 */

@Controller
@RequestMapping("/")
public class Home {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView homeRedirect() {
        return new ModelAndView("Redirect:/login");
    }

}
