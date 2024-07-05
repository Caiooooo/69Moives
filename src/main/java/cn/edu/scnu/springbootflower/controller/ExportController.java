package cn.edu.scnu.springbootflower.controller;

import cn.edu.scnu.springbootflower.entity.Flower;
import cn.edu.scnu.springbootflower.service.FlowerService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Controller
public class ExportController {
    @Autowired
    FlowerService flowerService;

    @RequestMapping("/export")
    public String export(@RequestParam(name = "pageNo", defaultValue = "1")Integer pageNo,
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
        model.addAttribute("fname",fname);
        model.addAttribute("flowerlist", flowerlist);
        return "export";
    }

    @RequestMapping("/export/data")
    @ResponseBody
    public List<Flower> exportData(@RequestParam(name = "pageNo", defaultValue = "1")Integer pageNo,
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
//        flowerlist = flowerlist.subList(startNum, endNum);
        flowerlist.sort(Comparator.comparingInt(Flower::getPrice).reversed());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("fname",fname);
        model.addAttribute("flowerlist", flowerlist);
        return flowerlist;
    }

    @RequestMapping("/export/datarev")
    @ResponseBody
    public List<Flower> exportDataRev(@RequestParam(name = "pageNo", defaultValue = "1")Integer pageNo,
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
//        flowerlist = flowerlist.subList(startNum, endNum);
        flowerlist.sort(Comparator.comparingInt(Flower::getPrice));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("fname",fname);
        model.addAttribute("flowerlist", flowerlist);
        return flowerlist;
    }

    @RequestMapping("/export/download")
    public void export(HttpServletResponse response){
        Map<String, Object> map = flowerService.queryPage("","","",0,Integer.MAX_VALUE,1, 120, "weekly","","");
        List<Flower> flowerlist = (List<Flower>)map.get("recourds");

        try {
            // 基于提供好的模板文件创建一个新的Excel表格对象
            XSSFWorkbook excel = new XSSFWorkbook();
            // 获得Excel文件中的一个Sheet页
            XSSFSheet sheet = excel.createSheet("Sheet1");
            sheet.createRow(0).createCell(0).setCellValue("电影ID");
            sheet.getRow(0).createCell(1).setCellValue("电影名称");
            sheet.getRow(0).createCell(2).setCellValue("热度");
            sheet.getRow(0).createCell(3).setCellValue("电影类别");
            for(int i =1;i<=flowerlist.size();i++){
                Flower flowers = flowerlist.get(i-1);
//                System.out.println(flowers);
                XSSFRow row = sheet.createRow(i);
                row.createCell(0).setCellValue(flowers.getFlowerid());
                row.createCell(1).setCellValue(flowers.getFname());
                row.createCell(2).setCellValue(flowers.getYourprice());
                row.createCell(3).setCellValue(flowers.getFclass());
//                row.createCell(3).setCellValue(flowers.getDirector());
            }
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=\"export.xlsx\"");
            // 通过输出流将文件下载到客户端浏览器中
            ServletOutputStream out = response.getOutputStream();
            excel.write(out);
            // 关闭资源
            out.flush();
            out.close();
            excel.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
