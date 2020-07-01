package cn.toy.www.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * @description: 查询内容
 * @author: Zero
 * @date: 2020/5/14 下午11:34
 */
@Setter
@Getter
@ToString
public class QueryContentVO {
    @ApiModelProperty("片名")
    private String videoName;
    @ApiModelProperty(value = "内容来源")
    private String fromBy;
    @ApiModelProperty("播出时间")
    private Long time;
    @ApiModelProperty(value = "关联平台名称")
    private String relativePlatformName;
    @ApiModelProperty("取证时间")
    private Timestamp forensicsTime;
    @ApiModelProperty("抓取人")
    private String crawler;
    @ApiModelProperty("状态：1-待处理；2-取证；3-发函；4-诉讼；5-和解")
    private Integer status;
    @ApiModelProperty(value = "是否完成：0-否；1-是")
    private Byte carryOut;
    @ApiModelProperty(value = "是否解除：0-否；1-是")
    private Byte lift;
    @ApiModelProperty(value = "一级分类")
    private String videoType;
    @ApiModelProperty(value = "二级分类")
    private String secondVideoType;
    @ApiModelProperty(value = "是否有水印；0-否；1-是")
    private Byte hasWatermark;
    @ApiModelProperty(value = "水印")
    private String watermark;
    @ApiModelProperty(value = "内容来源律所")
    private String lawOffice;
    @ApiModelProperty(value = "分配律所")
    private String distributionLawOffice;
    @ApiModelProperty(value = "频道")
    private String channel;
    @ApiModelProperty(value = "播放位置")
    private String position;
    @ApiModelProperty(value = "取证视频")
    private String forensicsVideo;
}
