package nju.web;

import nju.service.LoginService;
import nju.util.VerifyResult;
import nju.vo.LoginVerifyResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Srf on 2017/3/15
 */

@Controller
@RequestMapping("/")
public class LoginController {

    @Resource
    private LoginService loginService;

    @RequestMapping(value = "/login", method= RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(value="/login/verify", method = RequestMethod.POST)
    public ModelAndView verifyLogin(HttpServletRequest request) throws IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String role = request.getParameter("roles");
        LoginVerifyResult result;
        switch (role){
            case "Member":
                result = loginService.memberVerify(name,password);
                break;
            case "Hotel":
                result = loginService.hotelVerify(name,password);
                break;
            case "Manager":
                result = loginService.managerVerify(name,password);
                break;
            default:
                result = loginService.memberVerify(name,password);
        }
        if(result.getVerifyResult()== VerifyResult.SUCCESS) {
            HttpSession session = request.getSession(true);
            session.setAttribute("role",role);
            session.setAttribute("id",result.getId());
            switch (role) {
                case "Member":
                    return new ModelAndView("redirect:/member/" + result.getId() + "/basicinfo");
                case "Hotel":
                    return new ModelAndView("redirect:/hotel/" + result.getId() + "/basicinfo");
                case "Manager":
                    return new ModelAndView("redirect:/manager/" + result.getId() + "/approval");
                default:
                    return new ModelAndView("redirect:/member/" + result.getId() + "/basicInfo");
            }
        }
        else
            return new ModelAndView("redirect:/login?error="+result.getVerifyResult().toString());
    }

}
