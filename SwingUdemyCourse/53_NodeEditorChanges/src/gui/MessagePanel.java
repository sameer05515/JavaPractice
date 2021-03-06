package gui;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

/**
 * (52) CustomCellEditors, 26 minutes long and referred to one of the more difficult tutorials.
 *
 * Created by everduin on 12/16/2016.
 */
public class MessagePanel extends JPanel {

    private JTree serverTree;
    private ServerTreeCellRenderer treeCellRenderer;
    private ServerTreeCellEditor treeCellEditor;

    public MessagePanel() {

        /*Now we can set the icons*/
        /*51 - To Edit a cell you use editors
        * To view a cell you used renderers
        * - focusing on renderer for (51)*/
        treeCellRenderer = new ServerTreeCellRenderer();
        treeCellEditor = new ServerTreeCellEditor();

        /*Tree consists of leaves and nodes (They're all default mutable tree nodes)*/
        serverTree = new JTree(createTree());
        serverTree.setCellRenderer(treeCellRenderer);
        serverTree.setCellEditor(treeCellEditor);
        serverTree.setEditable(true);

        /*Set selection mode on the selection model -
        * which allows you to only select one at
        * a time. No CTRL + Click to multi-select*/
        serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

       /*Removed TreeSelectionListener - We care if it's checked, not selected (53)*/
        treeCellEditor.addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                ServerInfo info = (ServerInfo) treeCellEditor.getCellEditorValue();
                System.out.println(info + ": " + info.getId() + ": " + info.isChecked());
            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });

        setLayout(new BorderLayout());

        add(new JScrollPane(serverTree), BorderLayout.CENTER);
    }

    /*Good lesson on building up a tree*/
    private DefaultMutableTreeNode createTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Servers");

        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("USA");
        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(new ServerInfo("New York", 1, true));
        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(new ServerInfo("Boston", 2, true));
        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(new ServerInfo("Los Angeles", 3, false));

        /*Leaves*/
        branch1.add(server1);
        branch1.add(server2);
        branch1.add(server3);

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(new ServerInfo("London", 4, false));
        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(new ServerInfo("Edinburgh", 5, true));

        /*Leaves (Servers)*/
        branch2.add(server4);
        branch2.add(server5);

        /*Branches*/
        root.add(branch1);
        root.add(branch2);

        /*Root, some call this Top*/
        return root;
    }
}
