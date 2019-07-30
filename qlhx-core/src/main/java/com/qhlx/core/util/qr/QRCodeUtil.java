package com.qhlx.core.util.qr;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/30 11:04
 * @Description desc:
 */


public class QRCodeUtil {

    /**
     * 字符集
     */
    private static final String CHARSET = "utf-8";
    /**
     * 二维码格式
     */
    private static final String FORMAT_NAME = "JPG";
    /**
     * 二维码尺寸
     */
    private static final int QRCODE_SIZE = 400;
    /**
     * LOGO宽度
     */
    private static final int LOGO_SIZE = 80;


    /**
     * 生成二维码
     * @param content 吸入内容
     * @param logoPath 二维码中间的图片路径
     * @param toPath 生成的二维码
     * @throws Exception
     */
    public static void encode(String content, String logoPath, String toPath) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, logoPath, true);
        mkdirs(toPath);
        ImageIO.write(image, FORMAT_NAME, new File(toPath));
    }

    public static void encode(String content, String logoPath, OutputStream output)
            throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, logoPath, true);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    /**
     * 生成二维码
     * @param content
     * @param logoPath
     * @param needCompress
     * @return
     * @throws Exception
     */
    public static BufferedImage encode(String content, String logoPath, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, logoPath, needCompress);
        return image;
    }

    /**
     * 解析二维码内容
     * @param file
     * @return
     * @throws Exception
     */
    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    /**
     * 解析二维码内容
     * @param path
     * @return
     * @throws Exception
     */
    public static String decode(String path) throws Exception {
        return QRCodeUtil.decode(new File(path));
    }


    /**
     * 创建图片
     * @param content
     * @param logoPath
     * @param needCompress
     * @return
     * @throws Exception
     */
    private static BufferedImage createImage(String content, String logoPath, boolean needCompress) throws Exception {
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
                hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (logoPath == null || "".equals(logoPath)) {
            return image;
        }
        // 插入图片
        QRCodeUtil.insertImage(image, logoPath, needCompress);
        return image;
    }


    /**
     * 插图图片
     * @param source
     * @param logoPath
     * @param needCompress
     * @throws Exception
     */
    private static void insertImage(BufferedImage source, String logoPath, boolean needCompress) throws Exception {
        File file = new File(logoPath);
        if (!file.exists()) {
            System.err.println("" + logoPath + "   该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(logoPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > LOGO_SIZE) {
                width = LOGO_SIZE;
            }
            if (height > LOGO_SIZE) {
                height = LOGO_SIZE;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }


    /**
     * 创建文件夹
     * @param toPath
     */
    private static void mkdirs(String toPath) {
        File file = new File(toPath);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

}