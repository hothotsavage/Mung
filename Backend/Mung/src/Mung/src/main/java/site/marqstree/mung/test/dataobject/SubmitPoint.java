package site.marqstree.mung.test.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitPoint {

    private Integer id;

    private Double lat;

    private Double lng;

    private String location;

    private Float deep;

    private Float area;

    private String points;

    private String openId;

    private String images;

    private String detail;

    private Integer isMock=0;

    private Date createTime;
    
    private Date updateTime;

}
