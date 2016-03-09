/*
Jack Smith
ID: 110366081
Spring 2016
*/
import java.util.Scanner;

public class Point2D {

    public static void main(String[] args){
        
        int x = 0, y = 0, index = 1;
        double x_temp, y_temp, distanceFromCentroid;
        double smallestDist = Double.MAX_VALUE;
        UniLinkedList list = new UniLinkedList();
        OrderedDoublePair pair, pair_temp = new OrderedDoublePair(), centroidPair = new OrderedDoublePair();
        OrderedDoublePair closestToCentroidPair = new OrderedDoublePair();
        Scanner intScanner = new Scanner(System.in);
        String[] parts;
        System.out.println("Enter ordered pairs in the format 'x y' per line. Type 'done' when done.");
        do{
            try{
                x = intScanner.nextInt();
                y = intScanner.nextInt();
            }catch(Exception e){
                System.out.println("Error. Enter 2 numbers with a space inbetween per line. Type 'done' on a separate line.");
            }
            pair = new OrderedDoublePair(x , y);
            list.add(pair);
            index++;
            
        }while(intScanner.hasNextInt());
        
        System.out.println("Pairs stored:" + list); 
        System.out.println("Centroid = " + centroid(list));
        centroidPair = centroid(list);
        list.deduplicate();
        System.out.println("Centroid of unique points = " + centroid(list));
        System.out.println("Pair nearest to the origin = " + smallest(list));
        System.out.println("Pair farthest from the origin = " + largest(list));
       
        list.cursor.setPosition(list.getHead());
        while(list.cursor.hasNext()){
           list.cursor.next();
           pair_temp = (OrderedDoublePair) list.cursor.getData();
           distanceFromCentroid = distance(pair_temp, centroidPair);
           if(distanceFromCentroid < smallestDist){
               smallestDist = distanceFromCentroid;
               closestToCentroidPair = pair_temp;
           }
           if((pair_temp.getX() + pair_temp.getY()) % 2.0 == 0)
               list.remove(pair_temp);
        }
       
        System.out.println("After removing all the even summed points, the list is " + list);
        System.out.println(closestToCentroidPair + " = the pair closest to the centroid from the original list.");
            
    }
    
    public static double distance(OrderedDoublePair p, OrderedDoublePair q){
        return Math.sqrt(Math.pow(q.getX() - p.getX(),2) + Math.pow(q.getY() - p.getY(),2));
    }
    
    public static UniLinkedList<OrderedDoublePair> fromArray(OrderedDoublePair[] points){
        
        UniLinkedList list = new UniLinkedList();
        for (OrderedDoublePair point : points) {
            if(point != null)
                list.add(point);
        }
        return list;
    }
    
    public static UniLinkedList<OrderedDoublePair> from2DArray(OrderedDoublePair[][] points){
        
        UniLinkedList list = new UniLinkedList();
        for(int r = 0; r < points.length; r++){
            for(int c = 0; c < points[0].length; c++){
                list.add(points[r][c]);
            }
        }
        return list;
    }
    
    public static OrderedDoublePair centroid(UniLinkedList<OrderedDoublePair> points){
        
        double x_total = 0, y_total = 0;
        OrderedDoublePair temp = new OrderedDoublePair();
        points.cursor.setPosition(points.getHead());
        while(points.cursor.hasNext()){
            points.cursor.next();
            temp = (OrderedDoublePair) points.cursor.getData();
            x_total += temp.getX();
            y_total += temp.getY();
        }
        x_total /= points.size();
        y_total /= points.size();
        OrderedDoublePair centroid = new OrderedDoublePair(x_total, y_total);
        return centroid;
    }
    
    public static OrderedDoublePair smallest(UniLinkedList<OrderedDoublePair> points){
        
        double distance = Double.MAX_VALUE, distance_temp = 0;
        OrderedDoublePair temp1 = new OrderedDoublePair();
        OrderedDoublePair temp2 = new OrderedDoublePair();
        points.cursor.setPosition(points.getHead());
        while(points.cursor.hasNext()){
            points.cursor.next();
            temp1 = (OrderedDoublePair) points.cursor.getData();
            distance_temp = distance(temp1, OrderedDoublePair.ORIGIN);
            if(distance_temp < distance){
                distance = distance_temp;
                temp2 = temp1;
            }
        }
        return temp2;
    }
    
    public static OrderedDoublePair largest(UniLinkedList<OrderedDoublePair> points){
        
        double distance = 0, distance_temp = 0;
        OrderedDoublePair temp1 = new OrderedDoublePair();
        OrderedDoublePair temp2 = new OrderedDoublePair();
        points.cursor.setPosition(points.getHead());
        while(points.cursor.hasNext()){
            points.cursor.next();
            temp1 = (OrderedDoublePair) points.cursor.getData();
            distance_temp = distance(temp1, OrderedDoublePair.ORIGIN);
            if(distance_temp > distance){
                distance = distance_temp;
                temp2 = temp1;
            }
        }
        return temp2;
    }
    
    
    
}
