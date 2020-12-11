//package com.wavestoked.file.restcontroller;
//
//import kr.tenth.selec7.file.domain.model.TblFile;
//import kr.tenth.selec7.file.domain.model.TblFileAgency;
//import kr.tenth.selec7.file.domain.service.FileService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@RestController
//public class FileRestController {
//
//    private static final Logger logger = LoggerFactory.getLogger(FileRestController.class);
//
//
//    @Autowired
//    private FileService service;
//
//    @PostMapping("/uploadFile")
//    public Map<String,Object> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam Map<Object, Object> paramMap ) {
//
//        HashMap<String,Object> resultFinal = new HashMap<String, Object>();
//
//        String siteCateSeq =  paramMap.get("siteCateSeq").toString();
//        String siteGalleryRoot =  paramMap.get("siteGalleryRoot").toString();
//
//        long siteSeq = Long.parseLong(siteCateSeq); // String to long
//
//        Long newFileSeq = service.uploadFile(file,siteGalleryRoot,siteSeq);
//        //System.out.println(newFileSeq);
//
//        String nFseq = Long.toString(newFileSeq);
//
//        resultFinal.put("seqNo", nFseq);
//
//        return resultFinal;
//    }
//
//
//
//    @PostMapping("/uploadCompanyFile")
//    public Map<String,Object> uploadCompanyFile(@RequestParam("file") MultipartFile file) {
//
//        HashMap<String,Object> resultFinal = new HashMap<String, Object>();
//
//        Long newFileSeq = service.uploadCompanyFile(file);
//        //System.out.println(newFileSeq);
//
//        String nFseq = Long.toString(newFileSeq);
//
//        resultFinal.put("seqNo", nFseq);
//
//        return resultFinal;
//    }
//
//
//    @PostMapping("/uploadSkinFile")
//    public Map<String,Object> uploadSkinFile(@RequestParam("file") MultipartFile file) {
//
//        HashMap<String,Object> resultFinal = new HashMap<String, Object>();
//
//        Long newFileSeq = service.uploadSkinFile(file);
//        //System.out.println(newFileSeq);
//
//        String nFseq = Long.toString(newFileSeq);
//
//        resultFinal.put("seqNo", nFseq);
//
//        return resultFinal;
//    }
//
//    @PostMapping("/file/getFileAgencyName")
//    @ResponseBody
//    public Map<String,Object> getFileAgencyName(@RequestParam Map<Object, Object> paramMap)  {
//
//        String seqNo =  paramMap.get("seqNo").toString();
//
//        long sno = Long.parseLong(seqNo); // String to long
//
//        List<TblFileAgency> imgList = service.getFileAgencyName(sno);
//
//        HashMap<String,Object> resultFinal = new HashMap<String, Object>();
//
//        resultFinal.put("list", imgList);
//
//        return resultFinal;
//
//    }
//
//
//
//    @PostMapping("/file/getFileInfo")
//    @ResponseBody
//    public Map<String,Object> getFileInfo(@RequestParam Map<Object, Object> paramMap)  {
//
//        String seqNo =  paramMap.get("seqNo").toString();
//
//        long sno = Long.parseLong(seqNo); // String to long
//
//        TblFile getInfo = service.getFileInfo(sno);
//
//        HashMap<String,Object> resultFinal = new HashMap<String, Object>();
//
//        resultFinal.put("info", getInfo);
//
//        return resultFinal;
//
//    }
//
//
//    @GetMapping("/downloadFile/{fileName:.+}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
//        // Load file as Resource
//        Resource resource = service.loadFileAsResource(fileName);
//
//        // Try to determine file's content type
//        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
//        }
//
//        // Fallback to the default content type if type could not be determined
//        if(contentType == null) {
//            contentType = "application/octet-stream";
//        }
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }
//
//
//
//    @RequestMapping(value = "/getFileImg",method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String,Object> getFileImg(@RequestParam Map<Object, Object> paramMap)  {
//
//        //page cpage
//        HashMap<String,Object> resultFinal = new HashMap<String, Object>();
//
//        String fileSeqList =paramMap.get("fileSeqList").toString();
//
//        List<Long> fSeqList = Arrays.stream(fileSeqList.split(",")).map(Long::valueOf).collect(Collectors.toList());
//
//        List<TblFile> file = service.getFileImg(fSeqList);
//
//        resultFinal.put("file", file);
//
//        return resultFinal;
//    }
//
//    @RequestMapping(value = "/getFileImgDesc",method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String,Object> getFileImgDesc(@RequestParam Map<Object, Object> paramMap)  {
//
//        //page cpage
//        HashMap<String,Object> resultFinal = new HashMap<String, Object>();
//
//        String fileSeqList =paramMap.get("fileSeqList").toString();
//
//        List<Long> fSeqList = Arrays.stream(fileSeqList.split(",")).map(Long::valueOf).collect(Collectors.toList());
//
//        List<TblFile> file = service.getFileImgDesc(fSeqList);
//
//        resultFinal.put("file", file);
//
//        return resultFinal;
//    }
//
//
//    @RequestMapping(value = "/deleteFile",method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String,Object> deleteFile(@RequestParam Map<Object, Object> paramMap)  {
//
//        //page cpage
//        HashMap<String,Object> resultFinal = new HashMap<String, Object>();
//
//        String delId =paramMap.get("delId").toString();
//
//        service.deleteFile(delId);
//
//        resultFinal.put("msg", "Complete");
//
//        return resultFinal;
//    }
//
//
//
//
//
////    @GetMapping("/downloadAgencyFile/{fileName:.+}")
////    public ResponseEntity<Resource> downloadAgencyFile(@PathVariable String fileName, HttpServletRequest request){
////
////        String fileName1 ="https://img.selec7.com/agency/8dfc01d4-d7d6-4b5c-8f9a-4419be7d93d4.pdf";
////        // Load file as Resource
////        Resource resource = service.loadFileAsResource(fileName1);
////
////        // Try to determine file's content type
////        String contentType = null;
////
////        try {
////            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
////        } catch (IOException ex) {
////            logger.info("Could not determine file type.");
////        }
////
////        // Fallback to the default content type if type could not be determined
////        if(contentType == null) {
////            contentType = "application/octet-stream";
////        }
////
////        return ResponseEntity.ok()
////                .contentType(MediaType.parseMediaType(contentType))
////                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
////                .body(resource);
////    }
//
//
//    @GetMapping("/downloadAgencyFile/{fileName:.+}")
//    public ResponseEntity<Resource> downloadAgencyFile(@PathVariable String fileName, HttpServletRequest request){
//
//        String fileName1 ="https://img.selec7.com/agency/8dfc01d4-d7d6-4b5c-8f9a-4419be7d93d4.pdf";
//        // Load file as Resource
//        Resource resource = service.loadFileAsResource(fileName1);
//
//        // Try to determine file's content type
//        String contentType = null;
//
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
//        }
//
//        // Fallback to the default content type if type could not be determined
//        if(contentType == null) {
//            contentType = "application/octet-stream";
//        }
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }
//
//
//
//    @PostMapping("/deleteSelectSkinFile")
//    public Map<String,Object> deleteSelectSkinFile(@RequestParam Map<Object, Object> paramMap) {
//
//        HashMap<String,Object> resultFinal = new HashMap<String, Object>();
//
//        String msg = service.deleteSelectSkinFile(paramMap);
//
//        resultFinal.put("msg", msg);
//
//        return resultFinal;
//    }
//
//    @PostMapping("/deleteSkinOldFile")
//    public Map<String,Object> deleteSkinOldFile(@RequestParam Map<Object, Object> paramMap, HttpSession session) {
//
//        HashMap<String,Object> resultFinal = new HashMap<String, Object>();
//
//        String msg = service.deleteSkinOldFile(paramMap);
//
//        resultFinal.put("msg", msg);
//
//        return resultFinal;
//    }
//
//
//    @PostMapping("/deleteSelectFile")
//    public Map<String,Object> deleteSelectFile(@RequestParam Map<Object, Object> paramMap) {
//
//        HashMap<String,Object> resultFinal = new HashMap<String, Object>();
//
//        String msg = service.deleteSelectFile(paramMap);
//
//        resultFinal.put("msg", msg);
//
//        return resultFinal;
//    }
//
//}
