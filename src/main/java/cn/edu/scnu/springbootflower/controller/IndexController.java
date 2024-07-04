package cn.edu.scnu.springbootflower.controller;

import cn.edu.scnu.springbootflower.common.MD5Util;
import cn.edu.scnu.springbootflower.entity.Flower;
import cn.edu.scnu.springbootflower.entity.TbMember;
import cn.edu.scnu.springbootflower.mapper.MemberMapper;
import cn.edu.scnu.springbootflower.service.FlowerService;
import cn.edu.scnu.springbootflower.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private FlowerService flowerService;

    @Autowired
    private MemberService memberService;

//    @RequestMapping("/index")
//    public String index(Model model){
//        List<Flower> flowerList = flowerService.list();
//        model.addAttribute("flowerlist", flowerList);
//        model.addAttribute("fclasses", flowerService.findfclass());
//        return "index";
//    }
    @RequestMapping("/index")
    public String index(@RequestParam(name = "pageNo", defaultValue = "1")Integer pageNo,
                        @RequestParam(name = "fname", defaultValue = "")String fname,
                        @RequestParam(name = "actors", defaultValue = "")String actors,
                        @RequestParam(name = "director", defaultValue = "")String director,
                        @RequestParam(name = "fclass", defaultValue = "")String fclass,
                        @RequestParam(name = "region", defaultValue = "")String region,
                        @RequestParam(name = "orderMethod", defaultValue = "weekly")String orderMethod,
                        Integer minprice,
                        Integer maxprice,
                        Model model) {
        Integer pageSize = 12;
        if (minprice == null) {
            minprice = 0;
        }
        if (maxprice == null){
            maxprice = Integer.MAX_VALUE;
        }
        Map<String, Object> map = flowerService.queryPage(fname,fclass,region,minprice,maxprice,pageNo, pageSize, orderMethod,actors,director);
        int totalRecords = (Integer)map.get("count");
        System.out.println(totalRecords);
        System.out.println("热度查询方式：" + orderMethod);

        List<Flower> flowerlist = (List<Flower>)map.get("recourds");

        Integer pageCount = (totalRecords % pageSize == 0) ? (totalRecords/pageSize) : ((totalRecords/pageSize)+1);
        int startNum = 16 * (pageNo - 1);
        int endNum = Math.min(flowerlist.size(), 16*pageNo);

// Extract the first 16 elements
        flowerlist = flowerlist.subList(startNum, endNum);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("fname",fname);
        model.addAttribute("fclass", fclass);
        model.addAttribute("minprice", minprice);
        model.addAttribute("maxprice", maxprice);
        model.addAttribute("flowerlist", flowerlist);
        model.addAttribute("fclasses", flowerService.findfclass());
        model.addAttribute("regions", flowerService.findregions());
        return "index";
    }

    @RequestMapping("/index/flowerdetail")
    public String flowerdetail(String flowerid, Model model){
        model.addAttribute("flower", flowerService.findFlowerById(flowerid));
        return "flowerdetail";
    }

    @RequestMapping("/index/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/index/doLogin")
    @ResponseBody
    public String doLogin(String email, String password, HttpSession session){
        TbMember member = memberService.login(email, password);
//        member.setPassword("");
        if(member != null){
            session.setAttribute("memberLogin", member);
            return "登录成功!";
        }else{
            return "登陆失败!";
        }
    }

    @RequestMapping("/index/logOut")
    public String logOut(HttpSession session){
        session.removeAttribute("memberLogin");
        return "redirect:/index";
    }
    @RequestMapping("/index/toRegister")
    public String toRegister(){
        return "register";
    }
    @RequestMapping("/index/doRegister")
    @ResponseBody
    public String doRegister(String email, String passw1){
        TbMember member1 = memberService.getById(email);
        if(member1 != null){
            return "该用户已被注册!";
        }
        TbMember member = new TbMember();
        member.setEmail(email);
        member.setPassword(MD5Util.md5(passw1));
        memberService.save(member);
        return "注册成功";
    }
}
