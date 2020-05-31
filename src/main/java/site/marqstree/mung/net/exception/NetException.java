package site.marqstree.mung.net.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetException extends Throwable {
    private Integer code;
    private String message;
}
