package poker.app;

import base.CycleList;

public class LinkedListTest {

    public static void main(String args[]) {

        //creating LinkedList with 5 elements including head
/*    	CycleList linkedList = new CycleList();
        linkedList.appendIntoTail(new CycleList.Node("101"));
        linkedList.appendIntoTail(new CycleList.Node("201"));
        linkedList.appendIntoTail(new CycleList.Node("301"));
        linkedList.appendIntoTail(new CycleList.Node("401"));
        
        System.out.println("Linked List : " + linkedList);
        if(linkedList.isCyclic()){
            System.out.println("Linked List is cyclic as it contains cycles or loop");
        }else{
            System.out.println("LinkedList is not cyclic, no loop or cycle found");
        }  */ 
        
        CycleList linkedList2 = new CycleList();
        linkedList2.appendIntoTail(new CycleList.Node("101"));
        CycleList.Node cycle = new CycleList.Node("201");
        linkedList2.appendIntoTail(cycle);
        linkedList2.appendIntoTail(new CycleList.Node("301"));
        linkedList2.appendIntoTail(new CycleList.Node("401"));
        linkedList2.appendIntoTail(cycle);




        System.out.println("Linked List : " + linkedList2);

        if(linkedList2.isCyclic()){
        	   System.out.println("Linked List is cyclic as it contains cycles or loop");
        	}else{
        	   System.out.println("LinkedList is not cyclic, no loop or cycle found");
        	} 


        	
  

    } 
   
}



