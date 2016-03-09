/*
Jack Smith
ID: 110366081
Spring 2016
*/

public class OrderedDoublePair {
    
    private double x;
    private double y;
    public static final OrderedDoublePair ORIGIN = new OrderedDoublePair();
    
    public OrderedDoublePair(){
        x = 0;
        y = 0;
    }
    
    public OrderedDoublePair(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public OrderedDoublePair(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public double getX(){
        return x;
    }
    
    public void setX(double x){
        this.x = x;
    }
    
    public double getY(){
        return y;
    }
    
    public void setY(double y){
        this.y = y;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof OrderedDoublePair)
            return (((OrderedDoublePair)obj).getX() == this.x) && (((OrderedDoublePair)obj).getY() == this.y);
        else
            return false;
    }
    
    public boolean equalsIgnoreOrder(Object obj){
        if(obj instanceof OrderedDoublePair)
            return ((((OrderedDoublePair)obj).getX() == this.x) && (((OrderedDoublePair)obj).getY() == this.y) || 
                    (((OrderedDoublePair)obj).getX() == this.y) && (((OrderedDoublePair)obj).getY() == this.x));    
        else
            return false;
    }
    
    @Override
    public String toString(){
        return "(" + this.x + "," + this.y + ")";
    }
    
    @Override
    public int hashCode() { 
        int result;
        long temp = Double.doubleToLongBits (x);
        result = (int)  (temp ^ (temp >>>  32));
        temp = Double.doubleToLongBits (y);
        return 31 * result + (int) (temp ^ (temp >>>  32));
    }
    
}
