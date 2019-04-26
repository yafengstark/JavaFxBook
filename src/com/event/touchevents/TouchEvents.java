/*
 * Copyright (c) 2012, 2014, Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package touchevents;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.TouchPoint;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * Sample that shows how touch events can be used. UI contains four folders
 * and a row of blocks with a circle on one of the blocks. The folders
 * can be moved independently by touching one or more simultaneously
 * (number of folders that can be moved at one time can be limited by the
 * number of touch points the screen supports).
 * The circle can be moved from block to block by first touching the circle
 * and then touching an empty block. Jumping can continue until the finger
 * over the circle is removed.
 * 
 */
public class TouchEvents extends Application {
    
    @Override
    public void start(Stage primaryStage) {
         Group root = new Group();
         final TouchImage img1 = new TouchImage(50, 20,
                 new Image(TouchEvents.class.getResource(
                 "images/folder002.png").toExternalForm(), false));
         final TouchImage img2 = new TouchImage(350, 20,
                 new Image(TouchEvents.class.getResource(
                 "images/folder004.png").toExternalForm(), false));
         final TouchImage img3 = new TouchImage(150, 200,
                 new Image(TouchEvents.class.getResource(
                 "images/folder006.png").toExternalForm(), false));
         final TouchImage img4 = new TouchImage(450, 200,
                 new Image(TouchEvents.class.getResource(
                 "images/folder007.png").toExternalForm(), false));
        
         root.getChildren().addAll(img1, img2, img3, img4);
         
         for (int i = 1; i <= 5; i++) {
             Rectangle pad = new Rectangle(100, 85, Color.BLANCHEDALMOND);
             pad.setTranslateX((i*125-100));
             pad.setTranslateY(470);
             pad.setStroke(Color.GRAY);
             
             root.getChildren().add(pad);
         } 
         
         root.getChildren().add(new Ball(75, 512));
         
         Scene scene = new Scene(root, 650, 600);
         scene.setFill(Color.GAINSBORO);
        
         primaryStage.setTitle("Touch Events Example");
         primaryStage.setScene(scene);
         primaryStage.show();
    }
    
    /**
     * Image that can be dragged by a finger on a touch screen
     */
    public static class TouchImage extends ImageView {
        private long touchId = -1;
        double touchx, touchy;
        

        public TouchImage(int x, int y, Image img) {
            super(img);
            setTranslateX(x);
            setTranslateY(y);
            setEffect(new DropShadow(8.0, 4.5, 6.5, Color.DARKSLATEGRAY));
            
            setOnTouchPressed(new EventHandler<TouchEvent>() {
                @Override public void handle(TouchEvent event) {
                    if (touchId == -1) {
                        touchId = event.getTouchPoint().getId();
                        touchx = event.getTouchPoint().getSceneX() - getTranslateX();
                        touchy = event.getTouchPoint().getSceneY() - getTranslateY();
                    }
                    event.consume();
                }
            });

            setOnTouchReleased(new EventHandler<TouchEvent>() {
                @Override public void handle(TouchEvent event) {
                    if (event.getTouchPoint().getId() == touchId) {
                        touchId = -1;
                    }
                    event.consume();
                }
            });

            setOnTouchMoved(new EventHandler<TouchEvent>() {
                @Override public void handle(TouchEvent event) {
                    if (event.getTouchPoint().getId() == touchId) {
                        setTranslateX(event.getTouchPoint().getSceneX() - touchx);
                        setTranslateY(event.getTouchPoint().getSceneY() - touchy);
                    }
                    event.consume();
                }
            });
        }
    }
    
    /**
     * Ball that can jump from one rectangle to another by touching the
     * ball on a touch screen with one finger and touching another rectangle
     * with a second finger. As long as the finger currently over the ball is
     * not lifted, the ball can be jumped again.
     */
    private static class Ball extends Circle {
        double touchx, touchy;

        public Ball(int x, int y) {
            super(35);
            
            RadialGradient gradient = new RadialGradient(0.8, -0.5, 0.5, 0.5, 1,
                true, CycleMethod.NO_CYCLE, new Stop [] {
                    new Stop(0, Color.FIREBRICK),
                    new Stop(1, Color.BLACK)
                });
            
            setFill(gradient);
            setTranslateX(x);
            setTranslateY(y);

            setOnTouchPressed(new EventHandler<TouchEvent>() {
                @Override public void handle(TouchEvent event) {
                    if (event.getTouchCount() == 1) {
                        touchx = event.getTouchPoint().getSceneX() - getTranslateX();
                        touchy = event.getTouchPoint().getSceneY() - getTranslateY();
                        setEffect(new Lighting());
                    }
                    event.consume();
                }
            });

            setOnTouchReleased(new EventHandler<TouchEvent>() {
                @Override public void handle(TouchEvent event) {
                    setEffect(null);
                    event.consume();
                }
            });

            // Jump if the first finger touched the ball and is either
            // moving or still, and the second finger touches a rectangle
            EventHandler<TouchEvent> jumpHandler = new EventHandler<TouchEvent>() {
                @Override public void handle(TouchEvent event) {

                    if (event.getTouchCount() != 2) {
                        // Ignore if this is not a two-finger touch
                        return;
                    }

                    TouchPoint main = event.getTouchPoint();
                    TouchPoint other = event.getTouchPoints().get(1);

                    if (other.getId() == main.getId()) {
                        // Ignore if the second finger is in the ball and
                        // the first finger is anywhere else
                        return;
                    }

                    if (other.getState() != TouchPoint.State.PRESSED ||
                            other.belongsTo(Ball.this) ||
                            !(other.getTarget() instanceof Rectangle) ){
                        // Jump only if the second finger was just 
                        // pressed in a rectangle
                        return;
                    }

                    // Jump now
                    setTranslateX(other.getSceneX() - touchx);
                    setTranslateY(other.getSceneY() - touchy);

                    // Grab the destination touch point, which is now inside
                    // the ball, so that jumping can continue without
                    // releasing the finger
                    other.grab();

                    // The original touch point is no longer of interest, so
                    // call ungrab() to release the target
                    main.ungrab();

                    event.consume();
                }
            };

            setOnTouchStationary(jumpHandler);
            setOnTouchMoved(jumpHandler);
        }
    }
   
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
