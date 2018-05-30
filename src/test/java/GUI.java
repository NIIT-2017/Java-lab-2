import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class GUI {

	@Test
	public void getButtonsTest() {
		Automata Block=new Automata();
		AutomataGUIDemo A=new AutomataGUIDemo(new Automata());
		JButton[] buttons=A.getButtons();
		JButton currentButton=buttons[0];
		String actual=currentButton.getText();
		String expected=Block.getMenu()[0][1].toString();
		assertEquals(expected, actual);
		
		assertEquals(Block.getMenu()[0][0], A.getLabels()[0].getText());
	}

}
