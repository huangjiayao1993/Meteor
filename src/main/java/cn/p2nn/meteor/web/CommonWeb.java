package cn.p2nn.meteor.web;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.IdUtil;
import cn.p2nn.meteor.model.Result;
import cn.p2nn.meteor.utils.OssUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 常规控制器
 *
 * @author huangjiayao1993
 */
@Slf4j
@RestController
@RequestMapping("common")
@RequiredArgsConstructor
public class CommonWeb extends BaseWeb {

    /**
     * 通用上传
     *
     * @param file       文件
     * @param objectKey  云存储objectKey
     * @param businessId 业务标识
     * @return
     */
    @SneakyThrows
    @PostMapping("upload")
    public Result upload(@RequestParam MultipartFile file, @RequestParam String objectKey, @RequestParam(required = false) String businessId) {
        // 获取文件后缀
        String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(StrPool.DOT));
        // 获取时间戳
        String current = String.valueOf(DateUtil.current());
        // 获取随机数
        String uuid = IdUtil.fastSimpleUUID();
        // 拼接文件名称
        String fileName = StringUtils.join(uuid, current, fileSuffix);
        // 业务ID
        if (StringUtils.isBlank(businessId)) {
            businessId = DateUtil.format(DateUtil.date(), DatePattern.PURE_DATE_PATTERN);
        }
        // 拼接云存储objectKey
        String ok = StringUtils.join(objectKey, StrPool.SLASH, businessId, StrPool.SLASH, fileName);
        // 上传云存储
        String url = OssUtil.upload(file.getInputStream(), ok);
        return Result.success(url);
    }

}
