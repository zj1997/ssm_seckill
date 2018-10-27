package cn.zj.enums;

/**
 * @author zhaojie
 * @date 2018\9\16 0016 - 0:52
 */
public enum seckillEnums {

    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT(-1,"重复秒杀"),
    INNER_ERROE(-2,"系统错误"),
    DATA_ERROR(-3,"数据篡改");

    private Integer state;

    private String stateInfo;

    seckillEnums(Integer state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public static seckillEnums stateOf(Integer index){

        for (seckillEnums eumns: values()) {

            if(eumns.getState() == index){
                return eumns;
            }
        }
        return null;
    }

}
