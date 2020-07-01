package cn.toy.www;

/**
 * 通用错误码
 */
public enum ErrorCode {
    /**
     * 成功
     */
    OK(0, ""),
    /**
     * 操作失败
     */
    FAIL(-1, "操作失败"),
    /**
     * 成功
     */
    SUCCESS(0, "SUCCESS"),
    /**
     * 通用的操作失败
     */
    DEFAULT_FAIL(1, "通用的操作失败"),
    /**
     * 远程调度失败
     */
    RPC_ERROR(-2, "远程调度失败"),
    /**
     * 上传文件失败
     */
    UPLOAD_FILE_ERROR(2, "上传文件失败"),
    /**
     * 用户不存在
     */
    USER_NOT_FOUND(1000, "用户不存在"),
    /**
     * 密码错误
     */
    USER_PASSWORD_ERROR(1001, "密码错误"),
    /**
     * 密码与验证码不一致
     */
    PASSWORD_CONFIRM_PASSWORD_ERROR(1002, "密码与验证密码不一致"),
    /**
     * 发送短信验证码失败
     */
    SEND_VERIFICATION_CODE_ERROR(1003, "发送短信验证码失败"),
    /**
     * 验证码错误
     */
    VERIFICATION_CODE_ERROR(1004, "验证码错误"),

    /**
     * 验证码不存在
     */
    VERIFICATION_CODE_NOT_FOUND_ERROR(1005, "验证码不存在"),

    /**
     * 验证码验证未通过
     */
    VERIFICATION_CODE_NOT_PASS_ERROR(1006, "验证码验证未通过"),

    /**
     * 修改密码失败
     */
    CHANGE_PASSWORD_ERROR(1007, "密码修改失败"),

    /**
     * 手机号已存在
     */
    TEL_IS_HAD_ERROR(1008, "手机号已存在"),

    /**
     * 部门信息不存在
     */
    DEPARTMENT_IS_NULL_ERROR(2000, "部门信息不存在"),

    /**
     * 档口不存在
     */
    STALLS_NULL_ERROR(3000, "档口不存在"),

    /**
     * 档口已签约不能暂停招商
     */
    STALLS_CONTRACT_PAUSE_ERROR(3001, "档口已签约不能暂停招商"),

    /**
     * 不存在协助招商信息
     */
    ASSIST_INVESTMENT_PROMOTION_NULL_ERROR(3002, "不存在协助招商信息"),

    /**
     * 已存在协助招商信息
     */
    ASSIST_INVESTMENT_PROMOTION_HAD_ERROR(3003, "已存在协助招商信息"),

    /**
     * 项目不存在
     */
    PROJECT_NULL_ERROR(4000, "项目不存在"),

    /**
     * 合同信息不存在
     */
    CONTRACT_NULL_ERROR(5000, "签约信息不存在"),

    /**
     * 延期签约时间不能大于合同截止日
     */
    DELAY_TIME_AFTER_CONTRACT_ENT_TIME(5001, "延期签约时间不能大于合同截止日"),

    /**
     * 签约未期至不能到期离场
     */
    CONTRACT_IS_NOT_EXPIRE(5002, "签约未期至不能到期离场"),

    /**
     * 离场记录不存在
     */
    LEAVE_NULL_ERROR(6000, "离场记录不存在"),

    /**
     * 暂无审核中离场信息
     */
    LEAVE_IN_APPROVAL_NULL_ERROR(6001, "暂无审核中离场信息"),

    /**
     * 用户未绑定项目
     */
    USER_HAVE_NOT_BIND_PROJECT(7000, "用户未绑定项目"),
    /**
     * 商户主键不能为空
     */
    MERCHANT_ID_IS_NULL(7001, "商户主键不能为空"),

    /**
     * 档口不能为空
     */
    STALLS_ID_IS_NULL(7002, "档口不能为空"),

    /**
     * 检查时间不能为空
     */
    MARK_SALE_IS_NULL(7003, "未进行销量盘点"),

    /**
     * 今日已进行过开店检查
     */
    SHOP_INSPECTION_OPEN_IS_HAD(7004, "今日已进行过开店检查"),

    /**
     * 今日已进行过闭店检查
     */
    SHOP_INSPECTION_CLOSE_IS_HAD(7005, "今日已进行过闭店检查"),

    /**
     * 今日已提交过到岗人员信息
     */
    IS_HAVEN_COME_POSITION(7006, "今日已提交过到岗人员信息"),

    /**
     * 未到检查时间
     */
    NO_INSPECTION_TIME(7007, "未到检查时间"),

    /**
     * 检查时间不能为空
     */
    INSPECTION_TIME_IS_NULL(7008, "检查时间不能为空"),

    /**
     * 没有店铺信息
     */
    FINANCE_NO_MERCHANT(8000, "没有店铺信息"),

    /**
     * 没有代缴账单
     */
    FINANCE_NO_BILL_PAYABLE(8001, "没有代缴账单"),

    /**
     * 没有账单信息
     */
    FINANCE_NO_BILL(8002, "没有账单信息"),

    /**
     * 账单金额不合法
     */
    FINANCE_BILL_AMOUNT_ILLEGAL(8003, "账单金额不合法"),

    /**
     * 账单主键不能为空
     */
    FINANCE_BILL_ID_NULL(8004, "账单主键不能为空")
    ;

    private Integer code;
    private String msg;


    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ErrorCode codeOf(int code) {
        for (ErrorCode state : values()) {
            if (state.getCode() == code) {
                return state;
            }
        }
        return null;
    }
}
