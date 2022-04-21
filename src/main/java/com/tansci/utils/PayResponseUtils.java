package com.tansci.utils;

import com.tansci.config.PaymentConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @ClassName： PayResponseUtils.java
 * @ClassPath： com.tansci.utils.PayResponseUtils.java
 * @Description： 支付返回数据工具类
 * @Author： tanyp
 * @Date： 2022/4/21 10:12
 **/
@Slf4j
public class PayResponseUtils {

    /**
     * @MonthName： getRequestBody
     * @Description： 获取微信请求头
     * @Author： tanyp
     * @Date： 2022/4/21 10:02
     * @Param： [request]
     * @return： java.lang.String
     **/
    public static String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuffer body = new StringBuffer();
        ServletInputStream stream = null;
        BufferedReader reader = null;
        try {
            stream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while (Objects.nonNull((line = reader.readLine()))) {
                body.append(line);
            }
        } catch (IOException e) {
            log.error("====微信支付|退款回调【解析请求体】异常，异常信息:{}", e);
            throw new IOException(e);
        } finally {
            try {
                if (Objects.nonNull(stream)) stream.close();
                if (Objects.nonNull(reader)) reader.close();
            } catch (IOException e) {
                log.error("====微信支付|退款回调【解析请求体】异常，异常信息:{}", e);
                throw new IOException(e);
            }
        }
        return body.toString();
    }

    /**
     * @MonthName： decryptResponseBody
     * @Description： 微信V3密钥解密响应体
     * @Author： tanyp
     * @Date： 2022/4/21 10:16
     * @Param： [associatedData, nonce, ciphertext]
     * @return： java.lang.String
     **/
    public static String decryptResponseBody(String associatedData, String nonce, String ciphertext) {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            SecretKeySpec key = new SecretKeySpec(PaymentConfig.WX_APIV3_KEY.getBytes(StandardCharsets.UTF_8), "AES");
            GCMParameterSpec spec = new GCMParameterSpec(128, nonce.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.DECRYPT_MODE, key, spec);
            cipher.updateAAD(associatedData.getBytes(StandardCharsets.UTF_8));

            byte[] bytes;
            try {
                bytes = cipher.doFinal(Base64Utils.decodeFromString(ciphertext));
            } catch (GeneralSecurityException e) {
                log.error("====微信支付|退款回调【解密】异常，异常信息:{}", e);
                throw new IllegalArgumentException(e);
            }
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            log.error("====微信支付|退款回调【解密】异常，异常信息:{}", e);
            throw new IllegalStateException(e);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            log.error("====微信支付|退款回调【解密】异常，异常信息:{}", e);
            throw new IllegalArgumentException(e);
        }
    }

}
