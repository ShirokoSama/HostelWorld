package nju.web;

import com.sun.org.apache.xpath.internal.operations.Mod;
import nju.service.HotelBasicInfoService;
import nju.service.HotelBusinessService;
import nju.util.PayTypeFilter;
import nju.util.RoomTypeFilter;
import nju.vo.HotelInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Srf on 2017/3/14
 */

@Controller
@ResponseBody
@RequestMapping("/hotel")
public class HotelController {

    @Resource
    private HotelBasicInfoService hotelBasicInfoService;
    @Resource
    private HotelBusinessService hotelBusinessService;

    @RequestMapping(value = "/{id}/basicinfo", method = RequestMethod.GET)
    public ModelAndView getHotelBasicInfoPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("hotelInfo", hotelBasicInfoService.getHotelInfo(id));
        return new ModelAndView("/hotel/basicinfo");
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView getHotelEditPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("hotelInfo", hotelBasicInfoService.getHotelInfo(id));
        return new ModelAndView("/hotel/edit");
    }

    @RequestMapping(value = "/{id}/accomodation", method = RequestMethod.GET)
    public ModelAndView getAccomodationManagePage(Model model, @PathVariable("id") int id) {
        model.addAttribute("hotelInfo", hotelBasicInfoService.getHotelInfo(id));
        model.addAttribute("appointmentInfos", hotelBusinessService.getAllHotelOrderingAppointmentInfos(id));
        model.addAttribute("accomodationInfos", hotelBusinessService.getAllHotelStayingAccomodationInfos(id));
        return new ModelAndView("/hotel/accomodation");
    }

    @RequestMapping(value = "/{id}/rooms", method = RequestMethod.GET)
    public ModelAndView getHotelRoomsPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("hotelInfo", hotelBasicInfoService.getHotelInfo(id));
        model.addAttribute("roomInfos", hotelBusinessService.getAllHotelRoomInfos(id));
        return new ModelAndView("/hotel/rooms");
    }

    @RequestMapping(value = "/{id}/detailinfo", method = RequestMethod.GET)
    public ModelAndView getHotelDetailInfos(Model model, @PathVariable("id") int id) {
        model.addAttribute("hotelInfo", hotelBasicInfoService.getHotelInfo(id));
        model.addAttribute("accomodationInfos", hotelBusinessService.getAllHotelAccomodationInfos(id));
        model.addAttribute("appointmentInfos", hotelBusinessService.getAllHotelAppointmentInfos(id));
        model.addAttribute("accountInfos", hotelBusinessService.getAllHotelAccountInfos(id));
        return new ModelAndView("/hotel/hoteldetailinfo");
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public ModelAndView editHotelBasicInfo(Model model, @PathVariable("id") int id,
        HttpServletRequest request) {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String description = request.getParameter("description");
        String result = hotelBasicInfoService.editHotelInfo(id, name, address, description);
        return new ModelAndView("redirect:/hotel/"+id+"/edit?result="+result);
    }

    @RequestMapping(value = "/{id}/password", method = RequestMethod.POST)
    public ModelAndView modifyHotelPassword(Model model, @PathVariable("id") int id,
        HttpServletRequest request) {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String result = hotelBasicInfoService.modifyHotelPassword(id,oldPassword,newPassword);
        return new ModelAndView("redirect:/hotel/"+id+"/edit?result="+result);
    }

    @RequestMapping(value = "/{id}/plan", method = RequestMethod.POST)
    public ModelAndView createNewPlan(Model model, @PathVariable("id") int id,
        HttpServletRequest request) throws ParseException {
        int singleNum = Integer.parseInt(request.getParameter("singlePlanNum"));
        int singlePrice = Integer.parseInt(request.getParameter("singlePrice"));
        int doubleNum = Integer.parseInt(request.getParameter("doublePlanNum"));
        int doublePrice = Integer.parseInt(request.getParameter("doublePrice"));
        int flatNum = Integer.parseInt(request.getParameter("flatPlanNum"));
        int flatPrice = Integer.parseInt(request.getParameter("flatPrice"));
        int deluxeNum = Integer.parseInt(request.getParameter("deluxePlanNum"));
        int deluxePrice = Integer.parseInt(request.getParameter("deluxePrice"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date planDate = simpleDateFormat.parse(request.getParameter("planDate"));
        hotelBasicInfoService.modifyPlan(id,"single",singleNum,singlePrice);
        hotelBasicInfoService.modifyPlan(id,"double",doubleNum,doublePrice);
        hotelBasicInfoService.modifyPlan(id,"flat",flatNum,flatPrice);
        hotelBasicInfoService.modifyPlan(id,"deluxe",deluxeNum,deluxePrice);
        String result = hotelBasicInfoService.modifyPlanDate(id,planDate);
        return new ModelAndView("redirect:/hotel/"+id+"/edit?result="+result);
    }

    @RequestMapping(value = "/{hid}/accomodation", method = RequestMethod.POST)
    public ModelAndView makeNewAccomodation(@PathVariable("hid") int hid, HttpServletRequest request) {
        String roomType = RoomTypeFilter.chToEn(request.getParameter("roomType"));
        int cost = Integer.parseInt(request.getParameter("cost"));
        String memberName = request.getParameter("memberName");
        String payType = PayTypeFilter.chToEn(request.getParameter("payType"));
        String roomNum = request.getParameter("roomNum");
        String result = hotelBusinessService.createNewAccomodation(hid, roomType, cost, memberName, payType, roomNum);
        return new ModelAndView("redirect:/hotel/"+hid+"/accomodation?result="+result);
    }

    @RequestMapping(value = "/{hid}/appointment/{aid}", method = RequestMethod.POST)
    public ModelAndView settleAppointment(Model model, @PathVariable("hid") int hid,
        @PathVariable("aid") int aid, HttpServletRequest request) {
        String result = hotelBusinessService.settleAppointment(aid);
        return new ModelAndView("redirect:/hotel/"+hid+"/accomodation?result="+result);
    }

    @RequestMapping(value = "/{hid}/leave/{aid}", method = RequestMethod.POST)
    public ModelAndView leaveAccomodation(@PathVariable("hid") int hid, @PathVariable("aid") int aid) {
        String result = hotelBusinessService.makeAccomodationLeave(aid);
        return new ModelAndView("redirect:/hotel/"+hid+"/accomodation?result="+result);
    }

}
