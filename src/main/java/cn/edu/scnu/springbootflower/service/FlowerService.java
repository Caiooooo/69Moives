package cn.edu.scnu.springbootflower.service;

import cn.edu.scnu.springbootflower.entity.Flower;
import cn.edu.scnu.springbootflower.entity.MyFlower;
import cn.edu.scnu.springbootflower.mapper.FlowerMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.concurrent.Flow;

@Service
public class FlowerService  extends ServiceImpl<FlowerMapper, Flower> {
    @Autowired
    private FlowerMapper flowerMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    public Map<String, Object> queryPage(String fname, String fclass, String region, Integer minprice, Integer maxprice, Integer pageNo, Integer pageSize, String orderMethod, String actors, String director){
        QueryWrapper<Flower> queryWrapper = new QueryWrapper<>();
        if(!"".equals(fname) && fname !="")
            queryWrapper.like("fname", fname);
        if(!"".equals(actors) && fname !="")
            queryWrapper.like("actors", actors);
        if(!"".equals(director) && fname !="")
            queryWrapper.like("director", director);

        //fclass指代电影类型，region指代地区
        if(!"".equals(fclass) && fclass != "")
            queryWrapper.eq("fclass", fclass);
        if(!"".equals(region) && region != "")
            queryWrapper.eq("region", region);

        queryWrapper.between("yourprice", minprice, maxprice);

        //热度排序
        switch (orderMethod) {
            case "weekly": queryWrapper.orderByDesc("weekly_heat"); break;
            case "monthly": queryWrapper.orderByDesc("monthly_heat"); break;
            case "total": queryWrapper.orderByDesc("total_heat"); break;
            case "positive": queryWrapper.orderByDesc("positive_reviews"); break;
        }

        int count = flowerMapper.selectCount(queryWrapper).intValue();
        Page<Flower> page = new Page<Flower>(pageNo, pageSize);
        Page<Flower> flowerPage = flowerMapper.selectPage(page, queryWrapper);

        Map<String , Object> map = new HashMap<>();
        map.put("count", count);
        map.put("recourds", page.getRecords());
        return map;
    }

    public Flower findFlowerById(String flowerid){
        Object object = redisTemplate.opsForValue().get("flower_"+flowerid);
        if(object!=null){
            return (Flower)object;
        }else{
            Flower flower = flowerMapper.selectById(flowerid);
            redisTemplate.opsForValue().set("flower_" + flowerid, flower);
            return flower;
        }
    }
    public List<String> findregions() { //查找所有region类别
        QueryWrapper<Flower> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("distinct region");
        List<Flower> flowers = flowerMapper.selectList(queryWrapper);
        List<String> regions = new ArrayList<>();
        for (Flower flower: flowers) {
            regions.add(flower.getRegion());
        }
        return regions;
    }
    public List<String> findfclass() {
        QueryWrapper<Flower> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("distinct fclass");
        List<Flower> flowers = flowerMapper.selectList(queryWrapper);
        List<String> fclasses=new ArrayList<>();
        for (Flower flower:flowers){
            fclasses.add(flower.getFclass());
        }
        return fclasses;
    }

    public String uploadfile(MultipartFile file, String dir){
        // 1.判断后缀是否合法
        // 获取图名称，后缀名称
        // 截取后缀substring split (".png" ".jgp")
        String originName = file.getOriginalFilename();
        String extName = originName.substring(originName.lastIndexOf("."));
        if (!(extName.equalsIgnoreCase(".jpg") || extName.equalsIgnoreCase(".png")
                || extName.equalsIgnoreCase(".gif"))) {// 图片后缀不合法
            return "图片后缀不合法!";
        }
        // 判断木马(java的类判断是否是图片属性，也可以引入第三方jar包判断)
        try {
            BufferedImage bufImage = ImageIO.read(file.getInputStream());
            bufImage.getHeight();
            bufImage.getWidth();
        } catch (Exception e) {
            return "该文件不是图片！";
        }
        File _file = new File(dir, originName);
        if (!_file.exists()) {
            _file.mkdirs();
        }
        // 上传文件
        try {
            file.transferTo(_file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
    public String saveFlower(MyFlower myFlower) {
        //1.创建Flower对象
        Flower flower=new Flower();
        //生成UUID
        String flowerid = UUID.randomUUID().toString();
        flower.setFlowerid(flowerid);
        flower.setFname(myFlower.getFname());
        flower.setMyclass(myFlower.getMyclass());
        flower.setFclass(myFlower.getFclass());
        flower.setFclass1(myFlower.getFclass1());
        flower.setCailiao(myFlower.getCailiao());
        flower.setBaozhuang(myFlower.getBaozhuang());
        flower.setHuayu(myFlower.getHuayu());
        flower.setShuoming(myFlower.getShuoming());
        flower.setPrice(myFlower.getPrice());
        flower.setYourprice(myFlower.getYourprice());
        flower.setTejia(myFlower.getTejia());
        flower.setSellednum(0);

        // 2.生成多级路径
        String imgurl = "";
        //   /a/2/e/a/2/3/b/e/f
        for (int i = 0; i < 8; i++) {
            imgurl += "/"+Integer.toHexString(new Random().nextInt(16));
        }
        //获取resources文件夹路径
        String realpath = System.getProperty("user.dir")+"/src/main/resources";

        String dir = realpath + "/static/picture" + imgurl+"/";
        System.out.println(dir);
        //处理pictures
        MultipartFile pictures = myFlower.getPictures();
        String message ="";
        if(!"".equals(pictures.getOriginalFilename())){
            message = uploadfile(pictures,dir);
            if(!"".equals(message)){
                return message;
            }else{
                flower.setPictures(imgurl+"/"+pictures.getOriginalFilename());
            }}
        //处理picturem
        MultipartFile picturem = myFlower.getPicturem();
        if(!"".equals(picturem.getOriginalFilename())){
            message = uploadfile(picturem,dir);
            if(!"".equals(message)){
                return message;
            }else{
                flower.setPicturem(imgurl+"/"+picturem.getOriginalFilename());
            }}
        //处理pictureb
        MultipartFile pictureb = myFlower.getPictureb();
        if(!"".equals(pictureb.getOriginalFilename())){
            message = uploadfile(pictureb,dir);
            if(!"".equals(message)){
                return message;
            }else{
                flower.setPictureb(imgurl+"/"+pictureb.getOriginalFilename());
            }}
        //处理pictured
        MultipartFile pictured = myFlower.getPictured();
        if(!"".equals(pictured.getOriginalFilename())){
            message = uploadfile(pictured,dir);
            if(!"".equals(message)){
                return message;
            }else{
                flower.setPictured(imgurl+"/"+pictured.getOriginalFilename());
                System.out.println("数据库图片路径"+imgurl+pictured.getOriginalFilename());
            }}
        //处理bzpicture
        MultipartFile bzpicture = myFlower.getBzpicture();
        if(!"".equals(bzpicture.getOriginalFilename())){
            message = uploadfile(bzpicture,dir);
            if(!"".equals(message)){
                return message;
            }else{
                flower.setBzpicture(imgurl+"/"+bzpicture.getOriginalFilename());
            }}
        //处理cailiaopicture
        MultipartFile cailiaopicture = myFlower.getCailiaopicture();
        if(!"".equals(cailiaopicture.getOriginalFilename())){
            message = uploadfile(cailiaopicture,dir);
            if(!"".equals(message)){
                return message;
            }else{
                flower.setCailiaopicture(imgurl+"/"+cailiaopicture.getOriginalFilename());
            }}

        flowerMapper.insert(flower);

        return "商品添加成功";
    }

}
