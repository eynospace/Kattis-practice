import java.util.*;

class AVLVertex{
  AVLVertex(Node v){
      key = v;
      parent = left = right = null;
      height = 0;
      size = 1;
  }
  public AVLVertex parent, left, right;
  public Node key;
  public int height;
  public int size;
}

class AVL{
  public AVLVertex root;
  public AVL(){root = null;}
  public int height(AVLVertex T){
      if(T==null) return 0;
      else return T.height;
  }
  public int size(AVLVertex T){
      if(T==null) return 0;
      else return T.size;
  }

  public boolean search(Node v){
      AVLVertex res = search(root, v);
      if(res==null) return false;
      else return true;
  }

  public AVLVertex search(AVLVertex T, Node v){
      if(T==null) return null;
      else if(T.key.compareTo(v)==0) return T;
      else if(T.key.compareTo(v)<0) return search(T.right,v);
      else return search(T.left, v);
  }

  public Node findMin() {return findMin(root);}

  public Node findMin(AVLVertex T){
      if(T.left==null) return T.key;
      else return findMin(T.left);
  }

  public Node findMax(){return findMax(root);}

  public Node findMax(AVLVertex T){
      if(T.right==null) return T.key;
      else return findMax(T.right);
  }

  public Node successor(Node v){
      AVLVertex vPos = search(root, v);
      if(vPos==null) return null;
      else return successor(vPos);
  }

  public Node successor(AVLVertex T){
      if(T.right!=null) return findMin(T.right);
      else{
          AVLVertex par = T.parent;
          AVLVertex cur = T;
          while((par!=null)&&(cur==par.right)){
              cur = par;
              par = cur.parent;
          }
          if(par==null) return null;
          else return par.key;
      }
  }
  public Node predecessor(Node v){
      AVLVertex vPos = search(root, v);
      if(vPos==null) return null;
      else return predecessor(vPos);
  }

  public Node predecessor(AVLVertex T){
      AVLVertex par;
      AVLVertex cur;
      if(T.left!=null) return findMax(T.left);
      else{
          par = T.parent;
          cur = T;
          while ((par != null) && (cur == par.left)) { 
              cur = par;                                         // continue moving up
              par = cur.parent;
          }
      }
      if(par==null) return null;
      else return par.key;
  }

  public void insert(Node v){root = insert(root,v);}

  public AVLVertex insert(AVLVertex T, Node v){
      if(T==null) return new AVLVertex(v);
      if(T.key.compareTo(v)<0){
          T.right = insert(T.right, v);
          T.right.parent = T;
          T.right.height = Math.max(height(T.right.left), height(T.right.right)) + 1;
          T.right.size = size(T.right.left) + size(T.right.right) + 1;
      }
      else{
          T.left = insert(T.left, v);
          T.left.parent = T;
          T.left.height = Math.max(height(T.left.left), height(T.left.right)) + 1;
          T.left.size = size(T.left.left) + size(T.left.right) + 1;
      }
      T.height = Math.max(height(T.left), height(T.right)) + 1;
      T.size = size(T.left) + size(T.right) + 1;
      T = rebalance(T);
      return T;
  }

  public void delete(Node v){root = delete(root,v);}

  public AVLVertex delete(AVLVertex T, Node v){
      if(T==null) return T;
      if(T.key.compareTo(v)<0){
          T.right = delete(T.right,v);
      }
      else if(T.key.compareTo(v)>0){
          T.left = delete(T.left, v);
      }
      else{
          if(T.left==null && T.right==null) T=null;
          else if(T.left==null && T.right!=null){
              T.left.parent = T.parent;
              T = T.left;
          }
          else{
              Node successorV = successor(v);
              T.key = successorV;
              T.right = delete(T.right, successorV);
          }
      }
      if(T!=null){
          T.height = Math.max(height(T.left), height(T.right))+1;
          T.size = size(T.left)+size(T.right)+1;
      }
      T = rebalance(T);
      return T;
  }

  public AVLVertex rebalance(AVLVertex T){
      if(bf(T)==2){
          if(bf(T.left)>=0){
              T = rightRotate(T);
          }
          else if(bf(T.left)<0){
              T.left = leftRotate(T.left);
              T = rightRotate(T);
          }
      }
      else if(bf(T)==-2){
          if(bf(T.right)<=0){
              T = leftRotate(T);
          }
          else if(bf(T.right)>0){
              T.right = rightRotate(T.right);
              T = leftRotate(T);
          }
      }
      return T;
  }

  public int bf(AVLVertex T){
      if(T==null) return 0;
      return height(T.left) - height(T.right);
  }

  public AVLVertex leftRotate(AVLVertex T){
      AVLVertex w = T.right;
      w.parent = T.parent;
      T.parent = w;
      T.right = w.left;
      if(w.left!=null){w.left.parent =T;}
      w.left = T;
      T.height = Math.max(height(T.left), height(T.right))+1;
      w.height = Math.max(height(T.left),height(T.right))+1;
      return w;
  }

  public AVLVertex rightRotate(AVLVertex T){
      AVLVertex w = T.left;
      w.parent = T.parent;
      T.parent = w;
      T.left = w.right;
      if(w.right!=null){w.right.parent=T;}
      w.right = T;
      T.height = Math.max(height(T.left), height(T.right));
      w.height = Math.max(height(w.left), height(w.right));
      return w;
  }

  public int rank(AVLVertex T, Node v){
      int _rank = 0;
      if(T==null) return 0;
      if(T.key ==v) _rank = size(T.left)+1;
      else if(T.key.compareTo(v)>0){
          _rank = rank(T.left,v);
      }
      else if(T.key.compareTo(v)<0){
          _rank = rank(T.right, v)+size(T.left)+1;
      }
      return _rank;
  }

}
public class AVLTree{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    sc.nextLine();
    AVL T = new AVL();
    int[] team = new int[2*(n+1)];
    for(int i=0; i<m; i++){
      int t = sc.nextInt();
      int p = sc.nextInt();
      sc.nextLine();
      int score = team[2*t];
      int penalty = team[2*t+1];
      Node node = new Node(t, score, penalty);
          //delete repeated node
      if(T.search(node)){
        T.delete(node);
      }
          //update score & penalty in arr
      score++;
      penalty+=p;
          //insert updated node
      Node new_node = new Node(t, score, penalty);
      T.insert(new_node);
      //check team1 rank
      Node team1 = new Node(1, team[2], team[3]);
      int rank = 0;
      if(T.search(team1)){
        rank = T.size(T.root)+1;
      }else rank = T.rank(T.root, team1);
      System.out.print(rank+"\n");
    }
  }
}

class Node implements Comparable<Node>{
  int t;
  int s;
  int p;

  public Node(int t, int s, int p){
    this.t = t;
    this.s = s;
    this.p = p;
  }

  public int compareTo(Node other){
    int res = 0;
    if(this.s == other.s){
      res = other.p - this.p;
    }
    else res = this.s - other.s;
    return res;
  }
}