// Class: Height balanced AVL Tree
// Binary Search Tree
public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    
    public AVLTree Insert(int address, int size, int key) 
    {  
        AVLTree insert = new AVLTree(address,size,key);
        AVLTree root = this.getRoot();
        if(root.right==null){ 
            root.right = insert;
            insert.parent = root;
            insert.changeHeight();
            insert.balance();
            insert.changeHeight();
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
            AVLTree curr = this.getRoot().right.findDelete(new AVLTree(e.address, e.size, e.key));
            if(curr == null){
                return false;
            }
            AVLTree delete = curr;
            if(delete.left == null && delete.right == null){
                AVLTree par = delete.parent;
                if(par.left == delete){
                    par.left = null;
                }
                else if(par.right == delete){
                    par.right = null;
                }
                delete.parent = null;
                par.changeHeight();
                par.balance();
                par.changeHeight();
                return true;
            }
            if(delete.left == null){
                AVLTree swap = delete.right;
                AVLTree par = delete.parent;
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
                par.changeHeight();
                par.balance();
                par.changeHeight();
                return true;
            }
            if(delete.right == null){
                AVLTree swap = delete.left;
                AVLTree par = delete.parent;
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
                par.changeHeight();
                par.balance();
                par.changeHeight();
                return true;
            }
            AVLTree swap = delete.getNext();
            if(swap.left == null && swap.right == null){
                AVLTree par = swap.parent;
                AVLTree par1 = delete.parent;
                AVLTree lc = delete.left;
                AVLTree rc = delete.right;
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
                    swap.changeHeight();
                    swap.balance();
                    swap.changeHeight();
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
                par.changeHeight();
                par.balance();
                par.changeHeight();
                return true;
            }
            if(swap.left == null){
                AVLTree swap1 = swap.right;
                AVLTree par = swap.parent;
                AVLTree par1 = delete.parent;
                AVLTree lc = delete.left;
                AVLTree rc = delete.right;
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
                    swap.changeHeight();
                    swap.balance();
                    swap.changeHeight();
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
                par.changeHeight();
                par.balance();
                par.changeHeight();
                return true;
            }
            
        }
        return false;
    }
    
    public AVLTree Find(int key, boolean exact)
    { 
        AVLTree curr = this.getRoot();
        if(curr.right==null){
            return null;
        }
        curr = curr.right;
        if(exact){
            AVLTree ans = null;
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
            AVLTree ans = null;
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

    public AVLTree getFirst()
    { 
        if(this.getRoot().right==null){
            return null;
        }
        return this.getRoot().right.getMin();
    }

    public AVLTree getNext()
    { 
        if(this.right!=null){
            return this.right.getMin();
        }
        AVLTree par = this.parent;
        AVLTree curr = this;
        while(par!=null && curr!=par.left){
            curr = par;
            par = curr.parent;
        }
        return par;
    }
    public boolean sanity()
    { 
        AVLTree root = this;
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
        if(!root.sanityCheck()){
            return false;
        }
        if(!root.inorder())
            return false;
        return true;
    }
    private boolean sanityCheck(){
        boolean a;
        if(this.parent == null){
            return  true;
        }
        if(this.left==null && this.right == null){
            a =  (this.height == 0);
        }
        else if(this.left == null){
            a =  ((this.right.height + 1)==this.height);
        }
        else if(this.right == null){
            a = ((this.left.height + 1)==this.height);
        }
        else
            a = ((Math.max(this.left.height,this.right.height)+1)==this.height);
        return a;
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
        return this.sanityCheck();
    }
    private AVLTree getMin(){
        AVLTree curr = this;
        while(curr.left!=null){
            curr = curr.left;
        }
        return curr;
    }
    

    private AVLTree getRoot(){
        AVLTree curr = this;
        while(curr.parent!=null){
            curr = curr.parent;
        }
        return curr;
    }

    private int newHeight(){
        if(this.parent == null)
            return 0;
        if(this.left==null && this.right == null)
            return 0;
        else if(this.left == null)
            return this.right.height + 1;
        else if(this.right == null)
            return this.left.height + 1;
        return Math.max(this.left.height,this.right.height)+1;
    }
    private void changeHeight(){
        AVLTree node = this;
        while(node.parent!=null){
            node.height = node.newHeight();
            node = node.parent;
        }
    }
    private int heightDiff(){
        if(this.left == null && this.right == null){
            return 0;
        }
        if(this.left == null){
            return -1-this.right.height;
        }
        if(this.right == null){
            return this.left.height+1;
        }
        return this.left.height - this.right.height;
    }
    private void balance(){
        AVLTree node = this;
        if(node.parent ==null || node.parent.parent == null){
            return ;
        }
        if(node.heightDiff()>1){
            if(node.left.heightDiff()>=0){
                node = node.rightRotate();
            }
            else{
                node.left = node.left.leftRotate();
                node = node.rightRotate();
            }
        }
        else if(node.heightDiff()<-1){
            if(node.right.heightDiff()<0){
                node = node.leftRotate();
            }
            else{
                node.right = node.right.rightRotate();
                node = node.leftRotate();
            }
        }
        node.parent.balance();
    }
    
    private AVLTree leftRotate(){
        AVLTree node = this;
        AVLTree rc = node.right;
        AVLTree lrc = rc.left;
        AVLTree par = node.parent;
        rc.left = node;
        node.parent = rc;
        node.right = lrc;
        if(lrc!=null)
            lrc.parent = node;
        rc.parent = par;
        if(par.left == node){
            par.left = rc;
        }
        else{
            par.right =rc;
        }
        node.height = node.newHeight();
        rc.height = rc.newHeight();
        par.height = par.newHeight();
        return rc;
    }
    private AVLTree rightRotate(){
        AVLTree node = this;
        AVLTree lc = node.left;
        AVLTree rlc = lc.right;
        AVLTree par = node.parent;
        lc.right = node;
        node.parent = lc;
        node.left = rlc;
        if(rlc!=null)
            rlc.parent = node;
        lc.parent = par;
        if(par.left == node){
            par.left = lc;
        }
        else{
            par.right =lc;
        }
        node.height = node.newHeight();
        lc.height = lc.newHeight();
        par.height = par.newHeight();
        return lc;
    }
    private AVLTree insertNode(AVLTree node){
        if(this.left == null && this.right == null){
            if(this.key>node.key){
                this.left = node;
                node.parent = this;
                node.changeHeight();
                node.balance();
                node.changeHeight();
                return this;
            }
            else if(this.key<node.key){
                this.right = node;
                node.parent = this;
                node.changeHeight();
                node.balance();
                node.changeHeight();
                return this;
            }
            else{
                if(this.address>node.address){
                    this.left = node;
                    node.parent = this;
                    node.changeHeight();
                    node.balance();
                    node.changeHeight();
                    return this;
                }
                else if(this.address<node.address){
                    this.right = node;
                    node.parent = this;
                    node.changeHeight();
                    node.balance();
                    node.changeHeight();
                    return this;
                }
                else{
                    if(this.size>=node.size){
                        this.left = node;
                        node.parent = this;
                        node.changeHeight();
                        node.balance();
                        node.changeHeight();
                        return this;
                    }
                    else{
                        this.right = node;
                        node.parent = this;
                        node.changeHeight();
                        node.balance();
                        node.changeHeight();
                        return this;
                    }
                }
            }
        }
        else if(this.left == null){
            if(this.key>node.key){
                this.left = node;
                node.parent = this;
                node.changeHeight();
                node.balance();
                node.changeHeight();
                return this;
            }
            else if(this.key<node.key){
                AVLTree ans = this.right.insertNode(node);
                node.changeHeight();
                node.balance();
                node.changeHeight();
                return ans;
            }
            else{
                if(this.address>node.address){
                    this.left = node;
                    node.parent = this;
                    node.changeHeight();
                    node.balance();
                    node.changeHeight();
                    return this;
                }
                else if(this.address<node.address){
                    AVLTree ans = this.right.insertNode(node);
                    node.changeHeight();
                    node.balance();
                    node.changeHeight();
                    return ans;
                }
                else{
                    if(this.size>=node.size){
                        this.left = node;
                        node.parent = this;
                        node.changeHeight();
                        node.balance();
                        node.changeHeight();
                        return this;
                    }
                    else{
                        AVLTree ans = this.right.insertNode(node);
                        node.changeHeight();
                        node.balance();
                        node.changeHeight();
                        return ans;
                    }
                }
            }
        }
        else if(this.right == null){
            if(this.key>node.key){
                AVLTree ans = this.left.insertNode(node);
                node.changeHeight();
                node.balance();
                node.changeHeight();
                return ans;
            }
            else if(this.key<node.key){
                this.right = node;
                node.parent = this;
                node.changeHeight();
                node.balance();
                node.changeHeight();
                return this;
            }
            else{
                if(this.address>node.address){
                    AVLTree ans = this.left.insertNode(node);
                    node.changeHeight();
                    node.balance();
                    node.changeHeight();
                    return ans;
                }
                else if(this.address<node.address){
                    this.right = node;
                    node.parent = this;
                    node.changeHeight();
                    node.balance();
                    node.changeHeight();
                    return this;
                }
                else{
                    if(this.size>=node.size){
                        AVLTree ans = this.left.insertNode(node);
                        node.changeHeight();
                        node.balance();
                        node.changeHeight();
                        return ans;
                    }
                    else{
                        this.right = node;
                        node.parent = this;
                        node.changeHeight();
                        node.balance();
                        node.changeHeight();
                        return this;
                    }
                }
            }
        }
        else{
            if(this.key>node.key){
                AVLTree ans = this.left.insertNode(node);
                node.changeHeight();
                node.balance();
                node.changeHeight();
                return ans;
            }
            else if(this.key<node.key){
                AVLTree ans = this.right.insertNode(node);
                node.changeHeight();
                node.balance();
                node.changeHeight();
                return ans;
            }
            else{
                if(this.address>node.address){
                    AVLTree ans = this.left.insertNode(node);
                    node.changeHeight();
                    node.balance();
                    node.changeHeight();
                    return ans;
                }
                else if(this.address<node.address){
                    AVLTree ans = this.right.insertNode(node);
                    node.changeHeight();
                    node.balance();
                    node.changeHeight();
                    return ans;
                }
                else{
                    if(this.size>=node.size){
                        AVLTree ans = this.left.insertNode(node);
                        node.changeHeight();
                        node.balance();
                        node.changeHeight();
                        return ans;
                    }
                    else{
                        AVLTree ans = this.right.insertNode(node);
                        node.changeHeight();
                        node.balance();
                        node.changeHeight();
                        return ans;
                    }
                }
            }
        }
    }
    private AVLTree findDelete(AVLTree node){ 
        AVLTree curr = this.getRoot();
        if(curr.right==null){
            return null;
        }
        curr = curr.right;
        
        while(curr!=null){ 
            if(curr.key>node.key){
                curr = curr.left;
            }
            else if(curr.key<node.key){
                curr = curr.right;
            }
            else{
                if(curr.address>node.address){
                    curr = curr.left;
                }
                else if(curr.address<node.address){
                    curr = curr.right;
                }
                else{
                    if(curr.size>node.size){
                        curr = curr.left;
                    }
                    else if(curr.size<node.size){
                        curr = curr.right;
                    }
                    else{
                        return curr;
                    }
                }
            }
        }
        return curr;
    }
}




