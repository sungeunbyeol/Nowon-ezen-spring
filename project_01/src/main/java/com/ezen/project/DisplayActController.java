package com.ezen.project;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.project.model.ActivityDTO;
import com.ezen.project.model.ProgramDTO;
import com.ezen.project.model.ReviewActDTO;
import com.ezen.project.model.WishListActDTO;
import com.ezen.project.service.ActivityMapper;
import com.ezen.project.service.DisplayActMapper;

@Controller
public class DisplayActController {
	@Autowired
	private DisplayActMapper displayActMapper;
	
	@Autowired
	private ActivityMapper activityMapper;
	
	@RequestMapping(value="/display_activitySearchOk" , method=RequestMethod.POST)
	public String activitySearchOk(HttpServletRequest req, String code, 
			@RequestParam(required = false) String search, String bookdate) {
		
		HttpSession session = req.getSession();
		session.setAttribute("bookdate", bookdate);
		
		List<ActivityDTO> activityList = null;
		
		if(search.contains(", ")){
			String[]array = search.split(", ");
			String a_name = array[0];
			String addr = array[1];
			activityList = displayActMapper.activityDoubleSearchOk(a_name, addr);
		}else if(!code.equals("")) {
			activityList = displayActMapper.activitySearchOkfilterall(code);
		}else{
			activityList = displayActMapper.activitySearchOk(search);
		}
		
		LoginOkBeanUser loginOkBean = (LoginOkBeanUser)session.getAttribute("loginOkBean");
		
		if(loginOkBean != null) {
			int u_num = loginOkBean.getU_num();
			
			Map<String,String> params = new Hashtable<String, String>();
			params.put("u_num", String.valueOf(u_num));
			
			List<WishListActDTO> wishList = displayActMapper.getWishListAct(params);
			
			for(ActivityDTO adto : activityList) {
				for(WishListActDTO wdto : wishList) {
					if(adto.getA_num() == wdto.getA_num()) {
						adto.setWishList(1);
					}
				}
			}
		}
		
		Map<Integer, Integer> countReview = displayActMapper.countReview(activityList);
		Map<Integer, Double> averageReview = displayActMapper.averageReview(activityList);
		
		req.setAttribute("countReview", countReview);
		req.setAttribute("averageReview", averageReview);
		req.setAttribute("activityList", activityList);
		req.setAttribute("search", search);
		return "display/display_activitySearchOk";
	}
	
	@RequestMapping("/display_activitySearchOkfilter")
	public String activitySearchOkfilter(HttpServletRequest req, String search, String code, String bookdate) {
		List<ActivityDTO> activityList = null;
		
		if(search.equals("")) {
			activityList = displayActMapper.activitySearchOkfilterall(code);
		}else {
			activityList = displayActMapper.activitySearchOkfilter(search, code);
		}
		
		HttpSession session = req.getSession();
		LoginOkBeanUser loginOkBean = (LoginOkBeanUser)session.getAttribute("loginOkBean");
		
		if(loginOkBean != null) {
			int u_num = loginOkBean.getU_num();
			
			Map<String,String> params = new Hashtable<String, String>();
			params.put("u_num", String.valueOf(u_num));
			
			List<WishListActDTO> wishList = displayActMapper.getWishListAct(params);
			
			for(ActivityDTO adto : activityList) {
				for(WishListActDTO wdto : wishList) {
					if(adto.getA_num() == wdto.getA_num()) {
						adto.setWishList(1);
					}
				}
			}
		}
		
		req.setAttribute("activityList", activityList);
		session.setAttribute("bookdate", bookdate);
		return "display/display_activitySearchOk";
	}
	
	@RequestMapping("/display_activityContent")
	public String activityContent(HttpServletRequest req, int a_num) {
		HttpSession session = req.getSession();
		
		ActivityDTO adto = activityMapper.getActivity(a_num);
		String[] addr = adto.getA_address().split("@");
		
		List<ProgramDTO> programList = activityMapper.listProgram(a_num);
		
		adto.setA_info(adto.getA_info().replace("@", "\r\n"));
		adto.setA_notice(adto.getA_notice().replace("@", "\r\n"));
		adto.setA_address(adto.getA_address().replace("@", "\r\n"));
		
		for(ProgramDTO pdto : programList) {
			List<Integer> cBookerList = displayActMapper.listCurrentBooker(pdto.getP_num(), (String)session.getAttribute("bookdate"));
			int currentBooker = 0;
			for(int i=0; i<cBookerList.size(); ++i) {
				currentBooker += cBookerList.get(i);
			}
			pdto.setP_currentbooker(currentBooker);
		}
		
		int reviewCount = displayActMapper.getReviewCountByAct(a_num);
		
		double starAverage = displayActMapper.reviewActStar(a_num);
		starAverage = Math.round(starAverage*10)/10.0;//�Ҽ� 1��° �ڸ����� ǥ��
		List<ReviewActDTO> reviewList = displayActMapper.listReviewByAct(a_num);
		
		req.setAttribute("reviewCount", reviewCount);
		req.setAttribute("starAverage", starAverage);
		req.setAttribute("reviewList", reviewList);
		req.setAttribute("showAddr", addr[0]);
		req.setAttribute("adto", adto);
		req.setAttribute("programList", programList);
		
		return "display/display_activityContent";
	}
	
	@RequestMapping("/reviewAct")
	public String reviewAct(HttpServletRequest req, @RequestParam int a_num) {
		List<ReviewActDTO> reviewList = displayActMapper.listReviewByAct(a_num);
		int reviewCount = displayActMapper.getReviewCountByAct(a_num);
		double starAverage = displayActMapper.reviewActStar(a_num);
		starAverage = Math.round(starAverage*10)/10.0;//�Ҽ� 1��° �ڸ����� ǥ��
		
		req.setAttribute("reviewCount", reviewCount);
		req.setAttribute("reviewList", reviewList);
		req.setAttribute("starAverage", starAverage);
		return "board/reviewAct";
	}
	
//	ActivitySeachOk ���������� ���ø���Ʈ üũ/����
	@RequestMapping(value="/wishRelease4")
	public String wishRelease4(HttpServletRequest req, @RequestParam Map<String, String> params) {
		displayActMapper.wishDelete(params);
		List<ActivityDTO> actList = displayActMapper.getActivity(params);
		List<WishListActDTO> wishList = displayActMapper.getWishListAct(params);
		for(ActivityDTO adto : actList) {
			for(WishListActDTO wdto : wishList) {
				if(adto.getA_num() == wdto.getA_num()) {
					adto.setWishList(1);
				}
			}
		}
		req.setAttribute("activityList", actList);
		return "displayact/display_activitySearchOk";
	}
	
	@RequestMapping(value="/wishCheck4")
	public String wishCheck4(HttpServletRequest req, @RequestParam Map<String, String> params) {
		displayActMapper.wishCheck(params);
		List<ActivityDTO> actList = displayActMapper.getActivity(params);
		List<WishListActDTO> wishList = displayActMapper.getWishListAct(params);
		for(ActivityDTO adto : actList) {
			for(WishListActDTO wdto : wishList) {
				if(adto.getA_num() == wdto.getA_num()) {
					adto.setWishList(1);
				}
			}
		}
		req.setAttribute("activityList", actList);
		return "displayact/display_activitySearchOk";
	}
}
