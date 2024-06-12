package cn.edu.scnu.springbootflower.controller;

import cn.edu.scnu.springbootflower.entity.TbMember;
import cn.edu.scnu.springbootflower.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlayController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/playMoives")
    public String playMoives(@RequestParam(name = "moiveID", defaultValue = "-1")Integer moiveID
            ,HttpSession session,Model model){
        TbMember member = (TbMember) session.getAttribute("memberLogin");
        if(member==null){
            return "forward:/index/toLogin";
        }

        model.addAttribute("moiveID", moiveID);
        return "playVideo";
    }
}
