package com.event.treeviewDragDrop;

import java.io.Serializable;

/**
 * @author tony
 * @date 2019/5/6 23:38
 */
public class PlanningItem implements Serializable {

    private static final long serialVersionUID = 1L;
    private Double scene = null;
    private Integer path = null;
    private String move = null;



    /**
     * @return the scene
     */
    public Double getScene() {
        return scene;
    }

    /**
     * @param scene the scene to set
     */
    private void setScene(Double scene) {
        this.scene = scene;
    }

    /**
     * @return the path
     */
    public Integer getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    private void setPath(Integer path) {
        this.path = path;
    }

    /**
     * @return the move
     */
    public String getMove() {
        return move;
    }

    /**
     * @param move the move to set
     */
    private void setMove(String move) {
        this.move = move;
    }

    public PlanningItem(Object item) {
        super();
        if (item instanceof Double) {
            setScene((Double) item);
        } else if (item instanceof Integer) {
            setPath((Integer) item);
        } else if (item instanceof String) {
            setMove((String) item);
        }
    }

    @Override
    public String toString(){

        String result = "";
        if(scene != null){
            result = result+ scene;
        }else if(path != null){
            result = result+ path;
        } else if(move != null){
            result = result+ move;
        }
        return result;
    }

}
