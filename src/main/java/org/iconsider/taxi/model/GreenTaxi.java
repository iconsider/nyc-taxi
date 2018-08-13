package org.iconsider.taxi.model;

import org.iconsider.taxi.utils.DateUtil;

import java.io.Serializable;
import java.text.ParseException;

/**
 * Created by liu on 2018-8-12.
 */
public class GreenTaxi implements Serializable {
    private static final long serialVersionUID = 3058011713211322015L;
    /**
     * 供应商编号
     * 1 表示创新的移动技术有限责任公司
     * 2 表示VeriFone公司
     */
    private int vendorID;
    /**
     * 上车时间
     */
    private long pickUpTime;
    /**
     * 下车时间
     */
    private long dropOffTime;
    /**
     * 行车记录是否保存在车辆内存中
     * Y = store and forward trip
     * N = not a store and forward trip
     */
    private String storeFlag;
    /**
     * 最终的费率
     * 1 = Standard rate
     * 2 = JFK
     * 3 = Newark
     * 4 = Nassau or Westchester
     * 5 = Negotiated fare
     * 6 = Group ride
     */
    private int ratecodeID;
    /**
     * 乘客上车的区域ID
     */
    private int pickUpLocationID;
    /**
     * 乘客下车的区域ID
     */
    private int dropOffLocationID;
    /**
     * 乘客数量
     */
    private int passengerCount;
    /**
     * 行程距离
     */
    private float tripDistance;
    /**
     * 计价器计算的时间和路程费
     */
    private float fareAmount;
    /**
     * 杂项额外附加费。目前，这只包括0.50美元和1美元的高峰时间和隔夜费用
     */
    private float extra;
    /**
     * MTA税
     */
    private float mtaTax;
    /**
     * 信用卡tip，现金tip不包含在内
     */
    private float tipAmount;
    /**
     * 在旅途中支付的所有通行费的总额
     */
    private float tollsAmount;
    /**
     * ehail小费
     */
    private float ehailFee;
    /**
     * 附加费，2015开始征收
     */
    private float improvementSurcharge;
    /**
     * 向乘客收取的总费用。不包括现金小费
     */
    private float totalAmount;
    /**
     * 支付类型
     * 1 = Credit card
     * 2 = Cash
     * 3 = No charge
     * 4 = Dispute
     * 5 = Unknown
     * 6 = Voided trip
     */
    private int payType;
    /**
     * 指示旅行是街道冰雹还是基于使用中的计价器自动分配的调度，但司机可以修改它。
     * 1= Street-hail
     * 2= Dispatch
     */
    private int tripType;

    public GreenTaxi() {
    }

    public GreenTaxi(String str) {
        //limit可以比预期字段数量大一点
        String[] info = str.split(",", 25);

        if (info.length >= 19) {
            try {
                this.vendorID = "".equals(info[0]) ? 0 : Integer.parseInt(info[0]);
                this.pickUpTime = "".equals(info[1]) ? 0L : DateUtil.format01.parse(info[1]).getTime();
                this.dropOffTime = "".equals(info[2]) ? 0L : DateUtil.format01.parse(info[2]).getTime();
                this.storeFlag = "".equals(info[3]) ? "unknown" : info[3];
                this.ratecodeID = "".equals(info[4]) ? 0 : Integer.parseInt(info[4]);
                this.pickUpLocationID = "".equals(info[5]) ? 0 : Integer.parseInt(info[5]);
                this.dropOffLocationID = "".equals(info[6]) ? 0 : Integer.parseInt(info[6]);
                this.passengerCount = "".equals(info[7]) ? 0 : Integer.parseInt(info[7]);
                this.tripDistance = "".equals(info[8]) ? 0F : Float.parseFloat(info[8]);
                this.fareAmount = "".equals(info[9]) ? 0F : Float.parseFloat(info[9]);
                this.extra = "".equals(info[10]) ? 0F : Float.parseFloat(info[10]);
                this.mtaTax = "".equals(info[11]) ? 0F : Float.parseFloat(info[11]);
                this.tipAmount = "".equals(info[12]) ? 0F : Float.parseFloat(info[12]);
                this.tollsAmount = "".equals(info[13]) ? 0F : Float.parseFloat(info[13]);
                this.ehailFee = "".equals(info[14]) ? 0F : Float.parseFloat(info[14]);
                this.improvementSurcharge = "".equals(info[15]) ? 0F : Float.parseFloat(info[15]);
                this.totalAmount = "".equals(info[16]) ? 0F : Float.parseFloat(info[16]);
                this.payType = "".equals(info[17]) ? 0 : Integer.parseInt(info[17]);
                this.tripType = "".equals(info[18]) ? 0 : Integer.parseInt(info[18]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public GreenTaxi(int vendorID, long pickUpTime, long dropOffTime, String storeFlag, int ratecodeID, int pickUpLocationID, int dropOffLocationID, int passengerCount, float tripDistance, float fareAmount, float extra, float mtaTax, float tipAmount, float tollsAmount, float ehailFee, float improvementSurcharge, float totalAmount, int payType, int tripType) {
        this.vendorID = vendorID;
        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
        this.storeFlag = storeFlag;
        this.ratecodeID = ratecodeID;
        this.pickUpLocationID = pickUpLocationID;
        this.dropOffLocationID = dropOffLocationID;
        this.passengerCount = passengerCount;
        this.tripDistance = tripDistance;
        this.fareAmount = fareAmount;
        this.extra = extra;
        this.mtaTax = mtaTax;
        this.tipAmount = tipAmount;
        this.tollsAmount = tollsAmount;
        this.ehailFee = ehailFee;
        this.improvementSurcharge = improvementSurcharge;
        this.totalAmount = totalAmount;
        this.payType = payType;
        this.tripType = tripType;
    }

    /********* 辅助方法 *********/
    /**
     * 判断一个字符串是否以点开头
     * @return true -> 以点开头， false -> 不是以点开头
     */
    private boolean isStartWithDot(String str) {
        return str.startsWith(".");
    }

    @Override
    public String toString() {
        return "GreenTaxi{" +
                "vendorID=" + vendorID +
                ", pickUpTime=" + pickUpTime +
                ", dropOffTime=" + dropOffTime +
                ", storeFlag='" + storeFlag + '\'' +
                ", ratecodeID=" + ratecodeID +
                ", pickUpLocationID=" + pickUpLocationID +
                ", dropOffLocationID=" + dropOffLocationID +
                ", passengerCount=" + passengerCount +
                ", tripDistance=" + tripDistance +
                ", fareAmount=" + fareAmount +
                ", extra=" + extra +
                ", mtaTax=" + mtaTax +
                ", tipAmount=" + tipAmount +
                ", tollsAmount=" + tollsAmount +
                ", ehailFee=" + ehailFee +
                ", improvementSurcharge=" + improvementSurcharge +
                ", totalAmount=" + totalAmount +
                ", payType=" + payType +
                ", tripType=" + tripType +
                '}';
    }
}
