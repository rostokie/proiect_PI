import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;

public class ImageUtil {

    //metoda pentru incarcarea imaginii din fisier
    public static BufferedImage loadImage(String filename){
        BufferedImage img = null;

        try {
            img = ImageIO.read(new File(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return img;
    }

    //metoda pentru salvarea imaginii intr-un fisier, cu specificarea numarului de biti per pixel
    public static void saveImage(BufferedImage img, String fileName, String formatName, int bitsPerPixel) {
        try {
            //verificare si salvare in functie de numarul de biti per pixel
            if (bitsPerPixel == 8) {
                //salvare pe 8 biti
                BufferedImage grayscaleImage = convertToGrayscale(img);
                ImageIO.write(grayscaleImage, formatName, new File(fileName));
            } else if (bitsPerPixel == 24 || bitsPerPixel == 32) {
                //salvare pe 24 biti
                ImageIO.write(img, formatName, new File(fileName));
            } else {
                throw new IllegalArgumentException("Bits per pixel necunoscut: " + bitsPerPixel);
            }
        } catch (IOException e) {
            throw new RuntimeException("Eroare la salvarea imaginii: " + e.getMessage());
        }
    }

    //metoda pentru convertirea imaginii color in imagine grayscale
    private static BufferedImage convertToGrayscale(BufferedImage colorImage) {
        BufferedImage grayscaleImage = new BufferedImage(colorImage.getWidth(), colorImage.getHeight(),
                BufferedImage.TYPE_BYTE_GRAY);

        Graphics g = grayscaleImage.getGraphics();
        g.drawImage(colorImage, 0, 0, null);
        g.dispose();

        return grayscaleImage;
    }

    //metoda pentru afisarea imaginii intr-un panou
    public static void displayImage(BufferedImage img, String title){
        if(img == null)
            return;
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImagePanel imagePanel = new ImagePanel();
        imagePanel.setFitToScreen(false);
        imagePanel.setImage(img);

        frame.setContentPane(imagePanel);
        frame.pack();
        frame.setVisible(true);
    }

    //metoda pentru verificarea detaliilor imaginii
    public static void checkImageDetails(BufferedImage img) {
        int dataType = img.getRaster().getDataBuffer().getDataType();
        int numBands = img.getRaster().getNumBands();

        System.out.println("Tip de date al imaginii: " + dataType);
        System.out.println("Numarul de benzi (canale de culoare): " + numBands);

        if (dataType == DataBuffer.TYPE_BYTE && numBands == 1) {
            System.out.println("Imagine pe 8 biti");
        } else if (dataType == DataBuffer.TYPE_BYTE && numBands == 3) {
            System.out.println("Imagine pe 24 biti");
        } else {
            System.out.println("Alte tipuri de imagini");
        }
    }

    //metoda pentru verificarea detaliilor imaginii salvate
    public static void checkSavedImageDetails(String fileName) {
        try {
            BufferedImage savedImage = ImageIO.read(new File(fileName));
            int dataType = savedImage.getRaster().getDataBuffer().getDataType();
            int numBands = savedImage.getRaster().getNumBands();
            int bitsPerPixel = savedImage.getColorModel().getPixelSize();

            System.out.println("Detalii imagine salvata:");
            System.out.println("Tip de date al imaginii: " + dataType);
            System.out.println("Numarul de benzi (canale de culoare): " + numBands);

            if (dataType == DataBuffer.TYPE_BYTE && numBands == 1) {
                System.out.println("Imagine pe 8 biti (grayscale)");
            } else if (dataType == DataBuffer.TYPE_BYTE && numBands == 3) {
                System.out.println("Imagine pe 24 biti (color)");
            } else {
                System.out.println("Alte tipuri de imagini");
            }

            System.out.println("Numarul de biti per pixel: " + bitsPerPixel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
