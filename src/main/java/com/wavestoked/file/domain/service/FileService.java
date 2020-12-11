//package com.wavestoked.file.domain.service;
//
//
//import com.wavestoked.file.domain.model.TblFile;
//import com.wavestoked.file.domain.property.FileUploadProperties;
//import kr.tenth.selec7.file.domain.exception.FileDownloadException;
//import kr.tenth.selec7.file.domain.exception.FileUploadException;
//import kr.tenth.selec7.file.domain.model.TblFile;
//import kr.tenth.selec7.file.domain.model.TblFileAgency;
//import kr.tenth.selec7.file.domain.property.FileUploadProperties;
//import kr.tenth.selec7.file.domain.repository.FileAgencyRepository;
//import kr.tenth.selec7.file.domain.repository.FileRepository;
//import kr.tenth.selec7.util.S3Util;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//
//@Service
//public class FileService {
//
//    @Autowired
//    FileRepository fileRepository;
//
////    @Autowired
////    FileAgencyRepository fileAgencyRepository;
//
//    S3Util s3 = new S3Util();
//
//    private final Path fileLocation;
//
//    private static final String s3Bucket = "selec7-file";
//
//    @Autowired
//    public FileService(FileUploadProperties prop) {
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
//    //S3 파일업로드
//    public Long uploadFile(MultipartFile file, String siteGalleryRoot,Long siteCateSeq) {
//
//        String oriName = "";
//        String newFileExet = "";
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
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
//            TblFile insertFile = new TblFile();
//
//            insertFile.setSiteCateSeq(siteCateSeq);
//            insertFile.setFileSize(fileSize);
//            insertFile.setFileNameChange(fileName);
//            insertFile.setFileNameOri(oriName);
//            insertFile.setFileNameExtension(newFileExet);
//
//            fileRepository.save(insertFile);
//
//            //임시 파일 삭제
//            sourceFile.delete();
//
//            Long newFileSeq = fileRepository.getMaxMenuId();
//
//            return newFileSeq;
//
//        }catch(Exception e) {
//            throw new FileUploadException("["+fileName+"] 파일 업로드에 실패하였습니다. 다시 시도하십시오.",e);
//        }
//
//    }
//
//    //첫번째 파일 가져오기
//    public String getFirstProductImgFile(Long fileSeq,Long sCate) {
//
//        List<TblFile> result = fileRepository.findBySeqNoAndSiteCateSeqAndDelCheck(fileSeq,sCate,0);
//
//        //사이트 정보 가져오기
//        String getFileName = "";
//
//        if(result.size()==1){
//            getFileName =  result.get(0).getFileNameChange();
//        }
//
//        return getFileName;
//
//    }
//
//    //해당 파일들 전체 가져오기
//    public List<TblFile> getFileImg(List<Long> fileSeq) {
//        List<TblFile> result = fileRepository.findBySeqNoInAndDelCheckOrderByRegdate(fileSeq,0);
//        return result;
//    }
//
//    public List<TblFile> getFileImgDesc(List<Long> fileSeq) {
//        List<TblFile> result = fileRepository.findBySeqNoInAndDelCheckOrderByRegdateDesc(fileSeq,0);
//        return result;
//    }
//
//
//
//    //선택 파일 전체 가져오기
//    public TblFile getFileInfo(Long fileSeq) {
//        return fileRepository.findBySeqNoAndDelCheck(fileSeq,0);
//    }
//
//    //선택 파일 DB 삭제
//    public void deleteFile(String delId) {
//        long dId = Long.parseLong(delId); // String to long
//        fileRepository.deleteById(dId);
//    }
//
//    //S3 파일업로드
//    public Long uploadSkinFile(MultipartFile file) {
//
//        String oriName = "";
//        String newFileExet = "";
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//        //사이트 정보 가져오기
//        String siteGalleryRoot = "skin";
//        Long siteCateSeq = 0L;
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
//            TblFile insertFile = new TblFile();
//
//            insertFile.setSiteCateSeq(siteCateSeq);
//            insertFile.setFileSize(fileSize);
//            insertFile.setFileNameChange(fileName);
//            insertFile.setFileNameOri(oriName);
//            insertFile.setFileNameExtension(newFileExet);
//
//            fileRepository.save(insertFile);
//
//            //임시 파일 삭제
//            sourceFile.delete();
//
//            Long newFileSeq = fileRepository.getMaxMenuId();
//
//            return newFileSeq;
//
//        }catch(Exception e) {
//            throw new FileUploadException("["+fileName+"] 파일 업로드에 실패하였습니다. 다시 시도하십시오.",e);
//        }
//
//    }
//
//    //S3 파일업로드
//    public Long uploadCompanyFile(MultipartFile file) {
//
//        String oriName = "";
//        String newFileExet = "";
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//        //사이트 정보 가져오기
//        String siteGalleryRoot = "agency";
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
//            TblFileAgency insertFile = new TblFileAgency();
//
//            insertFile.setFileSize(fileSize);
//            insertFile.setFileNameChange(fileName);
//            insertFile.setFileNameOri(oriName);
//            insertFile.setFileNameExtension(newFileExet);
//
//            fileAgencyRepository.save(insertFile);
//
//            //임시 파일 삭제
//            sourceFile.delete();
//
//            Long newFileSeq = fileAgencyRepository.getMaxMenuId();
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
//    public Resource loadFileAsResource(String fileName) {
//        try {
//            Path filePath = this.fileLocation.resolve(fileName).normalize();
//            Resource resource = new UrlResource(filePath.toUri());
//
//            if(resource.exists()) {
//                return resource;
//            }else {
//                throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.");
//            }
//        }catch(MalformedURLException e) {
//            throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.", e);
//        }
//    }
//
//    public File multipartToFile(MultipartFile multipart) throws IllegalStateException {
//
//        File convFile = new File( multipart.getOriginalFilename());
//
//        try {
//            multipart.transferTo(convFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return convFile;
//    }
//
//
//    public List<TblFile> getAllImgList(Long siteCateSeq){
//
//        List<TblFile> result = fileRepository.findBySiteCateSeqAndDelCheckOrderByRegdateDesc(siteCateSeq,0);
//
//        return result;
//    }
//
//    public List<TblFile> getFileName(Long siteCateSeq,Long seqNo){
//        List<TblFile> result = fileRepository.findBySeqNoAndSiteCateSeqAndDelCheck(seqNo,siteCateSeq,0);
//        return result;
//    }
//
//
//    public List<TblFileAgency> getFileAgencyName(Long seqNo){
//        List<TblFileAgency> result = fileAgencyRepository.findBySeqNoAndDelCheckAndSiteCateSeq(seqNo);
//        return result;
//    }
//
//    public Long copy_insertFileInfo(TblFile copyInfo,Long siteCateSeq){
//
//        //신규 파일 등록
//        TblFile insertFile = new TblFile();
//
//        insertFile.setSiteCateSeq(siteCateSeq);
//        insertFile.setFileSize(copyInfo.getFileSize());
//        insertFile.setFileNameChange(copyInfo.getFileNameChange());
//        insertFile.setFileNameOri(copyInfo.getFileNameOri());
//        insertFile.setFileNameExtension(copyInfo.getFileNameExtension());
//
//        fileRepository.save(insertFile);
//
//        Long newFileSeq = insertFile.getSeqNo();
//
//        return newFileSeq;
//    }
//
//
//    public String deleteSelectSkinFile(Map<Object, Object> delInfo) {
//
//        String msg = "";
//
//        String delId =  delInfo.get("delId").toString();
//        long dId = Long.parseLong(delId); // String to long
//
//        //사이트 정보 가져오기
//        String siteGalleryRoot = "skin";
//        Long siteCateSeq = 0L;
//
//        List<TblFile> resultImg = fileRepository.findBySeqNoAndDelCheckAndSiteCateSeq(dId,siteCateSeq);
//
//        String fileNameChange = resultImg.get(0).getFileNameChange();
//
//        String imgFullRoot = siteGalleryRoot+"/"+fileNameChange;
//
//        //S3 파일 삭제
//        s3.fileDelete(s3Bucket,imgFullRoot);
//
//        //DB 삭제
//        fileRepository.deleteById(dId);
//
//        msg = "Complete";
//
//        return msg;
//    }
//
//
//    //스킨 파일 삭제
//    public String deleteSkinOldFile(Map<Object, Object> delInfo) {
//
//        String msg = "";
//
//        String delId =  delInfo.get("delId").toString();
//        long dId = Long.parseLong(delId); // String to long
//
//        //사이트 정보 가져오기
//        String siteGalleryRoot = "skin";
//        TblFile resultImg = fileRepository.findBySeqNoAndDelCheck(dId,0);
//        String fileNameChange = resultImg.getFileNameChange();
//        String imgFullRoot = siteGalleryRoot+"/"+fileNameChange;
//
//        //S3 파일 삭제
//        s3.fileDelete(s3Bucket,imgFullRoot);
//
//        //DB 삭제
//        fileRepository.deleteById(dId);
//
//        msg = "Complete";
//
//        return msg;
//    }
//
//
//    //파일 삭제
//    public String deleteSelectFile(Map<Object, Object> delInfo) {
//
//        String msg = "";
//
//        String delId =  delInfo.get("delId").toString();
//
//        String siteCateSeq =  delInfo.get("siteCateSeq").toString();
//        String siteGalleryRoot = delInfo.get("siteGalleryRoot").toString();
//
//        long dId = Long.parseLong(delId); // String to long
//        long sId = Long.parseLong(siteCateSeq); // String to long
//
//        List<TblFile> resultImg = fileRepository.findBySeqNoAndDelCheckAndSiteCateSeq(dId,sId);
//
//        String fileNameChange = resultImg.get(0).getFileNameChange();
//
//        String imgFullRoot = siteGalleryRoot+"/"+fileNameChange;
//
//        //S3 파일 삭제
//        s3.fileDelete(s3Bucket,imgFullRoot);
//
//        //DB 삭제
//        fileRepository.deleteById(dId);
//
//        msg = "Complete";
//
//        return msg;
//    }
//
//
//}