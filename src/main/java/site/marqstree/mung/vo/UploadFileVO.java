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

    //public UploadFileVO(String url, String fileName, String suffix) {
    //    this.url = url;
    //    this.fileName = fileName;
    //    this.suffix = suffix;
    //}
    //
    //public String getUrl() {
    //    return url;
    //}
    //
    //public void setUrl(String url) {
    //    this.url = url;
    //}
    //
    //public String getFileName() {
    //    return fileName;
    //}
    //
    //public void setFileName(String fileName) {
    //    this.fileName = fileName;
    //}
    //
    //public String getSuffix() {
    //    return suffix;
    //}
    //
    //public void setSuffix(String suffix) {
    //    this.suffix = suffix;
    //}
}
