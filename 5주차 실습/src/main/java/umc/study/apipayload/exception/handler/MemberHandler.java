package umc.study.apipayload.exception.handler;

import umc.study.apipayload.code.BaseErrorCode;
import umc.study.apipayload.exception.GeneralException;

public class MemberHandler extends GeneralException {

  public MemberHandler(BaseErrorCode errorCode) {
    super(errorCode);
  }
}