// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }   

    public A1List Insert(int address, int size, int key)
    {
        if(this.next == null){
            return null;
        }
        A1List curr = new A1List(address,size,key);
        curr.prev = this;
        curr.next = this.next;
        this.next = curr;
        curr.next.prev = curr;
        return curr;
    }

    public boolean Delete(Dictionary d) 
    {
        if(d!=null){
            A1List first = this.getFirst();
            if(first!=null){
                while(first!=null){
                    if(!(first.prev == null || first.next == null)){
                        if(first.key == d.key && first.address == d.address && first.size == d.size){
                            A1List prev = first.prev;
                            A1List next = first.next;
                            prev.next = next;
                            next.prev = prev;
                            first = null;
                            return true;
                        }
                        first = first.next;
                    }
                }
            }
        }
        return false;
    }


    public A1List Find(int k, boolean exact)
    {
        A1List first = this.getFirst();
        if(first==null){
            return null;
        }
        
        if(exact){
            while(first!=null){
                if(first.key == k){
                    if(!(first.prev == null || first.next == null))
                        return first;
                    else
                        return null;
                }
                first = first.next;
            }
        }
        else if(true){
            while(first!=null){
                if(first.key>=k){
                    if(!(first.prev == null || first.next == null))
                        return first;
                    else
                        return null;
                }
                first = first.next;
            }
        }
        return null;
    }


    public A1List getFirst()
    {
        A1List curr = this;
        while(curr.prev!=null){
            curr = curr.prev;
        }
        if(curr.prev == null && curr.next.next==null){
            return null;
        }
        return curr.next;
    }
    
    public A1List getNext() 
    {
        A1List curr = this;
        if((curr.prev == null && curr.next.next==null) || (curr.next == null && curr.prev.prev == null)){
            return null;
        }
        if(curr.next.next == null){
            return null;
        }
        return curr.next;
    }




    public boolean sanity()
    {   
        ///////
        //checks for cycle
        A1List forward1 = this.next;
        A1List forward2;
        if(forward1==null){
            forward2 = null;
        }
        else
            forward2 = this.next.next;
        A1List backward1 = this.prev;
        A1List backward2;
        if(backward1==null){
            backward2 = null;
        }
        else
            backward2 = this.prev.prev;
        while(forward2!=null && forward1!=null){
            if(forward1==forward2){
                return false;
            }
            forward1 = forward1.next;
            if(forward1==null){
                break;
            }
            if(forward2.next==null){
                break;
            }
            forward2 = forward2.next.next;
        }
        while(backward1!=null && backward2!=null){
            if(backward1==backward2){
                return false;
            }
            backward1 = backward1.prev;
            if(backward1==null){
                break;
            }
            if(backward2.prev==null){
                break;
            }
            backward2 = backward2.prev.prev;
        }
        //checks for cycle
        ///////////


        ///////////
        //checks for X.prev.next and X.next.prev to be pointing to same variable X
        if(this.prev !=null && this.next!=null){
            if(this.next.prev != this || this.prev.next !=this || this.next.prev != this.prev.next){
                return false;
            }
        }
        
        A1List forward = this.next;
        A1List backward = this.prev;
        while(forward!=null&&forward.next!=null&&forward.prev!=null){
            if(forward.next.prev != forward || forward.prev.next !=forward || forward.next.prev != forward.prev.next){
                return false;
            }
            forward = forward.next;
        }
        while(backward!=null&&backward.prev!=null&&backward.next!=null){
            if(backward.next.prev != backward || backward.prev.next !=backward || backward.next.prev != backward.prev.next){
                return false;
            }
            backward = backward.prev;
        }
        //checks for X.prev.next and X.next.prev to be pointing to same variable X
        //////////

        /////////
        //checks for sentinenl node's key/address/size values (they must be (-1,-1,-1))
        A1List head = this;
        A1List tail = this;
        if(this.prev == null){
            if(this.key!=-1 || this.address!=-1 || this.size!=-1){
                return false;
            }
        }
        if(this.next == null){
            if(this.key!=-1 || this.address!=-1 || this.size!=-1){
                return false;
            }
        }
        if(this.next!=null && this.prev!=null){
            while(head.prev.prev!=null){
                head = head.prev;
            }
            while(tail.next.next!=null){
                tail = tail.next;
            }
            head = head.prev;
            tail = tail.next;
            if(tail.key!=-1 || tail.address!=-1 || tail.size!=-1){
                return false;
            }
            if(head.key!=-1 || head.address!=-1 || head.size!=-1){
                return false;
            }
        }
        //checks for sentinenl node's key/address/size values (they must be (-1,-1,-1))
        ///////////
        
        return true;
    }

}


