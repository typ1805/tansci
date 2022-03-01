package com.tansci.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;

/**
 * @ClassName： QRCodeUtils.java
 * @ClassPath： com.tansci.utils.QRCodeUtils.java
 * @Description： 二维码生成解析工具类
 * @Author： tanyp
 * @Date： 2022/3/1 10:53
 **/
public class QRCodeUtils {

    // 编码格式,采用utf-8
    private static final String UNICODE = "utf-8";
    // 图片格式
    private static final String FORMAT = "png";
    // 二维码宽度,单位：像素pixels
    private static final int QRCODE_WIDTH = 400;
    // 二维码高度,单位：像素pixels
    private static final int QRCODE_HEIGHT = 400;

    /**
     * @MonthName： crateQRCode
     * @Description： 创建二维码
     * @Author： tanyp
     * @Date： 2022/3/1 11:38
     * @Param： [content, width, height]
     * @return： java.lang.String
     **/
    public static String crateQRCode(String content, Integer width, Integer height) {
        String resultImage = "";
        if (!StringUtils.isEmpty(content)) {

            @SuppressWarnings("rawtypes")
            HashMap<EncodeHintType, Comparable> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, UNICODE);
            // 指定二维码的纠错等级为中级
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            // 设置图片的边距
            hints.put(EncodeHintType.MARGIN, 2);

            if (Objects.isNull(width) || Objects.isNull(height)) {
                width = QRCODE_WIDTH;
                height = QRCODE_HEIGHT;
            }

            try {
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                MatrixToImageWriter.writeToStream(bitMatrix, FORMAT, outputStream);
                Base64.Encoder encoder = Base64.getEncoder();

                /**
                 * 原生转码前面没有 data:image/png;base64 这些字段，返回给前端是没法被解析，可让前端加，也能够在下面加上
                 */
                resultImage = "data:image/png;base64," + encoder.encodeToString(outputStream.toByteArray());
                return resultImage;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}