// AVL Node Class
class AVLNode {
int key;
AVLNode left, right;
int height;
AVLNode(int key) {
this.key = key;
height = 0; // Set initial height to 0 for new nodes
 left = right = null;
 }
 }
public class AVLTree {
// Utility function to get the height of a node
public static int height(AVLNode node) {
return node == null ? -1 : node.height;
}
 // Rotate node with left child
 public static AVLNode rotateWithLeftChild(AVLNode k2) {
AVLNode k1 = k2.left;
 k2.left = k1.right;
 k1.right = k2;
 k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
 k1.height = Math.max(height(k1.left), k2.height) + 1;
 return k1;
}

// Rotate node with right child
 public static AVLNode rotateWithRightChild(AVLNode k1) {
 AVLNode k2 = k1.right;
 k1.right = k2.left;
 k2.left = k1;
 k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
 k2.height = Math.max(height(k2.right), k1.height) + 1;
 return k2;
 }

 // Double rotation: left-right
 public static AVLNode doubleWithLeftChild(AVLNode k3) {
 k3.left = rotateWithRightChild(k3.left);
  return rotateWithLeftChild(k3);
 }
 // Double rotation: right-left
 public static AVLNode doubleWithRightChild(AVLNode k1) {
 k1.right = rotateWithLeftChild(k1.right);
 return rotateWithRightChild(k1);
 }
 public static AVLNode insert(int key, AVLNode node) {
 if (node == null)
 return new AVLNode(key);
 if (key < node.key) { //insert on left side and do LL or LR
 node.left = insert(key, node.left);
 if (height(node.left) - height(node.right) == 2)
 if (key < node.left.key)
 node = rotateWithLeftChild(node);
 else
 node = doubleWithLeftChild(node);
 } else if (key > node.key) { //insert on right side and do RR or RL
 node.right = insert(key, node.right);
 if (height(node.right) - height(node.left) == 2)
  if (key > node.right.key)
 node = rotateWithRightChild(node);
 else
 node = doubleWithRightChild(node);
 }
 node.height = Math.max(height(node.left), height(node.right)) + 1;
  return node;
 }

 // Find the minimum node in a tree
 public static AVLNode findMin(AVLNode node) {
  if (node == null || node.left == null)
 return node;
 return findMin(node.left);
 }
 public static AVLNode delete(int key, AVLNode node) {
 if (node == null)
 return null;

 if (key < node.key)
 node.left = delete(key, node.left);
 else if (key > node.key)
 node.right = delete(key, node.right);
 else // Node to be deleted found
 if (node.left != null && node.right != null) { // Two children
 AVLNode minNode = findMin(node.right);
 node.key = minNode.key;
 node.right = delete(minNode.key, node.right);
 } else // One or no child
 node = (node.left != null) ? node.left : node.right;

 if (node != null) {
 node.height = Math.max(height(node.left), height(node.right)) + 1;
 // Balance the tree
 if (height(node.left) - height(node.right) == 2)
  if (height(node.left.left) >= height(node.left.right))
   node = rotateWithLeftChild(node);
 else
 node = doubleWithLeftChild(node);
 else if (height(node.right) - height(node.left) == 2)
 if (height(node.right.right) >= height(node.right.left))
 node = rotateWithRightChild(node);
  else
 node = doubleWithRightChild(node);
 }
 return node;
 }
 public static void inOrder(AVLNode node) {
 if (node != null) {
 inOrder(node.left);
 System.out.print(node.key + " ");
  inOrder(node.right);
   }
 }
 public static void main(String[] args) {
 AVLNode root = null;
 int[] array = {3, 2, 1, 4, 5, 6, 7, 16, 15, 14, 13, 12, 11, 10, 8, 9};
 for (int x:array){
root = insert(x, root);
System.out.println("Height after inserting " + x + " is " + height(root));
}
 System.out.println("In-order traversal of the AVL tree: ");
 inOrder(root);
 root = delete(13, root);
 System.out.println("In-order traversal after deleting 13: ");
 inOrder
(root);
}
}
//output
Height after inserting 3 is 0
Height after inserting 2 is 1
Height after inserting 1 is 1
Height after inserting 4 is 2
Height after inserting 5 is 2
Height after inserting 6 is 2
Height after inserting 7 is 2
Height after inserting 16 is 3
Height after inserting 15 is 3
Height after inserting 14 is 3
Height after inserting 13 is 3
Height after inserting 12 is 3
Height after inserting 11 is 3
Height after inserting 10 is 3
Height after inserting 8 is 4
Height after inserting 9 is 4
In-order traversal of the AVL tree: 
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 
In-order traversal after deleting 13: 
1 2 3 4 5 6 7 8 9 10 11 12 14 15 16
