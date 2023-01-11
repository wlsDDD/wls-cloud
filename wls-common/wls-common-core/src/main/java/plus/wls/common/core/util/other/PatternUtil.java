package plus.wls.common.core.util.other;

import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 *
 * @author wls
 * @since 2021/01/18 11:26:40
 */
public final class PatternUtil {
    
    private static final String UUID_PATTERN = "^[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}$";
    private static final String MOBILE_PATTERN = "^1[3-9]\\d{9}$";
    private static final String IPV4_PATTERN = "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$";
    private static final String IPV6_PATTERN = "^([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$|^:((:[0-9a-fA-F]{1,4}){1,6}|:)$|^[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,5}|:)$|^([0-9a-fA-F]{1,4}:){2}((:[0-9a-fA-F]{1,4}){1,4}|:)$|^([0-9a-fA-F]{1,4}:){3}((:[0-9a-fA-F]{1,4}){1,3}|:)$|^([0-9a-fA-F]{1,4}:){4}((:[0-9a-fA-F]{1,4}){1,2}|:)$|^([0-9a-fA-F]{1,4}:){5}:([0-9a-fA-F]{1,4})?$|^([0-9a-fA-F]{1,4}:){6}:$";
    private static final String QQ_PATTERN = "^[1-9][0-9]{4,10}$";
    private static final String HTTP_PATTERN = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
    private static final String ID_CARD_PATTERN = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
    private static final String CHINESE_PATTERN = "^[\u4e00-\u9fa5],{0,}$";
    private static final String EMAIL_PATTERN = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    private static final String LANGUAGE_PATTERN = "^(en_us|zh_cn)$";
    private static final String DOT_SPLIT_CODE_PATTERN = "^([a-zZ-Z]+\\.)+[a-zA-Z]+$";
    private static final String EN_STR_PATTERN = "^[a-zA-Z]+$";
    private static final String WX_NO_PATTERN = "^[a-zA-Z0-9_-]{5,19}$";
    private static final String SPECIAL_PATTERN = "[`~!@#$%^&*()_\\-+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    /**
     * 验证	带区号的座机号码
     */
    private static final String LANDLINE_PTN = "^[0][1-9]{2,3}-[0-9]{5,10}$";
    /**
     * 验证不带区号的座机号码
     */
    private static final String LANDLINE_PTN_N = "^[1-9]{1}[0-9]{5,8}$";
    
    
    /**
     * 座机号码校验(不带区号）
     *
     * @author wls
     */
    public static boolean isLandlinePtnN(String src) {
        return null != src && matches(LANDLINE_PTN_N, src);
    }
    
    private static boolean matches(String pattern, String src) {
        return Pattern.matches(pattern, src);
    }
    
    /**
     * 座机号码校验(带区号）
     *
     * @author wls
     */
    public static boolean isLandlinePtn(String src) {
        return null != src && matches(LANDLINE_PTN, src);
    }
    
    /**
     * 国际化语言校验
     *
     * @author wls
     */
    public static boolean isLanguageLocale(String src) {
        return null != src && matches(LANGUAGE_PATTERN, src);
    }
    
    /**
     * 微信号校验
     *
     * @author wls
     */
    public static boolean isWeChatNo(String src) {
        return null != src && matches(WX_NO_PATTERN, src);
    }
    
    /**
     * 校验UUID格式
     *
     * @param src c284c52f-613a-427c-ad0e-78fdb4cd4af8
     *
     * @author wls
     */
    public static boolean isUuId(String src) {
        return null != src && matches(UUID_PATTERN, src);
    }
    
    /**
     * 校验手机号
     *
     * @param src 13999999999
     *
     * @author wls
     */
    public static boolean isMobile(String src) {
        return null != src && matches(MOBILE_PATTERN, src);
    }
    
    /**
     * 校验IPV4地址
     *
     * @param src 10.0.1.10
     *
     * @author wls
     */
    public static boolean isIpV4Address(String src) {
        return null != src && matches(IPV4_PATTERN, src);
    }
    
    /**
     * 校验IPV6地址
     *
     * @param src fe80::80fe:8c96:fd44:5e72%3
     *
     * @author wls
     */
    public static boolean isIpV6Address(String src) {
        return null != src && matches(IPV6_PATTERN, src);
    }
    
    /**
     * 校验QQ号（5-11位数字，以非0开头）
     *
     * @param src 1020444044
     *
     * @author wls
     */
    public static boolean isQq(String src) {
        return null != src && matches(QQ_PATTERN, src);
    }
    
    /**
     * 数字+字母 指定长度的字符串
     *
     * @param src    aSdf5we
     * @param minLen 最小长度
     * @param maxLen 最大长度
     *
     * @author wls
     */
    public static boolean isSimpleString(String src, int minLen, int maxLen) {
        return null != src && Pattern.matches("^([a-z]|[A-Z]|[0-9]){" + minLen + "," + maxLen + "}$", src);
    }
    
    /**
     * 校验HTTP(S) URL
     *
     * @param src http://m.emeik.cn
     *
     * @author wls
     */
    public static boolean isUrl(String src) {
        return null != src && matches(HTTP_PATTERN, src);
    }
    
    /**
     * 校验身份证
     *
     * @param src 51102319900806003x
     *
     * @author wls
     */
    public static boolean isIdCard(String src) {
        return null != src && matches(ID_CARD_PATTERN, src);
    }
    
    /**
     * 校验汉字
     *
     * @param src 这是汉字
     *
     * @author wls
     */
    public static boolean isChineseString(String src) {
        return null != src && matches(CHINESE_PATTERN, src);
    }
    
    /**
     * 校验邮箱
     *
     * @param src ac1242@sina.com
     *
     * @author wls
     */
    public static boolean isEmail(String src) {
        return null != src && matches(EMAIL_PATTERN, src);
    }
    
    /**
     * 校验指定长度数字字符串（数字支付密码、短信验证码等）
     *
     * @param src    521545
     * @param minLen 最小长度
     * @param maxLen 最大长度
     *
     * @author wls
     */
    public static boolean isLenNumber(String src, int minLen, int maxLen) {
        return null != src && Pattern.matches("^\\d{" + minLen + "," + maxLen + "}$", src);
    }
    
    /**
     * 校验点分隔的英文字符串，点不能是开头或结尾
     *
     * @author wls
     */
    public static boolean isDotSplitCode(String src) {
        return null != src && matches(DOT_SPLIT_CODE_PATTERN, src);
    }
    
    /**
     * 校验英文字符串
     *
     * @author wls
     */
    public static boolean isEnStr(String src) {
        return null != src && matches(EN_STR_PATTERN, src);
    }
    
    /**
     * 检验是否包含特色字符
     *
     * @return true：包含   false：不包含
     */
    public static boolean isContainSpecialStr(String str) {
        return null != str && Pattern.compile(SPECIAL_PATTERN).matcher(str).find();
    }
    
    /**
     * 替换特殊字符串为空
     */
    public static String replaceAllSpecialStr(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll(SPECIAL_PATTERN, "").trim();
    }
    
}
