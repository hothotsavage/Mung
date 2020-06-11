package site.marqstree.collegeentrance.server.controller;

import org.springframework.web.bind.annotation.PostMapping;

public class FileController {

    @PostMapping("/uploadimage")
    public ServerResponseVO<UploadFileVO> uploadImage(HttpServletRequest request,
                                                      @RequestParam("uploadimagefile") MultipartFile uploadFile,
                                                      @RequestParam("openid") String openId) throws IllegalStateException, IOException {
        // 如果文件不为空，写入上传路径，进行文件上传
        if (uploadFile.isEmpty()) {
            return ServerResponseVO.createByErrorEnum(ResponseEnum.UPLOAD_FAIL);
        }

        // 将上传文件保存本地，返回本地文件信息
        UploadFileVO uploadFileVO = fileService.saveFile(uploadFile);
        if(uploadFileVO==null){
            return ServerResponseVO.createByErrorMessage("保存失败");
        }

        return ServerResponseVO.createBySuccess(uploadFileVO);
    }
}
