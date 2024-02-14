import java.awt.image.BufferedImage;

public class ImageOperations {

    //metoda pentru adunarea a doua imagini
    public static BufferedImage addImages(BufferedImage img1, BufferedImage img2) {
        //inregistrarea timpului de inceput al operatiei
        long startTime = System.currentTimeMillis();

        //obtinerea dimensiunilor imaginilor
        int width = img1.getWidth();
        int height = img1.getHeight();

        //crearea unei noi imagini pentru rezultat
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //parcurgerea fiecarui pixel al imaginilor si adunarea componentelor RGB
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = img1.getRGB(x, y);
                int rgb2 = img2.getRGB(x, y);

                //adunarea componentelor RGB
                int red = Math.min(255, Math.max(0, (rgb1 >> 16 & 0xFF) + (rgb2 >> 16 & 0xFF)));
                int green = Math.min(255, Math.max(0, (rgb1 >> 8 & 0xFF) + (rgb2 >> 8 & 0xFF)));
                int blue = Math.min(255, Math.max(0, (rgb1 & 0xFF) + (rgb2 & 0xFF)));

                //asamblarea noii culori
                int newRGB = (red << 16) | (green << 8) | blue;
                resultImage.setRGB(x, y, newRGB);
            }
        }

        //inregistrarea timpului de final al operatiei
        long endTime = System.currentTimeMillis();
        //calcularea timpului de executie
        long executionTime = endTime - startTime;

        displayInformation("Adunare", img1, img2, executionTime);

        return resultImage;
    }

    //metoda pentru scaderea a doua imagini
    public static BufferedImage subtractImages(BufferedImage img1, BufferedImage img2) {
        //inregistrarea timpului de inceput al operatiei
        long startTime = System.currentTimeMillis();

        //obtinerea dimensiunilor imaginilor
        int width = img1.getWidth();
        int height = img1.getHeight();

        //crearea unei noi imagini pentru rezultat
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //parcurgerea fiecarui pixel al imaginilor si scaderea componentelor RGB
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = img1.getRGB(x, y);
                int rgb2 = img2.getRGB(x, y);

                //ccaderea componentelor RGB
                int red = Math.min(255, Math.max(0, Math.abs((rgb1 >> 16 & 0xFF) - (rgb2 >> 16 & 0xFF))));
                int green = Math.min(255, Math.max(0, Math.abs((rgb1 >> 8 & 0xFF) - (rgb2 >> 8 & 0xFF))));
                int blue = Math.min(255, Math.max(0, Math.abs((rgb1 & 0xFF) - (rgb2 & 0xFF))));

                //asamblarea noii culori
                int newRGB = (red << 16) | (green << 8) | blue;
                resultImage.setRGB(x, y, newRGB);
            }
        }

        //inregistrarea timpului de final al operatiei
        long endTime = System.currentTimeMillis();
        //calcularea timpului de executie
        long executionTime = endTime - startTime;

        displayInformation("Scadere", img1, img2, executionTime);

        return resultImage;
    }

    //metoda pentru afisarea informatiilor despre operatie
    private static void displayInformation(String operation, BufferedImage img1, BufferedImage img2, long executionTime) {
        System.out.println("\nOperatie: " + operation);
        System.out.println("Dimensiuni Imagine 1: " + img1.getWidth() + "x" + img1.getHeight());
        System.out.println("Dimensiuni Imagine 2: " + img2.getWidth() + "x" + img2.getHeight());
        System.out.println("Timp de Executie: " + executionTime + " milisecunde");
        System.out.println("------------------------------");
    }
}
