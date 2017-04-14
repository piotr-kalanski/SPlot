package org.knowm.xchart;

import org.knowm.xchart.internal.chartpart.Chart;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Adding additional functionalities to BitmapEncoder e.g. possibility to save list of charts to file
 */
public class BitmapEncoderExtension {
    public static void saveBitmap(List<Chart> charts, Integer rows, Integer cols, String fileName, BitmapEncoder.BitmapFormat bitmapFormat) throws IOException {
        BufferedImage bufferedImage = mergeImages(
            charts.stream().map(BitmapEncoder::getBufferedImage).collect(Collectors.toList()),
            rows,
            cols
        );

        OutputStream out = new FileOutputStream(BitmapEncoder.addFileExtension(fileName, bitmapFormat));
        try {
            ImageIO.write(bufferedImage, bitmapFormat.toString().toLowerCase(), out);
        } finally {
            out.close();
        }
    }

    private static BufferedImage mergeImages(List<BufferedImage> images, Integer rows, Integer cols) {
        BufferedImage first = images.get(0);
        int singleImageWidth = first.getWidth();
        int singleImageHeight = first.getHeight();
        int totalWidth = singleImageWidth * cols;
        int totalHeight = singleImageHeight * rows;
        BufferedImage mergedImage = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics g = mergedImage.getGraphics();
        for(int row=0; row < rows; row++) {
            for(int col=0; col < cols; col++) {
                BufferedImage image = images.get(row*cols + col);
                g.drawImage(image, col*singleImageWidth, row*singleImageHeight, null);
            }
        }

        return mergedImage;
    }

}
