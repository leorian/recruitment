package me.gacl.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiezhonggui on 16-4-24.
 */
public class ZxingCodeTest {
    public static void main(String[] args) throws WriterException, IOException {
        String content = "HelloWorld!!!http://blog.csdn.net/gao36951";
        String filePath = "/home/xiezhonggui/zxing/weixin.jpg";
        File file = new File(filePath);
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        //hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //hints.put(EncodeHintType.MARGIN, 0);
        //hints.put(EncodeHintType.MAX_SIZE, 350);
        //hints.put(EncodeHintType.MIN_SIZE, 100);
        MultiFormatWriter multiFormatWriter = null;
        BitMatrix bm = null;
        BufferedImage image = null;
        multiFormatWriter = new MultiFormatWriter();
        bm = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300, hints);
        int w = bm.getWidth();
        int h = bm.getHeight();
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                image.setRGB(x, y, bm.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        ImageIO.write(image, "jpeg", file);
        BufferedImage bufferedImage = ImageIO.read(file);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        BufferedImage logo = ImageIO.read(new File("/home/xiezhonggui/zxing/bd_logo.png"));
        int widthLogo = logo.getWidth(null) > image.getWidth()*2/10?(image.getWidth()*2/10):logo.getWidth(null);
        int heightLogo = logo.getHeight(null) > image.getHeight()*2/10?(image.getHeight()*2/10):logo.getHeight(null);
        int x = (bufferedImage.getWidth() - widthLogo) / 2;
        int y = (bufferedImage.getHeight() - heightLogo) / 2;
        graphics2D.drawImage(logo, x, y, widthLogo, heightLogo, null);
        graphics2D.drawRoundRect(x, y , widthLogo, heightLogo, 15, 15);
        graphics2D.setStroke(new BasicStroke(2));
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawRect(x, y, widthLogo, heightLogo);
        graphics2D.dispose();
        logo.flush();
        bufferedImage.flush();
        ImageIO.write(bufferedImage, "png", new File("/home/xiezhonggui/zxing/"+new Date()+".png"));


    }
}
