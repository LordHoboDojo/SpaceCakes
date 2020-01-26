import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class StarChart {
    public static ArrayList<Integer> hD = new ArrayList<>();
    BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
    Graphics g;

    public void createFile() throws IOException {
        g = image.createGraphics();
        g.setColor(Color.WHITE);
        HashTable[] data = readCoords("stars.txt");
        HashTable coordinates = data[0];
        HashTable magnitudes = data[1];
        HashTable names = data[2];
        ArrayList<Integer> list = getHDList();
        ArrayList<Couple> list1 = readConst("constellations.txt", names);
        plotStarsByMagnitude(coordinates, magnitudes, list);
        plotConst(coordinates, list1);
        ImageIO.write(image,"jpg",new File("result.jpg"));
    }
    public void plotStars(HashTable c, ArrayList<Integer> list)
    {
        for (Integer i : list)
        {
            Coordinate temp = translate((Coordinate) c.get(i));
        }
    }

    public void plotStarsByMagnitude(HashTable c, HashTable m, ArrayList<Integer> list)
    {
        double StarSize = 0;
        int j=0;
        for (Integer i : list)
        {
            Coordinate temp = translate((Coordinate) c.get(i));
            StarSize = Math.round(10.0 / ((double) m.get(i) + 2.0));
            g.fillOval((int)(temp.x),(int)(temp.y),(int)StarSize,(int)StarSize);
        }
    }

    public void plotConst(HashTable coordinates, ArrayList<Couple> list)
    {
        int m=0;
        for(Couple c : list)
        {
            int hd1 = c.hd1;
            int hd2 = c.hd2;
            Coordinate a = translate((Coordinate) coordinates.get(hd1));
            Coordinate b = translate((Coordinate) coordinates.get(hd2));
            g.drawLine((int)a.x,(int)a.y,(int)b.x,(int)b.y);
            System.out.println(m++);
        }
    }

    public HashTable[] readCoords(String fileName) throws IOException
    {
        Scanner scan = new Scanner(new File(fileName));
        HashTable coordinates = new HashTable(10000);
        HashTable magnitude = new HashTable(10000);
        HashTable names = new HashTable(10000);

        while(scan.hasNextLine())
        {
            String[] line = scan.nextLine().split(" ");

            double x = Double.parseDouble(line[0]);
            double y = Double.parseDouble(line[1]);
            double z = Double.parseDouble(line[2]);
            int hDNum = Integer.parseInt(line[3]);
            double magnitudes = Double.parseDouble(line[4]);
            double hRNum = Double.parseDouble(line[5]);
            hD.add(hDNum);

            coordinates.put(hDNum, new Coordinate(x, y));
            magnitude.put(hDNum, magnitudes);

            String conc = "";
            for(int i = 6, cnt = 0; i < line.length; i++, cnt++)
            {
                if (cnt > 0)
                    conc += " ";
                conc += line[i];
            }
            String[] temporary = conc.split(";");

            for(String name : temporary)
            {
                names.put(readName(name), hDNum);
            }
        }
        HashTable[] result = {coordinates, magnitude, names};
        return result;
    }

    public ArrayList<Couple> readConst(String fileName, HashTable names) throws IOException
    {
        Scanner scan = new Scanner(new File(fileName));
        ArrayList<Couple> list= new ArrayList<Couple>();
        int m=0;
        while (scan.hasNextLine())
        {
            String[] temp = scan.nextLine().split(",");
            list.add(new Couple(temp[0], temp[1], (Integer) names.get(temp[0]), (Integer) names.get(temp[1])));
            m++;
            System.out.println(m);
        }
        scan.close();
        return list;
    }

    public static String readName(String s)
    {
        int i;
        for(i = 0; i < s.length(); i++)
        {
            String temporary = s.substring(i, i + 1);
            if(!temporary.equals(" "))
                break;
        }
        return s.substring(i);
    }

    public static Coordinate translate(Coordinate c)
    {
        double newX = 0 + ((c.x - -1) / (1 - -1)) * (1000);
        double newY = 0 + ((c.y - 1) / (-1 - 1)) * (1000);
        return new Coordinate(newX, newY);
    }

    public ArrayList<Integer> getHDList()
    {
        return hD;
    }
}