//package com.wavestoked.web;
//
//import com.wavestoked.service.productReview.ProductReviewService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/review")
//public class ProductReviewRestController {
//    @Autowired
//    ProductReviewService productReviewService;
//
//
//    @RequestMapping(value = "/getList",method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String,Object> getList(@RequestParam Map<Object, Object> paramMap, HttpServletRequest httpServletRequest) {
//
//        HashMap<String,Object> resultFinal = new HashMap<String, Object>();
//
//
//        String siteInfo = httpServletRequest.getHeader("authorization");
//        siteInfo = siteInfo.replace("Bearer ","");
//
//        //page cpage
//        int pageNum=1;
//        int boardItem=12;
//
//        String boardSeq="0";
//        //siteCateSeq
//        long siteCateSeq = 0L;
//
//        try {
//            SiteContext getInfo = jwtDecoder.decodeSiteJwt(siteInfo);
//            siteCateSeq = Long.parseLong(getInfo.getSiteCateSeq()); // String to long
//
//            pageNum = Integer.parseInt(paramMap.get("cpage").toString());
//            boardSeq =paramMap.get("boardSeq").toString();
//            boardItem = Integer.parseInt(paramMap.get("boardItem").toString());
//
//        }catch (Exception e){
//            pageNum=1;
//        }
//
//        //product info s
//        Page<TblProductInfo> pagingResult = productListService.getList(pageNum,siteCateSeq,boardSeq,boardItem);
//
//        List<TblProductInfo> pageList = pagingResult.getContent();
//
//        String allCount = Long.toString(pagingResult.getTotalElements());
//        String tPage = Integer.toString(pagingResult.getTotalPages());
//        //product info e
//
//        resultFinal.put("list", pageList);
//        resultFinal.put("totalPage", tPage);
//        resultFinal.put("allCount", allCount);
//
//        return resultFinal;
//    }
//}
