package com.epam.esm.service.api.exception;

/**
 * The Error code class.
 */
public final class ErrorCode {

    public static final String TAG_NAME_INCORRECT = "00001";
    public static final String TAG_WITH_SUCH_NAME_ALREADY_EXIST = "00002";
    public static final String TAG_WITH_SUCH_ID_NOT_EXIST = "00003";
    public static final String TAG_WITH_SUCH_NAME_NOT_EXIST = "00004";
    public static final String TAG_ID_SPECIFIED_WHILE_CREATING = "00005";
    public static final String TAG_POPULAR_NOT_FOUND = "00016";

    public static final String GIFT_CERTIFICATE_WITH_SUCH_ID_NOT_EXIST = "00006";
    public static final String GIFT_CERTIFICATE_NAME_INCORRECT = "00007";
    public static final String GIFT_CERTIFICATE_DESCRIPTION_INCORRECT = "00008";
    public static final String GIFT_CERTIFICATE_PRICE_INCORRECT = "00009";
    public static final String GIFT_CERTIFICATE_DURATION_INCORRECT = "00010";
    public static final String GIFT_CERTIFICATE_ID_SPECIFIED_WHILE_CREATING = "00011";

    public static final String USER_NAME_INCORRECT = "00012";
    public static final String USER_LOGIN_INCORRECT = "00020";
    public static final String USER_PASSWORD_INCORRECT = "00021";
    public static final String USER_WITH_SUCH_LOGIN_ALREADY_EXIST = "00022";
    public static final String USER_WITH_SUCH_LOGIN_NOT_EXIST = "00024";
    public static final String USER_WITH_SUCH_ID_NOT_EXIST = "00013";
    public static final String INCORRECT_LOGIN_OR_PASSWORD = "00025";

    public static final String ORDER_ID_SPECIFIED_WHILE_CREATING = "00014";
    public static final String ORDER_WITH_SUCH_ID_NOT_EXIST = "00015";
    public static final String ORDER_CREATING_ERROR = "00019";

    public static final String ROLE_WITH_SUCH_NAME_NOT_EXIST = "00023";

    public static final String PAGINATION_INCORRECT_PAGE_NUMBER = "00017";
    public static final String PAGINATION_INCORRECT_SIZE = "00018";

    public static final String INTERNAL_SERVER = "500";
    public static final String METHOD_ARGUMENT_TYPE_MISMATCH = "406";
    public static final String INVALID_TYPE_PARAMETERS = "402";
    public static final String FORBIDDEN_ACCESS = "403";
    public static final String UNAUTHORIZED = "401";
    public static final String UNSUPPORTED_MEDIA_TYPE = "415";

    private ErrorCode() {
    }
}
