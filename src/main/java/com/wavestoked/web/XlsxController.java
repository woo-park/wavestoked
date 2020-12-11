//package com.wavestoked.web;
//
//import com.wavestoked.service.xlsx.XlsxService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.Calendar;
//import java.util.Map;
//
//
//
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class XlsxController {
//    @Autowired
//    XlsxService xlsxService;
//
//    @RequestMapping(value="/convertListToXlsx")
//
//
//    public void convertListToXlsx(@RequestParam Map<Object, Object> paramMap, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
//
//        OutputStream out = null;
//
//
//        /*
//        *
//        Long siteCateSeq = 0L;
//        String getAuth = CookieUtil.getValue(httpServletRequest,"siteinfo");
//        getAuth = getAuth.replace("Bearer%20","");
//        *
//        * */
//
//
//        /*
//        *
//        SiteContext getInfo = jwtDecoder.decodeSiteJwt(getAuth);
//        siteCateSeq = Long.parseLong(getInfo.getSiteCateSeq()); // String to long
//        *
//        * */
//
//
//        Calendar cal = Calendar.getInstance();
//        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
//        String datetime = formatter.format(cal.getTime());
//
//        String filename = "selec7_delivery_"+datetime+".xlsx";
//
//
//        try {
//
//            XSSFWorkbook workBook  = xlsxService.listToInputStream(paramMap);
//
//            httpServletResponse.reset();
//            httpServletResponse.setHeader("Content-Disposition", "attachment;filename="+filename+"");
//            httpServletResponse.setContentType("application/vnd.ms-excel");
//            out = new BufferedOutputStream(httpServletResponse.getOutputStream());
//
//            workBook.write(out);
//            out.flush();
//
//        } catch (Exception e) {
//
//        } finally {
//            if(out != null) out.close();
//        }
//    }
//}
