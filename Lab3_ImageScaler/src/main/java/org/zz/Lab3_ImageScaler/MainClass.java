package org.zz.Lab3_ImageScaler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

public class MainClass {

	public static void main(String[] args) throws Exception {
		if (args.length == 3 | args.length == 4)
		{
			try 
			{
				File imageinputfile = new File(args[0]);
				File imageoutputfile = new File(args[1]);
				BufferedImage imagein = ImageIO.read(imageinputfile);
				if (args.length == 4)
				{
					BufferedImage imageout = Scalr.resize(imagein, Scalr.Mode.FIT_EXACT, 
							Integer.parseInt(args[2]), Integer.parseInt(args[3]));
					ImageIO.write(imageout, "jpg", imageoutputfile);
				}
				else
				{
					BufferedImage imageout = Scalr.resize(imagein, Scalr.Mode.FIT_TO_WIDTH,
							Integer.parseInt(args[2]), 0);
					ImageIO.write(imageout, "jpg", imageoutputfile);
				}
			}
			catch (NumberFormatException exc)
			{
				System.out.println("Invalid value of width or height");
				System.out.println("Type this arguments: input_file_path output_file_path width [height]");
			}
			catch (IIOException exc)
			{
				System.out.println("Input file doesn't exist");
				System.out.println("Type this arguments: input_file_path output_file_path width [height]");
			}
			catch (FileNotFoundException exc)
			{
				System.out.println("Invalid path of output file");
				System.out.println("Type this arguments: input_file_path output_file_path width [height]");
			}
		}
		else
		{
			System.out.println("Wrong number of arguments");
			System.out.println("Type this arguments: input_file_path output_file_path width [height]");
		}
	}

}
