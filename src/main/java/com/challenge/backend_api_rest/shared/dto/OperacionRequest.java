package com.challenge.backend_api_rest.shared.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para realizar una operación matemática")
public class OperacionRequest {

    @Schema(description = "Primer número para la operación", example = "100.0", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal num1;

    @Schema(description = "Segundo número para la operación", example = "50.0", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal num2;

    public OperacionRequest() {
    }

    public OperacionRequest(BigDecimal num1, BigDecimal num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public BigDecimal getNum1() {
        return num1;
    }

    public void setNum1(BigDecimal num1) {
        this.num1 = num1;
    }

    public BigDecimal getNum2() {
        return num2;
    }

    public void setNum2(BigDecimal num2) {
        this.num2 = num2;
    }

}
