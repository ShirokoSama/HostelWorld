package nju.web;

import nju.service.MemberBasicInfoService;
import nju.service.MemberBusinessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Srf on 2017/3/14
 */

@Controller
@ResponseBody
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberBasicInfoService memberBasicInfoService;
    @Resource
    private MemberBusinessService memberBusinessService;

    @RequestMapping(value = "/{id}/basicinfo", method = RequestMethod.GET)
    public ModelAndView getMemberBasicInfoPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("memberInfo", memberBasicInfoService.getMemberInfo(id));
        return new ModelAndView("/member/basicinfo");
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView getEditCardInfoPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("memberInfo", memberBasicInfoService.getMemberInfo(id));
        return new ModelAndView("/member/edit");
    }

    @RequestMapping(value = "/{id}/appointment", method = RequestMethod.GET)
    public ModelAndView getMemberAppointmentPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("hotelInfos",memberBusinessService.getAllHotelInfos());
        model.addAttribute("memberInfo", memberBasicInfoService.getMemberInfo(id));
        return new ModelAndView("/member/appointment");
    }

    @RequestMapping(value = "/{id}/myorder", method = RequestMethod.GET)
    public ModelAndView getMemberAllAppointmentPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("memberInfo", memberBasicInfoService.getMemberInfo(id));
        model.addAttribute("appointmentInfos",memberBusinessService.getAllMemberAppointmentInfos(id));
        return new ModelAndView("/member/myorder");
    }

    @RequestMapping(value = "/{id}/myaccount", method = RequestMethod.GET)
    public ModelAndView getMemberDetailInfoPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("memberInfo", memberBasicInfoService.getMemberInfo(id));
        model.addAttribute("appointmentInfos", memberBusinessService.getAllMemberAppointmentInfos(id));
        model.addAttribute("accomodationInfos", memberBusinessService.getAllMemberAccomodationInfos(id));
        model.addAttribute("accountInfos", memberBusinessService.getAllMemberAccountInfos(id));
        return new ModelAndView("/member/myaccount");
    }

    @RequestMapping(value = "/{id}/createcard", method = RequestMethod.POST)
    public ModelAndView createMemberCard(Model model, @PathVariable("id") int id, HttpServletRequest request) {
        String bankcnum = request.getParameter("bankcnum");
        int money = Integer.parseInt(request.getParameter("money"));
        String result = memberBasicInfoService.createCard(id,bankcnum,money);
        model.addAttribute("memberInfo", memberBasicInfoService.getMemberInfo(id));
        if(result.equals("success"))
            return new ModelAndView("redirect:/member/"+id+"/basicinfo");
        else
            return new ModelAndView("redirect:/member/"+id+"/basicinfo?result=no enough money");
    }

    @RequestMapping(value = "/{id}/addmoney", method = RequestMethod.POST)
    public ModelAndView addCardMoney(Model model, @PathVariable("id") int id, HttpServletRequest request) {
        int money = Integer.parseInt(request.getParameter("money"));
        String result = memberBasicInfoService.addMoney(money, id);
        model.addAttribute("memberInfo", memberBasicInfoService.getMemberInfo(id));
        return new ModelAndView("redirect:/member/"+id+"/edit?result="+result);
    }

    @RequestMapping(value = "/{id}/usescore", method = RequestMethod.POST)
    public ModelAndView scoreToMoney(Model model, @PathVariable("id") int id, HttpServletRequest request) {
        int score = Integer.parseInt(request.getParameter("score"));
        String result = memberBasicInfoService.scoreToMoney(id, score);
        model.addAttribute("memberInfo", memberBasicInfoService.getMemberInfo(id));
        return new ModelAndView("redirect:/member/"+id+"/edit?result="+result);
    }

    @RequestMapping(value = "/{id}/bankcnum", method = RequestMethod.POST)
    public ModelAndView changeBankCardNum(Model model, @PathVariable("id") int id, HttpServletRequest request) {
        String bankcnum = request.getParameter("bankcnum");
        memberBasicInfoService.editCardInfo(id, bankcnum);
        model.addAttribute("memberInfo", memberBasicInfoService.getMemberInfo(id));
        return new ModelAndView("redirect:/member/"+id+"/edit?result=success");
    }

    @RequestMapping(value = "/{id}/password", method = RequestMethod.POST)
    public ModelAndView changePassword(Model model, @PathVariable("id") int id, HttpServletRequest request) {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String result = memberBasicInfoService.modifyMemberPassword(id,oldPassword,newPassword);
        model.addAttribute("memberInfo", memberBasicInfoService.getMemberInfo(id));
        return new ModelAndView("redirect:/member/"+id+"/edit?result="+result);
    }

    @RequestMapping(value = "/{mid}/appointment/{hid}", method = RequestMethod.POST)
    public ModelAndView makeAppointment(Model model, @PathVariable("mid") int mid,
        @PathVariable("hid") int hid, HttpServletRequest request) {
        String type = "";
        switch (request.getParameter("type")){
            case "单人间":
                type = "single";
                break;
            case "双人间":
                type = "double";
                break;
            case "套间":
                type = "flat";
                break;
            case "豪华间":
                type = "deluxe";
                break;
        }
        int num = Integer.parseInt(request.getParameter("num"));
        int days = Integer.parseInt(request.getParameter("days"));
        String result = memberBusinessService.makeAppointment(mid, hid, type, num ,days);
        model.addAttribute("memberInfo",memberBasicInfoService.getMemberInfo(mid));
        model.addAttribute("hotelInfos",memberBusinessService.getAllHotelInfos());
        return new ModelAndView("redirect:/member/"+mid+"/appointment?result="+result);
    }

    @RequestMapping(value = "/{mid}/cancel/{aid}", method = RequestMethod.POST)
    public ModelAndView cancelAppointment(Model model, @PathVariable("mid") int mid,
        @PathVariable("aid") int aid, HttpServletRequest request) {
        String result = memberBusinessService.cancelAppointment(aid);
        model.addAttribute("memberInfo", memberBasicInfoService.getMemberInfo(mid));
        model.addAttribute("appointmentInfos",memberBusinessService.getAllMemberAppointmentInfos(mid));
        return new ModelAndView("redirect:/member/"+mid+"/myorder?result="+result);
    }

}
