//package com.wavestoked.file.restcontroller;
//
//import kr.tenth.selec7.file.domain.model.TblFileAgent;
//import kr.tenth.selec7.file.domain.service.FileAgentService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//public class FileAgentController {
//
//    private static final Logger logger = LoggerFactory.getLogger(FileAgentController.class);
//
//    @Autowired
//    private FileAgentService fileAgentService;
//
//    @PostMapping("/file/uploadAgentFile")
//    public Map<String,Object> uploadAgentFile(@RequestParam("file") MultipartFile file) {
//
//        HashMap<String,Object> resultFinal = new HashMap<String, Object>();
//
//        Long newFileSeq = fileAgentService.uploadAgentFile(file);
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
//    @PostMapping("/file/getFileAgentInfo")
//    @ResponseBody
//    public Map<String,Object> getFileAgentInfo(@RequestParam Map<Object, Object> paramMap)  {
//
//        String seqNo =  paramMap.get("seqNo").toString();
//
//        long sno = Long.parseLong(seqNo); // String to long
//
//        TblFileAgent imgInfo = fileAgentService.getFileInfo(sno);
//
//        HashMap<String,Object> resultFinal = new HashMap<String, Object>();
//
//        resultFinal.put("list", imgInfo);
//
//        return resultFinal;
//
//    }
//
//
//
//}
