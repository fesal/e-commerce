package com.ecomerce.demo.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ecomerce.demo.controller.BaseAbstractController;
import org.springframework.web.multipart.MultipartFile;


public class FileUtil {
//	public static void saveImage(MultipartFile image, String path) {
//		FileOutputStream fos;
//		try {
//			fos = new FileOutputStream(path);
//			fos.write(image.getBytes());
//			fos.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public static void saveImage(MultipartFile image, String imageLocation, String imagteName, String imageExtension) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(imageLocation+ BaseAbstractController.NORMAL_IMAGE+imagteName+imageExtension);
			fos.write(image.getBytes());
			fos.close();
			/*FileUtil fu = new FileUtil();
			fu.saveImageWithScale(imageLocation+BaseAbstractController.NORMAL_IMAGE+imagteName+imageExtension, imageLocation, imagteName, imageExtension, 30, 30);
			fu.saveImageWithScale(imageLocation+BaseAbstractController.NORMAL_IMAGE+imagteName+imageExtension, imageLocation, imagteName, imageExtension, 130, 130);
			fu.saveImageWithScale(imageLocation+BaseAbstractController.NORMAL_IMAGE+imagteName+imageExtension, imageLocation, imagteName, imageExtension, 156, 156);
		*/} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteImage(String path) {
		File f = new File(path);
		f.delete();
	}
	
	public static void deleteImage(String imageLocation, String temporaryImageName, String imageExtension) {
		if(temporaryImageName!=null){
			File f = new File(imageLocation+BaseAbstractController.NORMAL_IMAGE+temporaryImageName+imageExtension);
			f.delete();
			f = new File(imageLocation+BaseAbstractController._30x30+temporaryImageName+imageExtension);
			f.delete();
			f = new File(imageLocation+BaseAbstractController._130x130+temporaryImageName+imageExtension);
			f.delete();
			f = new File(imageLocation+BaseAbstractController._156x156+temporaryImageName+imageExtension);
			f.delete();
		}
	}

	public void saveImageWithScale(String originalImagePath, String imageLocation, String imagteName, String imageExtension, int scaledWidth, int scaledHeight) {
		try {
			Image image = ImageIO.read(new File(originalImagePath));
			BufferedImage bi = createResizedCopy(image, scaledWidth, scaledHeight, true);
			File outputfile = new File(imageLocation +scaledWidth + "x" + scaledHeight + "/" + imagteName + imageExtension);
			ImageIO.write(bi, imageExtension.substring(1), outputfile);
		} catch (IOException e) {

		}
	}

	private BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight,
			boolean preserveAlpha) {
		int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
		Graphics2D g = scaledBI.createGraphics();
		if (preserveAlpha) {
			g.setComposite(AlphaComposite.Src);
		}
		g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
		g.dispose();
		return scaledBI;
	}

}
