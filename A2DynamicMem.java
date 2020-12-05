// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {

    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 
    // Your BST (and AVL tree) implementations should obey the property that keys in the left subtree <= root.key < keys in the right subtree. How is this total order between blocks defined? It shouldn't be a problem when using key=address since those are unique (this is an important invariant for the entire assignment123 module). When using key=size, use address to break ties i.e. if there are multiple blocks of the same size, order them by address. Now think outside the scope of the allocation problem and think of handling tiebreaking in blocks, in case key is neither of the two. 
    public void Defragment() {
        if(type==2){
            Dictionary temp = freeBlk.getFirst();
            Dictionary freeBlk2 = new BSTree();
            while(temp!=null){
                freeBlk2.Insert(temp.address, temp.size, temp.address);
                temp = temp.getNext();
            }
            temp = freeBlk2.getFirst();
            while(temp!=null && temp.getNext()!=null){
                Dictionary tempn = temp.getNext();
                Dictionary newtemp = new BSTree();
                if(temp.address + temp.size == tempn.address){
                    freeBlk2.Delete(temp);
                    freeBlk2.Delete(tempn);
                    Dictionary dtemp = new BSTree(temp.address,temp.size,temp.size);
                    freeBlk.Delete(dtemp);
                    Dictionary dtempn = new BSTree(tempn.address,tempn.size,tempn.size);
                    freeBlk.Delete(dtempn);
                    newtemp = freeBlk2.Insert(temp.address, temp.size + tempn.size, temp.address);
                    freeBlk.Insert(temp.address, temp.size + tempn.size, temp.size + tempn.size);
                    temp = newtemp;
                }
                else{
                    temp = temp.getNext();
                }
            }
            freeBlk2 = null;
        }
        else if(type==3){
            Dictionary temp = freeBlk.getFirst();
            Dictionary freeBlk2 = new AVLTree();
            while(temp!=null){
                freeBlk2.Insert(temp.address, temp.size, temp.address);
                temp = temp.getNext();
            }
            temp = freeBlk2.getFirst();
            while(temp!=null && temp.getNext()!=null){
                Dictionary tempn = temp.getNext();
                Dictionary newtemp = new AVLTree();
                if(temp.address + temp.size == tempn.address){
                    freeBlk2.Delete(temp);
                    freeBlk2.Delete(tempn);
                    Dictionary dtemp = new AVLTree(temp.address,temp.size,temp.size);
                    freeBlk.Delete(dtemp);
                    Dictionary dtempn = new AVLTree(tempn.address,tempn.size,tempn.size);
                    freeBlk.Delete(dtempn);
                    newtemp = freeBlk2.Insert(temp.address, temp.size + tempn.size, temp.address);
                    freeBlk.Insert(temp.address, temp.size + tempn.size, temp.size + tempn.size);
                    temp = newtemp;
                }
                else{
                    temp = temp.getNext();
                }
            }
            freeBlk2 = null;
        }
        else if(type==1){
            Dictionary temp = freeBlk.getFirst();
            Dictionary freeBlk2 = new BSTree();
            while(temp!=null){
                freeBlk2.Insert(temp.address, temp.size, temp.address);
                temp = temp.getNext();
            }
            temp = freeBlk2.getFirst();
            while(temp!=null && temp.getNext()!=null){
                Dictionary tempn = temp.getNext();
                Dictionary newtemp = new BSTree();
                if(temp.address + temp.size == tempn.address){
                    freeBlk2.Delete(temp);
                    freeBlk2.Delete(tempn);
                    Dictionary dtemp = new A1List(temp.address,temp.size,temp.size);
                    freeBlk.Delete(dtemp);
                    Dictionary dtempn = new A1List(tempn.address,tempn.size,tempn.size);
                    freeBlk.Delete(dtempn);
                    newtemp = freeBlk2.Insert(temp.address, temp.size + tempn.size, temp.address);
                    freeBlk.Insert(temp.address, temp.size + tempn.size, temp.size + tempn.size);
                    temp = newtemp;
                }
                else{
                    temp = temp.getNext();
                }
            }
            freeBlk2 = null;
        }
        
        return ;
    }
}