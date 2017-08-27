package nju.web;

import nju.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Srf on 2017/3/15
 */

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Resource
    private RegisterService registerService;

    @RequestMapping(value="/member", method=RequestMethod.GET)
    public String getMemberRegisterPage() {
        return "register/member";
    }

    @RequestMapping(value="/member", method=RequestMethod.POST)
    public ModelAndView registerMember(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        registerService.memberRegister(name,password);
        return new ModelAndView("redirect:/register/member?result=SUCCESS");
    }

    @RequestMapping(value="/hotel", method=RequestMethod.GET)
    public String getHotelRegisterPage() {
        return "register/hotel";
    }

    @RequestMapping(value="/hotel", method=RequestMethod.POST)
    public ModelAndView registerHotel(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String description = request.getParameter("description");
        int singleNum = Integer.parseInt(request.getParameter("singleNum"));
        int doubleNum = Integer.parseInt(request.getParameter("doubleNum"));
        int flatNum = Integer.parseInt(request.getParameter("flatNum"));
        int deluxeNum = Integer.parseInt(request.getParameter("deluxeNum"));
        int roomNumPerFloor = Integer.parseInt(request.getParameter("roomNumPerFloor"));
        String identityNum = registerService.hotelRegister(name,password,address,description).getIdentitynum();
        int roomNumCount = 100;
        roomNumCount = registerService.createHotelRooms(identityNum,"single",singleNum,roomNumCount,roomNumPerFloor);
        roomNumCount = registerService.createHotelRooms(identityNum,"double",doubleNum,roomNumCount,roomNumPerFloor);
        roomNumCount = registerService.createHotelRooms(identityNum,"flat",flatNum,roomNumCount,roomNumPerFloor);
        registerService.createHotelRooms(identityNum,"deluxe",deluxeNum,roomNumCount,roomNumPerFloor);
        registerService.createHotelPlans(identityNum,"single",singleNum);
        registerService.createHotelPlans(identityNum,"double",doubleNum);
        registerService.createHotelPlans(identityNum,"flat",flatNum);
        registerService.createHotelPlans(identityNum,"deluxe",deluxeNum);
        return new ModelAndView("redirect:/register/hotel?result=SUCCESS");
    }

}
