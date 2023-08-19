import java.util.EmptyStackException;
public class PilhaEncadeada {
    

    
  private class Node {
    public int element, element2;
    public Node next;

    public Node(int element, int element2) {
        this.element = element;
        this.element2 = element2;
        next = null;
    }
    
    public Node(int element,int element2, Node next) {
        this.element = element;
        this.element2 = element2;
        this.next = next;
    }        
}
private Node head;
private int count;
    //Insere o elemento no topo da lista
    public void push(int element, int element2)
    {
        Node n = new Node(element, element2);
        n.next = head;
        head = n;
        count++;
    }
    
    //remove e retorna o elemento do topo da pilha
    //erro se a pilha estiver vazia
public int pop()
{
if (count == 0)
{
    throw new EmptyStackException();
}
int elemRemovido = head.element;
            head = head.next;
            count--;
return elemRemovido;
}
//Retorna e retorna o elemento do topo da pilha
    //erro se a pilha estiver vazia
    public int top()
    {
       return head.element;
    }
    //Retorna o numero de elementos da pilha
public int size()
    {
        return count;
    }

     // retorna true se a pilha estiver vazia, e false caso contrário
     public boolean isEmpty() {
        return (head == null);
    }

    // esvazia a pilha
    public void clear() {
        head = null;
        count=0;
    }   
}



