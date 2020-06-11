package site.marqstree.mung.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileVO {
    String url;
    String fileName;
    String suffix;

}
