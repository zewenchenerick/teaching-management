package com.erick.vo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ClazzVO {

    private Integer id;
    private String name;
    private String room;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Integer masterId;
    private String subject;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
