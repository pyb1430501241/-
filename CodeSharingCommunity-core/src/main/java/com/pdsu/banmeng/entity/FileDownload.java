package com.pdsu.banmeng.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author 半梦
 * @since 2021-11-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("db_file_download")
public class FileDownload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文件id
     */
    private Integer fileId;

    /**
     * 上传人UID
     */
    private Integer bid;

    /**
     * 下载人ID
     */
    private Integer uid;

    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Boolean deleted;


}
