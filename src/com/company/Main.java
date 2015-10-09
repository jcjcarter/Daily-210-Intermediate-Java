package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Dimension d = new Dimension(sc.nextInt(), sc.nextInt());
        Color leftColor = new Color(sc.nextInt(), sc.nextInt(), sc.nextInt());
        Color rightColor = new Color(sc.nextInt(), sc.nextInt(), sc.nextInt());

        BufferedImage img = createImage(d, leftColor, rightColor);
        writeToFile(img, "rendered_image.png");
    }

    private static BufferedImage createImage(Dimension d, Color leftColor, Color rightColor) {
        BufferedImage img = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < d.width; x++) {
            double grad = x * 1.0 / d.width;
            int colorAsInt = findGradColor(leftColor, rightColor, grad);
            for (int y = 0; y < d.height; y++) {
                img.setRGB(x, y, colorAsInt);
            }
        }
        return img;
    }

    private static int findGradColor(Color leftColor, Color rightColor, double grad) {
        int r = leftColor.getRed() + (int)(grad * (rightColor.getRed() - leftColor.getRed()));
        int g = leftColor.getGreen() + (int)(grad * (rightColor.getGreen() - leftColor.getGreen()));
        int b = leftColor.getBlue() + (int)(grad * (rightColor.getBlue() - leftColor.getBlue()));
        return rgbToInt(r, g, b);
    }

    private static int rgbToInt(int r, int g, int b) {
        return (r << 8 | g) << 8 | b;
    }

    private static void writeToFile(BufferedImage img, String fileName) {
        try {
            ImageIO.write(img, "png", new File(fileName));
        }
        catch (IOException ex) {
            System.err.println("Unable to write png file: " + ex.getMessage());
        }
    }
}
