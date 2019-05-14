import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;


import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

   
public class TestEntity {

	private JFrame frmEltelTest;
	private JPanel panel;
	boolean allEntitiesBlocked = false;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					File file = new File("entityData.json");
					if(!file.exists() || file.isDirectory()) {
						
						JOptionPane.showMessageDialog(null, "File 'entityData.json' does not exist ");
						return;
					}
					ReadJSONFile.parserFile();
					TestEntity window = new TestEntity();
					window.frmEltelTest.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestEntity() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEltelTest = new JFrame();
		frmEltelTest.getContentPane().setEnabled(false);
		frmEltelTest.setTitle("Eltel test");
		frmEltelTest.setBounds(100, 100, 544, 428);
		frmEltelTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEltelTest.getContentPane().setLayout(null);
		
		//Create dynamic checkBoxs according to the JSON file size and max 10 
		for (int i=0; i<EntityData.listEntityData.size() && i < EntityData.MAX_ENTITY; i++) {
			JCheckBox chckbxNewCheckBox = new JCheckBox("elD"+EntityData.listEntityData.get(i).getEntityID() + "_" + EntityData.listEntityData.get(i).getName());
			chckbxNewCheckBox.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					int j;
					AbstractButton source = (AbstractButton)e.getSource();
					if (source == chckbxNewCheckBox) {
						for (j = 0; j< EntityData.listEntityData.size() && j < EntityData.MAX_ENTITY; j++) {
							String idName = "elD"+EntityData.listEntityData.get(j).getEntityID() + "_" + EntityData.listEntityData.get(j).getName();
							if (chckbxNewCheckBox.getText().equalsIgnoreCase(idName)) {
								break;
							}
						}
						EntityData.listEntityData.get(j).setIsVisible(source.isSelected());
						System.out.println("chckbxNewCheckBox: " + source.isSelected());
						SwingUtilities.updateComponentTreeUI(frmEltelTest);
					}
				}
			});
			chckbxNewCheckBox.setSelected(true);
			chckbxNewCheckBox.setBounds(6, 10+(i*26), 120, 23);
			frmEltelTest.getContentPane().add(chckbxNewCheckBox);
		}

		// Create timer
		final Timer time = new Timer(5000, null);
		time.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// Move the location of the shapes 
			if (!checkVisibelEntities()) {
				JOptionPane.showMessageDialog(frmEltelTest, "No Entitiy is selected !");
				time.stop();
			} else {
				allEntitiesBlocked = RandomMovment.moveEntitysLocation();	
				SwingUtilities.updateComponentTreeUI(frmEltelTest);
				if (allEntitiesBlocked) {
					
					JOptionPane.showMessageDialog(frmEltelTest, "All Entities are Blocked !");
					time.stop();
				}
			}
		}
	});

		// create start and stop buttons 
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time.start();
			}
		});
		btnStart.setBounds(6, 310, 89, 23);
		frmEltelTest.getContentPane().add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time.stop();
				String num = JOptionPane.showInputDialog(frmEltelTest,
                        "Please enter the number of movment\n you want to save to CSV file", null);
				if (num != null && !num.isEmpty()) {
					WriteCSVFile.csvWriter(Integer.parseInt(num));						
				}
			}
		});
		btnStop.setBounds(6, 337, 89, 23);
		frmEltelTest.getContentPane().add(btnStop);
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(150, 10, 350, 350);
		frmEltelTest.getContentPane().add(panel);
		
		NewShapes newComp = new NewShapes();
		newComp.setBackground(Color.LIGHT_GRAY);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(newComp, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(newComp, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
					.addContainerGap())
		);
		newComp.setLayout(new MigLayout("", "[]", "[]"));
		panel.setLayout(gl_panel);
	}
	
	public  boolean checkVisibelEntities() {
		for(int i=0 ; i < EntityData.listEntityData.size() && i < EntityData.MAX_ENTITY  ; i++) {
			if (EntityData.listEntityData.get(i).getIsVisible()) {
				return true;
			}
		}
		return false;	

	}
}

