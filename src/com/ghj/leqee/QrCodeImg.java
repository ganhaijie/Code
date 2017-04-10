package com.ghj.leqee;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;

import com.swetake.util.Qrcode;

public class QrCodeImg extends HttpServlet {
	final static int HEIGHT = 236;

	final static int WIDTH = 236;

	final static String CONTENT = "哈哈哈哈哈哈";

	final static String PATH = "picture.png";// 生成二维码存放路径

	public static void main(String[] args) throws IOException {
		QrCodeImg.getImg();
	}

	public static void getImg() throws IOException {
		Qrcode qrcode = new Qrcode();

		qrcode.setQrcodeErrorCorrect('M');

		qrcode.setQrcodeEncodeMode('B');

		qrcode.setQrcodeVersion(15);

		BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		Graphics2D graphics2D = bufferedImage.createGraphics();

		graphics2D.setBackground(Color.white);

		graphics2D.clearRect(0, 0, WIDTH, HEIGHT);

		graphics2D.setColor(Color.black);

		byte[] contentBytes = CONTENT.getBytes("utf-8");

		boolean[][] codeOut = qrcode.calQrcode(contentBytes);

		int offset = 3; // 设置偏移量

		for (int i = 0; i < codeOut.length; i++) {

			for (int j = 0; j < codeOut.length; j++) {

				if (codeOut[i][j]) {

					graphics2D.fillRect(j * 3 + offset, i * 3 + offset, 3, 3);
				}
			}

		}

		// 释放资源,清空缓存

		graphics2D.dispose();

		bufferedImage.flush();

		// 写出二维码

		File imgFile = new File(PATH);

		ImageIO.write(bufferedImage, "png", imgFile);
	}
}
