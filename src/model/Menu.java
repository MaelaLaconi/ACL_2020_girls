package model;


import model.etat.lab.Difficulty;
import model.etat.hero.Hero;
import start.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;


public class Menu  {
    public static boolean launcher=false;
    private JFrame frame;
    private int score=0; ;
    private  JLabel bestScore;
    private TestPane testPane;
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
                       testPane = new TestPane();

                       testPane.setBackground(new Color(247, 227, 177 ));
                       frame.add(testPane);
                       bestScore = new JLabel("Meilleur score : "+getScore());
                       JLabel team = new JLabel("Ⓡ Team Girls");
                       team.setFont(new Font("Serif", Font.ITALIC, 15));
                       bestScore.setFont(new Font("Serif", Font.PLAIN, 30));
                       bestScore.setForeground(new Color(166, 0, 0));
                       JPanel infos = new JPanel(new BorderLayout());
                       Border blackline = BorderFactory.createLineBorder(Color.black);
                       infos.setBorder(blackline);
                       infos.setBackground(new Color(56, 123, 93));
                       infos.add(bestScore, BorderLayout.WEST);
                       infos.add(team, BorderLayout.EAST);
                       testPane.setLayout(new BorderLayout());
                       testPane.add(infos, BorderLayout.SOUTH);


                       frame.pack();
                       frame.setLocationRelativeTo(null);
                       frame.setVisible(true);
                       frame.setResizable(false);
                   }
               });
       }



    public class TestPane extends JPanel {
        private List<String> menuItems;
        private String selectMenuItem;
        private String focusedItem;

        private MenuItemPainter painter;
        private Map<String, Rectangle> menuBounds;
        private JComboBox niveau;

        public TestPane()  {

            setBackground(Color.white);
            painter = new SimpleMenuItemPainter();
            menuItems = new ArrayList<>(25);
            menuItems.add("Start Game");
            menuItems.add("Options");
            menuItems.add("Help");
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
                            else if(text.equals("Help")){
                                JFrame help=new JFrame();
                                help.add(new JLabel(new ImageIcon(getClass().getResource("/images/helpOption.png"))));
                                help.pack();
                                help.setLocationRelativeTo(null);
                                help.setVisible(true);

                            }
                            else{
                                JFrame option=new JFrame("Options");
                                option.setSize(300, 300);
                                JPanel choices = new JPanel();
                                choices.setBackground(new Color(247, 227, 177 ));
                                JLabel character = new JLabel("Choose your character : ");
                                JButton belle = new JButton(new ImageIcon(getClass().getResource("/images/belle/bellesFly4.png")));
                                belle.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent arg0) {
                                        Hero.character="Belle";
                                    }
                                });

                                JButton bulle = new JButton(new ImageIcon(getClass().getResource("/images/bulle/bulleD1.png")));
                                bulle.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent arg0) {
                                        Hero.character="Bulle";
                                    }
                                });
                                JLabel difficulty = new JLabel("Choose difficulty : ");
                                Object[] elements = new Object[]{"1", "2", "3", "4", "5"};
                                niveau = new JComboBox(elements);
                                JButton submit = new JButton("Ok");
                                choices.add(character);
                                choices.add(belle);
                                choices.add(bulle);
                                choices.add(difficulty);
                                submit.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent arg0) {
                                        Difficulty.level= (String) niveau.getSelectedItem();
                                        System.out.println(niveau.getSelectedItem());
                                        option.dispose();

                                    }
                                });






                                choices.add(niveau);
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
                img = ImageIO.read(getClass().getResourceAsStream("/images/hero.png"));
                img2=ImageIO.read(getClass().getResourceAsStream("/images/fantome.png"));
                img3=ImageIO.read(getClass().getResourceAsStream("/images/fantome2.png"));
                img4=ImageIO.read(getClass().getResourceAsStream("/images/mechants/mojoG3.png"));

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

    public void setScore(int score) {
        if (score > this.score){
            this.bestScore = new JLabel("Meilleur score : "+score);
            this.score = score;
        }

    }
    public void update(){
        frame.dispose();
        frame = new JFrame("Pac Women");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testPane = new TestPane();

        testPane.setBackground(new Color(247, 227, 177 ));
        frame.add(testPane);
        bestScore = new JLabel("Meilleur score : "+getScore());
        JLabel team = new JLabel("Ⓡ Team Girls");
        team.setFont(new Font("Serif", Font.ITALIC, 15));
        bestScore.setFont(new Font("Serif", Font.PLAIN, 30));
        bestScore.setForeground(new Color(166, 0, 0));
        JPanel infos = new JPanel(new BorderLayout());
        Border blackline = BorderFactory.createLineBorder(Color.black);
        infos.setBorder(blackline);
        infos.setBackground(new Color(56, 123, 93));
        infos.add(bestScore, BorderLayout.WEST);
        infos.add(team, BorderLayout.EAST);
        testPane.setLayout(new BorderLayout());
        testPane.add(infos, BorderLayout.SOUTH);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

    }

    public int getScore() {
        return score;
    }

    public JFrame getFrame() {
        return frame;
    }
}