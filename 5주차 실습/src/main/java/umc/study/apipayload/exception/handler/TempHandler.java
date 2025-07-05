package umc.study.apipayload.exception.handler;

import umc.study.apipayload.code.BaseErrorCode;
import umc.study.apipayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}