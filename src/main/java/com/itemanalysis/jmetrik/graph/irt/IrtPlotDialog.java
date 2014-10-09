/*
 * Copyright (c) 2012 Patrick Meyer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.itemanalysis.jmetrik.graph.irt;

import com.itemanalysis.jmetrik.gui.Jmetrik;
import com.itemanalysis.jmetrik.selector.MultipleSelectionPanel;
import com.itemanalysis.jmetrik.sql.DataTableName;
import com.itemanalysis.jmetrik.sql.DatabaseName;
import com.itemanalysis.jmetrik.workspace.VariableChangeListener;
import com.itemanalysis.psychometrics.data.VariableInfo;
import org.apache.log4j.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

public class IrtPlotDialog extends JDialog {

    private IrtPlotCommand command = null;
    private MultipleSelectionPanel vsp = null;
    private DatabaseName dbName = null;
    private DataTableName table = null;
    static Logger logger = Logger.getLogger("jmetrik-logger");
    public boolean canRun = false;

    private JRadioButton expectedButton;
    private JCheckBox iccBox;
    private JCheckBox itemInfoBox;
    private JPanel itemPanel;
    private JCheckBox legendBox;
    private JCheckBox personInfoBox;
    private JPanel personPanel;
    private JCheckBox personSeBox;
    private JTextField pointsText;
    private JRadioButton probTypeButton;
    private JCheckBox tccBox;
    private ButtonGroup typeGroup;
    private JPanel typePanel;
    private JLabel xMaxLabel;
    private JTextField xMaxText;
    private JTextField xMinText;
    private JLabel xPointsLabel;
    private JPanel xaxisPanel;
    private JLabel xminLabel;
    private JFileChooser outputLocationChooser;
    private String outputPath = "";

    /** Creates new form IrtPlotDialog */
    public IrtPlotDialog(Jmetrik parent, DatabaseName dbName, DataTableName table, ArrayList<VariableInfo> variables) {
        super(parent, "IRT Plot", true);

        this.dbName=dbName;
        this.table=table;
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

        //prevent running an analysis when window close button is clicked
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                canRun = false;
            }
        });

        

        vsp = new MultipleSelectionPanel();
        vsp.setVariables(variables);

        JButton b1 = vsp.getButton1();
        b1.setText("Run");
        b1.addActionListener(new RunActionListener());

        JButton b2 = vsp.getButton2();
        b2.setText("Cancel");
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canRun=false;
                setVisible(false);
            }
        });

        JButton b4 = vsp.getButton3();
        b4.setText("Reset");
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vsp.reset();
            }
        });

        JButton b3 = vsp.getButton4();
        b3.setText("Save");
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (outputLocationChooser == null) outputLocationChooser = new JFileChooser();
                outputLocationChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                outputLocationChooser.setDialogType(JFileChooser.OPEN_DIALOG);
                outputLocationChooser.setDialogTitle("Select Location");
                if (outputLocationChooser.showDialog(IrtPlotDialog.this, "OK") != JFileChooser.APPROVE_OPTION) {
                    return;
                }

                File f = outputLocationChooser.getSelectedFile();
                outputPath = f.getAbsolutePath().replaceAll("\\\\", "/");
            }
        });

        initComponents();
        setResizable(false);
        setLocationRelativeTo(parent);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        typeGroup = new ButtonGroup();
        itemPanel = new JPanel();
        iccBox = new JCheckBox();
        itemInfoBox = new JCheckBox();
        legendBox = new JCheckBox();
        personPanel = new JPanel();
        tccBox = new JCheckBox();
        personInfoBox = new JCheckBox();
        personSeBox = new JCheckBox();
        typePanel = new JPanel();
        probTypeButton = new JRadioButton();
        expectedButton = new JRadioButton();
        xaxisPanel = new JPanel();
        xminLabel = new JLabel();
        xMinText = new JTextField();
        xMaxLabel = new JLabel();
        xMaxText = new JTextField();
        xPointsLabel = new JLabel();
        pointsText = new JTextField();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(5, 5, 0, 5);
        getContentPane().add(vsp, gridBagConstraints);

        itemPanel.setBorder(BorderFactory.createTitledBorder("Item"));
        itemPanel.setLayout(new GridLayout(3, 1, 5, 5));

        iccBox.setSelected(true);
        iccBox.setText("Characteristic curve");
        iccBox.setToolTipText("Item characteristic curve");
        itemPanel.add(iccBox);

        itemInfoBox.setText("Information function");
        itemInfoBox.setToolTipText("Item information function");
        itemPanel.add(itemInfoBox);

        legendBox.setSelected(true);
        legendBox.setText("Show legend");
        legendBox.setToolTipText("Show legend on plot");
        itemPanel.add(legendBox);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 5, 5, 2);
        getContentPane().add(itemPanel, gridBagConstraints);

        personPanel.setBorder(BorderFactory.createTitledBorder("Person"));
        personPanel.setLayout(new GridLayout(3, 1, 5, 5));

        tccBox.setSelected(true);
        tccBox.setText("Characteristic curve");
        tccBox.setToolTipText("Test characteristic curve");
        personPanel.add(tccBox);

        personInfoBox.setText("Information function");
        personInfoBox.setToolTipText("Test information function");
        personPanel.add(personInfoBox);

        personSeBox.setText("Standard error");
        personSeBox.setToolTipText("Person standard error");
        personPanel.add(personSeBox);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 2, 5, 2);
        getContentPane().add(personPanel, gridBagConstraints);

        typePanel.setBorder(BorderFactory.createTitledBorder("Curve Type"));
        typePanel.setLayout(new GridLayout(2, 1));

        typeGroup.add(probTypeButton);
        probTypeButton.setSelected(true);
        probTypeButton.setText("Category probability");
        probTypeButton.setToolTipText("Category probability curves");
        probTypeButton.setActionCommand("prob");
        typePanel.add(probTypeButton);

        typeGroup.add(expectedButton);
        expectedButton.setText("Expected score");
        expectedButton.setToolTipText("Expected score curve");
        expectedButton.setActionCommand("expected");
        typePanel.add(expectedButton);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 5, 5, 5);
        getContentPane().add(typePanel, gridBagConstraints);

        xaxisPanel.setBorder(BorderFactory.createTitledBorder("X-axis"));
        xaxisPanel.setLayout(new GridBagLayout());

        xminLabel.setText("Min");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 5, 5, 5);
        xaxisPanel.add(xminLabel, gridBagConstraints);
        xminLabel.getAccessibleContext().setAccessibleName("Min: ");

        xMinText.setText("-4.0");
        xMinText.setMinimumSize(new Dimension(50, 25));
        xMinText.setPreferredSize(new Dimension(50, 25));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new Insets(0, 0, 5, 5);
        xaxisPanel.add(xMinText, gridBagConstraints);

        xMaxLabel.setText("Max");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 5, 5, 5);
        xaxisPanel.add(xMaxLabel, gridBagConstraints);

        xMaxText.setText("4.0");
        xMaxText.setToolTipText("Maximum value");
        xMaxText.setMinimumSize(new Dimension(50, 25));
        xMaxText.setPreferredSize(new Dimension(50, 25));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new Insets(0, 0, 5, 5);
        xaxisPanel.add(xMaxText, gridBagConstraints);

        xPointsLabel.setText("Points");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 5, 5, 5);
        xaxisPanel.add(xPointsLabel, gridBagConstraints);

        pointsText.setText("31");
        pointsText.setToolTipText("Number of grid points");
        pointsText.setMinimumSize(new Dimension(50, 25));
        pointsText.setPreferredSize(new Dimension(50, 25));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new Insets(0, 0, 5, 5);
        xaxisPanel.add(pointsText, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 5, 5, 2);
        getContentPane().add(xaxisPanel, gridBagConstraints);

        pack();
    }// </editor-fold>

    public boolean canRun(){
        return canRun;
    }

    public IrtPlotCommand getCommand(){
        return command;
    }

    public VariableChangeListener getVariableChangedListener(){
        return vsp.getVariableChangedListener();
    }

    public class RunActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e){
            try{
                command = new IrtPlotCommand();
                VariableInfo[] vars = vsp.getSelectedVariables();
                if(vsp.hasSelection()){
                    for(VariableInfo v : vars){
                        command.getFreeOptionList("variables").addValue(v.getName().toString());
                    }

                    command.getPairedOptionList("data").addValue("db", dbName.toString());
                    command.getPairedOptionList("data").addValue("table", table.toString());

                    command.getSelectAllOption("item").setSelected("icc", iccBox.isSelected());
                    command.getSelectAllOption("item").setSelected("info", itemInfoBox.isSelected());

                    command.getSelectAllOption("person").setSelected("tcc", tccBox.isSelected());
                    command.getSelectAllOption("person").setSelected("info", personInfoBox.isSelected());
                    command.getSelectAllOption("person").setSelected("se", personSeBox.isSelected());


                    command.getSelectOneOption("type").setSelected(typeGroup.getSelection().getActionCommand());

                    command.getSelectAllOption("options").setSelected("legend", legendBox.isSelected());

                    command.getPairedOptionList("xaxis").addValue("min", xMinText.getText().trim());
                    command.getPairedOptionList("xaxis").addValue("max", xMaxText.getText().trim());
                    command.getPairedOptionList("xaxis").addValue("points", pointsText.getText().trim());

                    //get output directory
                    if(outputPath!=null && !"".equals(outputPath.trim())){
                        command.getFreeOption("output").add(outputPath.trim());
                    }

                    canRun=true;
                    setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(IrtPlotDialog.this,
                            "You must select variables for the plot",
                            "VariableSelection Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }catch(IllegalArgumentException ex){
                logger.fatal(ex.getMessage(), ex);
                JOptionPane.showMessageDialog(IrtPlotDialog.this,
                        ex.getMessage(),
                        "Syntax Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }//end RunAction



}
