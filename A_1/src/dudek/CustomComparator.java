package dudek;

import java.util.Comparator;

public class CustomComparator implements Comparator<Byte> {

	@Override
	public int compare(Byte arg0, Byte arg1) {
		return (String.format("%02X ", arg0)).compareTo(String.format("%02X ", arg1));
	}
}
