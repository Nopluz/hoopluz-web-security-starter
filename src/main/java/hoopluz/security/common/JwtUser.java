package hoopluz.security.common;

import hoopluz.security.JwtEncodeMapper;

public interface JwtUser {

  Integer getId();

  JwtEncodeMapper buildEncode();

}
