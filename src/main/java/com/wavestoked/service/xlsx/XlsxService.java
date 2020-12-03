package com.wavestoked.service.xlsx;

import com.wavestoked.domain.file.property.FileUploadProperties;
import com.wavestoked.domain.sampleOrder.SampleOrder;
import com.wavestoked.domain.sampleOrder.SampleOrderRepository;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class XlsxService {

    @Autowired
    SampleOrderRepository sampleOrderRepository;

    static XSSFRow row;
    private final Path fileLocation;


    @Autowired
    public XlsxService(FileUploadProperties prop) {
        this.fileLocation = Paths.get(prop.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileLocation);
        } catch (Exception e) {
            throw new FileUploadException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
        }
    }

    public XSSFWorkbook listToInputStream(Map<Object, Object> info) {


        List<Long> getSelectSeq =  new ArrayList<>();

        String selectSeq = info.get("selectSeq").toString();
        String[] seqList = selectSeq.split(",");

        for( String seqNo : seqList){
            long sSeq = Long.parseLong(seqNo);
            getSelectSeq.add(sSeq);
        }

        //xlsx 파일 출력시 선언
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("mySheet");

        row = sheet.createRow(0);
        row.createCell(0).setCellValue("No");
        row.createCell(1).setCellValue("주문번호");
        row.createCell(2).setCellValue("주문자명");
        row.createCell(3).setCellValue("주문명");
        row.createCell(4).setCellValue("택배회사");
        row.createCell(5).setCellValue("송장번호");


        List<SampleOrder> getOriderInfo =  sampleOrderRepository.findBySiteCateSeqAndDeliveryStateAndSeqNoInOrderByRegdateDesc(siteCateSeq,3,getSelectSeq);

        int rank  = 1;

        //설문답변자 가져오기
        for( int i=0; i<getOriderInfo.size(); i++){

            row = sheet.createRow(i+1);

            row.createCell(0).setCellValue(rank);

            SampleOrder getInfo = getOriderInfo.get(i);

            row.createCell(1).setCellValue("p-"+getInfo.getPgNumber());
            row.createCell(2).setCellValue(getInfo.getRecvName());
            row.createCell(3).setCellValue(getInfo.getTitle());
            row.createCell(4).setCellValue("");
            row.createCell(5).setCellValue("");

            rank++;
        }

        return workbook;

    }


    public String orderXlsxUpdate(MultipartFile file, Long siteCateSeq) {

        String msg = "";

        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //엑셀 index는 0부터 시작
        int rowindex=0;

        //시트 수
        XSSFSheet sheet = workbook.getSheetAt(0);
        //행의 수
        int rows = sheet.getPhysicalNumberOfRows();

        for(rowindex=1; rowindex <rows ; rowindex++) {

            //행 읽기
            XSSFRow row = sheet.getRow(rowindex);

            String pgNumber=row.getCell(1).getStringCellValue();
            String expressCompany=row.getCell(4).getStringCellValue();
            String expressNumber=row.getCell(5).getRawValue();

            if(!StrUtil.getNull(pgNumber)){

                String oriPgNumber = pgNumber.replaceAll("p-", "");
                System.out.println("oriPgNumber"+oriPgNumber);

                System.out.println("expressNumber"+expressNumber);

                LocalDateTime dateTime = LocalDateTime.now();

                SampleOrder updateInfo =  sampleOrderRepository.findByPgNumberAndSiteCateSeq(oriPgNumber,siteCateSeq);

                if (updateInfo != null) {

                    if(!StrUtil.getNull(expressCompany)&&!StrUtil.getNull(expressNumber)){
                        updateInfo.setExpressCompany(expressCompany);
                        updateInfo.setExpressNumber(expressNumber);
                        updateInfo.setDeliveryState(4);
                    }

                    updateInfo.setUptdate(dateTime);
                    sampleOrderRepository.save(updateInfo);
                } else {
                    // print out error
                }

            }

        }

        msg = "Complete";


        return msg;

    }

}
