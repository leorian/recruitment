package me.gacl.zxing;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xiezhonggui on 16-4-24
 */
public class ZxingGenarateTest {

    @Test
    public void testGenarateQrCode() {
        String text = "HelloWorld!!!";
        int width = 300;
        int height = 300;
        String format = "png";
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            File outputFile = new File("/home/xiezhonggui/zxing" + File.separator + UUID.randomUUID() + "png");
            try {
                MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testParserQrCode() {
        MultiFormatReader formatReader = new MultiFormatReader();
        try {
            BufferedImage image = ImageIO.read(new File("/home/xiezhonggui/zxing/zxing.png"));
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            try {
                Result result = formatReader.decode(binaryBitmap, hints);
                System.out.println("parser result: " + result.toString());
                System.out.println("orcode type: " + result.getBarcodeFormat());
                System.out.println("orcode Content: " + result.getText());
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
