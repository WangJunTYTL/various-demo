package com.baidu.ueditor.upload;

import com.BeanFactory;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.hehua.framework.image.ImageService;
import com.hehua.framework.image.domain.Image;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BinaryUploader {

    public static final State save(HttpServletRequest request,
                                   Map<String, Object> conf) {
        FileItemStream fileStream = null;
        boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;

        if (!ServletFileUpload.isMultipartContent(request)) {
            return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
        }

        ServletFileUpload upload = new ServletFileUpload(
                new DiskFileItemFactory());

        if (isAjaxUpload) {
            upload.setHeaderEncoding("UTF-8");
        }

        try {
            FileItemIterator iterator = upload.getItemIterator(request);

            while (iterator.hasNext()) {
                fileStream = iterator.next();

                if (!fileStream.isFormField())
                    break;
                fileStream = null;
            }

            if (fileStream == null) {
                return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
            }

            InputStream is = fileStream.openStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            out.write(buffer);
            ImageService imageService = BeanFactory.getBean(ImageService.class);
            try {
                byte[] imageBytes = buffer;
                Image image = imageService.createImage(imageBytes);
                State state = new BaseState(true);
                state.putInfo("size", image.getSize());
                state.putInfo("title", image.getFid());
                state.putInfo("state", "SUCCESS");
                state.putInfo("original", fileStream.getName());
                state.putInfo("type", image.getFormat());
                state.putInfo("url", image.getUrl());
                state.putInfo("id", image.getId());
                state.putInfo("fid", image.getFid());
                is.close();
                return state;
            } catch (IOException e) {
                return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);

        return list.contains(type);
    }
}
