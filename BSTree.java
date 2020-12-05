// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java
import java.util.*;
public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    {  
        BSTree insert = new BSTree(address,size,key);
        BSTree root = this.getRoot();
        if(root.right==null){ 
            root.right = insert;
            insert.parent = root;
            return insert;
        }
        root.right.insertNode(insert);
        return insert;
    }

    public boolean Delete(Dictionary e)
    { 
        if(e!=null){
            if(this.getRoot().right == null){
                return false;
            }
            BSTree curr = this.getRoot().right.Find(e.key, true);
            
            while(curr!=null){
                if(curr.key == e.key && curr.address == e.address && curr.size == e.size){
                    break;
                }
                else{
                    curr = curr.getNext();
                }
            }
            if(curr == null){
                return false;
            }
            BSTree delete = curr;
            if(delete.left == null && delete.right == null){
                BSTree par = delete.parent;
                if(par.left == delete){
                    par.left = null;
                }
                else if(par.right == delete){
                    par.right = null;
                }
                delete.parent = null;
                return true;
            }
            if(delete.left == null){
                BSTree swap = delete.right;
                BSTree par = delete.parent;
                if(par.left == delete){
                    par.left = swap;
                    swap.parent = par;
                }
                else if(par.right == delete){
                    par.right = swap;
                    swap.parent = par;
                }
                else{
                    return false;
                }
                delete.parent = null;
                delete.right = null;
                return true;
            }
            if(delete.right == null){
                BSTree swap = delete.left;
                BSTree par = delete.parent;
                if(par.left == delete){
                    par.left = swap;
                    swap.parent = par;
                }
                else if(par.right == delete){
                    par.right = swap;
                    swap.parent = par;
                }
                else{
                    return false;
                }
                delete.parent = null;
                delete.left = null;
                return true;
            }
            BSTree swap = delete.getNext();
            if(swap.left == null && swap.right == null){
                BSTree par = swap.parent;
                BSTree par1 = delete.parent;
                BSTree lc = delete.left;
                BSTree rc = delete.right;
                if(par == delete){
                    swap.parent = par1;
                    if(par1.left==delete){
                        par1.left = swap;
                    }
                    else{
                        par1.right = swap;
                    }
                    swap.left = lc;
                    lc.parent = swap;
                    return true;
                }
                if(par.left == swap){
                    par.left = null;
                }
                else if(par.right == swap){
                    par.right = null;
                }
                swap.parent = par1;
                swap.right = rc;
                swap.left = lc;
                if(lc != null)
                    lc.parent = swap;
                if(rc != null)
                    rc.parent = swap;
                if(par1.left!=null && par1.left == delete){
                    par1.left = swap;
                }
                else{
                    par1.right = swap;
                }
                delete.left = null;
                delete.right=null;
                delete.parent = null;
                return true;
            }
            if(swap.left == null){
                BSTree swap1 = swap.right;
                BSTree par = swap.parent;
                BSTree par1 = delete.parent;
                BSTree lc = delete.left;
                BSTree rc = delete.right;
                if(par == delete){
                    swap.parent = par1;
                    if(par1.left==delete){
                        par1.left = swap;
                    }
                    else{
                        par1.right = swap;
                    }
                    swap.left = lc;
                    lc.parent = swap;
                    return true;
                }
                if(par.left == swap){
                    par.left = swap1;
                    swap1.parent = par;
                }
                else if(par.right == swap){
                    par.right = swap1;
                    swap1.parent = par;
                }
                swap.parent = par1;
                swap.right = rc;
                swap.left = lc;
                if(lc != null)
                    lc.parent = swap;
                if(rc != null)
                    rc.parent = swap;
                if(par1.left!=null && par1.left == delete){
                    par1.left = swap;
                }
                else{
                    par1.right = swap;
                }
                return true;
            }
            
            return true;
        }
        return false;
    }
    public BSTree Find(int key, boolean exact)
    { 
        BSTree curr = this.getRoot();
        if(curr.right==null){
            return null;
        }
        curr = curr.right;
        if(exact){
            BSTree ans = null;
            while(curr!=null){ 
                if(curr.key>key){
                    curr = curr.left;
                }
                else if(curr.key<key){
                    curr = curr.right;
                }
                else{
                    ans = curr;
                    curr = curr.left;
                }
            }
            return ans;
        }
        else{
            BSTree ans = null;
            while(curr!=null){ 
                if(curr.key>key){
                    ans = curr;
                    curr = curr.left;
                }
                else if(curr.key<key){
                    curr = curr.right;
                }
                else{
                    ans =  curr;
                    curr = curr.left;
                }
            }
            return ans;
        }
    }


    public BSTree getFirst()
    { 
        if(this.getRoot().right==null){
            return null;
        }
        return this.getRoot().right.getMin();
    }

    public BSTree getNext()
    { 
        if(this.right!=null){
            return this.right.getMin();
        }
        BSTree par = this.parent;
        BSTree curr = this;
        while(par!=null && curr!=par.left){
            curr = par;
            par = curr.parent;
        }
        return par;
    }

    public boolean sanity()
    { 
        BSTree root = this;
        while(root.parent!=null){
            if(root.parent.left!=root && root.parent.right!=root){
                return false;
            }
            root = root.parent;
        }
        if(root.left != null){
            return false;
        }
        if(root.address!=-1 || root.size!=-1 || root.key!=-1){
            return false;
        }
        root = root.right;
        if(root==null){
            return true;
        }
        if(!root.inorder())
            return false;
        return true;
    }
    private boolean inorder(){
        if(this.left!=null){
            if(this.left.key>this.key) return false;
            if(this.left.key == this.key && this.left.address>this.address) return false;
            if(this.left.key == this.key && this.left.address==this.address &&this.left.size>this.size) return false;
            if(this.left.parent != this) return false;
            if(!this.left.inorder()){
                return false;
            }
        }
        if(this.right!=null){
            if(this.right.key<this.key) return false;
            if(this.right.key == this.key && this.right.address<this.address) return false;
            if(this.right.key == this.key && this.right.address==this.address &&this.right.size<this.size) return false;
            if(this.right.parent != this) return false;
            if(!this.right.inorder()){
                return false;
            }
        }
        return true;
    }
    
    private BSTree getMin(){
        BSTree curr = this;
        while(curr.left!=null){
            curr = curr.left;
        }
        return curr;
    }
    

    private BSTree getRoot(){
        BSTree curr = this;
        while(curr.parent!=null){
            curr = curr.parent;
        }
        return curr;
    }

    private BSTree insertNode(BSTree node){
        if(this.left == null && this.right == null){
            if(this.key>node.key){
                this.left = node;
                node.parent = this;
                return this;
            }
            else if(this.key<node.key){
                this.right = node;
                node.parent = this;
                return this;
            }
            else{
                if(this.address>node.address){
                    this.left = node;
                    node.parent = this;
                    return this;
                }
                else if(this.address<node.address){
                    this.right = node;
                    node.parent = this;
                    return this;
                }
                else{
                    if(this.size>=node.size){
                        this.left = node;
                        node.parent = this;
                        return this;
                    }
                    else{
                        this.right = node;
                        node.parent = this;
                        return this;
                    }
                }
            }
        }
        else if(this.left == null){
            if(this.key>node.key){
                this.left = node;
                node.parent = this;
                return this;
            }
            else if(this.key<node.key){
                return this.right.insertNode(node);
            }
            else{
                if(this.address>node.address){
                    this.left = node;
                    node.parent = this;
                    return this;
                }
                else if(this.address<node.address){
                    return this.right.insertNode(node);
                }
                else{
                    if(this.size>=node.size){
                        this.left = node;
                        node.parent = this;
                        return this;
                    }
                    else{
                        return this.right.insertNode(node);
                    }
                }
            }
        }
        else if(this.right == null){
            if(this.key>node.key){
                return this.left.insertNode(node);
            }
            else if(this.key<node.key){
                this.right = node;
                node.parent = this;
                return this;
            }
            else{
                if(this.address>node.address){
                    return this.left.insertNode(node);
                }
                else if(this.address<node.address){
                    this.right = node;
                    node.parent = this;
                    return this;
                }
                else{
                    if(this.size>=node.size){
                        return this.left.insertNode(node);
                    }
                    else{
                        this.right = node;
                        node.parent = this;
                        return this;
                    }
                }
            }
        }
        else{
            if(this.key>node.key){
                return this.left.insertNode(node);
            }
            else if(this.key<node.key){
                return this.right.insertNode(node);
            }
            else{
                if(this.address>node.address){
                    return this.left.insertNode(node);
                }
                else if(this.address<node.address){
                    return this.right.insertNode(node);
                }
                else{
                    if(this.size>=node.size){
                        return this.left.insertNode(node);
                    }
                    else{
                        return this.right.insertNode(node);
                    }
                }
            }
        }
    }
}




