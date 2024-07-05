package cn.edu.scnu.springbootflower.controller;

import cn.edu.scnu.springbootflower.entity.Flower;
import cn.edu.scnu.springbootflower.entity.TbMember;
import cn.edu.scnu.springbootflower.mapper.FlowerMapper;
import cn.edu.scnu.springbootflower.service.FlowerService;
import cn.edu.scnu.springbootflower.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PlayController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private FlowerMapper flowerMapper;
    @Autowired
    private FlowerService flowerService;

    @RequestMapping("/playMoives")
    public String playMoives(@RequestParam(name = "moiveID", defaultValue = "-1")String moiveID
            ,HttpSession session,Model model){
        TbMember member = (TbMember) session.getAttribute("memberLogin");
        Flower flower=flowerMapper.selectById(moiveID);
        System.out.println(moiveID);
        if(member==null) {
            return "forward:/index/toLogin";
        }
        else {
            if (flower.getVip()==1&&member.getIsvip()==0){
                return "forward:/beVip";
            }
        }

        model.addAttribute("moiveID", moiveID);
        return "playVideo";
    }
    @RequestMapping("/beVip")
    public String beVip(){
        return "bevip";
    }

    @RequestMapping("/buyVip") // 注意这里使用@PostMapping而非@RequestMapping
    @ResponseBody
    public String buyVip( String email,HttpSession session){
        memberService.updateVip(email, 1);
        return "/index/logOut";
    }


}
