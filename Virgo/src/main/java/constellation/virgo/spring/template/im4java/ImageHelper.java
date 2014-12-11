package constellation.virgo.spring.template.im4java;

import java.io.IOException;

import org.im4java.core.CompositeCmd;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.StandardStream;

public class ImageHelper {

	public static void main(String[] args) throws IOException,
			InterruptedException, IM4JavaException {
		compositeCardImage("D://logo.png", "D://template.png", "大地数字影院北京延庆金锣湾",
				"8888 8888", "D://card.png");
	}

	public static void compositeCardImage(String logoImagePath,
			String backgroundImagePath, String cinemaName, String cardNo,
			String CardImagePath) throws IOException, InterruptedException,
			IM4JavaException {
		boolean blWinOS = System.getProperty("os.name").toLowerCase()
				.indexOf("win") >= 0;
		IMOperation op = new IMOperation();
		op.addRawArgs("-geometry", "+20+20");
		op.addImage();
		op.addImage();
		op.addImage();

		boolean blGraphicsMagick = true;
		CompositeCmd cmdComposite = new CompositeCmd(blGraphicsMagick);
		if (blWinOS) { // linux无需设置此项
			cmdComposite
					.setSearchPath("C://Program Files//GraphicsMagick-1.3.19-Q8");
		}
		cmdComposite.setErrorConsumer(StandardStream.STDERR);
		cmdComposite.run(op, logoImagePath, backgroundImagePath, CardImagePath);

		ConvertCmd cmdConvert = new ConvertCmd(blGraphicsMagick);
		op = new IMOperation();
		// TODO 在Linux下需要安装微软雅黑字体
		op.font("Microsoft-YaHei");
		op.pointsize(15);
		op.fill("white");
		// op.encoding("UTF-8");
		// TODO UTF-8转码GBK，奇数中文仍为乱码
		op.draw("gravity SouthWest text 20,20 '"
				+ (blWinOS ? new String(cinemaName.getBytes("UTF-8"), "GBK")
						: cinemaName) + "'");
		// System.out.println(op);
		op.addImage();
		op.addImage();
		cmdConvert.setErrorConsumer(StandardStream.STDERR);
		cmdConvert.run(op, CardImagePath, CardImagePath);

		// op = new GMOperation();
		// op.font("Microsoft-YaHei");
		// op.pointsize(15);
		// op.fill("white");
		// op.draw("gravity NorthEast text 20,30 'XXCard'");
		// op.addImage();
		// op.addImage();
		// cmdConvert.setErrorConsumer(StandardStream.STDERR);
		// cmdConvert.run(op, CardImagePath, CardImagePath);

		op = new IMOperation();
		op.font("Microsoft-YaHei");
		op.pointsize(15);
		op.fill("white");
		op.draw("gravity SouthEast text 20,20 '"
				+ new String("会员卡号".getBytes("UTF-8"), "GBK") + "\n" + cardNo
				+ "'");
		op.addImage();
		op.addImage();
		cmdConvert.setErrorConsumer(StandardStream.STDERR);
		cmdConvert.run(op, CardImagePath, CardImagePath);
	}
}
