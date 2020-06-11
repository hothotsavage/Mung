package site.marqstree.mung.net.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StringRespBean {
    private Integer code;
    private String message;
    private String data;
}
