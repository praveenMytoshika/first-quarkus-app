package com.identity.model.response.excel_record;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcelRecord {
    private String recordId;
    private String recordName;
    private String recordType;
}
