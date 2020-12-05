// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size); 
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).
    // While inserting into the list, only call insert at the head of the list
    // Please note that ALL insertions in the DLL (used either in A1DynamicMem or used independently as the “dictionary” class implementation) are to be made at the HEAD (from the front).
    // Also, the find-first should start searching from the head (irrespective of the use for A1DynamicMem). Similar arguments will follow with regards to the ROOT in the case of trees (specifying this in case it was not so trivial to anyone of you earlier)
    public int Allocate(int blockSize) {
        if(blockSize>0){
            Dictionary insert = freeBlk.Find(blockSize, false);
            if(insert == null){
                return -1;
            }
            boolean deleted = freeBlk.Delete(insert);
            if(deleted == false){
                return -1;
            }
            if(insert.size>blockSize){
                Dictionary inserted = freeBlk.Insert(insert.address + blockSize, insert.size - blockSize , insert.size - blockSize);
                if(inserted == null){
                    return -1;
                }
            }
            Dictionary inserted = allocBlk.Insert(insert.address, blockSize, insert.address);
            if(inserted == null){
                return -1;
            }
            
            return insert.address;
        }
        return -1;
    } 
    // return 0 if successful, -1 otherwise
    public int Free(int startAddr) {
        Dictionary delete = allocBlk.Find(startAddr, true);
        if(delete==null){
            return -1;
        }
        boolean deleted = allocBlk.Delete(delete);
        if(deleted == false){
            return -1;
        }
        Dictionary inserted = freeBlk.Insert(startAddr, delete.size, delete.size);
        if(inserted == null){
            return -1;
        }
        return 0;
    }
}