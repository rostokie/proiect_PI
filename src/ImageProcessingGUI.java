import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class ImageProcessingGUI extends JFrame {
    private BufferedImage img1;
    private BufferedImage img2;
    private OpenImageListener openImageListener; //interfata ActionListener este folosita pentru a gestiona evenimentele butoanelor
    public ImageProcessingGUI() {
        setTitle("Procesare Imagine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BorderLayout());

        JButton addButton = new JButton("Adunare");
        JButton subtractButton = new JButton("Scadere");

        //adaugare ActionListener pentru butonul de adunare
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processImage(ImageOperation.ADD);
            }
        });

        //adaugare ActionListener pentru butonul de scadere
        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processImage(ImageOperation.SUBTRACT);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);

        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //metoda pentru procesarea imaginilor in funcție de operatia selectata
    private void processImage(ImageOperation operation) {
        if (img1 == null || img2 == null) {
            JOptionPane.showMessageDialog(this, "Selectați doua imagini mai întai.", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BufferedImage resultImage = null;

        switch (operation) {
            case ADD:
                resultImage = ImageOperations.addImages(img1, img2);
                break;
            case SUBTRACT:
                resultImage = ImageOperations.subtractImages(img1, img2);
                break;
        }

        if (resultImage != null) {
            //salveaza rezultatul cu 8 sau 24 biti, în functie de tipul imaginii
            int bitsPerPixel = resultImage.getColorModel().getPixelSize();
            ImageUtil.saveImage(resultImage, "C:/Users/roxan/OneDrive/Documentos/Info III/Semestru I/Procesarea Imaginilor/Save Images/result_image.bmp", "bmp", bitsPerPixel);
            ImageUtil.checkSavedImageDetails("C:/Users/roxan/OneDrive/Documentos/Info III/Semestru I/Procesarea Imaginilor/Save Images/result_image.bmp");
            ImageUtil.displayImage(resultImage, "Result Image");
        }
    }

    public void setImage1(BufferedImage img) {
        displayImageInPanel(img, "Image 1");
        img1 = img;
    }

    public void setImage2(BufferedImage img) {
        displayImageInPanel(img, "Image 2");
        img2 = img;
    }

    private void displayImageInPanel(BufferedImage img, String title) {
        if (img == null) {
            JOptionPane.showMessageDialog(this, "Imaginea nu poate fi afisata.", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ImagePanel imagePanel = new ImagePanel();
        imagePanel.setFitToScreen(false);
        imagePanel.setImage(img);

        frame.setContentPane(imagePanel);
        frame.pack();
        frame.setVisible(true);
    }

    //interfata OpenImageListener este utilizata pentru deschiderea imaginilor
    public interface OpenImageListener {
        void openImage1(BufferedImage img);
        void openImage2(BufferedImage img);
    }

    //metoda pentru a seta un ascultator pentru deschiderea imaginilor
    public void setOpenImageListener(OpenImageListener listener) {
        this.openImageListener = listener;
    }

    //enumeratia pentru operatiile pe imagini
    private enum ImageOperation {
        ADD,
        SUBTRACT
    }
}
