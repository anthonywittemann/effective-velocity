package witt.anthony;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MainC extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
	//4 EFFECTIVE VELOCITY MPH DIFFERENCE NEEDED! DEFAULT
	//assumes osLbl is - 15 mph from fB
	//assumes cU is -7 mph from fB
	//TODO FINISH LEFTIES ZONES and figure out why lefty zomes won't fill in
	private int clx; //click location x
	private int cly; //click location y
	private int mx; //mouse location x
	private int my; //mouse location y
	
	private boolean wH; //for a batter True if righty, false if lefty
	
	private JButton rHButton = new JButton();
	private JButton lHButton = new JButton();
	private JButton newBatterButton = new JButton();
	private JPanel battertyPanel = new JPanel();
	private JPanel nextPitchInfoPanel = new JPanel();
	private boolean showORhide = true;
	private JToggleButton showORhideButton = new JToggleButton(">", showORhide);
	private JLabel nextPitchLbl = new JLabel();
	private JLabel fBLbl = new JLabel("FastBall");
	private JLabel cULbl = new JLabel("Change-Up");
	private JLabel osLbl = new JLabel("Off Speed");
	private int aNr = 200; //alpha value for changing transparency of highlighted boxes
	
	//booleans for highlighting different boxes
	private boolean r0;
	private boolean r1;
	private boolean r2;
	private boolean r3;
	private boolean r4;
	private boolean r1n;
	private boolean r2n;
	private boolean r3n;
	private boolean r4n;
	private boolean l0;
	private boolean l1;
	private boolean l2;
	private boolean l3;
	private boolean l4;
	private boolean l1n;
	private boolean l2n;
	private boolean l3n;
	private boolean l4n;
	private boolean lc0;
	private boolean lc1;
	private boolean lc2;
	private boolean lc3;
	private boolean lc4;
	private boolean lc1n;
	private boolean lc2n;
	private boolean lc3n;
	private boolean lc4n;
	private boolean lo0;
	private boolean lo1;
	private boolean lo2;
	private boolean lo3;
	private boolean lo4;
	private boolean lo1n;
	private boolean lo2n;
	private boolean lo3n;
	private boolean lo4n;
	private boolean  C4;
	private boolean  C3;
	private boolean  C2;
	private boolean  C1;
	private boolean  C0;
	private boolean  C4n;
	private boolean  C3n;
	private boolean  C2n;
	private boolean  C1n;
	private boolean  O4;
	private boolean  O3;
	private boolean  O2;
	private boolean  O1;
	private boolean  O0;
	private boolean  O4n;
	private boolean  O3n;
	private boolean  O2n;
	private boolean  O1n;
	
	//info about pitches
	private int EVfactor;
	private JPanel SouthPanel = new JPanel();
	private JPanel PitchLogPanel = new JPanel();
	
	
	//stuff for asking what type of pitch it was
	private JPanel TopPanel = new JPanel();
	private JPanel pitchTypePanel = new JPanel();
	private JButton fastBButton = new JButton("Fastball");
	private JButton changeU = new JButton ("Change-Up"); 
	private JButton offSpeed = new JButton("Off Speed");
	//booleans for previous pitch type
	private boolean wasFB;
	private boolean wasCU;
	private boolean wasOS;
	//for highlighting boxes
	private boolean hiLiFB;
	private boolean hiLiCU;
	private boolean hiLiOS;
	private JLabel pitchLbl = new JLabel("Pitch:    ");
	private JLabel previousPitchLbl = new JLabel("Previous Pitch: ");
	
	
	
	
	public MainC(){
		setPreferredSize(new Dimension(700,700));
		JFrame jf = new JFrame("Effective Velocity");
		jf.setResizable(false);
		this.setLayout(new BorderLayout());
		
		
		//for Top Info
		battertyPanel.setLayout(new GridBagLayout()); battertyPanel.setBackground(new Color(0,0,0,0));
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = .2; c.weighty = 1; 	 rHButton.setVisible(false);	
		rHButton.setFont(new Font("Lucida", Font.BOLD, 48)); rHButton.setText("Righty"); battertyPanel.add(rHButton, c); 
		c.weightx = .4; c.weighty = 1;
		newBatterButton.setFont(new Font("Lucida", Font.BOLD, 54)); newBatterButton.setText("New Batter"); battertyPanel.add(newBatterButton, c); 
		c.weightx = .2; c.weighty = 1;lHButton.setVisible(false);
		lHButton.setFont(new Font("Lucida", Font.BOLD, 48)); lHButton.setText("Lefty"); battertyPanel.add(lHButton, c);
		TopPanel.setLayout(new GridLayout(2,0,30,30)); TopPanel.add(battertyPanel); TopPanel.setBackground(new Color(0,0,0,0));
		
		
		//just below top info (asks what Type of pitch it was)
		GridBagConstraints gi = new GridBagConstraints();
		pitchTypePanel.setLayout(new GridBagLayout());
		gi.weightx = .25; gi.weighty = 1;
		fastBButton.setFont(new Font("Lucida", Font.PLAIN, 36)); fastBButton.addActionListener(this); pitchTypePanel.add(fastBButton, gi);
		gi.weightx = .25; gi.weighty = 1;
		changeU.setFont(new Font("Lucida", Font.PLAIN, 36)); changeU.addActionListener(this); pitchTypePanel.add(changeU, gi);
		gi.weightx = .25; gi.weighty = 1;
		offSpeed.setFont(new Font("Lucida", Font.PLAIN, 36)); offSpeed.addActionListener(this); pitchTypePanel.add(offSpeed, gi);
		TopPanel.add(pitchTypePanel); pitchTypePanel.setBackground(new Color(0,0,0,0)); pitchTypePanel.setVisible(false);
		this.add(TopPanel, BorderLayout.NORTH);
		
		
		//just above bottom info
		SouthPanel.setLayout(new BorderLayout()); SouthPanel.setBackground(new Color(0,0,0,0)); 
		PitchLogPanel.setLayout(new GridBagLayout()); PitchLogPanel.setBackground(new Color(0,0,0,0)); PitchLogPanel.setVisible(true);
		GridBagConstraints gr = new GridBagConstraints();
		JPanel pI = new JPanel(); pI.setLayout(new BorderLayout()); pI.setBackground(new Color(0,0,0,0)); 
		pitchLbl.setForeground(Color.white);previousPitchLbl.setForeground(Color.white);
		pitchLbl.setFont(new Font("Lucida", Font.PLAIN, 20));previousPitchLbl.setFont(new Font("Lucida", Font.PLAIN, 20));
		pI.add(pitchLbl, BorderLayout.WEST); pI.add(previousPitchLbl, BorderLayout.EAST); pI.setVisible(true);
		gr.weightx = .2; gr.weighty = 1; gr.anchor = GridBagConstraints.LINE_START; 
		PitchLogPanel.add(pI, gr);
	//	gr.weightx = .8; gr.weighty = 1;
		
		
		//TODO add Panel with log of Pitches, instructions on how to use - ie hover mouse over pitch types, click in pitch location for a new pitch

		SouthPanel.add(PitchLogPanel, BorderLayout.NORTH);
		//Bottom Info
		nextPitchInfoPanel.setLayout(new GridBagLayout()); nextPitchInfoPanel.setBackground(new Color(0,0,0,0)); nextPitchInfoPanel.setVisible(true);
		GridBagConstraints gb = new GridBagConstraints();
		gb.weightx = .2; gb.weighty = 1; gb.anchor = GridBagConstraints.LINE_START;
		nextPitchLbl.setFont(new Font("Lucida", Font.PLAIN, 20)); nextPitchLbl.setText("Specific Pitches: "); nextPitchLbl.setForeground(new Color(255,152,0)); 
		nextPitchLbl.setVisible(false);nextPitchInfoPanel.add(nextPitchLbl, gb);
		gb.weightx = .18; gb.weighty = 1;
		fBLbl.setFont(new Font("Lucida", Font.PLAIN, 20)); fBLbl.setForeground(new Color(255,152,0));fBLbl.setVisible(false); nextPitchInfoPanel.add(fBLbl, gb);
		gb.weightx = .18; gb.weighty = 1;
		cULbl.setFont(new Font("Lucida", Font.PLAIN, 20)); cULbl.setForeground(new Color(255,152,0));cULbl.setVisible(false); nextPitchInfoPanel.add(cULbl, gb);
		gb.weightx = .18; gb.weighty = 1;
		osLbl.setFont(new Font("Lucida", Font.PLAIN, 20)); osLbl.setForeground(new Color(255,152,0));osLbl.setVisible(false); nextPitchInfoPanel.add(osLbl, gb);
		gb.weightx = .2; gb.weighty = 1; gb.anchor = GridBagConstraints.LINE_END;
		showORhideButton.setFont(new Font("Lucida", Font.BOLD, 20)); nextPitchInfoPanel.add(showORhideButton, gb);
		showORhideButton.addActionListener(this);
		nextPitchInfoPanel.setSize(getWidth(), 9 * getHeight()/10);
		SouthPanel.add(nextPitchInfoPanel, BorderLayout.SOUTH); 
		//pitch and previous pitch info
		this.add(SouthPanel, BorderLayout.SOUTH);
		
		
		//Frame stuff
		Container lid = jf.getContentPane();
		lid.add(this);
		rHButton.addActionListener(this); lHButton.addActionListener(this); newBatterButton.addActionListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(Color.black);
		jf.pack();
		jf.setVisible(true);
		this.requestFocusInWindow();
		javax.swing.Timer t = new javax.swing.Timer(50,this);
		t.start();
	}
	protected void setBooleansFalse(){
		if(C4 == true){
		}
		if(C3 == true){
		}
		if(C2 == true){
		}
		if(C1 == true){
		}
		if(C0 == true){
		}
		if(C4n == true){
		}
		if(C3n == true){
		}
		if(C2n == true){
		}
		if(C1n == true){
		}
		if(O4 == true){
		}
		if(O3 == true){
		}
		if(O2 == true){
		}
		if(O1 == true){
		}
		if(O0 == true){
		}
		if(O4n == true){
		}
		if(O3n == true){
		}
		if(O2n == true){
		}
		if(O1n == true){
		}
		if(r0 == true){
		}
		if(r1 == true){
		}
		if(r2 == true){
		}
		if(r3 == true){
		}
		if(r4 == true){
		}
		if(r1n == true){
		}
		if(r2n == true){
		}
		if(r3n == true){
		}
		if(r4n == true){
		}
		if(l0 == true){
		}
		if(l1 == true){
		}
		if(l2 == true){
		}
		if(l3 == true){
		}
		if(l4 == true){
		}
		if(l1n == true){
		}
		if(l2n == true){
		}
		if(l3n == true){
		}
		if(l4n == true){
		}
		if(lc0 == true){
		}
		if(lc1 == true){
		}
		if(lc2 == true){
		}
		if(lc3 == true){
		}
		if(lc4 == true){
		}
		if(lc1n == true){
		}
		if(lc2n == true){
		}
		if(lc3n == true){
		}
		if(lc4n == true){
		}
		if(lo0 == true){
		}
		if(lo1 == true){
		}
		if(lo2 == true){
		}
		if(lo3 == true){
		}
		if(lo4 == true){
		}
		if(lo1n == true){
		}
		if(lo2n == true){
		}
		if(lo3n == true){
		}
		if(lo4n == true){
		}
		r0 = false;
		r1 = false;
		r2 = false;
		r3 = false;
		r4 = false;
		r1n = false;
		r2n = false;
		r3n = false;
		r4n = false;
		l0 = false;
		l1 = false;
		l2 = false;
		l3 = false;
		l4 = false;
		l1n = false;
		l2n = false;
		l3n = false;
		l4n = false;
		C4 = false;
		C3 = false;
		C2 = false;
		C1 = false;
		C0 = false;
		C4n = false;
		C3n = false;
		C2n = false;
		C1n = false;
		O4 = false;
		O3 = false;
		O2 = false;
		O1 = false;
		O0 = false;
		O4n = false;
		O3n = false;
		O2n = false;
		O1n = false;
		lc0 = false;
		lc1 = false;
		lc2 = false;
		lc3 = false;
		lc4 = false;
		lc1n = false;
		lc2n = false;
		lc3n = false;
		lc4n = false;
		lo0 = false;
		lo1 = false;
		lo2 = false;
		lo3 = false;
		lo4 = false;
		lo1n = false;
		lo2n = false;
		lo3n = false;
		lo4n = false;
	}

	
	public void mousePressed(MouseEvent e) {
		clx = e.getX();
		cly = e.getY();
		if(clx > getWidth()/4 && cly > getHeight()/4 && clx < 3*getWidth()/4 && cly < 3 * getWidth()/4){
			pitchTypePanel.setVisible(true); pitchTypePanel.requestFocus();
			showORhide = false; 
			setBooleansFalse();
		}
		//for righty	
	}
	
	protected void AllFBTrue(){
		r4 = true; r3 = true; r2 = true; r1 = true; r0 = true; r4n = true; r3n = true; r2n = true; r1n = true;
	}
	
	protected void AllOSTrue(){
		O4 = true; O3 = true; O2 = true; O1 = true; O0 = true; O4n = true; O3n = true; O2n = true; O1n = true;
	}
	
	protected void AllCUTrue(){
		C4 = true; C3 = true; C2 = true; C1 = true; C0 = true; C4n = true; C3n = true; C2n = true; C1n = true;
	}
	
	// ===============================================================================================
	// ------------------------------ LOGIC FOR WHICH BOXES TO FILL ----------------------------------
	// ===============================================================================================
	
	//for righties - working
	protected void FillZone4R(){
		EVfactor = 4;
		if(wasFB == true){
			r4n = true; r3n = true; r2n = true;
			AllCUTrue();
			AllOSTrue();
			cULbl.setForeground(Color.red); osLbl.setForeground(Color.red); fBLbl.setForeground(Color.red);
		}
		else if(wasCU == true){
			r2 = true; r3 = true; r4 = true;
			C4n = true; C3n = true; C2n = true; 
			AllOSTrue();
			fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);cULbl.setForeground(Color.red);
		}
		else{ //wasOS == true	
			AllFBTrue();
			C2 = true; C3 = true; C4 = true;
			O2n = true; O3n = true; O4n  = true;
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red);osLbl.setForeground(Color.red);
		}
		nextPitchInfoPanel.setVisible(true); showORhide = true;
	}	
	protected void FillZone3R(){ 
		EVfactor = 3;
		if(wasFB == true){
			r4n = true; r3n = true;
			C2n = true; C3n = true; C4n = true; C1n = true;  
			AllOSTrue();
			cULbl.setForeground(Color.red); osLbl.setForeground(Color.red); fBLbl.setForeground(Color.red);
		}
		else if(wasCU == true){
			r1 = true; r2 = true; r3 = true; r4 = true;
			C4n = true; C3n = true;
			AllOSTrue();
			fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);cULbl.setForeground(Color.red);
		}
		else{	//wasOS == true
			AllFBTrue();
			C1 = true; C2 = true; C3 = true; C4 = true;
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red);osLbl.setForeground(Color.red);
		}
		nextPitchInfoPanel.setVisible(true); showORhide = true;
	}	
	protected void FillZone2R(){
		EVfactor = 2;
		if(wasFB == true){
			r4n = true; 
			C2n = true; C3n = true; C4n = true; C1n = true; C0 = true; 
			AllOSTrue();
			cULbl.setForeground(Color.red); osLbl.setForeground(Color.red); fBLbl.setForeground(Color.red);
		}
		else if(wasCU == true){
			r0 = true; r1 = true; r2 = true; r3 = true; r4 = true;
			O3 = true; O2 = true; O1 = true; O0 = true; O1n = true; O2n = true; O3n = true; O4n = true;
			fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);cULbl.setForeground(new Color(255,128,0));
		}
		else{	
			AllFBTrue(); //wasOS == true
			C0 = true; C1 = true; C2 = true; C3 = true; C4 = true;
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red);osLbl.setForeground(new Color(255,128,0));
		}
		nextPitchInfoPanel.setVisible(true); showORhide = true;
	}
	protected void FillZone1R(){
		EVfactor = 1;
		if(wasFB == true){
			C1 = true; C2n = true; C3n = true; C4n = true; C1n = true; C0 = true; 
			AllOSTrue();
			cULbl.setForeground(Color.red); osLbl.setForeground(Color.red);fBLbl.setForeground(new Color(255,128,0));
		}
		else if(wasCU == true){
			r1n = true; r0 = true; r1 = true; r2 = true; r3 = true; r4 = true;
			O2 = true; O1 = true; O0 = true; O1n = true; O2n = true; O3n = true; O4n = true;
			fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);cULbl.setForeground(new Color(255,128,0));
		}
		else{	//wasOS == true
			AllFBTrue(); 
			C1n = true; C0 = true; C1 = true; C2 = true; C3 = true; C4 = true;
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red); osLbl.setForeground(new Color(255,128,0));
		}
		nextPitchInfoPanel.setVisible(true); showORhide = true;
	}	
	protected void FillZone0R(){
		EVfactor = 0;
		if(wasFB == true){
			C2n = true; C3n = true; C4n = true; C1n = true;  
			AllOSTrue();
			cULbl.setForeground(Color.red); osLbl.setForeground(Color.red); fBLbl.setForeground(new Color(255,128,0));
		}
		else if(wasCU == true){
			r2n = true; r1n = true; r0 = true; r1 = true; r2 = true; r3 = true; r4 = true;
			O2 = true; O1 = true; O0 = true; O1n = true; O2n = true; O3n = true; O4n = true;
			fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red); cULbl.setForeground(new Color(255,128,0));
		}
		else{	//wasOS == true
			AllFBTrue();
			C0 = true; C1 = true; C2 = true; C3 = true; C4 = true;
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red); osLbl.setForeground(new Color(255,128,0));
		}
		nextPitchInfoPanel.setVisible(true); showORhide = true;
	}	
	protected void FillZone1Rn(){
		EVfactor = -1;
		if(wasFB == true){
			r4 = true;
			C2n = true; C3n = true; C4n = true; C1n = true;
			AllOSTrue();
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);
		}
		else if(wasCU == true){
			r3n = true; r2n = true; r1n = true; r0 = true; r1 = true;r2 = true;r3 = true;r4 = true;
			O0 = true;O4n = true;O3n = true;O2n = true;O1n = true;
			fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);cULbl.setForeground(new Color(255,128,0));
		}
		else{ //wasOS == true
			AllFBTrue();
			C1n = true; C0 = true; C1 = true; C2 = true; C3 = true; C4 = true;
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red);osLbl.setForeground(new Color(255,128,0));
		}
		nextPitchInfoPanel.setVisible(true); showORhide = true;
	}
	protected void FillZone2Rn(){
		EVfactor = -2;
		if(wasFB == true){
			r4 = true; r3 = true;
			C2n = true; C3n = true; C4n = true;
			AllOSTrue();
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);
		}
		else if(wasCU == true){
			AllFBTrue();
			O2 = true;O1 = true;O0 = true;O4n = true;O3n = true;O2n = true;O1n = true;
			fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);cULbl.setForeground(new Color(255,128,0));
		}
		else{ //wasOS == true
			AllFBTrue();
			AllCUTrue();
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red);osLbl.setForeground(new Color(255,128,0));
		}
		nextPitchInfoPanel.setVisible(true); showORhide = true;
	}
	protected void FillZone3Rn(){
		EVfactor = -3;
		if(wasFB == true){
			r4 = true; r3 = true; r2 = true;
			C2n = true; C3n = true; C4n = true;
			AllOSTrue();
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);
		}
		else if(wasCU == true){
			AllFBTrue();
			O1 = true;O0 = true;O4n = true;O3n = true;O2n = true;O1n = true;
			nextPitchInfoPanel.setVisible(true); showORhide = true;
			fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);cULbl.setForeground(new Color(255,128,0));
		}
		else{ //wasOS == true
			AllFBTrue();
			AllCUTrue();
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red);osLbl.setForeground(new Color(255,128,0));
		}
		nextPitchInfoPanel.setVisible(true); showORhide = true;
	}
	protected void FillZone4Rn(){
		EVfactor = -4;
		if(wasFB == true){
			r4 = true; r3 = true; r2 = true; r1 = true;
			C2n = true; C3n = true; C4n = true;
			AllOSTrue();
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);
		}
		else if(wasCU == true){
			AllFBTrue();
			O2 = true;O1 = true;O0 = true;O4n = true;O3n = true;O2n = true;O1n = true;
			fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);cULbl.setForeground(new Color(255,128,0));
		}
		else{ //wasOS == true
			AllFBTrue();
			AllCUTrue();
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red);osLbl.setForeground(new Color(255,128,0));
		}
		nextPitchInfoPanel.setVisible(true); showORhide = true;
	}
	
	
	//for lefties - working
	protected void FZ4L(){
		EVfactor = 4;
		if(wasFB == true){
			l4n = true; l3n = true; l2n = true;
			AllCUTrue();
			AllOSTrue();
			cULbl.setForeground(Color.red); osLbl.setForeground(Color.red); fBLbl.setForeground(Color.red);
		}
		else if(wasCU == true){
			l2 = true; l3 = true; l4 = true;
			lc4n = true; lc3n = true; lc2n = true; 
			AllOSTrue();
			fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);cULbl.setForeground(Color.red);
		}
		else{	//wasOS == true
			AllFBTrue();
			C2 = true; C3 = true; C4 = true;
			lo2n = true; lo3n = true; lo4n = true;
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red);osLbl.setForeground(Color.red);
		}
		nextPitchInfoPanel.setVisible(true); showORhide = true;
	}
	protected void FZ3L(){
		EVfactor = 3;
		if(wasFB == true){
			l4n = true; l3n = true;
			lc2n = true; lc3n = true; lc4n = true; lc1n = true;  
			AllOSTrue();
			cULbl.setForeground(Color.red); osLbl.setForeground(Color.red); fBLbl.setForeground(Color.red);
		}
		else if(wasCU == true){
			l1 = true; l2 = true; l3 = true; l4 = true;
			lc4n = true; lc3n = true;
			AllOSTrue();
			fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);cULbl.setForeground(Color.red);
		}
		else{	//wasOS == true
			AllFBTrue();
			lc1 = true; lc2 = true; lc3 = true; lc4 = true;
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red);osLbl.setForeground(Color.red);
		}
		nextPitchInfoPanel.setVisible(true); showORhide = true;
	}
	protected void FZ2L(){
		EVfactor = 2;
		if(wasFB == true){
			l4n = true; 
			lc2n = true; lc3n = true; lc4n = true; lc1n = true; lc0 = true; 
			AllOSTrue();
			cULbl.setForeground(Color.red); osLbl.setForeground(Color.red); fBLbl.setForeground(Color.red);
		}
		else if(wasCU == true){
			l0 = true; l1 = true; l2 = true; l3 = true; l4 = true;
			lo3 = true; lo2 = true; lo1 = true; lo0 = true; lo1n = true; lo2n = true; lo3n = true; lo4n = true;
			fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);cULbl.setForeground(new Color(255,128,0));
		}
		else{	//wasOS == true
			AllFBTrue();
			lc0 = true; lc1 = true; lc2 = true; lc3 = true; lc4 = true;
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red);osLbl.setForeground(new Color(255,128,0));
		}
		nextPitchInfoPanel.setVisible(true); showORhide = true;
	}
	protected void FZ1L(){
		EVfactor = 1;
		if(wasFB == true){
			lc2n = true; lc3n = true; lc4n = true; lc1n = true; lc0 = true; 
			AllOSTrue();
			cULbl.setForeground(Color.red); osLbl.setForeground(Color.red);fBLbl.setForeground(new Color(255,128,0));
		}
		else if(wasCU == true){
			l1n = true; l0 = true; l1 = true; l2 = true; l3 = true; l4 = true;
			lo2 = true; lo1 = true; lo0 = true; lo1n = true; lo2n = true; lo3n = true; lo4n = true;
			fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);cULbl.setForeground(new Color(255,128,0));
		}
		else{	//wasOS == true
			AllFBTrue();
			lc1n = true; lc0 = true; lc1 = true; lc2 = true; lc3 = true; lc4 = true;
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red); osLbl.setForeground(new Color(255,128,0));
		}
		nextPitchInfoPanel.setVisible(true); showORhide = true;
	}
	protected void FZ0L(){
		EVfactor = 0;
		if(wasFB == true){
			lc2n = true; lc3n = true; lc4n = true; lc1n = true; 
			AllOSTrue();
			cULbl.setForeground(Color.red); osLbl.setForeground(Color.red); fBLbl.setForeground(new Color(255,128,0));
		}
		else if(wasCU == true){
			l2n = true; l1n = true; l0 = true; l1 = true; l2 = true; l3 = true; l4 = true;
			lo2 = true; lo1 = true; lo0 = true; lo1n = true; lo2n = true; lo3n = true; lo4n = true;
			fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red); cULbl.setForeground(new Color(255,128,0));
		}
		else{	//wasOS == true
			AllFBTrue();
			C0 = true; C1 = true; C2 = true; C3 = true; C4 = true;
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red); osLbl.setForeground(new Color(255,128,0));
		}
		nextPitchInfoPanel.setVisible(true); showORhide = true;
	}
	protected void FZ1nL(){
		EVfactor = -1;
		if(wasFB == true){
			l4 = true;
			lc1n = true; lc2n = true; lc3n = true; lc4n = true; 
			AllOSTrue();
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);
		}
		else if(wasCU == true){
			l3n = true; l2n = true; l1n = true; l0 = true; l1 = true;l2 = true;l3 = true;l4 = true;
			lo0 = true;lo4n = true;lo3n = true;lo2n = true;lo1n = true;
			fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);cULbl.setForeground(new Color(255,128,0));
		}
		else{ //wasOS == true
			AllFBTrue();
			C1n = true; C0 = true; C1 = true; C2 = true; C3 = true; C4 = true;
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red);osLbl.setForeground(new Color(255,128,0));
		}
		nextPitchInfoPanel.setVisible(true); showORhide = true;
	}
	protected void FZ2nL(){
		EVfactor = -2;
		if(wasFB == true){
			l4 = true; l3 = true;
			lc2n = true; lc3n = true; lc4n = true; 
			AllOSTrue();
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);
		}
		else if(wasCU == true){
			AllFBTrue();
			lo2 = true;lo1 = true;lo0 = true;lo4n = true;lo3n = true;lo2n = true;lo1n = true;
			fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);cULbl.setForeground(new Color(255,128,0));
		}
		else{ //wasOS == true
			AllFBTrue();
			AllCUTrue();
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red);osLbl.setForeground(new Color(255,128,0));
		}
		nextPitchInfoPanel.setVisible(true); showORhide = true;
	}
	protected void FZ3nL(){
		EVfactor = -3;
		if(wasFB == true){
			l4 = true; l3 = true; l2 = true;
			lc2n = true; lc3n = true; lc4n = true; 
			AllOSTrue();
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);
		}
		else if(wasCU == true){
			AllFBTrue();
			lo1 = true;lo0 = true;lo4n = true;lo3n = true;lo2n = true;lo1n = true;
			fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);cULbl.setForeground(new Color(255,128,0));
		}
		else{ //wasOS == true
			AllFBTrue();
			AllCUTrue();
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red);osLbl.setForeground(new Color(255,128,0));
		}
		nextPitchInfoPanel.setVisible(true); showORhide = true;
	}
	protected void FZ4nL(){
		EVfactor = -4;
		if(wasFB == true){
			l4 = true; l3 = true; l2 = true; l1 = true;
			lc2n = true; lc3n = true; lc4n = true;
			AllOSTrue();
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);
		}
		else if(wasCU == true){
			AllFBTrue();
			lo2 = true;lo1 = true;lo0 = true;lo4n = true;lo3n = true;lo2n = true;lo1n = true;
			fBLbl.setForeground(Color.red); osLbl.setForeground(Color.red);cULbl.setForeground(new Color(255,128,0));
		}
		else{ //wasOS == true
			AllFBTrue();
			AllCUTrue();
			cULbl.setForeground(Color.red); fBLbl.setForeground(Color.red);osLbl.setForeground(new Color(255,128,0));
		}
		nextPitchInfoPanel.setVisible(true); showORhide = true;
	}
	
	
	protected void fillInBoxes(){
		int w = getWidth(); int h = getHeight();
		if(wH == true){
			if(clx > w/4 && cly > h/4){ 
				//column 1
				if(clx <w/4+w/10){
					//row 1 zone 0
					if(cly < h/4 + h/10){
						// checks to see what type of pitch it was
						FillZone0R();
					}
					//row 2 zone -1
					else if(cly < h/4 + h/5){
						FillZone1Rn();
					}
					//row 3 zone -2
					else if(cly < h/4 +3* h/10){
						FillZone2Rn();
					}
					//row 4 zone -3
					else if(cly < h/4 + 2* h/5){
						FillZone3Rn();
					}
					//row 5 zone -4
					else if(cly < 3*h/4){
						FillZone4Rn();
					}
				}
				//column 2
				else if(clx <w/4+w/5){
					//row 1 + 1
					if(cly < h/4 + h/10){
						FillZone1R();
					}
					//row 2 + 0
					else if(cly < h/4 + h/5){
						FillZone0R();
					}
					//row 3 - 1
					else if(cly < h/4 +3* h/10){
						FillZone1Rn();
					}
					//row 4 - 2
					else if(cly < h/4 + 2* h/5){
						FillZone2Rn();
					}
					//row 5 - 3
					else if(cly < 3*h/4){
						FillZone3Rn();
					}
				}
				//third column
				else if(clx <w/4+3*w/10){
					//row 1 +2
					if(cly < h/4 + h/10){
						FillZone2R();
					}
					//row 2 +1
					else if(cly < h/4 + h/5){
						FillZone1R();
					}
					//row 3 +0
					else if(cly < h/4 +3* h/10){
						FillZone0R();
					}
					//row 4 -1
					else if(cly < h/4 + 2* h/5){
						FillZone1Rn();
					}
					//row 5 -2
					else if(cly < 3*h/4){
						FillZone2Rn();
					}
				}
				//fourth column
				else if(clx <w/4+2*w/5){
					//row 1 + 3
					if(cly < h/4 + h/10){
						FillZone3R();
					}
					//row 2 + 2
					else if(cly < h/4 + h/5){
						FillZone2R();
					}
					//row 3 + 1
					else if(cly < h/4 +3* h/10){
						FillZone1R();
					}
					//row 4 + 0
					else if(cly < h/4 + 2* h/5){
						FillZone0R();
					}
					//row 5 -1
					else if(cly < 3*h/4){
						FillZone1Rn();
					}
				}
				//fifth column
				else if(clx <3*w/4){
					//row 1 + 4
					if(cly < h/4 + h/10){
						FillZone4R();
					}
					//row 2 + 3
					else if(cly < h/4 + h/5){
						FillZone3R();
					}
					//row 3 + 2
					else if(cly < h/4 +3* h/10){
						FillZone2R();
					}
					//row 2 + 1
					else if(cly < h/4 + 2* h/5){
						FillZone1R();
					}
					//row 5 + 0
					else if(cly < 3*h/4){
						FillZone0R();
					}
				}
			}
		}
		//for lefty
		else{
			if(clx > w/4 && cly > h/4){ 
				//first column
				if(clx <w/4+w/10){
					if(cly < h/4 + h/10){
						System.out.println("B1 + 4");
						FZ4L();
					}
					else if(cly < h/4 + h/5){
						System.out.println("B6 + 3");
						FZ3L();
					}
					else if(cly < h/4 +3* h/10){
						System.out.println("B11 + 2");
						FZ2L();
					}
					else if(cly < h/4 + 2* h/5){
						System.out.println("B16 + 1");
						FZ1L();
					}
					else if(cly < 3*h/4){
						System.out.println("B21 + 0");
						FZ0L();
					}
				}
				//second column
				else if(clx <w/4+w/5){
					if(cly < h/4 + h/10){
						System.out.println("B2 + 3");
						FZ3L();
					}
					else if(cly < h/4 + h/5){
						System.out.println("B7 + 2");
						FZ2L();
					}
					else if(cly < h/4 +3* h/10){
						System.out.println("B12 + 1");
						FZ1L();
					}
					else if(cly < h/4 + 2* h/5){
						System.out.println("B17 + 0");
						FZ0L();
					}
					else if(cly < 3*h/4){
						System.out.println("B22 - 1");
						FZ1nL();
					}
				}
				//third column
				else if(clx <w/4+3*w/10){
					if(cly < h/4 + h/10){
						System.out.println("B3 + 2");
						FZ2L();
					}
					else if(cly < h/4 + h/5){
						System.out.println("B8 + 1");
						FZ1L();
					}
					else if(cly < h/4 +3* h/10){
						System.out.println("B13 + 0");
						FZ0L();
					}
					else if(cly < h/4 + 2* h/5){
						System.out.println("B18 - 1");
						FZ1nL();
					}
					else if(cly < 3*h/4){
						System.out.println("B23 - 2");
						FZ2nL();
					}
				}
				//fourth column
				else if(clx <w/4+2*w/5){
					if(cly < h/4 + h/10){
						System.out.println("B4 + 1");
						FZ1L();
					}
					else if(cly < h/4 + h/5){
						System.out.println("B9 + 0");
						FZ0L();
					}
					else if(cly < h/4 +3* h/10){
						System.out.println("B14 - 1");
						FZ1nL();
					}
					else if(cly < h/4 + 2* h/5){
						System.out.println("B19 - 2");
						FZ2nL();
					}
					else if(cly < 3*h/4){
						System.out.println("B24 - 3");
						FZ3nL();
					}
				}
				//fifth column
				else if(clx <3*w/4){
					if(cly < h/4 + h/10){
						System.out.println("B5 + 0");
						FZ0L();
					}
					else if(cly < h/4 + h/5){
						System.out.println("B10 - 1");
						FZ1nL();
					}
					else if(cly < h/4 +3* h/10){
						System.out.println("B15 - 2");
						FZ2nL();
					}
					else if(cly < h/4 + 2* h/5){
						System.out.println("B20 - 3");
						FZ3nL();
					}
					else if(cly < 3*h/4){
						System.out.println("B25 - 4");
						FZ4nL();
					}
				}
			}
		}
	}
	
	
	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		if(wH == true){
			if(my > 19*getHeight()/20 && mx > getWidth() /5){
				if(mx < 2 * getWidth() / 5){
					fBLbl.setForeground(Color.red);
					cULbl.setForeground(new Color(255,152,0,52));
					osLbl.setForeground(new Color(255,152,0,52));
					hiLiFB = true; hiLiCU = false; hiLiOS = false;
					aNr = 200;
				}
				else if(mx < 3*getWidth()/5){
					cULbl.setForeground(Color.red);
					fBLbl.setForeground(new Color(255,152,0,52));
					osLbl.setForeground(new Color(255,152,0,52));
					hiLiFB = false; hiLiCU = true; hiLiOS = false;
					aNr = 200;
				}
				else if(mx < 4*getWidth()/5){
					osLbl.setForeground(Color.red);
					fBLbl.setForeground(new Color(255,152,0,52));
					cULbl.setForeground(new Color(255,152,0,52));
					hiLiFB = false; hiLiCU = false; hiLiOS = true;
					aNr = 200;
				}
			}
		}
		else{ //for lefties
			if(my > 19*getHeight()/20 && mx > getWidth() /5){
				if(mx < 2 * getWidth() / 5){
					fBLbl.setForeground(Color.red);
					cULbl.setForeground(new Color(255,152,0,52));
					osLbl.setForeground(new Color(255,152,0,52));
					hiLiFB = true; hiLiCU = false; hiLiOS = false;
					aNr = 200;
				}
				else if(mx < 3*getWidth()/5){
					cULbl.setForeground(Color.red);
					fBLbl.setForeground(new Color(255,152,0,52));
					osLbl.setForeground(new Color(255,152,0,52));
					hiLiFB = false; hiLiCU = true; hiLiOS = false;
					aNr = 200;
				}
				else if(mx < 4*getWidth()/5){
					osLbl.setForeground(Color.red);
					fBLbl.setForeground(new Color(255,152,0,52));
					cULbl.setForeground(new Color(255,152,0,52));
					hiLiFB = false; hiLiCU = false; hiLiOS = true;
					aNr = 200;
				}
			}
		}
	}
	
	//set all booleans to false -- called when a new batter comes up
	protected void setALLBsF(){
		r0 = false;
		r1 = false;
		r2 = false;
		r3 = false;
		r4 = false;
		r1n = false;
		r2n = false;
		r3n = false;
		r4n = false;
		l0 = false;
		l1 = false;
		l2 = false;
		l3 = false;
		l4 = false;
		l1n = false;
		l2n = false;
		l3n = false;
		l4n = false;
		C4 = false;
		C3 = false;
		C2 = false;
		C1 = false;
		C0 = false;
		C4n = false;
		C3n = false;
		C2n = false;
		C1n = false;
		O4 = false;
		O3 = false;
		O2 = false;
		O1 = false;
		O0 = false;
		O4n = false;
		O3n = false;
		O2n = false;
		O1n = false;
		lc0 = false;
		lc1 = false;
		lc2 = false;
		lc3 = false;
		lc4 = false;
		lc1n = false;
		lc2n = false;
		lc3n = false;
		lc4n = false;
		lo0 = false;
		lo1 = false;
		lo2 = false;
		lo3 = false;
		lo4 = false;
		lo1n = false;
		lo2n = false;
		lo3n = false;
		lo4n = false;
		pitchLbl.setText("Pitch: ");
		previousPitchLbl.setText("PP: ");
	}
	
	//for button clicks
	public void actionPerformed(ActionEvent e) {
		if(showORhide == true){
			nextPitchLbl.setVisible(true);
			fBLbl.setVisible(true);
			cULbl.setVisible(true);
			osLbl.setVisible(true);
		}
		else{
			nextPitchLbl.setVisible(false);
			fBLbl.setVisible(false);
			cULbl.setVisible(false);
			osLbl.setVisible(false);
		}
		if(e.getSource() == newBatterButton){
			rHButton.setVisible(true);
			lHButton.setVisible(true);
			newBatterButton.setVisible(false);
			setALLBsF();
		}
		if(e.getSource() == rHButton){
			wH = true;
			rHButton.setVisible(false);
			lHButton.setVisible(false);
			newBatterButton.setVisible(true);
		}
		else if(e.getSource() == lHButton){
			wH = false;
			rHButton.setVisible(false);
			lHButton.setVisible(false);
			newBatterButton.setVisible(true);
		}
		if(e.getSource() == showORhideButton){
			if(showORhide == false){
				showORhideButton.setText(">");
				showORhide = true;
			}
			else{
				showORhideButton.setText("<");
				showORhide = false;
			}
		}
		
		if(e.getSource() == fastBButton){
			String q = pitchLbl.getText();
			if(q == "Pitch: Fastball    "){
				previousPitchLbl.setText("PP: FB");
			}
			else if(q == "Pitch: Change Up    "){
				previousPitchLbl.setText("PP: CU");
			}
			else if(q == "Pitch: Off Speed    "){
				previousPitchLbl.setText("PP: OS");
			}
			pitchLbl.setText("Pitch: Fastball    ");
			pitchLbl.setForeground(new Color(255,0,0));
			pitchTypePanel.setVisible(false);
			wasFB = true; wasCU = false; wasOS = false;
			fillInBoxes();

		}
		else if(e.getSource() == changeU){
			String q = pitchLbl.getText();
			if(q == "Pitch: Fastball    "){
				previousPitchLbl.setText("PP: FB");
			}
			else if(q == "Pitch: Change Up    "){
				previousPitchLbl.setText("PP: CU");
			}
			else if(q == "Pitch: Off Speed    "){
				previousPitchLbl.setText("PP: OS");
			}
			pitchLbl.setForeground(new Color(255,128,0));
			pitchLbl.setText("Pitch: Change Up    ");
			pitchTypePanel.setVisible(false);
			wasFB = false; wasCU = true; wasOS = false;
			fillInBoxes();
		}
		else if(e.getSource() == offSpeed){
			String q = pitchLbl.getText();
			if(q == "Pitch: Fastball    "){
				previousPitchLbl.setText("PP: FB");
			}
			else if(q == "Pitch: Change Up    "){
				previousPitchLbl.setText("PP: CU");
			}
			else if(q == "Pitch: Off Speed    "){
				previousPitchLbl.setText("PP: OS");
			}
			pitchLbl.setForeground(new Color(255,255,0));
			pitchLbl.setText("Pitch: Off Speed    ");
			pitchTypePanel.setVisible(false);
			wasFB = false; wasCU = false;  wasOS = true;
			fillInBoxes();
		}
		
		if(aNr < 255){
			aNr ++;
			if(aNr == 255){
				aNr = 150;
			}
		}
		repaint();
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints rh1 = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh1);
		super.paintComponent(g2);
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.white);
		
		//for the grid
		int w = getWidth(); int h = getHeight();
		g2.drawLine(w / 4, h/4, 3*w/4, h/4);
		g2.drawLine(w / 4, h/4, w/4, 3* h/4);
		g2.drawLine(w / 4, 3*h/4, 3*w/4, 3*h/4);
		g2.drawLine(3*w / 4, h/4, 3*w/4,3* h/4);
		
		g2.setStroke(new BasicStroke(2));
		
		//vertical bars
		g2.drawLine(w/4 + w/10, h/4,w/4 + w/10, 3 * h/4);
		g2.drawLine(w/4 + w/5, h/4,w/4 + w/5, 3 * h/4);
		g2.drawLine(w/4 + 3*w/10, h/4,w/4 + 3*w/10, 3 * h/4);
		g2.drawLine(w/4 + 2*w/5, h/4,w/4 + 2*w/5, 3 * h/4);
		
		//horizontal bars
		g2.drawLine(w/4, h/4+h/10, 3*w/4, h/4+h/10);
		g2.drawLine(w/4, h/4+h/5, 3*w/4, h/4+h/5);
		g2.drawLine(w/4, h/4+3*h/10, 3*w/4, h/4+3*h/10);
		g2.drawLine(w/4, h/4+2*h/5, 3*w/4, h/4+2*h/5);
		
		//for filling boxes
		if(wH == true){ //for righties
			//if(showFB == true){
			if(hiLiFB == true){
				g2.setColor(new Color(255,0,0,aNr));
				if(r0 == true){
					g2.fillRect(w/4+1, h/4+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 + h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/5+1, h/4 + h/5+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 4*w/10+1, h/4 + 4*h/10+1, w /10-1, h/10-1);
				}
				if(r1 == true){
					g2.fillRect(w/4 + w/10+1, h/4+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/5+1, h/4 + w/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + w/5+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 4*w/10+1, h/4 + 3*w/10+1, w /10-1, h/10-1);
				}
				if(r1n == true){
					g2.fillRect(w/4 +1, h/4+ w/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 + w/5+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/5+1, h/4 + 3*w/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + 4*w/10+1, w /10-1, h/10-1);
				}
				if(r2 == true){
					g2.fillRect(w/4 + w/5+1, h/4 +1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + w/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 4*w/10+1, h/4 + w/5+1, w /10-1, h/10-1);
				}
				if(r2n == true){
					g2.fillRect(w/4 +1, h/4 + h/5+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/5+1, h/4 +2* h/5+1, w /10-1, h/10-1);
				}
				if(r3 == true){
					g2.fillRect(w/4 + 3*w/10+1, h/4 +1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 4*w/10+1, h/4 + w/10+1, w /10-1, h/10-1);
				}
				if(r3n == true){
					g2.fillRect(w/4 +1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 +2* h/5+1, w /10-1, h/10-1);
				}
				if(r4 == true){
					g2.fillRect(w/4 + 4*w/10+1, h/4 +1, w /10-1, h/10-1);
				}
				if(r4n == true){
					g2.fillRect(w/4 +1, h/4 +2* h/5+1, w /10-1, h/10-1);
				}
			}
			else if(hiLiCU == true){
				g2.setColor(new Color(255,128,0,aNr));
				if(C0 == true){
					g2.fillRect(w/4+1, h/4+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 + h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/5+1, h/4 + h/5+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 4*w/10+1, h/4 + 4*h/10+1, w /10-1, h/10-1);
				}
				if(C1 == true){
					g2.fillRect(w/4 + w/10+1, h/4+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/5+1, h/4 + w/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + w/5+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 4*w/10+1, h/4 + 3*w/10+1, w /10-1, h/10-1);
				}
				if(C1n == true){
					g2.fillRect(w/4 +1, h/4+ w/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 + w/5+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/5+1, h/4 + 3*w/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + 4*w/10+1, w /10-1, h/10-1);
				}
				if(C2 == true){
					g2.fillRect(w/4 + w/5+1, h/4 +1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + w/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 4*w/10+1, h/4 + w/5+1, w /10-1, h/10-1);
				}
				if(C2n == true){
					g2.fillRect(w/4 +1, h/4 + h/5+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/5+1, h/4 +2* h/5+1, w /10-1, h/10-1);
				}
				if(C3 == true){
					g2.fillRect(w/4 + 3*w/10+1, h/4 +1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 4*w/10+1, h/4 + w/10+1, w /10-1, h/10-1);
				}
				if(C3n == true){
					g2.fillRect(w/4 +1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 +2* h/5+1, w /10-1, h/10-1);
				}
				if(C4 == true){
					g2.fillRect(w/4 + 4*w/10+1, h/4 +1, w /10-1, h/10-1);
				}
				if(C4n == true){
					g2.fillRect(w/4 +1, h/4 +2* h/5+1, w /10-1, h/10-1);
				}
			}
			else if(hiLiOS == true){
				g2.setColor(new Color(255,255,0,aNr));
				if(O0 == true){
					g2.fillRect(w/4+1, h/4+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 + h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/5+1, h/4 + h/5+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 4*w/10+1, h/4 + 4*h/10+1, w /10-1, h/10-1);
				}
				if(O1 == true){
					g2.fillRect(w/4 + w/10+1, h/4+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/5+1, h/4 + w/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + w/5+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 4*w/10+1, h/4 + 3*w/10+1, w /10-1, h/10-1);
				}
				if(O1n == true){
					g2.fillRect(w/4 +1, h/4+ w/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 + w/5+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/5+1, h/4 + 3*w/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + 4*w/10+1, w /10-1, h/10-1);
				}
				if(O2 == true){
					g2.fillRect(w/4 + w/5+1, h/4 +1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + w/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 4*w/10+1, h/4 + w/5+1, w /10-1, h/10-1);
				}
				if(O2n == true){
					g2.fillRect(w/4 +1, h/4 + h/5+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/5+1, h/4 +2* h/5+1, w /10-1, h/10-1);
				}
				if(O3 == true){
					g2.fillRect(w/4 + 3*w/10+1, h/4 +1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 4*w/10+1, h/4 + w/10+1, w /10-1, h/10-1);
				}
				if(O3n == true){
					g2.fillRect(w/4 +1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 +2* h/5+1, w /10-1, h/10-1);
				}
				if(O4 == true){
					g2.fillRect(w/4 + 4*w/10+1, h/4 +1, w /10-1, h/10-1);
				}
				if(O4n == true){
					g2.fillRect(w/4 +1, h/4 +2* h/5+1, w /10-1, h/10-1);
				}
			}
	}
		else if (wH == false){
			if(hiLiFB == true){
				g2.setColor(new Color(255,0,0,aNr));
				if(l0 == true){
					g2.fillRect(w/4+ 4*w/10+1, h/4+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/5+1, h/4 + h/5+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 +1, h/4 + 4*h/10+1, w /10-1, h/10-1);
				}
				if(l1 == true){
					g2.fillRect(w/4 + 3*w/10+1, h/4 +1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 2*w/10+1, h/4 + h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 + 2*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 +1, h/4 + 3*h/10+1, w /10-1, h/10-1);
				}
				if(l1n == true){
					g2.fillRect(w/4 + 4*w/10+1, h/4 + h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + h/5+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 2*w/10+1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 +w/10+1, h/4 + 4*h/10+1, w /10-1, h/10-1);
				}
				if(l2 == true){
					g2.fillRect(w/4 + 2*w/10+1, h/4 +1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 + h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 +1, h/4 + 2*h/10+1, w /10-1, h/10-1);
				}
				if(l2n == true){
					g2.fillRect(w/4 + 4*w/10+1, h/4 + 2*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 2*w/10+1, h/4 + 4*h/10+1, w /10-1, h/10-1);
				}
				if(l3 == true){
					g2.fillRect(w/4 + w/10+1, h/4 +1, w /10-1, h/10-1);
					g2.fillRect(w/4 +1, h/4 + h/10+1, w /10-1, h/10-1);
				}
				if(l3n == true){
					g2.fillRect(w/4 + 4*w/10+1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + 4*h/10+1, w /10-1, h/10-1);
				}
				if(l4 == true){
					g2.fillRect(w/4 +1, h/4 +1, w /10-1, h/10-1);
				}
				if(l4n == true){
					g2.fillRect(w/4 + 4*w/10+1, h/4 + 4*h/10+1, w /10-1, h/10-1);
				}
			}
			else if(hiLiCU == true){
				g2.setColor(new Color(255,128,0,aNr));
				if(lc0 == true){
					g2.fillRect(w/4+ 4*w/10+1, h/4+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/5+1, h/4 + h/5+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 +1, h/4 + 4*h/10+1, w /10-1, h/10-1);
				}
				if(lc1 == true){
					g2.fillRect(w/4 + 3*w/10+1, h/4 +1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 2*w/10+1, h/4 + h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 + 2*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 +1, h/4 + 3*h/10+1, w /10-1, h/10-1);
				}
				if(lc1n == true){
					g2.fillRect(w/4 + 4*w/10+1, h/4 + h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + h/5+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 2*w/10+1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 +w/10+1, h/4 + 4*h/10+1, w /10-1, h/10-1);
				}
				if(lc2 == true){
					g2.fillRect(w/4 + 2*w/10+1, h/4 +1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 + h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 +1, h/4 + 2*h/10+1, w /10-1, h/10-1);
				}
				if(lc2n == true){
					g2.fillRect(w/4 + 4*w/10+1, h/4 + 2*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 2*w/10+1, h/4 + 4*h/10+1, w /10-1, h/10-1);
				}
				if(lc3 == true){
					g2.fillRect(w/4 + w/10+1, h/4 +1, w /10-1, h/10-1);
					g2.fillRect(w/4 +1, h/4 + h/10+1, w /10-1, h/10-1);
				}
				if(lc3n == true){
					g2.fillRect(w/4 + 4*w/10+1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + 4*h/10+1, w /10-1, h/10-1);
				}
				if(lc4 == true){
					g2.fillRect(w/4 +1, h/4 +1, w /10-1, h/10-1);
				}
				if(lc4n == true){
					g2.fillRect(w/4 + 4*w/10+1, h/4 + 4*h/10+1, w /10-1, h/10-1);
				}
			}
			else if(hiLiOS == true){
				g2.setColor(new Color(255,255,0,aNr));
				if(lo0 == true){
					g2.fillRect(w/4+1, h/4+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 + h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/5+1, h/4 + h/5+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 4*w/10+1, h/4 + 4*h/10+1, w /10-1, h/10-1);
				}
				if(lo1 == true){
					g2.fillRect(w/4 + 3*w/10+1, h/4 +1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 2*w/10+1, h/4 + h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 + 2*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 +1, h/4 + 3*h/10+1, w /10-1, h/10-1);
				}
				if(lo1n == true){
					g2.fillRect(w/4 + 4*w/10+1, h/4 + h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + h/5+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 2*w/10+1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 +w/10+1, h/4 + 4*h/10+1, w /10-1, h/10-1);
				}
				if(lo2 == true){
					g2.fillRect(w/4 + 2*w/10+1, h/4 +1, w /10-1, h/10-1);
					g2.fillRect(w/4 + w/10+1, h/4 + h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 +1, h/4 + 2*h/10+1, w /10-1, h/10-1);
				}
				if(lo2n == true){
					g2.fillRect(w/4 + 4*w/10+1, h/4 + 2*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 2*w/10+1, h/4 + 4*h/10+1, w /10-1, h/10-1);
				}
				if(lo3 == true){
					g2.fillRect(w/4 + w/10+1, h/4 +1, w /10-1, h/10-1);
					g2.fillRect(w/4 +1, h/4 + h/10+1, w /10-1, h/10-1);
				}
				if(lo3n == true){
					g2.fillRect(w/4 + 4*w/10+1, h/4 + 3*h/10+1, w /10-1, h/10-1);
					g2.fillRect(w/4 + 3*w/10+1, h/4 + 4*h/10+1, w /10-1, h/10-1);
				}
				if(lo4 == true){
					g2.fillRect(w/4 +1, h/4 +1, w /10-1, h/10-1);
				}
				if(lo4n == true){
					g2.fillRect(w/4 + 4*w/10+1, h/4 + 4*h/10+1, w /10-1, h/10-1);
				}
			}

		}
	}
	
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	
	
	public static void main(String args[]){
		new MainC();
	}

}
