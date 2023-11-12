package org.example;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Tesseract tesseract = new Tesseract();
        try {
//            tesseract.setDatapath("classpath://tessdata");
            tesseract.setDatapath("D:\\code\\github\\spring-boot-demo\\demo06\\src\\main\\resources\\tessdata\\tessdata");
//            tesseract.setLanguage("chi_sim");
            tesseract.setLanguage("money");

            // the path of your tess data folder
            // inside the extracted file
            final File file = new File("E:\\data\\回单原图\\money\\19.jpg");
            String text = tesseract.doOCR(file);

            // path of your image file
            System.out.print(text);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }


    // 首先使用高斯模糊来降低图像的噪声，然后使用Canny边缘检测器来检测图像的边缘。最后，使用膨胀操作来增强检测到的边缘。
    private void opencv() {
        Mat image = Imgcodecs.imread("image.jpg");

        // Reduce noise using Gaussian blur
        Mat blurredImage = new Mat();
        Imgproc.GaussianBlur(image, blurredImage, new Size(3, 3), 0);

        // Detect edges using Canny edge detector
        Mat edges = new Mat();
        Imgproc.Canny(blurredImage, edges, 50, 150);

        // Enhance edges using dilation
        Mat dilatedEdges = new Mat();
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
        Imgproc.dilate(edges, dilatedEdges, kernel);

        // Display the enhanced edges
        HighGui.imshow("Enhanced Edges", dilatedEdges);
        HighGui.waitKey();
    }
}