/*
Jack Smith
ID: 110366081
Spring 2016
*/

public class UniLinkedList<E> {
    
    private Node<E> head;
    private int count;
    Cursor cursor = new Cursor(this);
    
    public UniLinkedList(){
        head = new Node<>(null);
        count = 0;
    }
    
    public void setHead(Node<E> head){
        this.head = head;
    }
    
    public Node<E> getHead(){
        return head;
    }
    
    public void add(E element){
        
        Node<E> temp = new Node(element);
        cursor.setPosition(head);
        
        while(cursor.hasNext()){
            cursor.next();
        }
        
        cursor.getPosition().setNext(temp);
        count++;
    }
    
    public void addAfter(E mark, E elementToAdd) throws Exception{     
        
        Node<E> temp = new Node(elementToAdd);
        cursor.setPosition(head);
        boolean markFound = false;
        
        while(!markFound){
            
                if(!cursor.hasNext()){
                throw new Exception();
            }
            cursor.next();
            if((cursor.getPosition().getData().equals(mark))){ 
                Node<E> next = cursor.getPosition().getNext(); //save value so it is not lost
                cursor.getPosition().setNext(temp);             
                (cursor.getPosition().getNext()).setNext(next); 
                count++;
                markFound = true;
            }
            
        }
        
    }
    
    public void addFirst(E element){
        
        Node<E> temp = new Node(element);  
        Node<E> next = head.getNext();
        cursor.setPosition(head);
        cursor.getPosition().setNext(temp);
        (cursor.getPosition().getNext()).setNext(next);
        count++;
        
    }
    
    public int size(){
        return count;
    }
    
    public int indexOf(E element){
        
        cursor.setPosition(head);
        int index = 0;
        
        while(cursor.hasNext()){
            cursor.next();
            index++;
            if(cursor.getPosition().getData().equals(element))
                return index;
        }
        return -1;
        
    }
    
    public boolean contains(E element){
        
        cursor.setPosition(head);  
        while(cursor.hasNext()){
            cursor.next();
            if(cursor.getPosition().getData().equals(element))
                return true;
        }
        return false;
    }
    
    public boolean equals(UniLinkedList<E> list){
        
        cursor.setPosition(head);
        list.cursor.setPosition(list.getHead());
        
        while(cursor.hasNext() && list.cursor.hasNext()){
            cursor.next();
            list.cursor.next();
            if(!(cursor.getPosition().getData().equals(list.cursor.getPosition().getData())))
                return false;
        }
        return true;
        
    }
    
    public boolean isEmpty(){
        
        return head.getNext() == null;
        
    }
    
    public boolean remove(E element){
        
        cursor.setPosition(head);
        while(cursor.hasNext()){ //check two steps ahead
            if(cursor.getPosition().getNext().getData().equals(element)){
                Node<E> next = cursor.getPosition().getNext().getNext();
                cursor.getPosition().setNext(next);
                count--;
                return true;
            }
            cursor.next();
        }
        return false;
        
    }
    
    public boolean removeAll(E element){
        
        int i = count;
        cursor.setPosition(head);
        while(cursor.getPosition().getNext() != null){
            cursor.next();
            if(cursor.getPosition().getData().equals(element)){
                this.remove((E) cursor.getPosition().getData());
            }
        }
        return i != count; //if list size changes
    }
    
    public void deduplicate(){
           
        UniLinkedList noDupeList = new UniLinkedList();
        cursor.setPosition(head);
        while(cursor.hasNext()){
            cursor.next();
            if(!noDupeList.contains(cursor.getPosition().getData())){
                noDupeList.add(cursor.getPosition().getData());
            }
        }
        this.setHead(noDupeList.getHead());
       
    }
    
    public void clear(){
        
        head = null;
        head = new Node(null);
        
    }
    
    @Override
    public String toString(){
        
        String s = "";
        cursor.setPosition(head);
        while(cursor.hasNext()){
            cursor.next();
            s += cursor.getPosition().getData().toString();
            if(cursor.getPosition().getNext() != null)
                s += " -> ";
        }
        return s;
    }
    
    public static class Cursor<E>{
        
        private Node<E> position;
        
        private Cursor(UniLinkedList list){
            position = list.getHead();
        }
        
        public void setPosition(Node<E> n){
            position = n;
        }
        
        public Node<E> getPosition(){
            return position;
        }
        
        public E getData(){
            return position.getData();
        }
        
        public boolean hasNext(){
            return position.getNext() != null;
        }
        
        public E next(){
            position = position.getNext();
            return position.getData();
        }
        
    }
    
    private static class Node<E>{
        
        private E data;
        private Node next;
        
        public Node(E element){     
            this.data = element;
            this.next = null;
        }
        
        public Node(E element, Node<E> next){
            this.data = element;
            this.next = next;
        }
        
        public E getData(){
            return data;
        }
        
        public void setData(E element){
            this.data = element;
        }
        
        public Node<E> getNext(){
            return next;
        }
        
        public void setNext(Node<E> next){
            this.next = next;
        }
        
        @Override
        public int hashCode() {
            int result = data != null ? data.hashCode() : 0;
            return 31 * result + (next != null ? next.hashCode() : 0);
        }
        
        
    }
   
    
}
