package src;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class MainFrame extends JFrame {
    private static JButton mapLabelB = new JButton("Map:");
    private static JLabel mapLabel = new JLabel("Map:");
    private static JLabel inventoryLabel = new JLabel("Inventory:");
    private static JLabel inventoryList = new JLabel("");
    private static JLabel moneyLabel = new JLabel("Money: ");
    private static JLabel outputLabel = new JLabel("Output:");
    private static JLabel outputResLabel = new JLabel("results");
    private static JLabel waterLabel = new JLabel("Water: ");

    private static MainFrame mf_instance = null;

    // untuk table
    Object [][] rowData = new Object[10][11];
    Object [] header = new Object[11];
    DefaultTableModel model = new DefaultTableModel(rowData, header);
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    JTable map = new JTable(model);


    Game gameObj= new Game();

    public static void setOutputResLabel(String masukan) {
        outputResLabel.setText(masukan);
    }

    public static MainFrame getInstance() {
        if (mf_instance==null) {
            mf_instance = new MainFrame();
        }

        return mf_instance;
    }

    public MainFrame() {
        super("Engi's Farm");
        setLayout(new GridBagLayout());
        model.setValueAt("X",1,1);
        // Add label map
        GridBagConstraints cMapLabel = new GridBagConstraints();
        cMapLabel.insets = new Insets(5,0,10,0);
        cMapLabel.anchor = GridBagConstraints.LINE_START;
        cMapLabel.gridx=0;
        cMapLabel.gridy=0;
        add(mapLabel,cMapLabel);

        // Add inventory label
        GridBagConstraints cInventoryLabel = new GridBagConstraints();
        cInventoryLabel.anchor = GridBagConstraints.LINE_START;
        cInventoryLabel.insets = new Insets(5,0,10,40);
        cInventoryLabel.gridx=1;
        cInventoryLabel.gridy=0;
        add(inventoryLabel,cInventoryLabel);

        // Add tabel
        GridBagConstraints cTabel = new GridBagConstraints();
        cTabel.anchor = GridBagConstraints.LINE_START;
        cTabel.gridx=0;
        cTabel.gridy=1;
        Arrays.fill(header,"a");
        map.setRowHeight(20);
        TableColumnModel columnModel = map.getColumnModel();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
//        map.setDefaultRenderer(String.class, centerRenderer);
        for (int i=0; i<map.getColumnCount(); i++){
            columnModel.getColumn(i).setPreferredWidth(20);
            columnModel.getColumn(i).setCellRenderer(centerRenderer);
        }
        map.setFocusable(false);
        add(map,cTabel);

        // Add inventory list
        GridBagConstraints cInventoryList = new GridBagConstraints();
        cInventoryList.anchor = GridBagConstraints.LINE_START;
        cInventoryList.fill = GridBagConstraints.BOTH;
        cInventoryList.gridx=1;
        cInventoryList.gridy=1;
        inventoryList.setVerticalAlignment(JLabel.TOP);
        add(inventoryList,cInventoryList);

        // Add moneyLabel
        GridBagConstraints cMoneyLabel = new GridBagConstraints();
        cMoneyLabel.anchor = GridBagConstraints.LINE_START;
        cMoneyLabel.gridx=0;
        cMoneyLabel.gridy=2;
        add(moneyLabel,cMoneyLabel);

        // Add outputLabel
        GridBagConstraints cOutputLabel = new GridBagConstraints();
        cOutputLabel.anchor = GridBagConstraints.LINE_START;
        cOutputLabel.gridx=1;
        cOutputLabel.gridy=2;
        add(outputLabel,cOutputLabel);

        // Add waterLabel
        GridBagConstraints cWaterLabel = new GridBagConstraints();
        cWaterLabel.anchor = GridBagConstraints.LINE_START;
        cWaterLabel.gridx=0;
        cWaterLabel.gridy=3;
        add(waterLabel,cWaterLabel);

        // Add output result
        GridBagConstraints cOutputResLabel = new GridBagConstraints();
        cOutputResLabel.anchor = GridBagConstraints.LINE_START;
        cOutputResLabel.gridx=1;
        cOutputResLabel.gridy=3;
        add(outputResLabel,cOutputResLabel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Key Listener
        addKeyListener(new KeyListener() {

            public void keyPressed(KeyEvent event) {}

            public void keyTyped(KeyEvent event) {}

            public void keyReleased(KeyEvent event) {
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_A:
                        gameObj.movePlayer(3);
                        outputResLabel.setText("A!!");
                        break;
                    case KeyEvent.VK_S:
                        gameObj.movePlayer(2);
                        outputResLabel.setText("S!!");
                        break;
                    case KeyEvent.VK_D:
                        gameObj.movePlayer(1);
                        outputResLabel.setText("D!!");
                        break;
                    case KeyEvent.VK_W:
                        gameObj.movePlayer(0);
                        outputResLabel.setText("W!!");
                        break;
                    case KeyEvent.VK_I:
                        gameObj.playerInteract();
                        outputResLabel.setText("I!!");
                        break;
                    case KeyEvent.VK_K:
                        gameObj.playerKill();
//                        outputResLabel.setText("K!!");
                        break;
                    case KeyEvent.VK_T:
                        gameObj.playerTalk();
                        outputResLabel.setText("T!!");
                        break;
                    case KeyEvent.VK_G:
                        gameObj.playerGrow();
                        outputResLabel.setText("G!!");
                        break;
                    default:
                        outputResLabel.setText("Unknown!");
                        break;
                }
                gameObj.forwardTime();
                renderGUI();
            }
        });
        renderGUI();
        setFocusable(true);
        setVisible(true);
    }

    void renderGUI() {
        char [][] dummyMap = new char [10][11];
        for (int i=0; i<10; i++) {
            Arrays.fill(dummyMap[i],'\0');
        }
        int x,y;

        for (int i=0; i<gameObj.getListOfAnimal().getNeff(); i++) {
            x = gameObj.getListOfAnimal().get(i).getX();
            y = gameObj.getListOfAnimal().get(i).getY();
            dummyMap[y][x] = gameObj.getListOfAnimal().get(i).getRender();
        }

        // MAP
        for (int i=0; i<10; i++) {
            for(int j=0; j<11; j++) {
                if (gameObj.getPlayer().getX()==j && gameObj.getPlayer().getY()==i) {
                    model.setValueAt(gameObj.getPlayer().getRender(),i,j);
                }
                else if (dummyMap[i][j]=='\0') {
                    //mixer
                    if (gameObj.getMap()[i][j].getTypeCell()==1 || gameObj.getMap()[i][j].getTypeCell()==2 || gameObj.getMap()[i][j].getTypeCell()==3) {
                        model.setValueAt(gameObj.getMap()[i][j].getRender(),i,j);
                    }
                    //barn
                    else if (gameObj.getMap()[i][j].getTypeCell()==4) {
                        model.setValueAt(gameObj.getMap()[i][j].getRender(),i,j);
                    }
                    //coop
                    else if (gameObj.getMap()[i][j].getTypeCell()==5) {
                        model.setValueAt(gameObj.getMap()[i][j].getRender(),i,j);
                    }
                    //grassland
                    else if (gameObj.getMap()[i][j].getTypeCell()==6) {
                        model.setValueAt(gameObj.getMap()[i][j].getRender(),i,j);
                    }
                } else {
                    model.setValueAt(dummyMap[i][j],i,j);
                }
            }
        }

        //INVENTORY
        if (gameObj.getPlayer().getBag().getNeff()==0) {
            inventoryList.setText("Kosong");
        } else {
            inventoryList.setText("");
            List<Product> bag = gameObj.getPlayer().getBag();
            for (int i=0; i<bag.getNeff(); i++) {
                inventoryList.setText(inventoryList.getText() + (i+1)+". "+bag.get(i).getName()+"<br/>");
            }
            inventoryList.setText("<html>"+inventoryList.getText()+"</html>");
        }
        moneyLabel.setText("Money: "+gameObj.getPlayer().getMoney());
        waterLabel.setText("Water: "+gameObj.getPlayer().getWater());
    }
}
