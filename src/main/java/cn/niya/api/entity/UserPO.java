package cn.niya.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
@TableName(value = "tb_user")
public class UserPO implements Serializable {

    private Long id;

    private String userName;

    private String password;

    private String userEmail;

    private String headImg;

    private String headDefImg;

    private Boolean hasHeadImg;

    private String backImg;

    private String backDefImg;

    private Boolean hasBackImg;

    private Integer userMobile;

    private String userAddress;

    private Integer userStatus;

    private Date gmtCreate;

    private Date gmtModified;

    private String userAuthorities;

}
