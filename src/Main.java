import javax.swing.*;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //crearea unei instante a clasei ImageProcessingGUI
                ImageProcessingGUI gui = new ImageProcessingGUI();

                //setarea operatiei de inchidere a ferestrei principale la apasarea butonului de inchidere
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                //setarea unui ascultator pentru deschiderea imaginilor in fereastra principala
                gui.setOpenImageListener(new ImageProcessingGUI.OpenImageListener() {
                    @Override
                    public void openImage1(BufferedImage img) {
                        //setarea primei imagini in fereastra principala
                        gui.setImage1(img);
                    }

                    @Override
                    public void openImage2(BufferedImage img) {
                        //setarea celei de-a doua imagini in fereastra principala
                        gui.setImage2(img);
                    }
                });

                //adaugarea imaginilor pentru operatii
                BufferedImage img1 = ImageUtil.loadImage("./test_images/1-500x250-3.jpg");
                BufferedImage img2 = ImageUtil.loadImage("./test_images/2-500x250-2.jpg");

                //setarea primelor doua imagini in fereastra principala
                gui.setImage1(img1);
                gui.setImage2(img2);

                //afisarea detaliilor primei imagini
                System.out.println("Verificare detalii pentru prima imagine:");
                ImageUtil.checkImageDetails(img1);

                //afisarea detaliilor celei de-a doua imagini
                System.out.println("\nVerificare detalii pentru a doua imagine:");
                ImageUtil.checkImageDetails(img2);
            }
        });
    }
}
