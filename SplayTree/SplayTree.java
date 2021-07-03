import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

public class SplayTree {
    File file = new File("output.txt");
    PrintWriter fileWriter= new PrintWriter(file);
    private String[] arr =null;
    private Node root;

    SplayTree() throws FileNotFoundException {
    }

    void fillArray(){
       arr = new String[(int) Math.pow(2,maxDepth(root))-1];
        Arrays.fill(arr, "-");
    }

    void printTree(){
        for (String i :arr) {
            fileWriter.print(i+" ");
        }
        fileWriter.println();
    }

    void finish(){
        fileWriter.close();
    }

    void addElementToArray(Node root , int i){
        if (root==null)
            return;
        arr[i-1] = root.element;
        addElementToArray(root.left,2*i);
        addElementToArray(root.right,2*i+1);
    }

    public void insert(int ele)
    {
        Node node = root;
        Node parent = null;
        while (node != null)
        {
            parent = node;
            if (ele >Integer.parseInt(parent.element))
                node = node.right;
            else
                node = node.left;
        }
        node = new Node();
        node.element =String.valueOf(ele) ;
        node.parent = parent;
        if (parent == null)
            root = node;
        else if (ele > Integer.parseInt(parent.element))
            parent.right = node;
        else
            parent.left = node;
        Splay(node);

    }

    int maxDepth(Node node)
    {
        if (node == null)
            return 0;
        else
        {
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }

    public void makeLeftChildParent(Node child, Node parent)
    {
        if ((child == null) || (parent == null) || (parent.left != child) || (child.parent != parent))
            throw new RuntimeException("WRONG");

        if (parent.parent != null)
        {
            if (parent == parent.parent.left)
                parent.parent.left = child;
            else
                parent.parent.right = child;
        }
        if (child.right != null)
            child.right.parent = parent;

        child.parent = parent.parent;
        parent.parent = child;
        parent.left = child.right;
        child.right = parent;
    }

    public void makeRightChildParent(Node child, Node parent)
    {
        if ((child == null) || (parent == null) || (parent.right != child) || (child.parent != parent))
            throw new RuntimeException("WRONG");
        if (parent.parent != null)
        {
            if (parent == parent.parent.left)
                parent.parent.left = child;
            else
                parent.parent.right = child;
        }
        if (child.left != null)
            child.left.parent = parent;
        child.parent = parent.parent;
        parent.parent = child;
        parent.right = child.left;
        child.left = parent;
    }


    public void Splay(Node x)
    {
        while (x.parent != null)
        {
            Node Parent = x.parent;
            Node GrandParent = Parent.parent;
            if (GrandParent == null)
            {
                if (x == Parent.left)
                    makeLeftChildParent(x, Parent);
                else
                    makeRightChildParent(x, Parent);
            }
            else
            {
                if (x == Parent.left)
                {
                    if (Parent == GrandParent.left)
                    {
                        makeLeftChildParent(Parent, GrandParent);
                        makeLeftChildParent(x, Parent);
                    }
                    else
                    {
                        makeLeftChildParent(x, x.parent);
                        makeRightChildParent(x, x.parent);
                    }
                }
                else
                {
                    if (Parent == GrandParent.left)
                    {
                        makeRightChildParent(x, x.parent);
                        makeLeftChildParent(x, x.parent);
                    }
                    else
                    {
                        makeRightChildParent(Parent, GrandParent);
                        makeRightChildParent(x, Parent);
                    }
                }
            }
        }
        root = x;
    }

    public void remove(Node node)
    {
        if (node == null)
            return;

        Splay(node);
        if( (node.left != null) && (node.right !=null))
        {
            Node min = node.left;
            while(min.right!=null)
                min = min.right;

            min.right = node.right;
            node.right.parent = min;
            node.left.parent = null;
            root = node.left;
        }
        else if (node.right != null)
        {
            node.right.parent = null;
            root = node.right;
        }
        else if( node.left !=null)
        {
            node.left.parent = null;
            root = node.left;
        }
        else
        {
            root = null;
        }
        node.parent = null;
        node.left = null;
        node.right = null;
    }

    public Node findNode(int ele)
    {
        Node PrevNode = null;
        Node node = root;
        while (node != null)
        {
            PrevNode = node;
            if (ele > Integer.parseInt(node.element))
                node = node.right;
            else if (ele <Integer.parseInt(node.element))
                node = node.left;
            else if(ele == Integer.parseInt(node.element)) {
                Splay(node);
                return node;
            }
        }
        if(PrevNode != null)
        {
            Splay(PrevNode);
            return null;
        }
        return null;
    }

    public Node getRoot() {
        return root;
    }
}