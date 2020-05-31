package site.marqstree.mung.test.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenBean {
    private String access_token;
    private Integer expires_in;
}
