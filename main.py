import sys

class Node():
    def __init__(self, value):
        self.value = value
        self.parent = None
        self.left = None
        self.right = None
        self.color = 1

class RedBlackTree():
    def __init__(self):
        self.NIL = Node(0)
        self.NIL.color = 0
        self.NIL.left = None
        self.NIL.right = None
        self.root = self.NIL

    def insert(self, key):
        node = Node(key)
        node.parent = None
        node.value = key
        node.left = self.NIL
        node.right = self.NIL
        node.color = 1

        node_to_insert = None
        root = self.root

        while root != self.NIL:
            node_to_insert = root
            if node.value < root.value:
                root = root.left
            else:
                root = root.right

        node.parent = node_to_insert
        if node_to_insert == None:
            self.root = node
        elif node.value < node_to_insert.value:
            node_to_insert.left = node
        else:
            node_to_insert.right = node

        if node.parent == None:
            node.color = 0
            return

        if node.parent.parent == None:
            return

        self.fix_insert(node)


    def delete_node(self, value):
        self.delete_node_helper(self.root, value)

    # Node deletion
    def delete_node_helper(self, node, key):
        node_to_delete = self.NIL
        while node != self.NIL:
            if node.value == key:
                node_to_delete = node

            if node.value <= key:
                node = node.right
            else:
                node = node.left

        if node_to_delete == self.NIL:
            print("Cannot find key in the tree")
            return

        min_of_subtree = node_to_delete
        min_of_subtree_original_color = min_of_subtree.color
        if node_to_delete.left == self.NIL:
            child = node_to_delete.right
            self.replace(node_to_delete, node_to_delete.right)
        elif (node_to_delete.right == self.NIL):
            child = node_to_delete.left
            self.replace(node_to_delete, node_to_delete.left)
        else:
            min_of_subtree = self.minimum(node_to_delete.right)
            min_of_subtree_original_color = min_of_subtree.color
            child = min_of_subtree.right
            if min_of_subtree.parent == node_to_delete:
                child.parent = min_of_subtree
            else:
                self.replace(min_of_subtree, min_of_subtree.right)
                min_of_subtree.right = node_to_delete.right
                min_of_subtree.right.parent = min_of_subtree

            self.replace(node_to_delete, min_of_subtree)
            min_of_subtree.left = node_to_delete.left
            min_of_subtree.left.parent = min_of_subtree
            min_of_subtree.color = node_to_delete.color
        if min_of_subtree_original_color == 0:
            self.delete_fix(child)

    def print_tree(self):
        self.__print_helper(self.root, "", True)

    # Balance the tree after insertion
    def fix_insert(self, parent_of_new_node):
        while parent_of_new_node.parent.color == 1:
            if parent_of_new_node.parent == parent_of_new_node.parent.parent.right:
                grand_parent = parent_of_new_node.parent.parent.left
                if grand_parent.color == 1:
                    grand_parent.color = 0
                    parent_of_new_node.parent.color = 0
                    parent_of_new_node.parent.parent.color = 1
                    parent_of_new_node = parent_of_new_node.parent.parent
                else:
                    if parent_of_new_node == parent_of_new_node.parent.left:
                        parent_of_new_node = parent_of_new_node.parent
                        self.right_rotate(parent_of_new_node)
                    parent_of_new_node.parent.color = 0
                    parent_of_new_node.parent.parent.color = 1
                    self.left_rotate(parent_of_new_node.parent.parent)
            else:
                grand_parent = parent_of_new_node.parent.parent.right

                if grand_parent.color == 1:
                    grand_parent.color = 0
                    parent_of_new_node.parent.color = 0
                    parent_of_new_node.parent.parent.color = 1
                    parent_of_new_node = parent_of_new_node.parent.parent
                else:
                    if parent_of_new_node == parent_of_new_node.parent.right:
                        parent_of_new_node = parent_of_new_node.parent
                        self.left_rotate(parent_of_new_node)
                    parent_of_new_node.parent.color = 0
                    parent_of_new_node.parent.parent.color = 1
                    self.right_rotate(parent_of_new_node.parent.parent)
            if parent_of_new_node == self.root:
                break
        self.root.color = 0

    # Balancing the tree after deletion
    def delete_fix(self, node):
        while node != self.root and node.color == 0:
            if node == node.parent.left:
                conditional = node.parent.right
                if conditional.color == 1:
                    conditional.color = 0
                    node.parent.color = 1
                    self.left_rotate(node.parent)
                    conditional = node.parent.right

                if conditional.left.color == 0 and conditional.right.color == 0:
                    conditional.color = 1
                    node = node.parent
                else:
                    if conditional.right.color == 0:
                        conditional.left.color = 0
                        conditional.color = 1
                        self.right_rotate(conditional)
                        conditional = node.parent.right

                    conditional.color = node.parent.color
                    node.parent.color = 0
                    conditional.right.color = 0
                    self.left_rotate(node.parent)
                    node = self.root
            else:
                conditional = node.parent.left
                if conditional.color == 1:
                    conditional.color = 0
                    node.parent.color = 1
                    self.right_rotate(node.parent)
                    conditional = node.parent.left

                if conditional.right.color == 0 and conditional.left.color == 0:
                    conditional.color = 1
                    node = node.parent
                else:
                    if conditional.left.color == 0:
                        conditional.right.color = 0
                        conditional.color = 1
                        self.left_rotate(conditional)
                        conditional = node.parent.left

                    conditional.color = node.parent.color
                    node.parent.color = 0
                    conditional.left.color = 0
                    self.right_rotate(node.parent)
                    node = self.root
        node.color = 0

    def replace(self, u, v):
        if u.parent == None:
            self.root = v
        elif u == u.parent.left:
            u.parent.left = v
        else:
            u.parent.right = v
        v.parent = u.parent




    # Printing the tree
    def __print_helper(self, node, indent, last):
        if node != self.NIL:
            sys.stdout.write(indent)
            if last:
                sys.stdout.write("R----")
                indent += "     "
            else:
                sys.stdout.write("L----")
                indent += "|    "

            s_color = "RED" if node.color == 1 else "BLACK"
            print(str(node.value) + "(" + s_color + ")")
            self.__print_helper(node.left, indent, False)
            self.__print_helper(node.right, indent, True)

    def minimum(self, node):
        while node.left != self.NIL:
            node = node.left
        return node

    # def maximum(self, node):
    #     while node.right != self.NIL:
    #         node = node.right
    #     return node

    def left_rotate(self, x):
        y = x.right
        x.right = y.left
        if y.left != self.NIL:
            y.left.parent = x

        y.parent = x.parent
        if x.parent == None:
            self.root = y
        elif x == x.parent.left:
            x.parent.left = y
        else:
            x.parent.right = y
        y.left = x
        x.parent = y

    def right_rotate(self, x):
        y = x.left
        x.left = y.right
        if y.right != self.NIL:
            y.right.parent = x

        y.parent = x.parent
        if x.parent == None:
            self.root = y
        elif x == x.parent.right:
            x.parent.right = y
        else:
            x.parent.left = y
        y.right = x
        x.parent = y

if __name__ == "__main__":
    rbt = RedBlackTree()

    print("Number of edges: ")

    number_of_edges = int(input())

    print("\nInsert ", number_of_edges, " edges: ")
    for insertion in range(number_of_edges):
        rbt.insert(input())

    rbt.print_tree()

    print("\nDo u want to delete edge?")
    order = input()
    if order == 'yes':
        print("\nEnter the node to delete:")
        rbt.delete_node(input())
        print("\nAfter deleting an element:")
        rbt.print_tree()
    else:
        pass