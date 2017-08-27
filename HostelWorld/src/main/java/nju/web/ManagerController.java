package nju.web;

import com.sun.javafx.sg.prism.NGShape;
import com.sun.org.apache.xpath.internal.operations.Mod;
import nju.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * Created by Srf on 2017/3/15
 */

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Resource
    private ManagerHotelService managerHotelService;
    @Resource
    private ManagerAccountService managerAccountService;
    @Resource
    private HotelBasicInfoService hotelBasicInfoService;
    @Resource
    private MemberBasicInfoService memberBasicInfoService;
    @Resource
    private HotelBusinessService hotelBusinessService;
    @Resource
    private MemberBusinessService memberBusinessService;

    @RequestMapping(value = "/{id}/approval", method = RequestMethod.GET)
    public ModelAndView getHotelApprovalPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("managerInfo", managerAccountService.getManagerInfo(id));
        model.addAttribute("registerHotelInfos", managerHotelService.getAllRegisterHotelInfo());
        model.addAttribute("editHotelInfos", managerHotelService.getAllEditHotelInfo());
        return new ModelAndView("/manager/approval");
    }

    @RequestMapping(value = "/{id}/settlement", method = RequestMethod.GET)
    public ModelAndView getAccountSettlementPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("managerInfo", managerAccountService.getManagerInfo(id));
        model.addAttribute("accountInfos", managerAccountService.getAllApplyAccountInfo());
        return new ModelAndView("/manager/settlement");
    }

    @RequestMapping(value = "/{id}/hotelinfos", method = RequestMethod.GET)
    public ModelAndView getHotelInfosPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("managerInfo", managerAccountService.getManagerInfo(id));
        model.addAttribute("hotelInfos", managerHotelService.getAllHotelInfo());
        return new ModelAndView("/manager/hotelinfos");
    }

    @RequestMapping(value = "/{id}/memberinfos", method = RequestMethod.GET)
    public ModelAndView getMemberInfosPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("managerInfo", managerAccountService.getManagerInfo(id));
        model.addAttribute("memberInfos", managerAccountService.getAllMemberInfo());
        return new ModelAndView("/manager/memberinfos");
    }

    @RequestMapping(value = "/{id}/hoteldetailinfo/{hid}", method = RequestMethod.GET)
    public ModelAndView getHotelAccomodationInfoPage(Model model, @PathVariable("id") int id,
        @PathVariable("hid") int hid) {
        model.addAttribute("managerInfo", managerAccountService.getManagerInfo(id));
        model.addAttribute("hotelInfo", hotelBasicInfoService.getHotelInfo(hid));
        model.addAttribute("accomodationInfos", hotelBusinessService.getAllHotelAccomodationInfos(hid));
        return new ModelAndView("/manager/hoteldetailinfo");
    }

    @RequestMapping(value = "/{id}/memberdetailinfo/{mid}", method = RequestMethod.GET)
    public ModelAndView getMemberDetailInfoPage(Model model, @PathVariable("id") int id,
        @PathVariable("mid") int mid) {
        model.addAttribute("managerInfo", managerAccountService.getManagerInfo(id));
        model.addAttribute("memberInfo", memberBasicInfoService.getMemberInfo(mid));
        model.addAttribute("appointmentInfos", memberBusinessService.getAllMemberAppointmentInfos(mid));
        model.addAttribute("accountInfos", memberBusinessService.getAllMemberAccountInfos(mid));
        return new ModelAndView("/manager/memberdetailinfo");
    }

    @RequestMapping(value = "/{id}/finance", method = RequestMethod.GET)
    public ModelAndView getHostelWorldFinancePage(Model model, @PathVariable("id") int id) throws ParseException {
        model.addAttribute("managerInfo", managerAccountService.getManagerInfo(id));
        model.addAttribute("accountInfos", managerAccountService.getAllAccountInfo());
        model.addAttribute("hotelNames", managerAccountService.getHotelNames());
        model.addAttribute("hotelAccounts", managerAccountService.getHotelAccounts());
        model.addAttribute("dates", managerAccountService.getDateStrings());
        model.addAttribute("dateAccounts", managerAccountService.getDateAccounts());
        return new ModelAndView("/manager/finance");
    }

    @RequestMapping(value = "/{id}/approval/{hid}/register", method = RequestMethod.POST)
    public ModelAndView approvalHotelRegister(Model model, @PathVariable("id") int id,
        @PathVariable("hid") int hid) {
        String result = managerHotelService.approveHotelRegister(hid);
        return new ModelAndView("redirect:/manager/"+id+"/approval?result="+result);
    }

    @RequestMapping(value = "/{id}/abandon/{hid}/register", method = RequestMethod.POST)
    public ModelAndView abandonHotelRegister(Model model, @PathVariable("id") int id,
        @PathVariable("hid") int hid) {
        String result = managerHotelService.abandonHotelRegister(hid);
        return new ModelAndView("redirect:/manager/"+id+"/approval?result="+result);
    }

    @RequestMapping(value = "/{id}/approval/{hid}/edit", method = RequestMethod.POST)
    public ModelAndView approvalHotelEdit(Model model, @PathVariable("id") int id,
        @PathVariable("hid") int hid) {
        String result = managerHotelService.approveHotelEdit(hid);
        return new ModelAndView("redirect:/manager/"+id+"/approval?result="+result);
    }

    @RequestMapping(value = "/{id}/abandon/{hid}/edit", method = RequestMethod.POST)
    public ModelAndView abandonHotelEdit(Model model, @PathVariable("id") int id,
        @PathVariable("hid") int hid) {
        String result = managerHotelService.abandonHotelEdit(hid);
        return new ModelAndView("redirect:/manager/"+id+"/approval?result="+result);
    }

    @RequestMapping(value = "/{id}/settle/{aid}", method = RequestMethod.POST)
    public ModelAndView settleAccount(@PathVariable("id") int id, @PathVariable("aid") int aid) {
        String result = managerAccountService.settleAccount(aid);
        return new ModelAndView("redirect:/manager/"+id+"/settlement?result="+result);
    }

}
