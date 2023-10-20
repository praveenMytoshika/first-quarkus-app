package com.identity.service;

import com.identity.model.response.excel_record.ExcelRecord;
import com.identity.enums.RecordType;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Slf4j
public class ExcelService {

    public byte[] generateExcel() throws IOException {
        long startTime = System.currentTimeMillis();

        List<ExcelRecord> records = new ArrayList<>(20000);
        for(int i=1; i<=20000; i++) {
            records.add(
                    ExcelRecord.builder()
                            .recordId(String.valueOf(i))
                            .recordName("record_name-"+i)
                            .recordType(RecordType.MAIN.name())
                            .build()
            );
        }

        ExcelExportService excelExportService = new ExcelExportService(records);
        byte[] bytes = excelExportService.export();

        getExecutionTimeDifference(startTime, System.currentTimeMillis());

        return bytes;
    }

    private void getExecutionTimeDifference(long startTime, long now) {
        try {
            Instant instant = Instant.ofEpochMilli(now - startTime);
            ZoneId zoneId = ZoneId.of("UTC");
            LocalDateTime timeDiff = instant.atZone(zoneId).toLocalDateTime();

            log.info("Time taken to prepare the ExcelReport : {}hour {}min {}sec",
                    timeDiff.getHour(), timeDiff.getMinute(), timeDiff.getSecond());

        } catch(Exception ignored) { }
    }

}
