class Node
{
    int iData; // data used as key value
    Node leftChild; // this node’s left child
    Node rightChild; // this node’s right child

    public void displayNode()
    {
        System.out.print('{'); System.out.print(iData);
        System.out.print("} ");
    }
}

class Tree
{
    private Node root; // the only data field in Tree
    public void insert(int id)
    {
        Node newNode = new Node(); // make new node
        newNode.iData = id; // insert data
        if(root==null) // no node in root
            root = newNode;
        else
        {  Node current = root; // start at root
            Node parent;
            while(true) // (exits internally)
            {  parent = current;
                if(id < current.iData) // go left?
                {
                    current = current.leftChild;
                    if(current == null) // if end of the line,
                    { // insert on left
                        parent.leftChild = newNode;
                        return;
                    }
                } // end if go left
                else // or go right?
                { current = current.rightChild;
                    if(current == null) // if end of the line
                    { // insert on right
                        parent.rightChild = newNode;
                        return;
                    }
                } // end else go right
            } // end while
        } // end else not root
    } // end insert()

    public Node getSuccessor(Node delNode)
    {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild; // go to right child
        while(current != null) // until no more
        { // left children,
            successorParent = successor;
            successor = current;
            current = current.leftChild; // go to left child
        }
        // if successor not
        if(successor != delNode.rightChild) // right child,
        { 				// make connections
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }
    public boolean find(int key)
    { // (assumes non-empty tree)
        Node current = root; // start at root
        while(current.iData != key) // while no match
        {
            if(key < current.iData) // go left?
                current = current.leftChild;
            else
                current = current.rightChild; // or go right?
            if(current == null) // if no child,
                return false; // didn’t find it
        }
        return true; // found it
    }
    public Node findNode(int key)
    { // (assumes non-empty tree)
        Node current = root; // start at root
        while(current.iData != key) // while no match
        {
            if(key < current.iData) // go left?
                current = current.leftChild;
            else
                current = current.rightChild; // or go right?
            if(current == null) // if no child,
                return null; // didn’t find it
        }
        return current; // found it
    }
    public Node getRoot(){
        return root;
    }
    public Node Kok_Dugumu_Oncekiyle_Degistir(Node kok_dugum)
    { // (assumes non-empty tree)
        root=kok_dugum;
        Node current = root;
        Node parent = root;// start at root
        Node eski_kok_dugum = root;//geriye döndürmek için
        if(root.leftChild==null){// 4.
            current=root;
        } else {
            current=root.leftChild;
            while (current.rightChild!=null){
                parent=current;
                current=current.rightChild;
            }
            
        }
        if(current.leftChild==null && current.rightChild==null){// 1. sol tarafa-tan en sağda hiçbir yaprağı olmayan düğüm için
            current.rightChild=root.rightChild;
            current.leftChild=root.leftChild;
            root=current;
            parent.rightChild=null;

        }else if(current.rightChild==null ){// 2. sol tarafta en sağda olan ve sol yaprağı olan için
            parent.rightChild=current.leftChild;
            current.rightChild=root.rightChild;
            current.leftChild=root.leftChild;
            root=current;

        }else if(root.leftChild==current){// 3. kök düğümün solunda yer alan fakat hiçbir yaprağı olmayan düğüm
            root=current;
            root.leftChild=null;
        }else if(root==current && parent.leftChild==null){// 3. kök düğümün solunda yer alan fakat hiçbir yaprağı olmayan düğüm

        }
        return eski_kok_dugum; // found it
    }

    public void delete(int key) { // (assumes non-empty tree)
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;
        while (current.iData != key) // search for node
        {
            parent = current;
            if (key < current.iData) // go left?
            {
                isLeftChild = true;
                current = current.leftChild;
            } else // or go right?
            {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null) // end of the line,
                System.out.println("bulunamadı!");
        } // end while
        // found node to delete
        // continues with case:1
        // case 1: if no children, simply delete it
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root) // if root,
                root = null; // tree is empty
            else if (isLeftChild)
                parent.leftChild = null; // disconnect
            else // from parent
                parent.rightChild = null;
        }
        // continues with case 2:
        // if no right child, replace with left subtree
        else if (current.rightChild == null)
            if (current == root)
                root = current.leftChild;
            else if (isLeftChild) // left child of parent
                parent.leftChild = current.leftChild;
            else // right child of parent
                parent.rightChild = current.leftChild;
// if no left child, replace with right subtree
        else if (current.leftChild == null)
            if (current == root)
                root = current.rightChild;
            else if (isLeftChild) // left child of parent
                parent.leftChild = current.rightChild;
            else // right child of parent
                parent.rightChild = current.rightChild;
// continues with case 3:...

        // delete() continued – case 3
        else // two children, so replace with inorder successor
        {
            // get successor of node to delete (current)
            Node successor = getSuccessor(current);
            // connect parent of current to successor instead
            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.leftChild = successor;
            else
                parent.rightChild = successor;
            // connect successor to current’s left child
            successor.leftChild = current.leftChild;
        } // end else two children
// (successor cannot have a left child)
    } // end delete()
    public void preOrder(Node localRoot)
    {
        if(localRoot != null)
        {
            System.out.print(localRoot.iData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }
}

class TreeApp
{
    public static void main(String[] args)
    {
        Tree theTree = new Tree(); // make a tree
        /*
        theTree.insert(53); // insert 3 nodes
        theTree.insert(30);           A -- şartı
        theTree.insert(72);
        theTree.insert(14);
        theTree.insert(39);
        theTree.insert(61);
        theTree.insert(84);
        theTree.insert(9);
        theTree.insert(23);
        theTree.insert(34);
        theTree.insert(47);
        theTree.insert(79);

         */
        /*
        theTree.insert(53); // insert 3 nodes
        theTree.insert(30);
        theTree.insert(72);        B -- şartı
        theTree.insert(14);
        theTree.insert(39);
        theTree.insert(61);
        theTree.insert(84);
        theTree.insert(9);
        theTree.insert(23);
        theTree.insert(34);
        theTree.insert(79);

         */
        /*
        theTree.insert(39); // insert 3 nodes
        theTree.insert(30);
        theTree.insert(72);         C --şartı
        theTree.insert(61);
        theTree.insert(84);
        theTree.insert(79);

         */
        theTree.insert(39); // insert 3 nodes
        theTree.insert(72);
        theTree.insert(61);
        theTree.insert(84);
        theTree.insert(79);
        theTree.preOrder(theTree.findNode(39));
        Node eski_kok = theTree.Kok_Dugumu_Oncekiyle_Degistir(theTree.getRoot());
        System.out.println("eski kök: " + eski_kok.iData);
        System.out.println(theTree.find(39));
        System.out.println(theTree.getRoot().iData);
    } // end main()
} // end class TreeApp





