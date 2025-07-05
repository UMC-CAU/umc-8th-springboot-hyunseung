package umc.study.apipayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.study.apipayload.code.BaseErrorCode;
import umc.study.apipayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private BaseErrorCode code;
    ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }
    ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}