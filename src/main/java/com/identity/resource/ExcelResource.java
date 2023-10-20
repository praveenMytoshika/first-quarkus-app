package com.identity.resource;

import com.identity.service.ExcelService;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.buffer.impl.BufferImpl;
import io.vertx.core.http.HttpServerResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import lombok.AllArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.io.IOException;

@Path("/api/excel")
@AllArgsConstructor
public class ExcelResource {

    private final ExcelService excelService;

    @GET
    @Path("/download")
    @Operation(description = "Download Excel", summary = "Download Excel")
    @APIResponses(value = { @APIResponse(responseCode = "200", description = "Excel Downloaded") })
    public void downloadExcel(@Context HttpServerResponse response) throws IOException {
        response.putHeader("Content-Disposition", "attachment; filename=data.xlsx");
        //response.putHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        byte[] bytes = excelService.generateExcel();

        Buffer data = new BufferImpl();
        data.appendBytes(bytes);

        response.end(data);
    }
}
