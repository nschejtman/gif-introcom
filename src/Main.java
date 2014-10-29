import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStreamImpl;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        try {
            //Get path
            String path = System.getProperty("user.dir");

            //Get image
            BufferedImage originalImage = ImageIO.read(new File("image.gif"));
            byte[] imageInByte = getByteArray(originalImage);

            //Set black to red
            imageInByte[13] = 127;       //R
            imageInByte[14] = 0;         //G
            imageInByte[15] = 0;         //B

            //Get and save altered image
            BufferedImage alteredImage = getImage(imageInByte);
            saveImage(alteredImage, path, "alteredImage", "gif");

            //Display images
            displayImage(originalImage);
            displayImage(alteredImage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayImage(BufferedImage image) {
        JFrame f = new JFrame("Image.gif");

        ImageIcon icon = new ImageIcon(image);
        JLabel label = new JLabel(icon);

        f.getContentPane().add(label);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private static byte[] getByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "gif", baos);
        baos.flush();
        byte[] byteArray = baos.toByteArray();
        baos.close();
        return byteArray;
    }

    private static BufferedImage getImage(byte[] imageInByte) throws IOException {
        InputStream in = new ByteArrayInputStream(imageInByte);
        return ImageIO.read(in);
    }

    private static void saveImage(BufferedImage image, String path, String name, String extension) throws IOException {
        ImageIO.write(image, extension, new File(path + name + "." + extension));
    }

}
