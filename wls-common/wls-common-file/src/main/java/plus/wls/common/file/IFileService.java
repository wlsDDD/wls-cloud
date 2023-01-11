package plus.wls.common.file;

import java.io.File;

/**
 * 文件服务接口
 *
 * @author wls
 * @date 2022-07-14 14:28:34
 * @since 1.0.0
 */
public interface IFileService {
    
    /**
     * 上传文件
     *
     * @param file 文件
     *
     * @return {@link String }
     *
     * @author wls
     * @date 2022-07-14 14:30:09
     * @since 1.0.0
     */
    String uploadFile(File file);
    
    /**
     * 下载文件
     *
     * @param url url
     *
     * @return {@link String }
     *
     * @author wls
     * @date 2022-07-14 14:30:59
     * @since 1.0.0
     */
    String downloadFile(String url);
    
}
