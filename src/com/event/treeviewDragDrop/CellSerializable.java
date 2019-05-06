package com.event.treeviewDragDrop;

import javafx.scene.control.TreeCell;

import java.io.*;

/**
 * @author tony
 * @date 2019/5/7 0:18
 */
public class CellSerializable implements Externalizable {

    private TreeCell<PlanningItem> cell;

    public CellSerializable(TreeCell<PlanningItem> cell){
        this.cell = cell;
    }

    public TreeCell<PlanningItem> getCell() {
        return cell;
    }

    private void writeObject(ObjectOutputStream output) throws IOException {
//        output.defaultWriteObject();
        output.writeUTF(cell.getTreeItem().getValue().toString());
    }
    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
//        input.defaultReadObject();
        String value = input.readUTF();
        System.out.println(value);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {


        out.writeObject(cell);
//        cell.getTreeItem()

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//        String = (String) in.readObject();
//        age = in.readInt();
        cell = (TreeCell<PlanningItem>)in.readObject();
    }
}
