package model;


import model.etat.hero.Hero;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Menu  {
    public static boolean launcher=false;
    private JFrame frame;
    public Menu(){


                   EventQueue.invokeLater(new Runnable() {
                   @Override
                   public void run() {
                       try {
                           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                       } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                           ex.printStackTrace();
                       }

                       frame = new JFrame("Pac Women");
                       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                       JLabel welcome = new JLabel("Welcome");
                       welcome.setFont(new Font("Serif", Font.PLAIN, 44));
                       welcome.setForeground(Color.white);
                       TestPane testPane = new TestPane();

                       testPane.add(welcome);
                       testPane.setBackground(new Color(247, 227, 177 ));
                       frame.add(testPane);

                       frame.pack();
                       frame.setLocationRelativeTo(null);
                       frame.setVisible(true);
                   }
               });
       }



    public class TestPane extends JPanel {
        private List<String> menuItems;
        private String selectMenuItem;
        private String focusedItem;

        private MenuItemPainter painter;
        private Map<String, Rectangle> menuBounds;

        public TestPane()  {

            setBackground(Color.white);
            painter = new SimpleMenuItemPainter();
            menuItems = new ArrayList<>(25);
            menuItems.add("Start Game");
            menuItems.add("Options");
            menuItems.add("Exit");
            selectMenuItem = menuItems.get(0);

            MouseAdapter ma = new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    String newItem = null;

                    for (String text : menuItems) {

                        Rectangle bounds = menuBounds.get(text);
                        if (bounds.contains(e.getPoint())) {
                            newItem = text;
                            if(text.equals("Start Game")){
                                    launcher=true;

                            }
                            else if(text.equals("Exit")){
                                  System.exit(0);

                            }
                            else{
                                JFrame option=new JFrame("Options");
                                option.setSize(300, 300);
                                JPanel choices = new JPanel();
                                choices.setBackground(new Color(247, 227, 177 ));
                                JLabel character = new JLabel("Choose your character : ");
                                JButton belle = new JButton(new ImageIcon("resources/images/belle/bellesFly4.png"));
                                JButton bulle=new JButton(new ImageIcon("resources/images/bulle/bulleD5.png"));
                                belle.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent arg0) {
                                        Hero.character="Belle";

                                    }
                                });
                                bulle.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        Hero.character="Bulle";
                                    }
                                });

                                JLabel difficulty = new JLabel("Choose difficulty : ");
                                JTextField level = new JTextField(2);
                                JButton submit = new JButton("Ok");
                                choices.add(character);
                                choices.add(belle);
                                choices.add(bulle);
                                submit.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {

                                    }
                                });
                              //  choices.setLayout(new BoxLayout(choices, BoxLayout.Y_AXIS));
                                choices.add(difficulty);
                                choices.add(level);
                                choices.add(submit);
                                option.add(choices);
                                option.pack();
                                option.setLocationRelativeTo(null);
                                option.setVisible(true);
                            }
                            break;
                        }
                    }
                    if (newItem != null && !newItem.equals(selectMenuItem)) {
                        selectMenuItem = newItem;
                        repaint();
                    }

                }


                @Override
                public void mouseMoved(MouseEvent e) {
                    focusedItem = null;
                    for (String text : menuItems) {
                        Rectangle bounds = menuBounds.get(text);
                        if (bounds.contains(e.getPoint())) {
                            focusedItem = text;
                            repaint();
                            break;
                        }
                    }
                }

            };

            addMouseListener(ma);
            addMouseMotionListener(ma);

            InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
            ActionMap am = getActionMap();

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "arrowDown");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "arrowUp");

            am.put("arrowDown", new MenuAction(1));
            am.put("arrowUp", new MenuAction(-1));

        }

        @Override
        public void invalidate() {
            menuBounds = null;
            super.invalidate();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension( 600, 600);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Image img = null;
            Image img2= null;
            Image img3= null;
            Image img4= null;
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            try {
                img = ImageIO.read(new File("resources/images/hero.png"));
                img2=ImageIO.read(new File("resources/images/fantome.png"));
                img3=ImageIO.read(new File("resources/images/fantome2.png"));
                img4=ImageIO.read(new File("resources/images/mechants/mojoG3.png"));

            } catch (IOException e) {
                e.printStackTrace();
            }

            //Drawing hero
            g2d.drawImage(img, 10, 10, 130,130,this);

            //Drawing mojo
            g2d.drawImage(img4, 360, 10, 250,130,this);



            // Drawing ghost
            double rotationRequired2 = Math.toRadians (25);
            double locationX = getWidth() / 2;
            double locationY = getWidth() / 2;
            AffineTransform tx2 = AffineTransform.getRotateInstance(rotationRequired2, locationX, locationY);
            AffineTransformOp op2 = new AffineTransformOp(tx2, AffineTransformOp.TYPE_BILINEAR);
            g2d.drawImage(op2.filter((BufferedImage) img3, null), 180, 180,85,85, this);
            double rotationRequired = Math.toRadians (60);
            AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            g2d.drawImage(op.filter((BufferedImage) img2, null), 350, 180,82,82, this);


            if (menuBounds == null) {
                menuBounds = new HashMap<>(menuItems.size());
                int width = 60;
                int height = 60;
                for (String text : menuItems) {
                    Dimension dim = painter.getPreferredSize(g2d, text);
                    width = Math.max(width, dim.width);
                    height = Math.max(height, dim.height);
                }

                int x = (int) ((getWidth() - (width )) / 2.3);

                int totalHeight = (height + 10) * menuItems.size();
                totalHeight += 5 * (menuItems.size() - 1);

                int y = (getHeight() - totalHeight) / 2;

                for (String text : menuItems) {
                    menuBounds.put(text, new Rectangle(x, y, 120, 60));
                    y += 60 + 10 + 5;
                }

            }
            for (String text : menuItems) {
                Rectangle bounds = menuBounds.get(text);
                boolean isSelected = text.equals(selectMenuItem);
                boolean isFocused = text.equals(focusedItem);
                painter.paint(g2d, text, bounds, isSelected, isFocused);
            }


            g2d.dispose();
        }

        public class MenuAction extends AbstractAction {

            private final int delta;

            public MenuAction(int delta) {
                this.delta = delta;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                int index = menuItems.indexOf(selectMenuItem);
                if (index < 0) {
                    selectMenuItem = menuItems.get(0);

                }
                index += delta;
                if (index < 0) {
                    selectMenuItem = menuItems.get(menuItems.size() - 1);
                } else if (index >= menuItems.size()) {
                    selectMenuItem = menuItems.get(0);
                } else {
                    selectMenuItem = menuItems.get(index);
                }
                repaint();
            }

        }

    }

    public interface MenuItemPainter {

        public void paint(Graphics2D g2d, String text, Rectangle bounds, boolean isSelected, boolean isFocused);

        public Dimension getPreferredSize(Graphics2D g2d, String text);

    }

    public class SimpleMenuItemPainter implements MenuItemPainter {

        public Dimension getPreferredSize(Graphics2D g2d, String text) {
            return g2d.getFontMetrics().getStringBounds(text, g2d).getBounds().getSize();
        }

        @Override
        public void paint(Graphics2D g2d, String text, Rectangle bounds, boolean isSelected, boolean isFocused) {
            FontMetrics fm = g2d.getFontMetrics();
            if (isSelected) {
                paintBackground(g2d, bounds, Color.PINK, Color.WHITE);
            } else if (isFocused) {
                paintBackground(g2d, bounds, Color.PINK, Color.WHITE);
            } else {
                paintBackground(g2d, bounds, Color.DARK_GRAY, Color.LIGHT_GRAY);
            }
            int x = bounds.x + ((bounds.width - fm.stringWidth(text)) / 2);
            int y = bounds.y + ((bounds.height - fm.getHeight()) / 2) + fm.getAscent();
            g2d.setColor(isSelected ? Color.WHITE : Color.LIGHT_GRAY);
            g2d.drawString(text, x, y);
        }

        protected void paintBackground(Graphics2D g2d, Rectangle bounds, Color background, Color foreground) {
            g2d.setColor(background);
            g2d.fill(bounds);
            g2d.setColor(foreground);
            g2d.draw(bounds);
        }

    }



    public JFrame getFrame() {
        return frame;
    }
}