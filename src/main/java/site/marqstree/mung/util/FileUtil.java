package site.marqstree.mung.util;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import site.marqstree.mung.vo.UploadFileVO;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

public class FileUtil {

    private static class Holder {
        private static final FileUtil INSTANCE = new FileUtil();
    }

    //获取单例
    private FileUtil getInstance(){
        return FileUtil.Holder.INSTANCE;
    }

    // 上传文件相对路径jar包
    static private String uploadSubPath = "";
    // 下载路径url前缀
    // 例如：http://192.168.137.1:5050/api/file/download/
    static private String downloadUrlPrefix = "";

    // 将下载相对路径拼接成下载绝对路径
    // subUploadPath:下载相对路径
    public String getUploadFilePath() throws FileNotFoundException {
        // 构建上传文件的存放路径
        //获取根目录
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists())
            path = new File("");
        System.out.println("path:"+path.getAbsolutePath());

        //如果上传目录为/static/images/upload/，则可以如下获取：
        File uploadPath = new File(path.getAbsolutePath(), uploadSubPath);
        if(!uploadPath.exists())
            uploadPath.mkdirs();
        System.out.println("upload url:"+uploadPath.getAbsolutePath());
        return uploadPath.getAbsolutePath();
    }

    //在下载路径中查找文件
    public File findFile(String fileName) throws FileNotFoundException{
        String uploadPath = getUploadFilePath();
        String realPath = uploadPath + File.separator + fileName;
        File file  = new File(realPath);
        if (file.exists())
            return file;
        else
            return null;
    }

    //返回本地存储文件名
    public UploadFileVO saveFile(MultipartFile uploadFile) throws IOException{
        // 测试MultipartFile接口的各个方法
        System.out.println("文件类型ContentType=" + uploadFile.getContentType());
        System.out.println("文件组件名称Name=" + uploadFile.getName());
        System.out.println("文件原名称OriginalFileName=" + uploadFile.getOriginalFilename());
        System.out.println("文件大小Size=" + uploadFile.getSize() / 1024 + "KB");
        // 获取文件的后缀名,比如图片的jpeg,png
        String suffixName = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf(".")+1);
        System.out.println("上传的后缀名为：" + suffixName);

        if(uploadFile.isEmpty())
            return null;

        //写入上传路径，进行文件上传
        //在开发测试模式时，得到的地址为：{项目跟目录}/target/static/images/upload/
        //在打包成jar正式发布时，得到的地址为：{发布jar包目录}/static/images/upload/

        // 获取上传的文件名称，并结合存放路径，构建新的文件名称
        //使用uuid，保存文件名唯一性
        String uuidFileName = UUID.randomUUID().toString();
        //String filename = file.getOriginalFilename();
        String uploadPath = getUploadFilePath();
        String fileName = uuidFileName + "." + suffixName;
        File savedFile = new File(uploadPath, fileName);

        // 判断路径是否存在，不存在则新创建一个
        if (!savedFile.getParentFile().exists()) {
            savedFile.getParentFile().mkdirs();
        }

        // 将上传文件保存到目标文件目录
        uploadFile.transferTo(savedFile);

        UploadFileVO uploadFileVO = new UploadFileVO(downloadUrlPrefix + fileName,
                fileName,
                suffixName);
        //if(uploadFileVO==null) {
        //    return null;
        //}
        //uploadFileVO.setFileName(fileName);
        //uploadFileVO.setUrl(appConfig.downloadUrlPrefix + fileName);
        //uploadFileVO.setSuffix(suffixName);
        return uploadFileVO;
    }

    // 下载文件
    public void downloadFile(HttpServletResponse response, String fileName) throws FileNotFoundException {
        File file = findFile(fileName);
        if(file == null){
            throw new FileNotFoundException("文件不存在");
        }

        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}