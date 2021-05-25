import javax.swing.*;

public class ImageType {
    String urlName;
    int xStart, yStart, width, height;
    JPanel canvas;

    public ImageType(String urlName, int xStart, int yStart, int width, int height, JPanel canvas) {
        this.urlName = urlName;
        this.xStart = xStart;
        this.yStart = yStart;
        this.width = width;
        this.height = height;
        this.canvas = canvas;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public int getXStart() {
        return xStart;
    }

    public void setXStart(int xStart) {
        this.xStart = xStart;
    }

    public int getYStart() {
        return yStart;
    }

    public void setYStart(int yStart) {
        this.yStart = yStart;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public JPanel getCanvas() {
        return canvas;
    }

    public void setCanvas(JPanel canvas) {
        this.canvas = canvas;
    }

    public void drawImageWithScale(){
        JLabel image = new JLabel(new ImageIcon(((new ImageIcon(this.urlName))).getImage().getScaledInstance(this.width , this.height, java.awt.Image.SCALE_SMOOTH)));
        image.setBounds(this.xStart, this.yStart, this.width,this.height);
        this.canvas.add(image);
    }
    public void drawImageWithoutScale(){
        JLabel image = new JLabel(new ImageIcon(this.urlName));
        image.setBounds(this.xStart, this.yStart, this.width,this.height);
        this.canvas.add(image);
    }
    public void drawGIF(){
        JLabel gif = new JLabel(new ImageIcon(this.urlName));
        gif.setBounds(this.xStart, this.yStart, this.width,this.height);
        this.canvas.add(gif);
    }
}
