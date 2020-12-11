//package com.wavestoked.file.domain.service;
//
//
//import kr.tenth.selec7.file.domain.exception.FileUploadException;
//import kr.tenth.selec7.file.domain.model.TblFileAgent;
//import kr.tenth.selec7.file.domain.property.FileUploadProperties;
//import kr.tenth.selec7.file.domain.repository.FileAgentRepository;
//import kr.tenth.selec7.util.S3Util;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.util.UUID;
//
//
//@Service
//public class FileAgentService {
//
//
//    @Autowired
//    FileAgentRepository fileAgentRepository;
//
//    S3Util s3 = new S3Util();
//
//    private final Path fileLocation;
//
//    private static final String s3Bucket = "selec7-file";
//
//    @Autowired
//    public FileAgentService(FileUploadProperties prop) {
//        this.fileLocation = Paths.get(prop.getUploadDir())
//                .toAbsolutePath().normalize();
//
//        try {
//            Files.createDirectories(this.fileLocation);
//        }catch(Exception e) {
//            throw new FileUploadException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
//        }
//    }
//
//
//    //선택 파일 전체 가져오기
//    public TblFileAgent getFileInfo(Long fileSeq) {
//        return fileAgentRepository.findBySeqNoAndDelCheck(fileSeq,0);
//    }
//
//
//    //S3 파일업로드
//    public Long uploadAgentFile(MultipartFile file) {
//
//        String oriName = "";
//        String newFileExet = "";
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//        //사이트 정보 가져오기
//        String siteGalleryRoot = "agent";
//
//        Long fileSize = file.getSize();
//
//        String changeName = UUID.randomUUID().toString();
//
//        try {
//
//            // 파일명에 부적합 문자가 있는지 확인한다.
//            if(fileName.contains(".."))
//                throw new FileUploadException("파일명에 부적합 문자가 포함되어 있습니다. " + fileName);
//
//            //확장자 정보 가져오기
//            int index = fileName.lastIndexOf(".");
//
//            if (index != -1) {
//                newFileExet = fileName.substring(index + 1);
//            }
//
//            oriName = fileName;
//            fileName = changeName + "." +newFileExet;
//
//            Path targetLocation = this.fileLocation.resolve(fileName);
//            String oriFileLocation = targetLocation.toString();
//
//            //서버 임시 저장
//            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//
//            //임시 저장 파일
//            File sourceFile = new File(oriFileLocation);
//
//            //폴더 만들기
//            s3.createFolder(s3Bucket, siteGalleryRoot);
//
//            //s3 업로드
//            s3.fileUpload(s3Bucket,siteGalleryRoot,sourceFile);
//
//            //신규 파일 등록
//            TblFileAgent insertFile = new TblFileAgent();
//
//            insertFile.setFileSize(fileSize);
//            insertFile.setFileNameChange(fileName);
//            insertFile.setFileNameOri(oriName);
//            insertFile.setFileNameExtension(newFileExet);
//
//            fileAgentRepository.save(insertFile);
//
//            //임시 파일 삭제
//            sourceFile.delete();
//
//            Long newFileSeq = fileAgentRepository.getMaxMenuId();
//
//            return newFileSeq;
//
//        }catch(Exception e) {
//            throw new FileUploadException("["+fileName+"] 파일 업로드에 실패하였습니다. 다시 시도하십시오.",e);
//        }
//
//    }
//
//
//}