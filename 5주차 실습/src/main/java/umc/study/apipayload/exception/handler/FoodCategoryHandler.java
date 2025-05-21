package umc.study.apipayload.exception.handler;

import umc.study.apipayload.code.BaseErrorCode;
import umc.study.apipayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {

    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}