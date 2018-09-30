import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


/** class to analyze image and tell end of a feature on fingerprint*/
public class FingerprintAnalyzer
{
  BufferedImage image;
  int length;
  int width;
  // Create a new, empty image that will store the fingerprint markings
  BufferedImage out;

/** constructor reads in image from file and makes new buffered image
* @param fileName name of file
*/
  public FingerprintAnalyzer(String fileName)
  {
    try
    {
      image = ImageIO.read(new File(fileName));
    }
    catch(FileNotFoundException e)
    {
      System.out.println("File does not exist");
    }
    catch(IOException e)
    {
      System.out.println("idk what this error is");
    }
    length = image.getHeight();
    width = image.getWidth();
    out = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
  }

/** method to find features in image and make a new image with red pixels indicating end of feature*/
  public void findFeatures()
  {
    /* CODE THAT CHECKS EDGE PIXELS -- NOT NECESSARY
    //checks horizontal edge cases
    for(int i=1;i<width-1;i++)
    {
      Color pixelTop = new Color(image.getRGB(i,0));
      Color pixelBot = new Color(image.getRGB(i,length-1));
      if(pixelTop.getBlue() == 0)
      {
        int nCount=0; //variable to count the number of neighbors that a specific pixel has
        out.setRGB(i,0,0)
        for(int y = 0;y<2;y++)
        {
          for(int x = i-1;x<=(i+1);x++)
          {
            Color testPixel = new Color(image.getRGB(x,y));
            if(testPixel.getBlue()==0)
            {
              nCount++;
            }
          }
        }
        if(nCount<=2)
        {
          // Set the RGB value at (17, 201)(x, y) coordinate to red
          // Use a hexadecimal representation for the value of "red"
          out.setRGB(i,0,0xff0000);
        }
      }
      else
      {
        out.setRGB(i,0,0xffffff);
      }
      if(pixelBot.getBlue()==0)
      {
        int nCount=0; //variable to count the number of neighbors that a specific pixel has
        out.setRGB(i,length-1,0);
        for(int y = length-2;y<length;y++)
        {
          for(int x = i-1;x<=(i+1);x++)
          {
            Color testPixel = new Color(image.getRGB(x,y));
            if(testPixel.getBlue()==0)
            {
              nCount++;
            }
          }
        }
        if(nCount<=2)
        {
          // Set the RGB value at (17, 201)(x, y) coordinate to red
          // Use a hexadecimal representation for the value of "red"
          out.setRGB(i,length-1,0xff0000);
        }
      }
      else
      {
        out.setRGB(i,length-1,0xffffff);
      }
    }

    //checks vertical edge cases
    for(int i=1;i<length-1;i++)
    {
      Color pixelLeft = new Color(image.getRGB(0,i));
      Color pixelRight = new Color(image.getRGB(width-1,i));
      if(pixelLeft.getBlue() == 0)
      {
        int nCount=0; //variable to count the number of neighbors that a specific pixel has
        out.setRGB(0,i,0)
        for(int y = 0;y<2;y++)
        {
          for(int x = i-1;x<=(i+1);x++)
          {
            Color testPixel = new Color(image.getRGB(x,y));
            if(testPixel.getBlue()==0)
            {
              nCount++;
            }
          }
        }
        if(nCount<=2)
        {
          // Set the RGB value at (17, 201)(x, y) coordinate to red
          // Use a hexadecimal representation for the value of "red"
          out.setRGB(i,0,0xff0000);
        }
      }
      else
      {
        out.setRGB(i,0,0xffffff);
      }
      if(pixelBot.getBlue()==0)
      {
        int nCount=0; //variable to count the number of neighbors that a specific pixel has
        out.setRGB(i,length-1,0);
        for(int y = length-2;y<length;y++)
        {
          for(int x = i-1;x<=(i+1);x++)
          {
            Color testPixel = new Color(image.getRGB(x,y));
            if(testPixel.getBlue()==0)
            {
              nCount++;
            }
          }
        }
        if(nCount<=2)
        {
          // Set the RGB value at (17, 201)(x, y) coordinate to red
          // Use a hexadecimal representation for the value of "red"
          out.setRGB(i,length-1,0xff0000);
        }
      }
      else
      {
        out.setRGB(i,length-1,0xffffff);
      }
    }
    */

    //checks middle pixels for feature endings
    for(int i=1;i<length-1;i++)
    {
      for(int j=1;j<width-1;j++)
      {
        //if color at specified location is black, check its neighbors for other black pixels
        //if 1 or less black pixels are neighbor, then this is endpoint
        Color pixel = new Color(image.getRGB(j,i));
        int color = pixel.getBlue();
        if(color == 0)
        {
          //conditional block that scans through the neighboring blocks and adds to nCount if black
          int nCount=0; //variable to count the number of neighbors that a specific pixel has
          out.setRGB(j,i,0);
          for(int y = i-1;y<=(i+1);y++)
          {
            for(int x = j-1;x<=(j+1);x++)
            {
              Color testPixel = new Color(image.getRGB(x,y));
              if(testPixel.getBlue()==0)
              {
                nCount++;
              }
            }
          }
          if(nCount==2)
          {
            // Set the RGB value at (17, 201)(x, y) coordinate to red
            // Use a hexadecimal representation for the value of "red"
            out.setRGB(j,i,0xff0000);
          }
        }
        else
        {
          out.setRGB(j,i,0xffffff);
        }
      }
    }

//scans the edge pixels, but does not make red pixels if there's only one neighboring black pixel
    for(int i=0;i<width;i++)
    {
      Color pixelTop = new Color(image.getRGB(i,0));
      Color pixelBot = new Color(image.getRGB(i,length-1));
      int colorTop = pixelTop.getBlue();
      int colorBot = pixelBot.getBlue();
      if(colorTop==0)
      {
        out.setRGB(i,0,0);
      }
      else
      {
        out.setRGB(i,0,0xffffff);
      }
      if(colorBot==0)
      {
        out.setRGB(i,length-1,0);
      }
      else
      {
        out.setRGB(i,length-1,0xffffff);
      }
    }
    for(int i=0;i<length;i++)
    {
      Color pixelTop = new Color(image.getRGB(0,i));
      Color pixelBot = new Color(image.getRGB(width-1,i));
      int colorTop = pixelTop.getBlue();
      int colorBot = pixelBot.getBlue();
      if(colorTop==0)
      {
        out.setRGB(0,i,0);
      }
      else
      {
        out.setRGB(0,i,0xffffff);
      }
      if(colorBot==0)
      {
        out.setRGB(width-1,i,0);
      }
      else
      {
        out.setRGB(width-1,i,0xffffff);
      }
    }

    /*try
    {
      ImageIO.write(out, "png", new File("marked_features.png"));
      System.out.println("file written");
    }
    catch(IOException e)
    {
      System.out.println("Cannot make .png from this");
    }*/
  }

/** getter for out image
* @return output image
*/
  public BufferedImage getImage()
  {
    return out;
  }

/** main method
* @param args unused
*/
  public static void main(String[] args)
  {
    FingerprintAnalyzer newTest1 = new FingerprintAnalyzer("test1.png");
    newTest1.findFeatures();
    BufferedImage markedImage1 = newTest1.getImage();
    try
    {
      ImageIO.write(markedImage1, "png", new File("marked_features1.png"));
      System.out.println("file written");
    }
    catch(IOException e)
    {
      System.out.println("Cannot make .png from this");
    }

    FingerprintAnalyzer newTest2 = new FingerprintAnalyzer("test2.png");
    newTest2.findFeatures();
    BufferedImage markedImage2 = newTest2.getImage();
    try
    {
      ImageIO.write(markedImage2, "png", new File("marked_features2.png"));
      System.out.println("file written");
    }
    catch(IOException e)
    {
      System.out.println("Cannot make .png from this");
    }
  }
}
